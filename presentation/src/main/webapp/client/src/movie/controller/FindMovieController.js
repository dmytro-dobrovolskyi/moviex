(function (define) {

    "use strict";

    define(
        [],
        function () {
            var FindMovieController = function ($rootScope, $scope, $log, $stateParams, Movie, SpringDataRestAdapter) {

                $log.info($stateParams.isForce);
                $log.info($stateParams.title);

                $rootScope.isLoaded = true;
                $scope.title = $stateParams.title;

                $scope.findMovie = function () {
                    $scope.movies = null;
                    $scope.clicked = true;

                    var response = Movie.findByTitle({title: $scope.title, isForce: $stateParams.isForce === "true"}, function (data, getHeaders) {
                        SpringDataRestAdapter
                            .process(data.$promise)
                            .then(function (processedResponse) {
                                $log.debug(processedResponse);
                                $scope.movies = processedResponse._embeddedItems;

                                $log.info($scope.movies);

                                $scope.clicked = false;
                            });

                        var titleWords = $scope.title.split(" ");

                        if (getHeaders().result === ResultInfo.BY_WORD_REQUEST_REQUIRED && titleWords.length > 1) {
                            $log.debug("Retrieving...");
                            Movie.requestByWordAndPersist(titleWords, function (result) {
                                $log.debug(result);
                            })
                        }
                    })
                };

                if ($scope.title) {
                    $scope.findMovie();
                }
            };
            return ["$rootScope", "$scope", "$log", "$stateParams", "Movie", "SpringDataRestAdapter", FindMovieController];
        }
    );

    var ResultInfo = {
        OK: "OK",
        BY_WORD_REQUEST_REQUIRED: "BY_WORD_REQUEST_REQUIRED"
    }
})
(define);
