package com.deepnighttwo.yasu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * User: mzang
 * Date: 2014-09-30
 * Time: 13:08
 */
@WebServlet(name = "topo", urlPatterns = {"/topo"})
public class Topo extends ServletBase {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCommonHeaders(resp);

        String topoid = req.getParameter("topoid");

        Map<String, Object> topo = service.getTopologyDetailsWithComponentDetails(topoid);

        resp.getOutputStream().write(gson.toJson(topo).getBytes());
        resp.setHeader("Content-Type", "application/json; charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
