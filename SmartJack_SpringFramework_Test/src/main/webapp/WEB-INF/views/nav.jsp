<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
  <title>NAV BAR</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<nav class="navbar navbar-inverse" style="margin: 1% 5% 1% 5%;">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">HelloWorld</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="./loginResult.do"> GALLERY </a></li>
      <li><a href="./gologinRecord.do"> LOGIN RECORD </a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
	<c:choose>
    <c:when test="${user.auth eq 'ROLE_Admin'}">
         <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${user.id} 님  (관리자)</a></li>
    </c:when>
    <c:otherwise>
       <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${user.id} 님  (사용자)</a></li>
    </c:otherwise>
</c:choose>
      <li><a href="./logout.do"><span class="glyphicon glyphicon-log-out"></span> LOGOUT</a></li>
    </ul>
  </div>
</nav>