<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 인증</title>
<style type="text/css">
body {font-family: Arial, Helvetica, sans-serif;
background-color: #a9a9a9;}

#container {
	border: 5px solid gray;
	border-radius: 10px;
	width: 360px;
	padding: 20px;
	margin: 60px auto;
	text-align: center;
	background-color: white;
}

/* Full-width input fields */
input[type=text] {
  width: 90%;
  padding: 15px;
/*   margin: 5px 0 22px 0; */
  margin: auto;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus{
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
	function sendEmail(){
		var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    	
    	var email = $("#email").val();
    	
		if(!regEmail.test(email)){
			swal("이메일 형식 오류","입력 예 > scproject@gmail.com");
		}else{
			$.ajax({ 
	    		url: "./sendEmail.do",
	    		data: "user_email="+$("#email").val(), 
	    		type: "post", 
	    		success: function(msg) { 
		    			if (msg == "true") { 
		    				console.log(msg);
		    				swal("Email인증","인증키 전송 성공"); 
		    				$("#email").attr("readonly","readonly");
		    				$("#sendE").val("재전송");
		    			} else { 
		    				swal("Email인증 오류","인증키 전송 실패"); 
		    			} 
// 	    			}
	    		},
	    		error: function(){
	    			swal("오류","잘못된 요청입니다.");
	    		}
	    	}); 
		}
	}
	
	function keyCheck(){
		$.ajax({ 
    		url: "./emailCheck.do", 
    		type: "post", 
    		data: "emKey="+$("#emKey").val(),
    		success: function(msg) { 
    			if (msg == "true") { 
    				swal("Email인증","인증 성공"); 
    				$("#useEmail").css("display","");
    			} else { 
    				swal("Email인증 오류","인증 실패"); 
    				$("#useEmail").css("display","none");
    			} 
    		},
    		error: function(){
    			swal("잘못된 요청입니다.");
    		}
    	}); 
	}
	
	$(document).ready(function(){
   	 	$("#useEmail").click(function(){
			var val = document.getElementById("email").value;
			opener.document.getElementById("email").value = val;
			self.close();	
		});
    });
</script>
<body>
<div id="container"> 
	<form action="">
		<div id="chkEmail">
			<div class="form-group">
			<hr>
				<input type="text" name="email" id="email" placeholder="이메일 입력 ( xxxxx@xxx.xxx )" /> 
				<button type="button" class="btn btn-basic" onclick="sendEmail();" id="sendE"  >전송</button>
			</div>
			<div class="form-group">
				<input type="text" name="emKey" id="emKey" placeholder="인증 키 입력" /> 
				<button type="button" class="btn btn-basic" id="chkE" onclick="keyCheck();" >인증</button>
			</div>
			<div class="form-group" style="text-align: center;">
				<button type="button" class="btn btn-basic" id=useEmail  style="display: none;" >인증완료</button>
			<hr>
			</div>
		</div>
	</form>
</div>
</body>
</html>