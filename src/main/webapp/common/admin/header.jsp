<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
    <link href="<c:url value='/template/web/css/header.css' />" rel="stylesheet" type="text/css" media="all"/>
</head>
<div id="navbar" class="navbar navbar-default ace-save-state title-header">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Trang quản trị
                </small>
            </a>
        </div>
        <div class="navbar-buttons navbar-header pull-right collapse navbar-collapse" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue dropdown-modal">
                    <c:if test="${not empty USERMODEL}">
                <li class="nav-item">
                    <a class="nav-link" >Wellcome ${USERMODEL.fullName}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href='<c:url value="/thoat?action=logout"/>'>Thoát</a>
                </li>
                </c:if>
                </li>
            </ul>
        </div>
    </div>
</div>
