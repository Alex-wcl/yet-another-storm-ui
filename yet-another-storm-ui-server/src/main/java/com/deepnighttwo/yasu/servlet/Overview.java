package com.deepnighttwo.yasu.servlet;

import com.deepnighttwo.yasu.model.Topology;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mzang
 * Date: 2014-09-30
 * Time: 13:08
 */
@WebServlet(name = "overview", urlPatterns = {"/overview"})
public class Overview extends ServletBase {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCommonHeaders(resp);

        Map<String, Object> overview = new HashMap<String, Object>();

        Map<String, Object> clusterConfig = service.getClusterConfig();
        Map<String, Object> clusterSummary = service.getClusterSummary();
        Map<String, Object> supervisorSummary = service.getSupervisorSummary();

        Topology[] topos = service.getTopologiesSummary();

        overview.put("cluserConfig", clusterConfig);
        overview.put("clusterSummary", clusterSummary);
        overview.put("supervisorSummary", supervisorSummary);
        overview.put("topos", topos);

        resp.getOutputStream().write(gson.toJson(overview).getBytes());
        resp.setHeader("Content-Type", "application/json; charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }


}
