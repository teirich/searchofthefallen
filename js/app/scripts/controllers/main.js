'use strict';

angular.module('search-of-the-fallen')
  .controller('MainCtrl', ['$scope', '$http', '$sce', function($scope, $http, $sce) {

    $scope.rowOptions = [10, 25, 50, 100];

    $scope.showResults = false;
    $scope.rows = 10;
    $scope.start = 0;
    $scope.numFound = 0;

    $scope.pages = 0;
    $scope.currentPage = 1;

    $scope.getStructure = function () {
      $http.get('/api/info/novelStructure')
        .then(function(success){
          $scope.novels = success.data;
        });
    };

    $scope.getStructure();

    $scope.generateDropdown = function (novel) {
      var values = [];
      if (!novel) {
        return values;
      }
      novel.books.forEach (function (book) {
        if(book.number === 0 || book.number === 100 || book.number === 101){
          var bookend = {
            title: book.title,
            value: book.number,
            disabled: false
          };
          values.push(bookend);
        } else {
          var header = {
            title: '-' + book.title + '-',
            value: null,
            disabled: true
          };
          values.push(header);

          for (var i = book.start; i <= book.end; i++) {
            var chapter = {
              title: 'Chapter ' + i,
              value: i,
              disabled: false
            };
            values.push(chapter);
          }
        }
      });
      return values;
    };

    $scope.$watch('selectedNovel', function (newVal){
      $scope.bookDropdown = $scope.generateDropdown(newVal);
      $scope.selectedChapter = '';
    });

    $scope.highlight = function (text) {
      //should really sanitize $scope.text first
      if($scope.text === undefined || $scope.text === null || $scope.text === '') {
        return text;
      }
      var splitTxt = $scope.text.split(' ');
      var joinTxt = '(' + splitTxt.join('|') + ')';

      return $sce.trustAsHtml('<p>' + text.replace(new RegExp(joinTxt, 'gi'), function(match) {
          return '<mark>' + match + '</mark>';
        }) + '</p>');
    };

    $scope.newSearch = function () {
      $scope.start = 0;
      $scope.numFound = 0;
      $scope.pages = 0;
      $scope.currentPage = 1;
      $scope.search();
    };

    $scope.search = function () {
      var path = $scope.upTo ? '/api/search/upTo' : '/api/search';

      $http.get(path, {
        params: {
          text: $scope.text,
          novel: $scope.selectedNovel.order,
          chapter: $scope.selectedChapter,
          rows: $scope.rows,
          start: $scope.start
        }
      }).then(function (success) {
        var data = success.data;
        console.log('in success');
        $scope.numFound = data.numFound;
        $scope.rows = data.rows;
        $scope.start = data.start;
        $scope.results = data.searchResults;
        $scope.pages = Math.ceil($scope.numFound / $scope.rows);
        $scope.showResults = true;
      });
    };

    $scope.resolveNovel = function (novel) {
      if (novel === 0 || novel === 100 || novel === 101) {
        return '';
      }
      return $scope.novels.find(function (elem) {
        return elem.order === novel;
      }).name;
    };

    $scope.resolveChapter = function (chapterNum) {
      if (chapterNum === 0) {
        return 'Prologue';
      }
      if (chapterNum === 100) {
        return 'Epilogue';
      }
      if (chapterNum === 101) {
        return 'Epilogue II';
      }
      return 'Chapter ' + chapterNum;
    };

    $scope.nextPage = function () {
      console.log('in nextPage');
      $scope.start = $scope.start + $scope.rows;
      $scope.currentPage++;
      $scope.search();
    };

    $scope.prevPage = function () {
      console.log('in prevPage');
      $scope.start = $scope.start - $scope.rows;

      if($scope.start < 0){
        $scope.start = 0;
      }
      $scope.currentPage--;
      $scope.search();
    };
  }]);
