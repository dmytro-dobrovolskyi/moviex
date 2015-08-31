(function (define) {

    "use strict";

    define(
        [],
        function () {
            var SearchMovieController = function ($scope, $log, $stateParams, $state, $location, Movie, SpringDataRestAdapter) {
                $scope.isMoviesLoading = true;
                if ($stateParams.title) {
                    $scope.title.value = $stateParams.title;

                    var isForce = $stateParams.isForce === "true";

                    Movie()
                        .findByTitle({isForce: isForce, title: $stateParams.title}, function (data, getHeaders) {
                            SpringDataRestAdapter
                                .process(data)
                                .then(function (processedResponse) {
                                    $scope.result = processedResponse._embeddedItems;
                                });
                            $scope.isMoviesLoading = !($scope.isMoviesLoaded = true);
                            $location.search('isForce', null);
                            $scope.isForceBtnShown = !isForce;
                        });
                }
            };
            return ["$scope", "$log", "$stateParams", "$state", "$location", "Movie", "SpringDataRestAdapter", SearchMovieController];
        }
    );

    var ResultInfo = {
        OK: "OK",
        BY_WORD_REQUEST_REQUIRED: "BY_WORD_REQUEST_REQUIRED"
    }
})
(define);
