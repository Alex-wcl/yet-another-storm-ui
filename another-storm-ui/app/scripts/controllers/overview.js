/**
 * Created by mzang on 2014-09-28.
 */

'use strict';

/**
 * @ngdoc function
 * @name anotherStormUiApp.controller:OverviewCtrl
 * @description
 * # AboutCtrl
 * Controller of the anotherStormUiApp
 */
angular.module('anotherStormUiApp')
    .controller('OverviewCtrl', ["$scope", "client", function ($scope, client) {
        client.overview(function (data, status) {
            $scope.overview = data;
            console.log(data);
        })
    }]);
