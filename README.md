yet-yet-another-storm-ui
================

yet another storm ui based on storm ui rest api release with 0.9.2

# Introduction

Origin storm ui is not handy for some cases. Thanks for the 0.9.2 release brings out the rest api which is the footstone of this project.

This project is trying to build yet another ui for storm.

# High Lights

 * Provide a Host perspective to show runtime status of topologies.
 * Provide one-page web application experience
 * Other enhancements...

This is the UI at first glance:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/first-glance.png)

# Happy Path

If interested, this section will help to run this web application.

## Dependencies

 * Need storm 0.9.2 (0.9.3 is not tested yet). Start storm ui using "storm ui"
 * Need Maven 3 to build
 * Developed using Java 7 (Should work on Java 6)
 * Run in Tomcat (Any Java EE container should works)
 * Tested and support Chrome and Firefox. IE is not supported (Not a web ui expert, don't know why and has no plan to support IE (-: )

## Build

Get code from github. Goes into directory "yet-another-storm-ui/yet-another-storm-ui-server", run "maven clean pacakge install". Then goes to "yet-another-storm-ui/yet-another-storm-ui-server/target", there should be a "yasu.war".

## Deploy

Copy the "yasu.war" to the Tomcat's webapp dir ( NOTE: Please don't rename the war file, if rename is needed, please refer to the last paragraph "Touble Shooting -> YASU not works if rename the war file" of this section) and this webapp will run in subdomain "yasu"

## Use & Settings

Open the link "http://TOMCAT_SERVER_IP:PORT/yasu" in browser, like "http://127.0.0.1:8181/yasu". You might see a page like this:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/happypath-first-open.png)

It is almost there. YASU now need a configuration to know the Storm UI url so that YASU can get storm cluster status data.

Click the Settings tab, specifiy the Storm UI url as follow:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/happypath-settings.png)

Click "Update Storm URL" button. If update succeeded, there will be a tooltip says "Storm url updated to AAA.BBB.CCC.DDD:EEEEE" and all topologies should be listed as tabs after Host tab, as follow:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/happypath-settings-success.png)

If Update failed, there will be a tooltip as follow:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/happypath-settings-fail.png)

Then please check the storm url and update again.

## Trouble Shootings

### Topologies Tabs not listed

After the Storm URL is updated succeeded, all topologies should be listed as tabs after Host tab. But for firefox, this is not done automatically. Then please press F5 to refresh the whole page.

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/happypath-settings-trouble-shootings-notopos.png)

### YASU not works if rename the war file

If rename the war file is needed, a file need to be modified:

* yet-another-storm-ui/app/scripts/app.js: "return $http.get("/yasu/" + restPath);" should be changed to "return $http.get("/THE_WAR_FILE_NAME/" + restPath);" . For example: return $http.get("/myyasu/" + restPath);

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/happypath-troubleshoottings-changewarname.png)

The rest steps are the same ( and surely open the link with the new subdomain name)

Following are details about the UI.

# About the UI

The new storm ui is still web-based and trying to provide one-page web application experience. It will use tabs to list top-level content. There are "Overview" tab, "Settings" tab, "Host" tab and topology tab(s).

Tabs are listed on the right-top of the page. The following is details about the tabs.

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-header.png)
 
## Overview tab

Overview tab is quite the same like the storm origin UI, including Cluster Summary, Topology Summary, Supervisor Summary and Cluster Config. 

One small enhancement it will show the IP together with the host name.

Here is the snapshot

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-overview.png)

## Settings tab
 
Settings tab now has one setting only. In case there are more than one storm cluster, yasu supports switch storm restful URL in this tab. Specify a new URL and click the "Update Storm URL" button, the new URL will be stored in cookie and sent to server. And the UI's data will comes from the new URL.

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-settings.png)


## Host tab

Host tab is new to storm origin ui. It list status start from host perspective. 

This tab shows host status in the following order:

+ Host
 - Slots status on the Host (Process)
   * Executors status (Thread in the same Process) on the Slot

In this tab, it is easy to find out if memory-critical components(Spout/Bolt) run on the same slot or if CPU-critical components run on the same host. These can be useful for trouble-shooting.

One new column planning to be added is the restart times of an component. Storm will manage to restart a bad component automantically, it is hard to know how many times a component restarted. (Only knows how long it is up. It might restart one or more times prior to the last startup).

Here is the snapshot

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-hosts.png)


## Topology tab(s)

Each topology is listed as a tab. 

There are six parts on topology tab

First two is Topology summary and Topology stats:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-topology-1.png)

Theses are the same as storm default. 

Then the spout part:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-topo-spout-1.png)

In this part, some key columns uses different background color. And there is a detail button to show the details of the spout:
 
 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-topo-spout-2.png)

Basically this part comes from spout page of origin storm ui but tighter.
 
 * Shows status of 10 minutes only. Mostly that is what should be cared about for a real-time system.
 * Skip the empty part. For example, if there is no error, there will be no error table rather than show an empty one.
 * Add host IP of executor.
 * Shorten system component name. For example, metrics component's name is full class name which is kind of long. Now it is shorten to "__" plus "class short name".
 
Then the bolt part. The changes are quite the same as spout:

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-topo-bolt-1.png)

Finally comes the config parts. It is separated to two parts, "Different configs from default" and "Same configs with default".  

 ![image](https://raw.githubusercontent.com/deepnighttwo/yet-another-storm-ui/master/README.img/intro-topo-config.png)
 

PS: Topology actions are not supported for now. This might be supported together with ACL.

# How to use

## Project Structure

There are two project. One is called yet-another-storm-ui which is only front-end based on angularjs. Another is yet-another-storm-ui-server which provide yet-another-storm-ui data with rest api too.

user send request to yet-another-storm-ui, yet-another-storm-ui request data from yet-another-storm-ui-server and yet-another-storm-ui-server pull data from storm rest api.

## Configuration

There are two configs to be changed.

* yet-another-storm-ui-server/src/main/resources/conf.properties: set asu.restapilocation to be a correct storm rest api endpoint (or you can change it in the Settings tab at runtime)
* yet-another-storm-ui/app/scripts/app.js: "return $http.get("/yasu/" + restPath);" should be changed to "return $http.get("http://TOMCAT_SERVER_IP:PORT/SUB_DIR_FOR_YASU" + restPath);" . For example: return $http.get("http://1.2.3.4:8181/yasu/" + restPath);


## Compile&Deploy

Go into yet-another-storm-ui-server and run "mvn clean package install", it will copy static files from yet-another-storm-ui and build a war file named yasu.war. 

To deploy it, simply copy it to a java container, e.g. tomcat's webapp folder.

# How to develop

yet-another-storm-ui-server is quite a simple java restful app. it uses annotations to add servlet. There is client wrap to communicate with storm rest api. And there is a service to do more works with raw data returned from storm api (adding caches maybe). Also some beans is created to store storm api data for convenience.

yet-another-storm-i is created using yeoman, using AngularJS as framework. if you want to use grunt, please run "bower install & npm install" for the first time.

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



