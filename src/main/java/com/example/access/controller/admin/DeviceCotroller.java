package com.example.access.controller.admin;

import com.example.access.constant.SystemConstant;
import com.example.access.model.DeviceModel;
import com.example.access.paging.PageRequest;
import com.example.access.paging.Pageble;
import com.example.access.service.IDeviceService;
import com.example.access.service.ISiteSevice;
import com.example.access.sort.Sorter;
import com.example.access.ultis.FormUltis;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin-device-list")
public class DeviceCotroller extends HttpServlet {
    @Inject
    private IDeviceService deviceService;
    @Inject
    private ISiteSevice siteService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeviceModel deviceModel = FormUltis.toModel(DeviceModel.class, request);
        String view = "";
        if (deviceModel.isList()) {
            Pageble pageble = new PageRequest(deviceModel.getPage(), deviceModel.getMaxPageItem(), new Sorter(deviceModel.getSortName(),
                    deviceModel.getSortBy()));

            deviceModel.setListResult(deviceService.findAll(pageble));
            deviceModel.setTotalItem(deviceService.getTotalItemDevice());
            deviceModel.setTotalPage((int) Math.ceil((double) deviceModel.getTotalItem() / deviceModel.getMaxPageItem()));

            view = "/views/admin/device/list.jsp";

        } else if (deviceModel.isEdit()) {
            if (deviceModel.getId() != null) {
                deviceModel = deviceService.findOne(deviceModel.getId());
            }
            view = "/views/admin/device/edit.jsp";
        }

        responseMessage(request);
        request.setAttribute("side", siteService.findAll());
        request.setAttribute(SystemConstant.MODEL, deviceModel);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void responseMessage(HttpServletRequest request) {
        if (request.getParameter("message") != null) {
            String message;
            String messageResponse = "";
            String alert = "";
            message = request.getParameter("message");
            switch (message) {
                case "insert_success":
                    messageResponse = "Insert Success";
                    alert = "success";
                    break;
                case "update_success":
                    messageResponse = "Update Success";
                    alert = "success";
                    break;
                case "delete_success":
                    messageResponse = "Delete Success";
                    alert = "success";
                    break;
                case "error_system":
                    messageResponse = "Error!";
                    alert = "danger";
                    break;
            }
            request.setAttribute("messageResponse", messageResponse);
            request.setAttribute("alert", alert);
        }
    }
}
