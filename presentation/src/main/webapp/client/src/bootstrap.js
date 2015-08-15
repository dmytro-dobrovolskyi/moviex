(function (define, require) {

    "use strict";
    /**
     * Defines alias in 'path' and no-AMD dependencies in 'shim'.
     */
    require.config({
        paths: {
            "angular": "../lib/angular/angular.min",
            "uiRouter": "../lib/angular-ui-router/release/angular-ui-router.min",
            "ngMaterial": "../lib/angular-material/angular-material.min",
            "ngAria": "../lib/angular-aria/angular-aria.min",
            "ngAnimate": "../lib/angular-animate/angular-animate.min",
            "ngBootstrap": "../lib/angular-bootstrap/ui-bootstrap-tpls.min"
        },
        shim: {
            "angular": {
                exports: "angular"
            },
            "uiRouter": {
                deps: ["angular"]
            },
            "ngMaterial": {
                deps: ["angular"]
            },
            "ngAria": {
                deps: ["angular"]
            },
            "ngAnimate": {
                deps: ["angular"]
            },
            "ngBootstrap": {
                deps: ["angular"]
            }
        }
    });


    define(["angular", "uiRouter", "ngMaterial", "ngAria", "ngAnimate", "ngBootstrap"],
        function (angular) {

        /**
         * Bootstraps the whole application.
         */
        require(["main"], function (app) {
            angular.bootstrap(document.getElementById(app), [app]);
        });

    });

})(define, require);
