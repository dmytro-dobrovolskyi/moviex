(function () {

    "use strict";

    define(
        [],
        function () {
            return function ($stateProvider, $urlRouterProvider) {

                $urlRouterProvider.otherwise("/home");

                $stateProvider
                    .state("root", {
                        url: "/",
                        views: {
                            navbar: {
                                templateUrl: "client/assets/views/start/navbar.view.html",
                                controller: function ($log, $scope) {
                                    $log.info("NavbarController");
                                    $scope.isCollapsed = true;
                                }
                            }
                        }
                    })
                    .state("root.home", {
                        url: "^/home",
                        views: {
                            "container@": {
                                templateUrl: "client/assets/views/start/home.view.html",
                                controller: "HomeController"
                            }
                        }
                    })
                    .state("root.find-movie", {
                        url: "^/find-movie",
                        views: {
                            "container@": {
                                templateUrl: "client/assets/views/movie/find-movie.view.html",
                                controller: "FindMovieController"
                            }
                        }
                    });
            }
        }
    )
})(define);
