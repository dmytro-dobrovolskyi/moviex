(function (define, require) {

    /**
     * Defines alias in 'path' and no-AMD dependencies in 'shim'.
     */
    require.config({
        paths: {
            "angular" : "../lib/angular/angular.min",
            "ngRoute" : "../lib/angular-route/angular-route.min"
        },
        shim: {
            "angular" : {
                exports: "angular"
            },
            "ngRoute" : {
                deps : ["angular"]
            }
        }
    });


    define(["angular"], function (angular) {

        /**
         * Bootstraps the whole application.
         */
        require(["main"], function (app) {
            angular.bootstrap(document.getElementById(app), [app]);
        });

    });

})(define, require);
