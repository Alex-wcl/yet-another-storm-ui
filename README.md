another-storm-ui
================

another storm ui based on storm ui rest api 0.9.2

# Introduction

Origin storm ui is not handly for some cases. Thanks for the 0.9.2 release brings out the rest api which is the footstone of this project.

This project is trying to build another ui for storm. 

# About the UI

The new storm ui is still web-based. Instead of using links, it will use tabs to list top-level content. There are "Overview" tab, "Host" tab and topology tab(s).

Tabs are listed on the right-top of the page. The following is details about the tabs.

## Overview tab

Overview tab is quite the same like the storm origin UI, including Cluster Summary, Topology Summary, Supervisor Summary and Cluster Config. 

One small enhancement it will show the IP together with the host name.

Here is the snapshot

 ![image](https://raw.githubusercontent.com/deepnighttwo/another-storm-ui/master/README.img/overview.png)


## Host tab

Host tab is new to storm origin ui. It list status start from host perspective. 

This tab shows host status in the folling order:

+ Host
 - Slots status on the Host (Process)
   * Executors status (Thread in the same Process) on the Slot

In this tab, it is easy to find out if memory-critical components(Spout/Bolt) run on the same slot or if CPU-critical components run on the same host. These can be useful for trouble-shooting.

One new status planning to be added to this tab is the restart times of an compoent. Becuase storm will manage to restart a bad component automantically, it is hard to know how many times a component restarted. (Only knows how long it is up. It might restart one time or much more before the uptime).

Here is the snapshot

 ![image](https://raw.githubusercontent.com/deepnighttwo/another-storm-ui/master/README.img/host.png)


# Topology tab(s)

Each topology is listed as a tab. 

Key points of the tab:

* Try to show the topology on one page. There is no need to click links to see the bolt/spout status
* Diff topo configs with storm cluster's config. Hightlights the different configs
* Kill topology is not supported


# How to use

## Project Structure

There are two project. One is called another-storm-ui which is only front-end based on angularjs. Another is another-storm-ui-server which provide another-storm-ui data with rest api too.

user send request to another-storm-ui, another-storm-ui request data from another-storm-ui-server and another-storm-ui-server pull data from storm rest api.

## Configuration

Threre are two files to be configed. 

* another-storm-ui-server/src/main/resources/conf.properties: set asu.restapilocation to be a correct storm rest api endpoint
* another-storm-ui/app/scripts/app.js: line 24, set "$http.get("http://10.8.91.154:8080/asu/" + restPath);" to be the location where another-storm-ui-server runs


## Compile&Deploy

Go into another-storm-ui-server and run "mvn clean package install", it will copy static files from another-storm-ui and build a war file named asu.war. 

To deoloy it, simply copy it to a java container, e.g. tomcat's webapp folder.


# More

There are more features to deliver, such as 
 - ACL, expecially for kill topology.
 - JMX integration.
 - More monitoring&management. 
 
# Release Notes

## Release 0.0.1

### Feature

 - Add Host perspective. Show slots and executors running on the host
 - Show topology config as two parts: the same with default ande different with default
 - Intergate topology components details into the same page
 - Show IP together with hostname

### Bugfix



