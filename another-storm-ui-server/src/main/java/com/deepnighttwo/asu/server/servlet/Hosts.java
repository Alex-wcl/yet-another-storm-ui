package com.deepnighttwo.asu.server.servlet;

import com.deepnighttwo.asu.server.model.Host;

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
@WebServlet(name = "hosts", urlPatterns = {"/hosts"})
public class Hosts extends ServletBase {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCommonHeaders(resp);

        Map<String, Host> hosts = service.getHostWithExecutorDetails();

        resp.getOutputStream().write(gson.toJson(hosts.values()).getBytes());
        resp.setHeader("Content-Type", "application/json; charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }
}
