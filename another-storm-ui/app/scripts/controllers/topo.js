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
app.controller('TopoCtrl', ['$scope', function ($scope) {

    //
    // Setup the data-model for the chart.
    //

    $scope.data = "asdfasdfasdfasdfasdf";

    var chartDataModel = {

        nodes: [
            {
                name: "Example Node 1asdfasdfasdf",
                id: 0,
                x: 0,
                y: 0,
                width: 1000,
                inputConnectors: [
                    {
                        name: "aaaaa\nbbbbbb\ncccc\nddddd\n"
                    },
                    {
                        name: "B"
                    },
                    {
                        name: "C"
                    }
                ],
                outputConnectors: [
                    {
                        name: "A"
                    },
                    {
                        name: "B"
                    },
                    {
                        name: "C"
                    }
                ]
            },

            {
                name: "Example Node 2",
                id: 1,
                x: 400,
                y: 200,
                inputConnectors: [
                    {
                        name: "A"
                    },
                    {
                        name: "B"
                    },
                    {
                        name: "C"
                    }
                ],
                outputConnectors: [
                    {
                        name: "A"
                    },
                    {
                        name: "B"
                    },
                    {
                        name: "C"
                    }
                ]
            }

        ],

        connections: [
            {
                source: {
                    nodeID: 0,
                    connectorIndex: 1
                },

                dest: {
                    nodeID: 1,
                    connectorIndex: 2
                }
            }
        ]
    };
    $scope.chartViewModel = new flowchart.ChartViewModel(chartDataModel);

}]);


