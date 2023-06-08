package com.example.access.controller.admin.api;

import com.example.access.model.DeviceModel;
import com.example.access.model.UserModel;
import com.example.access.service.IDeviceSevice;
import com.example.access.service.IUserSevice;
import com.example.access.ultis.HttpUltis;
import com.example.access.ultis.SessionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api-admin-device")
public class DeviceAPI extends HttpServlet {
    @Inject
    private IDeviceSevice deviceSevice;
    @Inject
    private IUserSevice userSevice;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        DeviceModel deviceModel = HttpUltis.of(request.getReader()).toModel(DeviceModel.class);
        UserModel userModel = (UserModel) SessionUtils.getInstance().getValue(request,"USERMODEL");
        deviceModel.setCreatedby(userModel.getFullName());
        deviceModel = deviceSevice.save(deviceModel);
        mapper.writeValue(response.getOutputStream(), deviceModel);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        DeviceModel updateDevice = HttpUltis.of(request.getReader()).toModel(DeviceModel.class);
        UserModel userModel = (UserModel) SessionUtils.getInstance().getValue(request,"USERMODEL");
        updateDevice.setModifiedby(userModel.getFullName());
        updateDevice = deviceSevice.update(updateDevice);
        mapper.writeValue(response.getOutputStream(), updateDevice);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        DeviceModel deleteDevice = HttpUltis.of(request.getReader()).toModel(DeviceModel.class);
        deviceSevice.delete(deleteDevice.getIds());
        mapper.writeValue(response.getOutputStream(), "{}");
    }
}
