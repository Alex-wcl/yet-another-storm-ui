package com.deepnighttwo.yasu.servlet;

import com.deepnighttwo.yasu.model.StormDataService;
import com.deepnighttwo.yasu.util.ConfigUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * User: mzang
 * Date: 2014-08-26
 * Time: 19:54
 */

public class ServletBase extends HttpServlet {

    Gson gson = new Gson();

    private static final StormDataService DEFAULT_SERVICE = new StormDataService(ConfigUtil.getProp("asu.restapilocation"));

    private static final Map<String, StormDataService> SERVICE_MAP = new HashMap<String, StormDataService>();

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

    public StormDataService getStormDataService(HttpServletRequest req) {

        if (req.getCookies() == null) {
            return DEFAULT_SERVICE;
        }

        for (Cookie cookie : req.getCookies()) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if ("stormURL".equals(name)) {
                return getStormDataService(value);
            }
        }
        return DEFAULT_SERVICE;
    }

    public StormDataService getStormDataService(String value) {
        value = handleHostValueFormat(value);
        StormDataService stormDataService = SERVICE_MAP.get(value);
        if (stormDataService == null) {
            stormDataService = new StormDataService(value);
            stormDataService.getClusterConfig();
            SERVICE_MAP.put(value, stormDataService);
            return stormDataService;
        } else {
            return stormDataService;
        }

    }

    public String handleHostValueFormat(String host) {
        try {
            host = URLDecoder.decode(host, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("failed to decode cookie stormURL", e);
        }
        host = host.toLowerCase().trim();
        host = host.replace("http://", "");
        host = host.replace("http：//", "");
        host = host.replace("/", "");
        host = host.replace("：", ":");
        return host;
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCommonHeaders(resp);
    }
}
