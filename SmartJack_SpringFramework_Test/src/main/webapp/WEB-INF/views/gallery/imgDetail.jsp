<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
      <hr>
      <h2 style="text-align: center;">이미지 정보</h2> 
      <hr>  
  <table class="table table-striped">
  
    <tbody>
    <tr>
            <td> 이미지 이름 </td>
        <td><h4>${imgInfo.image_name}</h4> </td>
    </tr>
        <tr>
            <td> 아이디 </td>
        <td><h4>${imgInfo.id}</h4> </td>
    </tr>
        <tr>
            <td> 사이즈 </td>
        <td><h4>${imgInfo.file_size} byte </h4> </td>
    </tr>
        <tr>
            <td> 업로드 시점 </td>
        <td><h4>${imgInfo.upload_time}</h4> </td>
    </tr>

    </tbody>

</table>
</div>
</body>
</html>