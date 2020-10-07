<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
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

<style>
body {font-family: Arial, Helvetica, sans-serif;
background-color: #a9a9a9;}
* {box-sizing: border-box;}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
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

.auth{

	width:30%;
	padding: 14px 20px;
	text-align: center;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color:  #595959;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: left;
  width: 50%;
}

/* Add padding to container elements */
.container {
  padding: 16px;
  border: 5px solid gray;
  border-radius: 10px;
  width:600px;
  margin : auto;
  background-color: white;
  
}

/* The Modal (background) */
.modal {

  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: #474e5d;
  padding-top: 50px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* Style the horizontal ruler */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
 
/* The Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 15px;
  font-size: 40px;
  font-weight: bold;
  color: #f1f1f1;
}

.close:hover,
.close:focus {
  color: #f44336;
  cursor: pointer;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .signupbtn {
     width: 100%;
  }
}
</style>
<script type="text/javascript">

function emailChk(){
	var pop = window.open("./emailChkForm.do", "_blank", "toolbar=yes,scrollbars=yes,resizable=no,top=200,left=700,width=450,height=320");
}

function idChk(){
	var pop = window.open("./idChkForm.do", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=500,width=400,height=400");
}

window.onload = function(){
	
	$("#pw").keyup(function(){
		var pw = $(this).val();
		var pwLen = $(this).val().length;
		var regExpPw = /(?=.*\d{1,16})(?=.*[a-zA-Z]{1,16}).{4,16}$/;

		if(pw.indexOf(" ") != -1){
			$("#msg1").css({"color":"red","font-size":"12px"});
			$("#msg1").html("공백이 포함된 비밀번호는 입력 하실 수 없습니다.");
		}else if(!regExpPw.test(pw)){
			$("#msg1").css({"color":"red","font-size":"12px"});
			$("#msg1").html("영문,숫자,특수문자[ex) `~!@#$%^&*]를 조합한 4자~16자의 비밀번호를 입력해야 합니다.");
		}else{
			$("#msg1").html("");
		}
	});
	
	$("#passOk").keyup(function(){
		var pw = document.getElementById('pw');
		var pwChk = document.getElementById('passOk');
		if(pw.value != pwChk.value){
			$("#msg2").css({"color":"red","font-size":"12px"});
			$("#msg2").html("비밀번호가 일치하지 않습니다.");
			pwChk.focus();
		}else{
			$("#msg2").css({"color":"blue","font-size":"12px"});
			$("#msg2").html("비밀번호가 일치합니다.");
		}
	});
	
	document.getElementById('signUp').onclick = function(){
		var idVal = document.getElementById("id");
		var pwVal = document.getElementById("pw");
		var pwChkVal = document.getElementById("passOk");
		var eVal = document.getElementById("email");
		
		if(idVal.value== ""){
			swal("아이디를 입력해 주세요");
			idVal.focus();
			return false;
		}else if(pwVal.value == ""){ // 입력을 안하면
			swal("비밀번호를 입력해 주세요");
			pwVal.focus();
			return false;
		}else if(eVal.value == ""){ // 입력을 안하면
			swal("이메일을 입력해 주세요");
			pwVal.focus();
			return false;
		}else if(pwVal.value.trim() != pwChkVal.value.trim()){
			swal("비밀번호가 다릅니다.");
			pwChkVal.focus();
			return false;
		}else{
			document.getElementById('signUp').setAttribute('disabled','disabled');
			document.form.submit();
		} 
	}
}

</script>
<body>

	<div>
		<form method="post" id="form" name="form" action="./signUpSuccess.do">
			<div class="container">
				<h1>회원가입</h1>
				<hr>

				<label for="email"><b>이메일</b></label> <input type="text" id="email"
					name="email" placeholder="이메일 입력" readonly="readonly" required>
				<button onclick="emailChk();" style="margin-bottom: 5px;"
					class="auth">이메일 인증</button>

				<hr>
				<label for="ID"><b>아이디</b> <input type="text" name="id"
					id="id" readonly="readonly" placeholder="아이디 입력" required>
					<button onclick="idChk();" style="margin-bottom: 5px;" class="auth">
						아이디 중복 체크</button> </label>
				<hr>
				<label for="PW"><b>비밀번호</b></label> <input type="password" id="pw"
					name="pw" placeholder="비밀번호입력">
				<div style="height: 15px;">
					<label id="msg1"> </label>
				</div>
				<hr>
				<label for="PW-REPEAT"><b>비밀번호 확인</b></label> <input type="password"
					type="password" id="passOk" placeholder="비밀번호 확인" required>
				<div style="height: 15px;">
					<label id="msg2"> </label>
				</div>
				<hr>
				<div class="clearfix">
					<button type="button" onclick="javascript:history.back(-1)"
						class="cancelbtn">취소</button>
					<button type="submit" id="signUp" class="signupbtn">회원가입</button>
				</div>
			</div>
		</form>
	</div>


</body>
</html>
