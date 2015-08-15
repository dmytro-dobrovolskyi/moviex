(function (define, angular) {

    "use strict";

    define(
        [
            "welcome/WelcomeModule",
            "welcome/WelcomeStateManager"
        ],
        function (WelcomeModule, WelcomeStateManager) {

            var appName = "moviex";
            var app = angular.module(appName,
                [
                    "ui.router",
                    WelcomeModule
                ])
                .config(WelcomeStateManager);

            return appName;
        }
    );
})(define, angular);
