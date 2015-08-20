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
                    var response = Movie.findByTitle({title: $scope.title});

                    $log.debug(response);
                    SpringDataRestAdapter
                        .process(response.$promise)
                        .then(function(processedResponse) {
                            $scope.movies = processedResponse._embeddedItems;
                        })

                }
            };
            return ["$rootScope", "$scope", "$log", "Movie", "SpringDataRestAdapter", FindMovieController];
        }
    );
})(define);
