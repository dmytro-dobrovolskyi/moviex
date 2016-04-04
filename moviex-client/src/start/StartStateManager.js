(function () {

    "use strict";

    define(
        [],
        function () {
            var StartStateManager = function ($stateProvider, $urlRouterProvider) {
                $urlRouterProvider.otherwise("/home");

                $stateProvider
                    .state("root", {
                        views: {
                            navbar: {
                                templateUrl: "assets/views/start/navbar.view.html",
                                controller: function ($log, $scope) {
                                    $scope.isCollapsed = true;
                                }
                            }
                        }
                    })
                    .state("root.home", {
                        url: "/home",
                        views: {
                            "container@": {
                                templateUrl: "assets/views/start/home.view.html",
                                controller: "HomeController"
                            }
                        }
                    });
            };
            return ["$stateProvider", "$urlRouterProvider", StartStateManager];
        }
    )
})(define);
