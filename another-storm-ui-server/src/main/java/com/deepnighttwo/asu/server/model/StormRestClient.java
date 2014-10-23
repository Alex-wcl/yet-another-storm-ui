package com.deepnighttwo.asu.server.model;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * User: mzang
 * Date: 2014-09-24
 * Time: 14:46
 */
public class StormRestClient {

    private static Gson gson = new Gson();

    private static PoolingClientConnectionManager ccm = new PoolingClientConnectionManager();

    static {
        ccm.setMaxTotal(4096);
        ccm.setDefaultMaxPerRoute(4096);
    }

    private static HttpClient client = new DefaultHttpClient(ccm);

    String apiBase;

    public StormRestClient(String stormUIHost) {
        updateStormUIHost(stormUIHost);

    }

    public void updateStormUIHost(String stormUIHost) {
        this.apiBase = "http://" + stormUIHost + "/api/v1/";
    }

    public static void main(String[] args) throws IOException {
        StormRestClient client = new StormRestClient("10.8.74.105:8080");
        String clusterSummary = client.getClusterSummary();
        System.out.println(clusterSummary);
        String clusterConfig = client.getClusterConfig();
        System.out.println(clusterConfig);
        String supervisorSummary = client.getSupervisorSummary();
        System.out.println(supervisorSummary);
        Topologies topoSummary = client.getTopoSummary();
        System.out.println(topoSummary);

        Gson gson = new Gson();

        for (Topology topo : topoSummary.getTopologies()) {
            String topoId = topo.getId();
            System.out.println("------" + topoId + "------");
            String topoDetails = client.getTopologyDetails(topoId);
            System.out.println(topoDetails);
            String topoVisual = client.getTopologyVisualization(topoId);
            System.out.println(topoVisual);
            Map topoMap = gson.fromJson(topoDetails, Map.class);

            System.out.println("------spouts------");
            List<Map> spouts = (List<Map>) topoMap.get("spouts");
            for (Map spout : spouts) {
                String spoutId = (String) spout.get("spoutId");
                String spoutDetails = client.getComponentDetails(topoId, spoutId);
                System.out.println(spoutDetails);
            }

            System.out.println("------bolts------");
            List<Map> bolts = (List<Map>) topoMap.get("bolts");
            for (Map bolt : bolts) {
                String boltId = (String) bolt.get("boltId");
                String boltDetails = client.getComponentDetails(topoId, boltId);
                System.out.println(boltDetails);
            }

        }

    }

    public String getClusterSummary() throws IOException {
        return getApiData("cluster/summary");
    }

    public Topologies getTopoSummary() {
        String topos = getApiData("topology/summary");
        Topologies topologies = gson.fromJson(topos, Topologies.class);
        return topologies;
    }

    public String getSupervisorSummary() {
        return getApiData("supervisor/summary");
    }

    public String getClusterConfig() {
        return getApiData("cluster/configuration");
    }

    public String getTopologyDetails(String topoId) {
        String url = String.format("topology/%s?sys=true&window=600", topoId);
        return getApiData(url);
    }

    public String getTopologyVisualization(String topoId) {
        String url = String.format("topology/%s/visualization", topoId);
        return getApiData(url);
    }

    public String getComponentDetails(String topoId, String compId) {
        String url = String.format("topology/%s/component/%s?sys=true", topoId, compId);
        return getApiData(url);
    }


    private String getApiData(String url) {
        HttpGet get = new HttpGet(apiBase + url);
        HttpResponse response = null;
        try {
            response = client.execute(get);
            return readContent(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Got io exception");
        }
    }


    private static String readContent(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        try {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }


}
