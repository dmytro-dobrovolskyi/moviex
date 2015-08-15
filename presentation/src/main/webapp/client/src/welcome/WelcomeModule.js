(function (define, angular) {

    "use strict";

    define(
        ["welcome/controller/WelcomeController"],
        function (WelcomeController) {

            var moduleName = "WelcomeModule";

            angular
                .module(moduleName, [])
                .controller("WelcomeController", WelcomeController);

            return moduleName;
        }
    );

})(define, angular);
