(function () {

    "use strict";

    define(
        [],
        function () {
            return function ($stateProvider, $urlRouterProvider) {

                $urlRouterProvider.otherwise('/home');

                $stateProvider
                    .state('find-movie', {
                        url: '/find-movie',
                        templateUrl: 'client/assets/views/find-movie.view.html',
                        controller: "FindMovieController"
                    });
            };
        }
    )
})(define);
