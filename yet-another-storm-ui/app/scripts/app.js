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
        'ngTouch',
        'flowChart',
        'ui.bootstrap'
    ]);


app.run(function ($rootScope, $routeParams, $anchorScroll, $location) {
    $rootScope.orderByField = {};
    $rootScope.reverseSort = {};

//    $rootScope.$on('$routeChangeSuccess', function (newRoute, oldRoute) {
//        console.log("rrrrrrrrrrrrrrrrrrrrrrrrrrr"+$routeParams.scrollTo);
//        $location.hash($routeParams.scrollTo);
//        $anchorScroll();
//    });
});


function updateTabs($rootScope, topos, status) {
    $rootScope.tabs = [];
    $rootScope.tabs = [
        {tabName: "Overview", tabId: "Overview", tabLink: "/overview"},
        {tabName: "Settings", tabId: "Settings", tabLink: "/settings"},
        {tabName: "Host", tabId: "Host", tabLink: "/host"}
    ];

    for (var i = 0; i < topos.length; i++) {
        console.log("added" + topos[i].name);
        $rootScope.tabs.push({tabName: topos[i].name, tabId: topos[i].id, tabLink: "/topo"});
    }
}

app.factory('client', ['$http', function ($http) {
    var request = function (restPath) {
//        return $http.get("http://127.0.0.1:8080/" + restPath);
        return $http.get("/" + restPath);
    };

    return {
        topos: function (callback) {
            request('topolist').success(callback);
        },
        overview: function (callback) {
            request('overview').success(callback);
        },
        hosts: function (callback) {
            request('hosts').success(callback);
        },
        topo: function (topoid, callback) {
            request('topo?topoid=' + topoid).success(callback);
        },
        checkStormURL: function (callback) {
            request('checkStormURL').success(callback);
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
        }).when('/host', {
            templateUrl: 'views/host.html',
            controller: 'HostCtrl'
        }).when('/settings', {
            templateUrl: 'views/settings.html',
            controller: 'SettingsCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
});
