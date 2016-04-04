(function (define, require) {

    "use strict";
    /**
     * Defines alias in 'path' and no-AMD dependencies in 'shim'.
     */
    require.config({
        paths: {
            "angular": "../lib/angular/angular",
            "uiRouter": "../lib/angular-ui-router/release/angular-ui-router.min",
            "ngBootstrap": "../lib/angular-bootstrap/ui-bootstrap-tpls.min",
            "ngResource": "../lib/angular-resource/angular-resource.min",
            "ngSpingHateoas": "../lib/angular-spring-data-rest/dist/angular-spring-data-rest.min",
            "ngAnimate": "../lib/angular-animate/angular-animate.min"
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
                        .config(['$uibTooltipProvider', function ($uibTooltipProvider) {
                            $uibTooltipProvider.options({animation: false});
                        }]);
                    
                    angular.bootstrap(document.getElementById(appName), [appName]);
                })
        }
    );
})(define, require);
