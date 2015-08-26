(function (define, angular) {

    "use strict";

    define(
        [
            "movie/controller/FindMovieController",
            "movie/service/model/Movie",
            "movie/directive/SrcErrorDirective"
        ],
        function (FindMovieController, Movie, SrcErrorDirective) {

            var moduleName = "MovieModule";

            angular
                .module(moduleName, [])
                .factory("Movie", Movie)
                .controller("FindMovieController", FindMovieController)
                .directive("srcErr", SrcErrorDirective);

            return moduleName;
        }
    );
})(define, angular);
