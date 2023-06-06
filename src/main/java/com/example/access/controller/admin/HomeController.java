package com.example.access.controller.admin;

import com.example.access.model.UserModel;
import com.example.access.service.IUserSevice;
import com.example.access.ultis.FormUltis;
import com.example.access.ultis.SessionUtils;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/admin-home", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    @Inject
    private IUserSevice userSevice;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String view = "";
        if (action != null) {
            if (action.equals("login")) {
                String messsage = request.getParameter("message");
                String alert = request.getParameter("alert");
                if(messsage!=null&&alert!=null){
                    request.setAttribute("message",resourceBundle.getString(messsage));
                    request.setAttribute("alert",alert);
                }
                view = "/views/login.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(view);
                rd.forward(request, response);
            } else if (action.equals("logout")) {
                SessionUtils.getInstance().removeValue(request, "USERMODEL");
                response.sendRedirect(request.getContextPath() + "/trang-chu");
            }
        } else {
            view = "/views/admin/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("login")) {
                UserModel userModel = FormUltis.toModel(UserModel.class, request);
                userModel = userSevice.findCheckUser(userModel);
                if (userModel != null) {
                    SessionUtils.getInstance().putValue(request, "USERMODEL",userModel);
                    if (userModel.getRole().getCode().equals("user")) {
                        response.sendRedirect(request.getContextPath() + "/trang-chu");
                    } else if (userModel.getRole().getCode().equals("admin")) {
                        response.sendRedirect(request.getContextPath() + "/admin-home");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=username_password_invalid&alert=danger");
                }
            }
        }
    }
}
