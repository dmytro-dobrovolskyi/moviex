(function () {

    "use strict";

    define(
        [],
        function () {
            return function ($stateProvider, $urlRouterProvider) {

                $urlRouterProvider.otherwise("/home");

                $stateProvider
                    .state("root", {
                        url: "/home",
                        views: {
                            navbar: {
                                templateUrl: "client/assets/views/navbar.view.html",
                                controller : "HomeController"
                            }
                        }
                    })
                    .state("root.find-movie", {
                        url: "^/find-movie",
                        views: {
                            "container@": {
                                templateUrl: "client/assets/views/find-movie.view.html",
                                controller: "FindMovieController"
                            }
                        }
                    });
            }
        }
    )
})(define);
