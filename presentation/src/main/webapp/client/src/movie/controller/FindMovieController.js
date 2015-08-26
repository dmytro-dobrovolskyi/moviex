(function (define) {

    "use strict";

    define(
        [],
        function () {
            var FindMovieController = function ($rootScope, $scope, $log, $stateParams, $location, Movie, SpringDataRestAdapter) {
                $rootScope.isLoaded = true;
                $scope.title = $stateParams.title;

                if ($stateParams.isForce === "true") {
                    $location.search('isForce', null);
                    $scope.isForceBtnHidden = true;
                }

                var doRequest = function () {
                    $scope.isMovieLoading = true;

                    var response = Movie.findByTitle(
                        {title: $scope.title, isForce: $stateParams.isForce === "true"}, function (data, getHeaders) {
                            SpringDataRestAdapter
                                .process(data.$promise)
                                .then(function (processedResponse) {
                                    $scope.movies = processedResponse._embeddedItems;
                                });

                            var titleWords = $scope.title.split(" ");

                            if (getHeaders().result === ResultInfo.BY_WORD_REQUEST_REQUIRED && titleWords.length > 1) {
                                Movie.requestByWordAndPersist(titleWords);
                            }
                            $scope.isMovieLoading = false;
                        })
                };

                $scope.findMovie = function () {
                    $scope.movies = null;
                    $stateParams.isForce = false;
                    doRequest();
                    $scope.isForceBtnHidden = false;
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
