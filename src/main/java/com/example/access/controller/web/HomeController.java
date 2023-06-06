package com.example.access.controller.web;

import com.example.access.model.DeviceModel;
import com.example.access.service.IDeviceSevice;
import com.example.access.service.ISiteSevice;
import com.example.access.service.impl.DeviceService;
import com.example.access.service.impl.SiteService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {
    @Inject
    private ISiteSevice siteSevice;
    @Inject
    private IDeviceSevice deviceSevice;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DeviceModel deviceModel = new DeviceModel();
        //deviceSevice.save(deviceModel);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
