package org.examples.dispatch_servlet.servlets;

import java.io.IOException;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CustomServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String headerNames =
            Collections
                .list(req.getHeaderNames())
                    .stream()
                        .map(h -> h + ": " + req.getHeader(h))
                        .reduce((f, s) -> f.isEmpty() ? s : f + ", " + s)
                            .orElse("");

        logger.info("Headers: " + headerNames);

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        logger.info("doPost called");

        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        
        logger.info("Init called, server info: " + config.getServletContext().getServerInfo());

        super.init(config);
    }
    
}
