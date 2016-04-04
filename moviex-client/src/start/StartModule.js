(function (define, angular) {

    "use strict";

    define(
        [
            "start/controller/HomeController",
            "start/StartStateManager"
        ],
        function (HomeController, StartStateManager) {

            var moduleName = "StartModule";

            angular
                .module(moduleName, [])
                .controller("HomeController", HomeController)
                .config(StartStateManager);
            return moduleName;
        }
    );
})(define, angular);
