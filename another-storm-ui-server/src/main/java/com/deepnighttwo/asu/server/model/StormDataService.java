package com.deepnighttwo.asu.server.model;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * User: mzang
 * Date: 2014-09-29
 * Time: 18:37
 */
public class StormDataService {
    StormRestClient client;
    static Gson gson = new Gson();

    public StormDataService(String stormUIHost) {
        client = new StormRestClient(stormUIHost);
    }

    public Topology[] getTopologiesSummary() {
        Topologies topologies = client.getTopoSummary();
        if (topologies == null || topologies.getTopologies() == null) {
            return new Topology[0];
        }
        return topologies.getTopologies();
    }


    public String getClusterSummary() throws IOException {
        return client.getClusterSummary();
    }

    public String getSupervisorSummary() {
        return client.getSupervisorSummary();
    }

    public String getClusterConfig() {
        return client.getClusterConfig();
    }


    public static Map<String, Object> toMaps(String json) {
        return gson.fromJson(json, Map.class);
    }


    public static String getIpByHostName(String hostName) {
        try {
            InetAddress address = InetAddress.getByName(hostName);
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            return "N/A";
        }
    }




}
