<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/css/slick.css" />
<link rel="stylesheet" href="${path}/css/slick-theme.css" />
<link rel="stylesheet" href="${path}/css/main.css" />
<link rel="stylesheet" href="${path}/css/main2.css" />
<link href="${path}/css/map.css" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

</head>

<body>
	<%@ include file="./header.jsp"%>


<div class="mainTxtContent">
  <div class="main__container">
    <p class="main__container__text">
      내 주변
    </p>
    <p class="main__container__text text_last">을 만나보세요</p>
    <ul class="main__container__list">
      <li class="main__container__list__item">#테니스장</li>
      <li class="main__container__list__item">#축구장</li>
      <li class="main__container__list__item">#운동장</li>
      <li class="main__container__list__item">#수영장</li>
    </ul>
  </div>
</div>
	<div class="picsWrapper">
	<div class="options">
		<div class="option"
			style="background: url(${path}/image/mainGym.jpg); background-size: cover;">
			<div class="shadow"></div>
			<div class="label">
				<div class="icon">
				<i class="fas fa-solid fa-dumbbell"></i>
				</div>
				<div class="info">
					<div class="main">북부스포츠센터 헬스장</div>
					<div class="sub">충남 천안시 서북구 성환읍 성환1로 45</div>
				</div>
			</div>
		</div>
		<div class="option"
			style="background: url(${path}/image/facilities/EE25K0036224.jpg); background-size: cover;">
			<div class="shadow"></div>
			<div class="label">
				<div class="icon">
					<i class="fas fa-regular fa-futbol"></i>
				</div>
				<div class="info">
					<div class="main">북부스포츠센터 축구장</div>
					<div class="sub">충남 천안시 서북구 성환읍 성환1로 45</div>
				</div>
			</div>
		</div>
		<div class="option active"
			style="background: url(${path}/image/mainPool.jpg); background-size: cover;">
			<div class="shadow"></div>
			<div class="label">
				<div class="icon">
					<i class="fas fa-solid fa-swimmer"></i>
				</div>
				<div class="info">
					<div class="main">한들문화센터 수영장</div>
					<div class="sub">충남 천안시 서북구 음봉로 861-50 (백석동)</div>
				</div>
			</div>
		</div>
		<div class="option"
			style="background: url(${path}/image/facilities/EE25M4616164.jpg); background-size: cover;">
			<div class="shadow"></div>
			<div class="label">
				<div class="icon">
					<i class="fas fa-walking"></i>
				</div>
				<div class="info">
					<div class="main">충청연수원 실내체육관</div>
					<div class="sub">충남 천안시 서북구 직산읍 남산2길 41</div>
				</div>
			</div>
		</div>
		<div class="option"
			style="background: url(${path}/image/facilities/CC26N1922175.jpg); background-size: cover;">
			<div class="shadow"></div>
			<div class="label">
				<div class="icon">
					<i class="fa-solid fa-baseball"></i>
				</div>
				<div class="info">
					<div class="main">천안야구장</div>
					<div class="sub">충남 천안시 동남구 천안대로 320 (삼룡동)</div>
				</div>
			</div>
		</div>
		<div class="option"
			style="background: url(${path}/image/facilities/BA10A0015174.jpg); background-size: cover;">
			<div class="shadow"></div>
			<div class="label">
				<div class="icon">
					<i class="fas fa-solid fa-flag-checkered"></i>
				</div>
				<div class="info">
					<div class="main">한국도로공사 천안지사 사옥 후면(테니스장)</div>
					<div class="sub">충청남도 천안시 동남구 망향로 14</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<div style="display: flex; justify-content: space-between; margin: 10px 60px;">
		<div style="width: 46%;">
		<div style="position: relative;">
			<h5 style="display: inline-block; line-height: 40px;">내 주변 체육시설 보기</h5>
			<a href="${path}/map" style="position: absolute; right: 0; line-height: 36px;">바로가기 ></a>
		</div>
			<div id="map" style="width: 100%; height: 270px;"></div>
		</div>
		
		 <!-- board list area -->
		<div id="board-list" style="width: 46%; padding-left: 10px;">
		<div style="position: relative;">
			<h5 style="display: inline-block; line-height: 40px;">커뮤니티 게시판</h5>
			<a href="${path}/boardlist?pageNo=1" style="position: absolute; right: 0; line-height: 36px;">바로가기 ></a>
		</div>
			<ul style="position: relative; padding-left: 0; margin-bottom: 0px;">
				<li style="display: inline-block; width: 50%;"><button type="button" class="viewBtn current <c:if test="${fn:length(cntBoardList) == 0}">notMove</c:if>">조회순</button></li><li style="display: inline-block; width: 50%;"><button type="button" class="recentBtn <c:if test="${fn:length(cntBoardList) == 0}">notMove</c:if>">최신순</button></li>
			</ul>
			<div style="width: 100%; overflow: hidden; height: 240px; padding: 0;" class="container">
				<table class="board-table">
					<thead style="background: white; position: relative; z-index: 10;">
						<tr>
							<th scope="col" class="th-num">제목</th>
							<th scope="col" class="th-title">작성자</th>
							<th scope="col" class="th-date">작성일</th>
						</tr>
					</thead>
					<tbody class="table-body">
						<c:if test="${fn:length(cntBoardList) == 0}">
							<tr>
								<td colspan="3">게시글이 등록되지 않았습니다</td>
							</tr>
						</c:if>
						<c:choose>
							<c:when test="${fn:length(cntBoardList) > 4}">
								<c:set var="totalLength" value="4" />
							</c:when>
							<c:otherwise>
								<c:set var="totalLength" value="${fn:length(cntBoardList)}" />
							</c:otherwise>
						</c:choose>
						<c:forEach var="board" items="${cntBoardList}" begin="0" end="${totalLength}"
							step="1">
							<tr style="height: 44.73px;">
								<td><a href="${path}/detail?postno=${board.postno}" style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; width: 100px;">
										${board.title} </a></td>
								<td>${board.writer}</td>
								<td>${board.updatedate}</td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(cntBoardList) < 4}">
							<c:forEach var="empty" begin="1" end="${4-fn:length(cntBoardList)}" step="1">
							<tr>
								<td style="height: 44.73px;"></td>
								<td style="height: 44.73px;"></td>
								<td style="height: 44.73px;"></td>
							</tr>
							</c:forEach>
						</c:if>
						<c:forEach var="board" items="${latestBoardList}" begin="0"
							end="${totalLength}" step="1">
							<tr>
								<td><a href="${path}/detail?postno=${board.postno}" style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; width: 100px;">
										${board.title}</a></td>
								<td>${board.writer}</td>
								<td>${board.updatedate}</td>
							</tr>
						</c:forEach>
						<c:if test="${fn:length(latestBoardList) < 4}">
							<c:forEach var="empty" begin="1" end="${4-fn:length(latestBoardList)}" step="1">
							<tr>
								<td style="height: 44.73px;"></td>
								<td style="height: 44.73px;"></td>
								<td style="height: 44.73px;"></td>
							</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${apiKey}&libraries=services,clusterer,drawing"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
	<script type="text/javascript" src="${path}/js/main.js"></script>
	<script type="text/javascript">
					var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
					var originalList = '${parsedList}';
					var path = '${path}';
					var parsedList = JSON.parse(originalList);
					var markers = [];
					var options = { //지도를 생성할 때 필요한 기본 옵션
							center: new kakao.maps.LatLng(36.810006318863174, 127.14461070042337), //지도의 중심좌표.
							//지도의 레벨(확대, 축소 정도)
							level: 6
						};
					

					var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
					map.setZoomable(false);

					// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
					var mapTypeControl = new kakao.maps.MapTypeControl();

					// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
					// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
					map.addControl(mapTypeControl, kakao.maps.ControlPosition.BOTTOMLEFT);


					var clusterer = new kakao.maps.MarkerClusterer({
						map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
						averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
						minLevel: 9, // 클러스터 할 최소 지도 레벨 
					});

					for (let i = 0; i < parsedList.length; i++) {
						displayMarker(parsedList[i], path);
					}	
				</script>
<%@ include file="./footer.jsp"%>