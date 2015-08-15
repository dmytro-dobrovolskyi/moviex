(function (define) {

    "use strict";

    define(
        [
            "welcome/WelcomeModule",
            "ngRoute"
        ],
        function (WelcomeModule) {

            var appName  = "moviex";
            var app =  angular.module("moviex",
                [WelcomeModule, "ngRoute"])
                .config(function($routeProvider) {
                    $routeProvider
                        .when("/search", {
                            templateUrl : "client/assets/views/search.view.html"
                        })
                });

            return appName;
        }
    );
})(define);
