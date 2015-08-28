(function (define, angular) {

    "use strict";

    define(
        [
            "movie/controller/FindMovieController",
            "movie/controller/MovieSearchRequestController",
            "movie/controller/MovieDetailsController",
            "movie/service/model/Movie",
            "movie/directive/SrcErrorDirective",
            "movie/MovieStateManager"
        ],
        function (FindMovieController, MovieSearchRequestController, MovieDetailsController, Movie, SrcErrorDirective, MovieStateManager) {

            var moduleName = "MovieModule";

            angular
                .module(moduleName, [])
                .factory("Movie", Movie)
                .controller("FindMovieController", FindMovieController)
                .controller("MovieSearchRequestController", MovieSearchRequestController)
                .controller("MovieDetailsController", MovieDetailsController)
                .directive("srcErr", SrcErrorDirective)
                .config(MovieStateManager);
            return moduleName;
        }
    );
})(define, angular);
