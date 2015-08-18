(function (define, angular) {

    "use strict";

    define(
        [
            "start/controller/HomeController"
        ],
        function (HomeController) {

            var moduleName = "StartModule";

            angular
                .module(moduleName, [])
                .controller("HomeController", HomeController);

            return moduleName;
        }
    );
})(define, angular);
