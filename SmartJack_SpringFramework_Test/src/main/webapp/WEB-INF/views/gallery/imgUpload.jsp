<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
body {font-family: Arial, Helvetica, sans-serif;
background-color: #a9a9a9;}

.container {
  padding: 16px;
  border: 5px solid gray;
  border-radius: 10px;
  width:400px;
  margin : auto;
  margin-top:5%;
  background-color: white;
  
}
#result{
margin-top: 2%;
 border: 0.5px solid gray;
  width: 99%;
   text-align: left;
}
</style>
<script>
function popUpClose(){
	self.close();
}
</script>
<body>
<div class="container">
<hr>
<h1 style="text-align: center">파일 업로드</h1>
<hr>
<form action="./fileupload.do", method="post" enctype="multipart/form-data">
    <input class="btn" type="file", name="uploadfile" placeholder="이미지 선택" /><br/>
    <div>
    <input class="btn" type="submit" onclick="alert('파일 업로드!');" value="파일 업로드" >
    <button  class="btn" style="float:right;" onclick="popUpClose()">닫기</button>
    </div>
        <div>
<span id='result' class="btn"  >파일명 : ${result}</span>
    </div>
    
<!-- style="display:none;" -->
</form>
</div>


</body>
</html>