package com.deepnighttwo.yasu.servlet;

import com.deepnighttwo.yasu.model.StormDataService;

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

        String newStormRestURL = req.getParameter("newStormRestHost");

        setCommonHeaders(resp);

        Map<String, Object> ret = new HashMap<String, Object>();

        StormDataService service = getStormDataService(newStormRestURL);

        service.getClusterSummary();

        String stormRestHost = service.getStormRestHost();
        ret.put("stormRestHost", stormRestHost);

        resp.getOutputStream().write(gson.toJson(ret).getBytes());
        resp.setHeader("Content-Type", "application/json; charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }


}
