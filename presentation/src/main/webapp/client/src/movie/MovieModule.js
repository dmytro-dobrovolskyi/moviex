(function (define, angular) {

    "use strict";

    define(
        [
            "movie/controller/FindMovieController",
            "movie/controller/SearchMovieController",
            "movie/controller/MovieDetailsController",
            "movie/service/model/Movie",
            "movie/directive/SrcErrorDirective",
            "movie/MovieStateManager"
        ],
        function (FindMovieController, SearchMovieController, MovieDetailsController, Movie, SrcErrorDirective, MovieStateManager) {

            var moduleName = "MovieModule";

            angular
                .module(moduleName, [])
                .factory("Movie", Movie)
                .controller("FindMovieController", FindMovieController)
                .controller("MovieSearchRequestController", SearchMovieController)
                .controller("MovieDetailsController", MovieDetailsController)
                .directive("srcErr", SrcErrorDirective)
                .config(MovieStateManager);
            return moduleName;
        }
    );
})(define, angular);
