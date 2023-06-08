package com.example.access.controller.admin;

import com.example.access.constant.SystemConstant;
import com.example.access.model.DeviceModel;
import com.example.access.paging.PageRequest;
import com.example.access.paging.Pageble;
import com.example.access.service.IDeviceSevice;
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
    private IDeviceSevice deviceSevice;
    @Inject
    private ISiteSevice siteSevice;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeviceModel deviceModel = FormUltis.toModel(DeviceModel.class, request);
        String view = "";
        if (deviceModel.getType().equals(SystemConstant.LIST)) {
            Pageble pageble = new PageRequest(deviceModel.getPage(), deviceModel.getMaxPageItem(), new Sorter(deviceModel.getSortName(),
                    deviceModel.getSortBy()));
            deviceModel.setListResult(deviceSevice.findAll(pageble));
            deviceModel.setTotalItem(deviceSevice.getTotalItemDevice());
            deviceModel.setTotalPage((int) Math.ceil((double) deviceModel.getTotalItem() / deviceModel.getMaxPageItem()));
            request.setAttribute(SystemConstant.MODEL, deviceModel);
            view = "/views/admin/device/list.jsp";
        } else if (deviceModel.getType().equals(SystemConstant.EDIT)) {
            if(deviceModel.getId()!= null){
                deviceModel = deviceSevice.findOne(deviceModel.getId());
            }
            else {

            }
            view = "/views/admin/device/edit.jsp";
        }
        if(request.getParameter("message")!=null){
            String message ;
            String messageResponse="";
            String alert="";
            message = request.getParameter("message");
            if(message.equals("insert_success")){
                messageResponse = "Insert Success";
                alert = "success";
            } else if(message.equals("update_success")){
                messageResponse = "Update Success";
                alert = "success";
            } else if(message.equals("delete_success")){
                messageResponse = "Delete Success";
                alert = "success";
            } else if(message.equals("error_system")){
                messageResponse = "Error!";
                alert = "danger";
            }
            request.setAttribute("messageResponse",messageResponse);
            request.setAttribute("alert",alert);
        }
        request.setAttribute("side",siteSevice.findAll());
        request.setAttribute(SystemConstant.MODEL, deviceModel);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
