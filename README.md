yet-yet-another-storm-ui
================

yet another storm ui based on storm ui rest api release with 0.9.2

# Introduction

Origin storm ui is not handy for some cases. Thanks for the 0.9.2 release brings out the rest api which is the footstone of this project.

This project is trying to build yet another ui for storm. 

# About the UI

The new storm ui is still web-based. Instead of using links, it will use tabs to list top-level content. There are "Overview" tab, "Settings" tab, "Host" tab and topology tab(s).

Tabs are listed on the right-top of the page. The following is details about the tabs.

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/head.png)
 
# Settings tab
 
Settings tab now has one setting only. In case there are more than one storm cluster, yasu supports switch storm restful URL in this tab. Specify a new URL and click the "Update Storm URL" button, the new URL will be stored in cookie and sent to server. And the UI's data will comes from the new URL.

## Overview tab

Overview tab is quite the same like the storm origin UI, including Cluster Summary, Topology Summary, Supervisor Summary and Cluster Config. 

One small enhancement it will show the IP together with the host name.

Here is the snapshot

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/overview.png)

## Settings tab

For now there is only one setting, the storm restful api URL. In this tab, the URL can be specified. Then the UI will show the new storm cluster's data.

## Host tab

Host tab is new to storm origin ui. It list status start from host perspective. 

This tab shows host status in the following order:

+ Host
 - Slots status on the Host (Process)
   * Executors status (Thread in the same Process) on the Slot

In this tab, it is easy to find out if memory-critical components(Spout/Bolt) run on the same slot or if CPU-critical components run on the same host. These can be useful for trouble-shooting.

One new column planning to be added is the restart times of an component. Storm will manage to restart a bad component automantically, it is hard to know how many times a component restarted. (Only knows how long it is up. It might restart one or more times prior to the last startup).

Here is the snapshot

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/hosts.png)


# Topology tab(s)

Each topology is listed as a tab. 

There are six parts on topology tab

First two is Topology summary and Topology stats:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/topology-1.png)

Theses are the same as storm default. 

Then the spout part:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/topo-spout-1.png)

In this part, some key columns uses different background color. And there is a detail button to show the details of the spout:
 
 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/topo-spout-2.png)

Basically this part comes from spout page of origin storm ui but tighter.
 
 * Shows status of 10 minutes only. Mostly that is what should be cared about for a real-time system.
 * Skip the empty part. For example, if there is no error, there will be no error table rather than show an empty one.
 * Add host IP of executor.
 * Shorten system component name. For example, metrics component's name is full class name which is kind of long. Now it is shorten to "__" plus "class short name".
 
Then the bolt part. The changes are quite the same as spout:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/topo-bolt-1.png)

Finally comes the config parts. It is separated to two parts, "Different configs from default" and "Same configs with default".  

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/topo-config.png)
 

PS: Topology actions are not supported for now. This might be supported together with ACL.

# How to use

## Project Structure

There are two project. One is called yet-another-storm-ui which is only front-end based on angularjs. Another is yet-another-storm-ui-server which provide yet-another-storm-ui data with rest api too.

user send request to yet-another-storm-ui, yet-another-storm-ui request data from yet-another-storm-ui-server and yet-another-storm-ui-server pull data from storm rest api.

## Configuration

Threre are two files to be configed. 

* yet-another-storm-ui-server/src/main/resources/conf.properties: set asu.restapilocation to be a correct storm rest api endpoint
* yet-another-storm-ui/app/scripts/app.js: line 37, set "$http.get("http://[IP_OF_YASU_SERVER]:8080/asu/" + restPath);" to be the location where yet-another-storm-ui-server runs


## Compile&Deploy

Go into yet-another-storm-ui-server and run "mvn clean package install", it will copy static files from yet-another-storm-ui and build a war file named yasu.war. 

To deploy it, simply copy it to a java container, e.g. tomcat's webapp folder.

# How to develop

yet-another-storm-ui-server is quite a simple java restful app. it uses annotations to add servlet. There is client wrap to communicate with storm rest api. And there is a service to do more works with raw data returned from storm api (adding caches maybe). Also some beans is created to store storm api data for convenience.

yet-another-storm-ui is created using yeoman, using AngularJS as framework. if you want to use grunt, please run "bower install & npm install" for the first time.

# More

There are more features to deliver, such as 
 - ACL, especially for kill topology.
 - JMX integration.
 - More monitoring&management. 
 
# Release Notes

## Release 0.0.1

### Feature

 - Add Host perspective. Show slots and executors running on the host
 - Show topology config as two parts: the same with default ande different with default
 - Integrate topology components details into the same page
 - Show IP together with hostname

### Bugfix



