<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<%
response.setHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
if (request.getProtocol().equals("HTTP/1.1"))
	response.setHeader("Cache-Control", "no-cache");
%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<title>Insert title here</title>
<script>
	CHEDITOR.create(document.querySelector('#editor'), {
		language : "ko"
	});
	window.onload = function() {
		ck = CKEDITOR.replace("editor");
	};
</script>
<script type="text/javascript" src="${path}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${path}/ckeditor/config.js"></script>
<link href="${path}/css/write.css" rel="stylesheet" type="text/css" />

<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
	crossorigin="anonymous"></script>
<!-- 부트스트랩-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>

<body>


<%@ include file="../header.jsp"%>

	<div class="container">
		<form action="${path}/write" class="outerForm" method="post">
			<c:if test="${sessionScope.loginId == null}">
				<script>
					alert("로그인 후 사용 가능합니다.");
					location.href = "${path}/login";
				</script>
			</c:if>
				<div class="wrap">
					<form name="form1" class="newBoardForm" method="post">
						<div class="container">
							<div id="main">
								<div id="notice_box">
									<strong class="hilite">시민의 소리에 귀 귀울이는 3조가 되겠습니다.</strong><br />
									<br /> 게시판 운영 중 아래의 경우 임의삭제 될 수 있음을 알려드립니다.<br /> - 광고성 글, 단체
									또는 개인을 비방⋅음해하는 게시글<br /> - 개인정보(주민등록번호 등)노출 게시글<br /> - 저속한
									표현, 반복게시물, 답변이 불필요한 내용 등의 게시글<br />
								</div>

								<c:if test="${sessionScope.loginId != null}">
									<div class="row">
										<div class="col">
											<input type="text" class="form-control" readonly="readonly"
												value="환영합니다.ID : ${sessionScope.loginId} 님."
												aria-label="First name"
												style="width: 300px; margin-bottom: 20px;">
										</div>
									</div>
								</c:if>
								<select class="form-select" aria-label="Default select example">
									<option selected>카테고리</option>
									<option value="1">축구장</option>
									<option value="2">수영장</option>
									<option value="3">테니스장</option>
									<option value="4">볼링장</option>
									<option value="5">풋살장</option>
								</select>

								<div class="input-group mb-3">
									<span class="input-group-text" id="inputGroup-sizing-default">제목</span>
									<input type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-default" id="title"
										placeholder="제목 입력(4-100)" name="title" maxlength="100">
								</div>

								<!--  체크에디터 폼-->
								<textarea class="form-control" id="content" name="content"></textarea>
								<script type="text/javascript">
									CKEDITOR.replace('content', {
										height : 500,
									});
									$(function() {
										CKEDITOR
												.replace(
														'content',
														{
															filebrowserUploadUrl : '${pageContext.request.contextPath}/adm/fileupload.do'
														});
									});
									config.fillEmptyBlocks = false;
								</script>
								<div class="input-group mb-3">
									<span class="input-group-text" id="inputGroup-sizing-default">해시태그</span>
									<input type="text" class="form-control"
										aria-label="Sizing example input"
										aria-describedby="inputGroup-sizing-default" id="hashtag"
										placeholder="#해시태그" name="hashtag" maxlength="100">
								</div>
								<div class="updatebtn">
									<button class="btn btn-dark" type="button" id="BtnUpdate"
										style="clear: both">게시글 작성</button>
									<button class="btn btn-dark" type="button" id="CancelDelete"
										style="clear: both">작성 취소</button>
								</div>
								<div id="area">
									<div class="areaPoll ">
										<div class="leftArea">
											<p>
												<b>현재 페이지 내 게시된 정보 문의는 아래의 담당부서에 문의주시기 바랍니다.</b>
											</p>
											<p>
												담당부서: 기획안전부 <span>041-559-3604</span>
											</p>
											<p>최종수정: 2021-03-22</p>
										</div>
										<hr />
										<input type="hidden" id="a_no" name="a_no" value="25">
										<input type="hidden" id="a_cate" name="a_cate" value="portal">
										<input type="hidden" id="etc_page" name="etc_page" value="">
										<dl class="rightArea">
											<dt>
												<b>이 페이지에서 제공하는 정보에 만족하셨습니까?</b>
											</dt>
											<dd>
												<span><input type="radio" name="point" id="point1"
													value="5" checked="checked"><label for="point1">&nbsp;매우만족</label></span>
												<span><input type="radio" name="point" id="point2"
													value="4" class="cbr-type"> <label for="point2">&nbsp;만족</label></span>
												<span><input type="radio" name="point" id="point3"
													value="3" class="cbr-type"> <label for="point3">&nbsp;보통</label></span>
												<span><input type="radio" name="point" id="point4"
													value="2" class="cbr-type"> <label for="point4">&nbsp;불만족</label></span>
												<span><input type="radio" name="point" id="point5"
													value="1" class="cbr-type"> <label for="point5">&nbsp;매우불만족</label></span>
											</dd>
											<dd>
												<br> <br>
												<div class="form-floating">
													<textarea class="form-control" placeholder="Comments"
														id="floatingTextarea"></textarea>
													<label for="floatingTextarea" placeholder="Comments"></label>
												</div>
											</dd>
										</dl>
									</div>
								</div>
								<!-- main 끝  -->
							</div>
							<!-- container 끝 -->
						</div>
					</form>
					<div id="footer">
						<h2>임시 dddfooter</h2>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script>
		$(document).ready(function() {
		const form1 = $(".newBoardForm");
		const form2 = $(".outerForm");

			$("#CancelDelete").click(function() {
				if (confirm("취소하시겠습니까??")) {
					location.href = "${path}/boardlist?pageno=1";
				}
			});
			

			$("#BtnUpdate").click(function() {

				const title = $("#title").val();
				const content = $("#content").val();
				const hashtag = $("#hashtag").val();
				
				
				if (title == "") {
					alert("제목을 입력하세요");
					form1.title.focus();
					return false;
				}
				/*if (content == "") {
					alert("내용을 입력하세요");
					form1.content.focus();
					return false;
				}*/
				
				if (hashtag == "") {
					alert("해시태그를 입력하세요");
					form1.hashtag.focus();
					return false;
				}
				
				var chk = confirm("글 등록을 하시겠습니까??");
				if (chk) {
					alert("게시글 작성이 완료되었습니다.");
					form2.attr("action", "${path}/write");
					form2.submit();
				}

			});
		});
		
	</script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.0.0/moment.min.js"></script>
</body>

</html>