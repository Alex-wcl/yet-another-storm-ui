package com.deepnighttwo.asu.server.servlet;

import com.deepnighttwo.asu.server.model.Host;
import com.deepnighttwo.asu.server.model.Topology;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: mzang
 * Date: 2014-09-30
 * Time: 13:08
 */
@WebServlet(name = "hosts", urlPatterns = {"/hosts"})
public class Hosts extends ServletBase {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCommonHeaders(resp);

        List<Host> hosts = new ArrayList<Host>();

        Topology[] topos = service.getTopologiesSummary();

        for (Topology topo : topos) {
            String topoId = topo.getId();
            System.out.println("------" + topoId + "------");
            Map<String, Object> topoDetails = service.getTopologyDetails(topoId);

            System.out.println("------spouts------");
            List<Map> spouts = (List<Map>) topoDetails.get("spouts");
            for (Map spout : spouts) {
                String spoutId = (String) spout.get("spoutId");
                Map<String, Object> spoutDetails = service.getComponentDetails(topoId, spoutId);
                System.out.println(spoutDetails);
            }

            System.out.println("------bolts------");
            List<Map> bolts = (List<Map>) topoDetails.get("bolts");
            for (Map bolt : bolts) {
                String boltId = (String) bolt.get("boltId");
                Map<String, Object> boltDetails = service.getComponentDetails(topoId, boltId);
                System.out.println(boltDetails);
            }

        }


        resp.getOutputStream().write(gson.toJson("").getBytes());
        resp.setHeader("Content-Type", "application/json; charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }


}
