(function (define, angular) {

    "use strict";

    define(
        [
            "start/StartModule",
            "movie/MovieModule"
        ],
        function (StartModule, MovieModule) {

            var appName = "moviex";
            var app = angular.module(appName,
                [
                    "ui.router",
                    "ui.bootstrap",
                    "ngResource",
                    "ngAnimate",
                    "spring-data-rest",
                    StartModule,
                    MovieModule
                ])
                .config(['$tooltipProvider', function ($tooltipProvider) {
                    $tooltipProvider.options({animation: false});
                }]);
            return appName;
        }
    );
})(define, angular);
