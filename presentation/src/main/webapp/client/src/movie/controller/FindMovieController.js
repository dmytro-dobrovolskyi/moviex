(function (define) {

    "use strict";

    define(
        [],
        function () {
            var FindMovieController = function ($rootScope, $scope, $log, Movie, SpringDataRestAdapter) {
                $log.debug("FindMovieController");
                $rootScope.isLoaded = true;

                $scope.title = null;
                $scope.findMovie = function () {
                    var response = Movie.findByTitle({title: $scope.title}, function (data, getHeaders) {
                        SpringDataRestAdapter
                            .process(data.$promise)
                            .then(function (processedResponse) {
                                $log.debug(processedResponse);
                                $scope.movies = processedResponse._embeddedItems;
                            });

                        var titleWords = $scope.title.split(" ");

                        if (getHeaders().request && titleWords.length > 1) {
                            $log.debug("Retrieving...");
                            Movie.requestAndSave(titleWords, function (result) {
                                $log.debug(result);
                            })
                        }

                    })
                }
            };
            return ["$rootScope", "$scope", "$log", "Movie", "SpringDataRestAdapter", FindMovieController];
        }
    );
})(define);
