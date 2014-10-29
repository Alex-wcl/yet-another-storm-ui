/**
 * Created by mzang on 2014-09-28.
 */

'use strict';

/**
 * @ngdoc function
 * @name anotherStormUiApp.controller:TabCtrl
 * @description
 * # AboutCtrl
 * Controller of the anotherStormUiApp
 */
app.controller("TabCtrl", ['$rootScope', '$location', '$scope', 'client', function ($rootScope, $location, $scope, client) {
    $rootScope.tabs = [];

    client.topos(function (topos, status) {
        $rootScope.tabs = [
            {tabName: "Overview", tabId: "Overview", tabLink: "/overview"}
        ];

        $rootScope.tabs.push(
            {tabName: "Host", tabId: "Host", tabLink: "/host"}
        );

        for (var i = 0; i < topos.length; i++) {
            $rootScope.tabs.push({tabName: topos[i].name, tabId: topos[i].id, tabLink: "/topo"});
        }


    });

    $scope.isActive = function (tabId) {
        var params = $location.search();

        if (params.tabId === tabId) {
            return true;
        }

        if (($location.path() === "/" || $location.path() === "/overview") && tabId === "Overview") {
            return true;
        }

    }
}]);