package com.deepnighttwo.yasu.servlet;

import com.deepnighttwo.yasu.model.Topology;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: mzang
 * Date: 2014-09-29
 * Time: 18:33
 */

@WebServlet(name = "topolist", urlPatterns = {"/topolist"})
public class TopoList extends ServletBase {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCommonHeaders(resp);
        Topology[] topos = service.getTopologiesSummary();
        resp.getOutputStream().write(gson.toJson(topos).getBytes());
        resp.setHeader("Content-Type", "application/json; charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
