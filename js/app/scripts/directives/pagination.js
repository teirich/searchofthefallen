'use strict';

/**
 * @ngdoc directive
 * @name search-of-the-fallen.directive:pagination
 * @description
 * # pagination
 */

function PaginationController() {

}

angular.module('search-of-the-fallen').component('pagination', {
  templateUrl: 'views/pagination.html',
  controller: PaginationController,
  controllerAs: 'pg',
  bindings: {
    currentPage: '<',
    totalPages: '<',
    prevPageFn: '=',
    nextPageFn: '='
  }
});
