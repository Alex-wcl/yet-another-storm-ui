package com.deepnighttwo.yasu.servlet;

import com.deepnighttwo.yasu.model.StormDataService;
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
@WebServlet(name = "checkStormURL", urlPatterns = {"/checkStormURL"})
public class CheckStormURL extends ServletBase {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCommonHeaders(resp);

        Map<String, Object> overview = new HashMap<String, Object>();

        StormDataService service = getStormDataService(req);
        Map<String, Object> clusterSummary = service.getClusterSummary();
        overview.put("clusterSummary", clusterSummary);

        resp.getOutputStream().write(gson.toJson(overview).getBytes());
        resp.setHeader("Content-Type", "application/json; charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }


}
