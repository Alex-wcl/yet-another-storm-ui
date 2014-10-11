'use strict';

/**
 * @ngdoc function
 * @name anotherStormUiApp.controller:HostCtrl
 * @description
 * # MainCtrl
 * Controller of the anotherStormUiApp
 */
angular.module('anotherStormUiApp')
    .controller('HostCtrl', ["$scope", "client", function ($scope, client) {
        client.hosts(function (data, status) {
            $scope.hosts = data;
        })
    }]);
