package com.deepnighttwo.asu.server.servlet;

import com.deepnighttwo.asu.server.model.StormDataService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: mzang
 * Date: 2014-08-26
 * Time: 19:54
 */

public class ServletBase extends HttpServlet {

    Gson gson = new Gson();

    StormDataService service = new StormDataService("10.8.91.151:8080");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    void setCommonHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With");
        resp.setHeader("Content-Type", "text/html; charset=UTF-8");

    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCommonHeaders(resp);
    }
}
