<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>
<style type="text/css">
::-webkit-scrollbar{width: 7px;}
::-webkit-scrollbar-track {background-color:white;}
::-webkit-scrollbar-thumb {background-color:gray;border-radius: 50px;}
::-webkit-scrollbar-thumb:hover {background: black;}
::-webkit-scrollbar-button:start:decrement,::-webkit-scrollbar-button:end:increment {
width:1px;height:1px;background: white;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<meta charset="UTF-8">
<title>아이디 중복 검사</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
body {font-family: Arial, Helvetica, sans-serif;
background-color: #a9a9a9;}
#container{
  border: 5px solid gray;
 border-radius: 10px;
	 width:320px; 
	 padding:20px; 
	 margin: 60px auto; 
	 text-align: center; 
	 background-color: white;
}
/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
/*   margin: 5px 0 22px 0; */
  margin: auto;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}
button {
  background-color: #595959;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}
button:hover {
  opacity:1;
}

</style>
</head>
<script type="text/javascript" src="./js/jquery-3.5.1.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#id").keyup(function(){
			var inputLength = $(this).val().length;
			var id = $(this).val();
			var regExpId = /^[a-zA-Z0-9]{4,12}$/;
			
			if(id.indexOf(" ") != -1){
				$("#result").css("color","red");
				$("#result").html("공백이 포함된 아이디는 입력 하실 수 없습니다.");
			}else if(inputLength<4){
				$("#result").css("color","red");
				$("#result").html("4자리 이상, 12자리 미만 아이디를 입력해 주세요.");
			}else if(!regExpId.test(id)){
				$("#result").css("color","red");
				$("#result").html("영문과 숫자만 사용 가능합니다.");
			}else{
				$("#result").html("");
			}
		});
		
		$("#chk").click(function(){
			var inputLength = $("#id").val().length;
			var id = $("#id").val();
			var regExpId = /^[a-zA-Z0-9]{4,12}$/;
			if(id.indexOf(" ") != -1){
				swal("입력오류","공백이 포함된 아이디는 입력 하실 수 없습니다.");
			}else if(inputLength<4){
				swal("입력오류","4자리 이상, 12자리 미만 아이디를 입력해 주세요.");
			}else if(!regExpId.test(id)){
				swal("입력오류","영문과 숫자만 사용 가능합니다.");
			}else{
				$.ajax({ 
					url: "./idChk.do",
					data: "id="+$("#id").val(), 
					type: "post", 
					success: function(msg) { 
						if (msg=="false") { 
							$("#result").css("color","red");
							$("#result").html("사용불가능한 아이디 입니다.");
							$("#id").val("");
						} else { 
							$("#id").attr("readonly","readonly");
							$("#result").css("color","blue");
							$("#result").html("사용가능한 아이디 입니다.");
							$("#useId").css("display","block");
						} 
					},
					error: function(){
						swal("오류","잘못된 요청입니다.");
					}	
				});
			}
		});
		
		$("#useId").click(function(){
			var val = document.getElementById("id").value;
			opener.document.getElementById("id").value = val;
			/* opener는 나를 열었던 대상(부모테이블)을 이야기한다. */
			self.close();	
		});
		
	});
	
</script>
<body>
	<div id="container">
	<form>
		<table>
			<tr>
				<td>
								<hr>
				 <b style="text-align: center;">아이디 중복확인</b>
					<input type="text" id="id" name="id" maxlength="12" placeholder="사용아이디 입력">
					<button class="btn btn-basic" type="button" id="chk">중복확인</button>
				</td>

			</tr>
			<tr>
				<td><span id="result" style="color:gray;">영문,숫자만 사용한 8~12자리</span>
				<hr></td>
			</tr>
			<tr>
				<td style="text-align: center;"><button class="btn btn-basic" type="button" id="useId" style="display:none;" onclick="useId()">사용</button> </td>
			</tr>
			
		</table>
	</form>
	</div>
	
</body>
</html>