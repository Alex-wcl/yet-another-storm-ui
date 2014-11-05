'use strict';

/**
 * @ngdoc function
 * @name anotherStormUiApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the anotherStormUiApp
 */
app.controller('SettingsCtrl', ['$scope', '$rootScope', '$cookies', '$timeout', "client",
    function ($scope, $rootScope, $cookies, $timeout, client) {
        $scope.stormURL = $cookies.stormURL;

        $scope.stormURL = $cookies.stormURL;

        $scope.setStormURL = function () {

            $cookies.stormURL = $scope.stormURL;

            client.checkStormURL(function (data, status) {
                $scope.showMessage = true;

                client.topos(function (topos, status) {
                    updateTabs($rootScope, topos, status);
                });

                $timeout(function () {
                    $scope.showMessage = false;
                }, 5000, true);
            });
        };
    }]);
