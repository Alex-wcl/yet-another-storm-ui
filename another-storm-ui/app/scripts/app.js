'use strict';

/**
 * @ngdoc overview
 * @name anotherStormUiApp
 * @description
 * # anotherStormUiApp
 *
 * Main module of the application.
 */
var app = angular
    .module('anotherStormUiApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch'
    ]);


app.factory('client', ['$http', function ($http) {
    var request = function (restPath) {
        return $http.get("http://127.0.0.1:8080/" + restPath);
    };

    return {
        topos: function (callback) {
            request('topolist').success(callback);
        }
    };
}]);


app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            redirectTo: '/overview'
        }).when('/overview', {
            templateUrl: 'views/overview.html',
            controller: 'OverviewCtrl'
        })
        .when('/topo', {
            templateUrl: 'views/topo.html',
            controller: 'TopoCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.controller("TabCtrl", ['$rootScope', '$location', '$scope', 'client', function ($rootScope, $location, $scope, client) {
    $rootScope.tabs = [];

    client.topos(function (topos, status) {
        $rootScope.tabs = [
            {tabName: "Overview", tabId: "Overview", tabLink: "/overview"}
        ];

        for (var i = 0; i < topos.length; i++) {
            $rootScope.tabs.push({tabName: topos[i].name, tabId: topos[i].id, tabLink: "/topo"});
        }
    });

    $scope.isActive = function (tabId) {
        var params = $location.search();

        if (params.tabId === tabId) {
            return true;
        }

        if ($location.path() === "/" && tabPath === "/overview") {
            return true;
        }

    }
}]);






