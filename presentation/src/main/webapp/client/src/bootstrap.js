(function (define, require) {

    "use strict";
    /**
     * Defines alias in 'path' and no-AMD dependencies in 'shim'.
     */
    require.config({
        paths: {
            "angular": "../lib/angular/angular.min",
            "uiRouter": "../lib/angular-ui-router/release/angular-ui-router.min"
        },
        shim: {
            "angular": {
                exports: "angular"
            },
            "uiRouter": {
                deps: ["angular"]
            }
        }
    });


    define(["angular", "uiRouter"], function (angular) {

        /**
         * Bootstraps the whole application.
         */
        require(["main"], function (app) {
            angular.bootstrap(document.getElementById(app), [app]);
        });

    });

})(define, require);

