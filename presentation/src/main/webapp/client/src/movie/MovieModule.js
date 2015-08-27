(function (define, angular) {

    "use strict";

    define(
        [
            "movie/controller/FindMovieController",
            "movie/controller/MovieSearchRequestController",
            "movie/service/model/Movie",
            "movie/directive/SrcErrorDirective",
            "movie/MovieStateManager"
        ],
        function (FindMovieController, MovieSearchRequestController, Movie, SrcErrorDirective, MovieStateManager) {

            var moduleName = "MovieModule";

            angular
                .module(moduleName, [])
                .factory("Movie", Movie)
                .controller("FindMovieController", FindMovieController)
                .controller("MovieSearchRequestController", MovieSearchRequestController)
                .directive("srcErr", SrcErrorDirective)
                .config(MovieStateManager);
            return moduleName;
        }
    );
})(define, angular);
