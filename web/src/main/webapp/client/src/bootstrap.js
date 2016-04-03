(function (define, require) {

    "use strict";
    /**
     * Defines alias in 'path' and no-AMD dependencies in 'shim'.
     */
    require.config({
        paths: {
            "angular": "../lib/angular/angular.min",
            "uiRouter": "../lib/angular-ui-router/release/angular-ui-router.min",
            "ngBootstrap": "../lib/angular-bootstrap/ui-bootstrap-tpls.min",
            "ngResource": "../lib/angular-resource/angular-resource.min",
            "ngSpingHateoas": "../lib/angular-spring-data-rest/dist/angular-spring-data-rest.min",
            "ngAnimate": "../lib/angular-animate/angular-animate.min"
        },
        shim: {
            "angular": {
                exports: "angular"
            },
            "uiRouter": {
                deps: ["angular"]
            },
            "ngBootstrap": {
                deps: ["angular"]
            },
            "ngResource": {
                deps: ["angular"]
            },
            "ngSpingHateoas": {
                deps: ["ngResource"]
            },
            "ngAnimate": {
                deps: ["angular"]
            }
        }
    });


    define(["angular", "uiRouter", "ngBootstrap", "ngResource", "ngSpingHateoas", "ngAnimate"],
        function (angular) {

            /**
             * Bootstraps the whole application.
             */
            require(["main"], function (app) {
                angular.bootstrap(document.getElementById(app), [app]);
            });

        });

})(define, require);
