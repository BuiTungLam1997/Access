package com.example.access.controller.admin.api;

import com.example.access.repository.SiteRepository;
import com.example.access.repository.entity.SiteEntity;
import com.example.access.ultis.HttpUltis;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet(urlPatterns = "/api-admin-site")
public class SiteAPI extends HttpServlet {

    private SiteRepository siteRepository;
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiUtils(request, response);
        SiteEntity site = HttpUltis.of(request.getReader()).toModel(SiteEntity.class);
     //   site = siteRepository.save(site);
        mapper.writeValue(response.getOutputStream(), site);
    }
    private void apiUtils(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
    }

}
