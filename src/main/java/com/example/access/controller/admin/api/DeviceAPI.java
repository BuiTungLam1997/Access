package com.example.access.controller.admin.api;

import com.example.access.model.DeviceModel;
import com.example.access.paging.PageRequest;
import com.example.access.paging.Pageble;
import com.example.access.repository.DeviceRepository;
import com.example.access.repository.entity.DeviceEntity;
import com.example.access.service.IDeviceService;
import com.example.access.service.IUserSevice;
import com.example.access.sort.Sorter;
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
import java.util.List;

@WebServlet(urlPatterns = "/api-admin-device")
public class DeviceAPI extends HttpServlet {
    @Inject
    private DeviceRepository deviceRepository;
    @Inject
    private IDeviceService deviceService;
    ObjectMapper mapper = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiUtils(request, response);
        Pageble pageble = new PageRequest();
        List<DeviceEntity> list = deviceRepository.findAll(pageble);
        DeviceEntity device = HttpUltis.of(request.getReader()).toModel(DeviceEntity.class);
        List<DeviceEntity> deviceEntity = deviceRepository.findByAnything(device);
        mapper.writeValue(response.getOutputStream(), deviceEntity);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiUtils(request, response);
        DeviceEntity deviceEntity = HttpUltis.of(request.getReader()).toModel(DeviceEntity.class);
        deviceEntity = deviceRepository.save(deviceEntity);
        mapper.writeValue(response.getOutputStream(), deviceEntity);
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
