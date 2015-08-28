(function (define) {

    "use strict";

    define(
        [],
        function () {
            var MovieSearchRequestController = function ($scope, $log, $stateParams, $state, $location, Movie, SpringDataRestAdapter) {
                $scope.isMoviesLoading = true;
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

                $scope.toDetails = function (selectedItem) {
                    $scope.$parent.selectedSearchResult = selectedItem;
                    $state.go('^.details', {movieLink: encodeURIComponent(JSON.stringify(selectedItem._links.movie))});
                };
            };
            return ["$scope", "$log", "$stateParams", "$state", "$location", "Movie", "SpringDataRestAdapter", MovieSearchRequestController];
        }
    );

    var ResultInfo = {
        OK: "OK",
        BY_WORD_REQUEST_REQUIRED: "BY_WORD_REQUEST_REQUIRED"
    }
})
(define);
