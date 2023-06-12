package com.example.access.controller.admin.api;

import com.example.access.model.DeviceModel;
import com.example.access.service.IDeviceService;
import com.example.access.service.IUserSevice;
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

@WebServlet(urlPatterns = "/api-admin-device")
public class DeviceAPI extends HttpServlet {
    @Inject
    private IDeviceService deviceService;
    ObjectMapper mapper = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiUtils(request, response);
        DeviceModel deviceModel = HttpUltis.of(request.getReader()).toModel(DeviceModel.class);
        deviceModel = deviceService.save(deviceModel, request);
        mapper.writeValue(response.getOutputStream(), deviceModel);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiUtils(request, response);
        DeviceModel updateDevice = HttpUltis.of(request.getReader()).toModel(DeviceModel.class);
        updateDevice = deviceService.update(updateDevice, request);
        mapper.writeValue(response.getOutputStream(), updateDevice);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiUtils(request, response);
        DeviceModel deleteDevice = HttpUltis.of(request.getReader()).toModel(DeviceModel.class);
        deviceService.delete(deleteDevice.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }

    private void apiUtils(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
    }
}
