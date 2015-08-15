(function (define, angular) {

    "use strict";

    define(
        [
            "welcome/controller/WelcomeController",
            "welcome/controller/FindMovieController"
        ],
        function (WelcomeController, FindMovieController) {

            var moduleName = "WelcomeModule";

            angular
                .module(moduleName, [])
                .controller("WelcomeController", WelcomeController)
                .controller("FindMovieController", FindMovieController);

            return moduleName;
        }
    );

})(define, angular);
