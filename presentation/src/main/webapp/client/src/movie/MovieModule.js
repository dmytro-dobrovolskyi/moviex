(function (define, angular) {

    "use strict";

    define(
        [
            "movie/controller/FindMovieController"
        ],
        function (FindMovieController) {

            var moduleName = "MovieModule";

            angular
                .module(moduleName, [])
                .controller("FindMovieController", FindMovieController);

            return moduleName;
        }
    );
})(define, angular);
