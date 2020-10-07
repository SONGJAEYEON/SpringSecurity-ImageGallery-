<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<style>
body {font-family: Arial, Helvetica, sans-serif;
background-color: #a9a9a9;}
* {
  box-sizing: border-box;
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
/* body { */
/*   background-color: #f1f1f1; */
/*   font-family: Arial; */
/* } */

/* Center website */
.main {
  max-width: 1000px;
  margin: auto;
}

h1 {
  font-size: 50px;
  word-break: break-all;
}

.row {
  margin: 10px -16px;
  border: 5px solid gray;
   border-radius: 10px;
}

/* Add padding BETWEEN each column */
.row,
.row > .column {
  padding: 0px;
  border-radius: 10px;
}

/* Create three equal columns that floats next to each other */
.column {
  float: left;
  width: 33.33%;
/*   display: none; /* Hide all elements by default */ */
}

/* Clear floats after rows */ 
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Content */
.content {
  background-color: white;
  padding: 10px;
  border: 5px solid gray;
  border-radius: 10px;
}

/* The "show" class is added to the filtered elements */
.show {
  display: block;
}

/* Style the buttons */
.btn {
  border: none;
  outline: none;
  padding: 12px 16px;
  background-color: white;
  cursor: pointer;
}

.btn:hover {
  background-color: #ddd;
}

.btn.active {
  background-color: #666;
  color: white;
}
</style>
<body>
<%@include file="/WEB-INF/views/nav.jsp" %>
<div class="container">
<hr>
<!-- <button type="button" onclick="imgDetail()" class="btn btn-success"> 이미지 상세정보</button> -->

<div>
<button type="button" onclick="imgUpload()" class="btn btn-success" style="float:right;"> 이미지 업로드 </button>
<h2 style="text-align: left;">상세정보는 이미지 클릭으로!</h2>
<hr>
</div>
<div class="main" id="main">
</div>
</div>

<script>
window.onload=function(){
	$.ajax({
		url:"./getAllImage.do",
		type:"post",
		dataType:"json",
		success:function(data){
			var cnt=1;
			$.each(data,function(key,value){
				if(cnt%3==1){
					var rowHtml="<div class='row'></div>"; 
					$('#main').append(rowHtml);
					var colHtml="";
					colHtml+="<div class='column'> "       ;
					colHtml+="<div class='content'> "       ;
				    colHtml+="  <img src='./resources/upload/"+value.stored_name+"' alt='"+value.image_name+"' style='width:100%' onclick='imgDetail("+value.image_seq+")'/>"       ;
				    colHtml+="</div>"                                                                                        ;
				    colHtml+="</div>"                                                                                        ;
				    $('.row').last().append(colHtml);
					cnt++;
				}
				else{
					var colHtml="";
					colHtml+="<div class='column'> "       ;
					colHtml+="<div class='content'> "       ;
					colHtml+="  <img src='./resources/upload/"+value.stored_name+"' alt='"+value.image_name+"' style='width:100%' onclick='imgDetail("+value.image_seq+")'/>"       ;
				    colHtml+="</div>"                                                                                        ;
				    colHtml+="</div>"                                                                                         ;
				    $('.row').last().append(colHtml);
					cnt++;
				}
			})
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
		
	});
};
function imgUpload(){
	var pop = window.open("./imgUploadForm.do", "_blank", "toolbar=yes,scrollbars=yes,resizable=no,top=200,left=700,width=450,height=320");
}
function imgDetail(seq){
// 	alert(seq);
	var pop = window.open("./imgDetail.do?seq="+seq, "_blank", "toolbar=yes,scrollbars=yes,resizable=no,top=200,left=700,width=450,height=320");
}


</script>
</body>
</html>