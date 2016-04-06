(function (define) {

    "use strict";

    define(
        [],
        function () {
            var SearchMovieController = function ($scope, $log, $stateParams, $state, $location, Movie, SpringDataRestAdapter) {
                $scope.isMoviesLoading = true;
                if ($stateParams.title) {
                    $scope.title.value = $stateParams.title;

                    var tryHarder = $stateParams.tryHarder === "true";

                    new Movie()
                        .findByTitle({tryHarder: tryHarder, title: $stateParams.title}, function (data) {
                            SpringDataRestAdapter
                                .process(data)
                                .then(function (processedResponse) {
                                    $scope.result = processedResponse._embeddedItems;
                                });
                            $scope.isMoviesLoading = !($scope.isMoviesLoaded = true);
                            $scope.isTryHarderBtnShown = !tryHarder;
                        });
                }
            };
            return ["$scope", "$log", "$stateParams", "$state", "$location", "Movie", "SpringDataRestAdapter", SearchMovieController];
        }
    );

})
(define);
