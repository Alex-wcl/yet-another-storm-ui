package com.deepnighttwo.asu.server.model;

import com.google.gson.Gson;

/**
 * User: mzang
 * Date: 2014-09-29
 * Time: 18:37
 */
public class StormDataService {
    StormRestClient client;
    Gson gson = new Gson();

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


}
