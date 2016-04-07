(function (define, require) {

    "use strict";
    /**
     * Defines alias in 'path' and no-AMD dependencies in 'shim'.
     */
    require.config({
        waitSeconds: 200,
        paths: {
            "angular": "../angular/angular",
            "uiRouter": "../angular-ui-router/release/angular-ui-router.min",
            "ngBootstrap": "../angular-bootstrap/ui-bootstrap-tpls.min",
            "ngResource": "../angular-resource/angular-resource.min",
            "ngSpingHateoas": "../angular-spring-data-rest/dist/angular-spring-data-rest.min",
            "ngAnimate": "../angular-animate/angular-animate.min"
        },
        shim: {
            "uiRouter": {
                "deps": ["angular"]
            },
            "ngBootstrap": {
                "deps": ["angular"]
            },
            "ngResource": {
                "deps": ["angular"]
            },
            "ngSpingHateoas": {
                "deps": ["angular"]
            },
            "ngAnimate": {
                "deps": ["angular"]
            }
        }
    });

    require
    (
        [
            "uiRouter",
            "ngBootstrap",
            "ngResource",
            "ngSpingHateoas",
            "ngAnimate"
        ],
        function () {
            require(
                [
                    "start/StartModule",
                    "movie/MovieModule"
                ],
                function (StartModule, MovieModule) {
                    var appName = "moviex";
                    
                    angular.module(appName,
                        [
                            "ui.router",
                            "ui.bootstrap",
                            "ngResource",
                            "ngAnimate",
                            "spring-data-rest",
                            StartModule,
                            MovieModule
                        ])
                        .run(["$rootScope", function ($rootScope) {
                            $rootScope.resourcesUrl = "https://googledrive.com/host/0B5bvhq7seDzuY3hWbTltYXBia28/"
                        }])
                        .config(['$uibTooltipProvider', function ($uibTooltipProvider) {
                            $uibTooltipProvider.options({animation: false});
                        }]);
                    
                    angular.bootstrap(document.getElementById(appName), [appName]);
                })
        }
    );
})(define, require);
