/**
 * Created by mzang on 2014-09-28.
 */

'use strict';

/**
 * @ngdoc function
 * @name anotherStormUiApp.controller:TopoCtrl
 * @description
 * # AboutCtrl
 * Controller of the anotherStormUiApp
 */
app.controller('TopoCtrl', ['$scope', '$location', "client", function ($scope, $location, client) {
    var params = $location.search();

    client.topo(params.tabId, function (data, status) {
        $scope.topo = data;
    });

    $scope.isCollapsed = [];

    $scope.data = [
        {
            "title": "title1",
            "content": "content1  " + ((new Date()).toUTCString())
        },
        {
            "title": "title2",
            "content": "content1"

        }
    ];

}]);


