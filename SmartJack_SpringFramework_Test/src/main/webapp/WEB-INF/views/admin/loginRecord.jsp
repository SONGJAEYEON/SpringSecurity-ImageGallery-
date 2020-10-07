<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
</script>
<body>
<%@include file="/WEB-INF/views/nav.jsp" %>
<div class="container">
  <h2>로그인 이력</h2>
  <p>관리자만 볼 수 있는 페이지~</p>           
  <table class="table table-striped">
    <thead>
      <tr>
        <th> Sequence </th>
        <th> Username </th>
        <th> login / logout </th>
        <th> Time </th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="log" items="${logList}" varStatus="vs">
      <tr>
        <td>${log.log_seq}</td>
        <td>${log.id}</td>
        <td>${log.in_or_out}</td>
        <td>${log.log_time}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>