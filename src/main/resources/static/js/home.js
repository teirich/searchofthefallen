angular.module('search-of-the-fallen', [])
    .controller('home', function($scope, $http, $sce) {
        $scope.novels = [
            {order: 1, display: 'Gardens of the Moon'},
            {order: 2, display: 'Deadhouse Gates'},
            {order: 3, display: 'Memories of Ice'},
            {order: 4, display: 'House of Chains'},
            {order: 5, display: 'Midnight Tides'},
            {order: 6, display: 'The Bonehunters'},
            {order: 7, display: 'Reaper\'s Gale'},
            {order: 8, display: 'Toll the Hounds'},
            {order: 9, display: 'Dust of Dreams'},
            {order: 10, display: 'The Crippled God'}
        ];

        $scope.rowOptions = [10, 25, 50, 100];

        $scope.showResults = false;
        $scope.rows = 10;
        $scope.start = 0;
        $scope.numFound = 0;

        $scope.pages = 0;
        $scope.currentPage = 1;

        $scope.highlight = function (text) {
            //should really sanitize $scope.text first
            return $sce.trustAsHtml('<p>' + text.replace(new RegExp($scope.text, 'gi'), function(match) {
                return '<mark>' + match + '</mark>';
            }) + '</p>');
        };

        $scope.search = function () {
            console.log('in search: ' + $scope.text);
            var path = $scope.upTo ? '/api/search/upTo' : '/api/search';
            $http.get(path, {
                params: {
                    text: $scope.text,
                    novel: $scope.selectedNovel.order,
                    rows: $scope.rows,
                    start: $scope.start
                }
            }).success(function (data) {
                console.log('in success');
                $scope.numFound = data.numFound;
                $scope.rows = data.rows;
                $scope.start = data.start;
                $scope.results = data.searchResults;
                $scope.pages = Math.ceil($scope.numFound / $scope.rows);
                $scope.showResults = true;
            });
        };

        $scope.nextPage = function(){
            console.log('in nextPage');
            $scope.start = $scope.start + $scope.rows;
            $scope.currentPage++;
            $scope.search();
        };

        $scope.prevPage = function(){
            console.log('in prevPage');
            $scope.start = $scope.start - $scope.rows;

            if($scope.start < 0){
                $scope.start = 0;
            }
            $scope.currentPage--;
            $scope.search();
        };
    });
