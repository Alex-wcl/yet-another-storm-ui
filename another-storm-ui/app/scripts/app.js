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

app.controller("TabCtrl", function ($rootScope, $location, $scope) {
    $rootScope.tabs = [
        {tabName: "Overview", tabId: "Overview", tabLink: "/overview"}
    ];
    $rootScope.tabs.push({tabName: "Topo1", tabId: "Topo1-2014-09-28", tabLink: "/topo"});
    $rootScope.tabs.push({tabName: "Topo2", tabId: "Topo2-2014-09-28", tabLink: "/topo"});
    $rootScope.tabs.push({tabName: "Topo3", tabId: "Topo3-2014-09-28", tabLink: "/topo"});

    $scope.isActive = function (tabId) {
        var params = $location.search();

        if (params.tabId === tabId) {
            return true;
        }

        if ($location.path() === "/" && tabPath === "/overview") {
            return true;
        }

    }
});


