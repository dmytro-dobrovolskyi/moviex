(function (define) {

    "use strict";

    define(
        [],
        function () {
            var MovieDetailsController = function ($scope, $log, $stateParams, Movie, SpringDataRestAdapter) {

                Movie(JSON.parse(decodeURIComponent($stateParams.movieLink)).href)
                    .get(function (data) {
                        if (!$scope.selectedSearchResult) {
                            SpringDataRestAdapter
                                .process(data)
                                .then(function (processedResponse) {
                                    processedResponse._resources("movieSearchMetadata").get(function (data) {
                                        $scope.selectedSearchResult = data;
                                    });
                                });
                        }
                        $scope.details = data;
                    });
            };
            return["$scope", "$log", "$stateParams", "Movie", "SpringDataRestAdapter", MovieDetailsController];
        })
})
(define);
