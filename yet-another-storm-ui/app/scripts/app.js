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

function updateTabsOnFail($rootScope) {
  $rootScope.tabs = [];
  $rootScope.tabs = [
    {tabName: "Overview", tabId: "Overview", tabLink: "/overview"},
    {tabName: "Settings", tabId: "Settings", tabLink: "/settings"},
    {tabName: "Host", tabId: "Host", tabLink: "/host"}
  ];
}

function defaultFail($scope) {
  $scope.showAPIFail = true;
}


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
    return $http.get("/yasu/" + restPath);
  };

  return {
    topos: function (callback, failcallback) {
      request('topolist').success(callback).error(failcallback);
    },
    overview: function (callback, failcallback) {
      request('overview').success(callback).error(failcallback);
    },
    hosts: function (callback, failcallback) {
      request('hosts').success(callback).error(failcallback);
    },
    topo: function (topoid, callback, failcallback) {
      request('topo?topoid=' + topoid).success(callback).error(failcallback);
    },
    checkStormURL: function (newStormRestHost, callback, failcallback) {
      request('checkStormURL?newStormRestHost=' + newStormRestHost).success(callback).error(failcallback);
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
