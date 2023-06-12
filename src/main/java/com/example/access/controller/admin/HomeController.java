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
    private IUserSevice userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String view = "";
        if (action == null) {
            view = "/views/admin/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
            return;
        }

        String url = request.getContextPath();
        if (action.equals("login")) {
            checkMessage(request);
            view = "/views/login.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        } else {
            SessionUtils.getInstance().removeValue(request, "USERMODEL");
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            return;
        }

        String url = request.getContextPath();
        if (action.equals("login")) {
            UserModel userModel = FormUltis.toModel(UserModel.class, request);
            userModel = userService.findCheckUser(userModel);

            if (userModel != null && userModel.isUser()) {
                SessionUtils.getInstance().putValue(request, "USERMODEL", userModel);
                url += "/trang-chu";
            } else if (userModel != null && userModel.isAdmin()) {
                SessionUtils.getInstance().putValue(request, "USERMODEL", userModel);
                url += "/admin-home";
            } else {
                url += "/dang-nhap?action=login&message=username_password_invalid&alert=danger";
            }
            response.sendRedirect(url);
        }
    }
    public void checkMessage(HttpServletRequest request) {
        String message = request.getParameter("message");
        String alert = request.getParameter("alert");
        if (message != null && alert != null) {
            request.setAttribute("message", resourceBundle.getString(message));
            request.setAttribute("alert", alert);
        }
    }
}

