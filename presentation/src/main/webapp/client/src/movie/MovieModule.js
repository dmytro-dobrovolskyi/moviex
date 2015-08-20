(function (define, angular) {

    "use strict";

    define(
        [
            "movie/controller/FindMovieController",
            "movie/service/model/Movie"
        ],
        function (FindMovieController, Movie) {

            var moduleName = "MovieModule";

            angular
                .module(moduleName, [])
                .factory("Movie", Movie)
                .controller("FindMovieController", FindMovieController);

            return moduleName;
        }
    );
})(define, angular);
