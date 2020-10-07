<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<style type="text/css">
::-webkit-scrollbar{width: 7px;}
::-webkit-scrollbar-track {background-color:white;}
::-webkit-scrollbar-thumb {background-color:gray;border-radius: 50px;}
::-webkit-scrollbar-thumb:hover {background: black;}
::-webkit-scrollbar-button:start:decrement,::-webkit-scrollbar-button:end:increment {
width:1px;height:1px;background: white;}
</style>


<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;
background-color: #a9a9a9;}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
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
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
    border: 5px solid gray;
  border-radius: 10px;
  width:500px;
  height:300px;
  margin : auto;
  margin-top:6%;
  background-color: white;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s
}

@-webkit-keyframes animatezoom {
  from {-webkit-transform: scale(0)} 
  to {-webkit-transform: scale(1)}
}
  
@keyframes animatezoom {
  from {transform: scale(0)} 
  to {transform: scale(1)}
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}
</style>
</head>
<body>


<div id="id01" >
  <form   action="./login.do" method="POST" onsubmit="return valChk();">
    <div class="container">
      <label for="uname"><b>아이디</b></label>
      <input  name="id" id="id" type="text" placeholder="아이디를 입력해 주세요" required>

      <label for="psw"><b>비밀번호</b></label>
      <input name="pw" id="pw" type="password" placeholder="비밀번호를 입력해 주세요" required>
        <div style="height:16px; text-align: left; margin-left: 20px; margin-top: 10px;">
		<label id="msg" style="color: red; font-size: 13px;"></label>		
		<label id="err" style="color: red; font-size: 13px;">${ERRORMSG}</label>
     </div>
      <button type="submit">로그인</button>
      <button onClick="location.href='./signUpForm.do'">회원가입</button>
    </div>
  </form>
</div>

</body>
</html>