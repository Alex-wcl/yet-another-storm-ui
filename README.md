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

## Host tab

Host tab is new to storm origin ui. It list status start from host perspective. 

This tab shows host status in the folling order:

+ Host
 - Slots status on the Host (Process)
   * Executors status (Thread in the same Process) on the Slot

In this tab, it is easy to find out if memory-critical components(Spout/Bolt) run on the same slot or if CPU-critical components run on the same host. These can be useful for trouble-shooting.

One new status planning to be added to this tab is the restart times of an compoent. Becuase storm will manage to restart a bad component automantically, it is hard to know how many times a component restarted. (Only knows how long it is up. It might restart one time or much more before the uptime).

# Topology tab(s)

Each topology is listed as a tab. 

Key points of the tab:

* Try to show the topology on one page. There is no need to click links to see the bolt/spout status
* Diff topo configs with storm cluster's config. Hightlights the different configs







