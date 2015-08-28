(function () {

    "use strict";

    define(
        [],
        function () {
            var MovieStateManager = function ($stateProvider) {
                $stateProvider
                    .state("root.find-movie", {
                        url: "/find-movie",
                        views: {
                            "container@": {
                                templateUrl: "client/assets/views/movie/find-movie.view.html",
                                controller: "FindMovieController"
                            }
                        }
                    })
                    .state("root.find-movie.search-result", {
                        url: "/?title?isForce",
                        reloadOnSearch: false,
                        views: {
                            "content": {
                                templateUrl: "client/assets/views/movie/movie-search-result.view.html",
                                controller: "MovieSearchRequestController"
                            }
                        }
                    })
                    .state("root.find-movie.details", {
                        url: "/details/{movieLink}",
                        views: {
                            "content": {
                                templateUrl: "client/assets/views/movie/movie-details.view.html",
                                controller: "MovieDetailsController"
                            }
                        }
                    });
            };
            return ["$stateProvider", MovieStateManager];
        }
    )
})(define);
