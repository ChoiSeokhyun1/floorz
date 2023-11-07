<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
			<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
			<% response.setHeader("Cache-Control","no-store"); response.setHeader("Pragma","no-cache");
				response.setDateHeader("Expires",0); if(request.getProtocol().equals("HTTP/1.1"))
				response.setHeader("Cache-Control","no-cache"); %>
				<!DOCTYPE html>
				<html>

				<head>
					<meta charset="UTF-8">



					<title>Insert title here</title>


					<!-- Bootstrap -->
					<link href="${path}/css/bootstrap.css" rel="stylesheet" type="text/css" />
					<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
					<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
					<style>
						#postno,
						#title {
							border: none;
						}
					</style>



				</head>

				<body>
				<%@ include file="../header.jsp"%>
					<div class="container" style="font-size: 16px;">
						<hr />
						<div class="row">
							<div class="col-md-12">
								<form method="POST" name="opinionForm" id="opinionForm">
									<button type="button" class="likesBtn" <c:if test="${sessionScope.loginId eq null || sessionScope.loginId eq ''}">disabled</c:if>>추천하기</button>
									<button type="button" class="reportBtn" <c:if test="${sessionScope.loginId eq null || sessionScope.loginId eq ''}">disabled</c:if>>신고하기</button>
								</form>

								<form id="updateForm" method="post">
									<table class="table table-condensed">
										<thead>
											<tr>
												<th width="10%">제목 - <input id="title" name="title" value="${viewPage.title}" readOnly/></th>
                               					<th width="50%">글번호 :<input id="postno" type="text"  name="postno" value=" ${viewPage.postno}" readOnly/></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>작성일
												</td>
												<td>
													<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
														value="${viewPage.changedate}" /> / 수정일
													<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
														value="${viewPage.updatedate}" />
													<span style="float:right">조회수 : ${viewPage.viewcnt} </span>
												</td>
											</tr>
											<tr>
												<td>작성자
												</td>
												<td>
													<label for="userid">${viewPage.writer}</label>
													 <span style='float:right'>추천수 : ${viewPage.recommend} </span>
                                   					 <span style='float:right; margin-right: 10px;'>신고수 : ${viewPage.report}</span>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<p>${viewPage.content}</p>
												</td>
											</tr>
										</tbody>
									</table>


									<table class="table table-condensed">
										<tr>
											<td>
												<span class="form-inline" role="form">
													<button type="button" id="list_btn"
														class="btn btn-default">목록</button>
													<button type="button" id="update_btn"
														class="btn btn-default" <c:if test="${viewPage.writer ne sessionScope.loginId}">disabled</c:if>>수정</button>
													<button type="button" id="delete_btn"
														class="btn btn-default" <c:if test="${viewPage.writer ne sessionScope.loginId}">disabled</c:if>>삭제</button>
												</span>
											</td>
										</tr>
									</table>
								</form>
								<div class="rowtable">
									<table class="table" id="article-table">
										<thead>
											<tr>
												<th class="created-at"><a>댓글번호 </a></th>
												<th class="user-id"><a>작성자</a></th>
												<th class="user-id"><a>내용</a></th>
												<th class="created-at"><a>작성일</a></th>
												<th class="created-at"><a>수정일</a></th>
												<th class="created-at"><a>추천수</a></th>
												<th class="created-at"><a>신고수</a></th>
											</tr>
										</thead>

										<tbody>

											<c:forEach items="${list}" var="comment">
												<tr>
													<td>
														<c:out value="${comment.replynumber}" />
													</td>
													<td>
														<c:out value="${comment.replyUserID}" />
													</td>
													<td>
														<c:out value="${comment.replytext}" />
													</td>
													<td>
														<fmt:formatDate pattern="yyyy/MM/dd"
															value="${comment.updatedate}" />
													<td>
														<fmt:formatDate pattern="yyyy/MM/dd"
															value="${comment.changedate}" />

													<td>
														<c:out value="${comment.recommend}" />
													</td>
													<td>
														<c:out value="${comment.report}" />
													</td>
												</tr>
											</c:forEach>


										</tbody>
									</table>

								</div>
								<p>
								<div class="form-group" style="font-size: 16px;">
									<input type="text" id="commentParentName" name="commentParentName"
										class="form-control col-lg-2" data-rule-required="true" placeholder="작성자 : "
										maxlength="10" value="아이디 : ${sessionScope.loginId} 님" readonly>
								</div>
								</p>
								<form method="POST" action="${path}/reply?postno=${viewPage.postno}">
									<textarea id="replytext" name="replytext" class="form-control col-lg-12"
										style="width:100%" rows="4"></textarea>

									</span>
									</td>
									</tr>
									</table>
									<table class="table table-condensed" >
										<thead>
											<tr>
												<td>
													<span style='float:right'>

														<button type="submit" class="btn btn-default" <c:if test="${sessionScope.loginId eq null ||  sessionScope.loginId eq ''}">disabled</c:if>>댓글쓰기</button>
														<button type="button" id="chochun" class="btn btn-default">댓글 추천
														</button>
														<button type="button" id="singo" class="btn btn-default">댓글
															신고</button>
								</form>
								</span>
								</td>
								</tr>
								</thead>
								</table>

								<script>

								</script>
							</div>
						</div>
						<hr />
					</div>


					<script type="text/javascript" src="${path}/js/manageInfo.js"></script>
					<script>

						var path = '${path}';
						var postNumber = '${viewPage.postno}';
						let form = $("#infoForm");		// 페이지 이동 form(리스트 페이지 이동, 조회 페이지 이동)
						let mForm = $("#updateForm");	// 페이지 데이터 수정 from


						$("#list_btn").on("click", function (e) {
							var chk = confirm("목록 페이지로 가시겠습니까?");
							if (chk) {
								location.href = path + 'boardlist?pageNo=1';
							}
						});


						$("#update_btn").on("click", function (e) {
							mForm.attr("action", "/update");
							mForm.attr("method", "post");
							var chk = confirm("수정 페이지로 가시겠습니까?");
							if (chk) {
								mForm.submit();
							}
						});


						$("#delete_btn").on("click", function (e) {
							var form = $("form"); // 
							form.attr("action", "/delete?postno=${viewPage.postno}");
							form.attr("method", "post");
							var chk = confirm("삭제 진짜 페이지로 가시겠습니까?");
							if (chk) {
								form.submit();
							}
						});


						$(".likesBtn").on("click", function () {
							fn_opinionChk("1", "추천")
						});

						$(".reportBtn").on("click", function () {
							fn_opinionChk("2", "신고")
						});



					</script>
				</body>

				</html>