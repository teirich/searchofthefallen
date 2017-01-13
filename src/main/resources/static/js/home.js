angular.module('search-of-the-fallen', [])
    .controller('home', function($scope, $http) {
        $scope.volumes = [
            {abbr: 'GOTM', display: 'Gardens of the Moon'},
            {abbr: 'DG', display: 'Deadhouse Gates'},
            {abbr: 'MOI', display: 'Memories of Ice'},
            {abbr: 'HOC', display: 'House of Chains'},
            {abbr: 'MT', display: 'Midnight Tides'},
            {abbr: 'BH', display: 'The Bonehunters'},
            {abbr: 'RG', display: 'Reaper\'s Gale'},
            {abbr: 'TTH', display: 'Toll the Hounds'},
            {abbr: 'DOD', display: 'Dust of Dreams'},
            {abbr: 'TCG', display: 'The Crippled God'}
        ];

        $scope.rowOptions = [10, 25, 50, 100];

        $scope.showResults = false;
        $scope.rows = 10;
        $scope.start = 0;
        $scope.numFound = 0;

        $scope.pages = 0;
        $scope.currentPage = 1;

        $scope.search = function(){
            console.log('in search');
            console.log($scope.selectedVolume);
            console.log($scope.selectedVolume.abbr);

            $http.get('/api/search', {
                params: {
                    text: $scope.text,
                    volume: $scope.selectedVolume.abbr,
                    rows: $scope.rows,
                    start: $scope.start
                }
            }).success(function(data) {
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
