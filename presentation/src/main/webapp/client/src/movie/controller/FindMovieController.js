(function (define) {

    "use strict";

    define(
        [],
        function () {
            var FindMovieController = function ($rootScope, $scope, $log, $stateParams, $location, Movie, SpringDataRestAdapter) {
                $rootScope.isLoaded = true;
                $scope.title = $stateParams.title;
                $scope.isForceBtnHidden = $stateParams.isForce === "true";

                $log.info("FindMovieController");

                var doRequest = function () {
                    Movie
                        .findByTitle({isForce: $stateParams.isForce === "true", title: $scope.title}, function (data, getHeaders) {
                            SpringDataRestAdapter
                                .process(data.$promise)
                                .then(function (processedResponse) {
                                    $scope.movies = processedResponse._embeddedItems;
                                });

                            var titleWords = $scope.title.split(" ");

                            if (getHeaders().result === ResultInfo.BY_WORD_REQUEST_REQUIRED && titleWords.length > 1) {
                                Movie.requestByWordAndPersist(titleWords);
                            }
                            $scope.isMoviesLoaded = true;
                            $location.search('isForce', null);
                            $stateParams.isForce = false;
                        })
                };

                $scope.findMovie = function () {
                    doRequest();
                    $scope.movies = null;
                    $scope.isForceBtnHidden = false;
                    $location.search('title', $scope.title);
                };

                if ($scope.title) {
                    doRequest();
                }

            };
            return ["$rootScope", "$scope", "$log", "$stateParams", "$location", "Movie", "SpringDataRestAdapter", FindMovieController];
        }
    );

    var ResultInfo = {
        OK: "OK",
        BY_WORD_REQUEST_REQUIRED: "BY_WORD_REQUEST_REQUIRED"
    }
})
(define);
