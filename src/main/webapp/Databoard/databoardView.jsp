<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Web Project JHR</title>

<!-- Custom fonts for this template -->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="../vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- ============================================ Side bar ============================================ -->
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="../index.jsp">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">Web Project</div>
			</a>

			<!-- Divider --> 
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link" href="../index.jsp">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>메인 페이지</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">pages</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <c:choose>
						<c:when test="${ not empty name }">
							<span>${ name } 님</span>
						</c:when>
						<c:otherwise>
							<span>로그인 후 이용하세요</span>
						</c:otherwise>
					</c:choose>

			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Login Screens:</h6>
						<a class="collapse-item" href="../membership/login.do">로그인</a> <a
							class="collapse-item" href="../membership/register.do">회원가입</a> <a
							class="collapse-item" href="../membership/forgotpassword.do">비밀번호
							찾기</a> <a class="collapse-item" href="../membership/pass.do">프로필</a>
					</div>
				</div></li>

			<li class="nav-item"><a class="nav-link"
				href="../freeboard/list.do"> <i class="fas fa-fw fa-table"></i>
					<span>자유게시판</span></a></li>
			<li class="nav-item"><a class="nav-link"
				href="../qnaboard/list.do"> <i class="fas fa-fw fa-table"></i> <span>질문게시판</span></a>
			</li>
			<li class="nav-item"><a class="nav-link"
				href="../databoard/list.do"> <i class="fas fa-fw fa-table"></i>
					<span>자료실</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

			<!-- Sidebar Message -->
			<div class="sidebar-card d-none d-lg-flex">
				<img class="sidebar-card-illustration mb-2"
					src="../img/undraw_rocket.svg" alt="...">
				<p class="text-center mb-2">
					<strong>WebProject_JHR</strong>는 아래 링크에서 다운로드 할 수 있습니다.
				</p>
				<a class="btn btn-success btn-sm"
					href="https://github.com/jhrchicken/WebProject">다운로드</a>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- ======================================== Top bar ======================================== -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					
					<h3 class="m-0 font-weight-bold text-primary">&nbsp;자료실</h6><br/>

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<c:if test="${ not empty name }">
							<!-- Nav Item - Alerts -->
							<li class="nav-item dropdown no-arrow mx-1"><a
								class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <!-- Counter - Alerts -->
									<span class="badge badge-danger badge-counter">3+</span>
							</a></li>

							<!-- Nav Item - Messages -->
							<li class="nav-item dropdown no-arrow mx-1"><a
								class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i>
									<!-- Counter - Messages --> <span
									class="badge badge-danger badge-counter">7</span>
							</a></li>
						</c:if>

						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <c:choose>
									<c:when test="${ not empty name }">
										<span class="mr-2 d-none d-lg-inline text-gray-600 small">${ name }
											님</span>
										<img class="img-profile rounded-circle"
											src="img/undraw_profile.svg">
									</c:when>
									<c:otherwise>
										<span>로그인 후 이용하세요</span>
									</c:otherwise>
								</c:choose>
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<c:choose>
									<c:when test="${ not empty name }">
										<a class="dropdown-item" href="../membership/pass.do"> <i
											class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 프로필
										</a>
										<div class="dropdown-divider"></div>
										<a class="dropdown-item"
											onclick="location.href='../membership/logout.do'"> <i
											class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
											로그아웃
										</a>
									</c:when>
									<c:otherwise>
										<a class="dropdown-item" href="../membership/login.do"> <i
											class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>로그인
										</a>
									</c:otherwise>
								</c:choose>
							</div></li>
					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- ======================================== Content ======================================== -->
				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<p class="mb-4">자료실은 파일을 반드시 첨부해야 하는 게시판입니다. 필요한 자료를 다운로드 해보세요.</p><br/>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">${ dto.title }</h6>
						</div>
						<div class="card-body">
							<div class="table-responsive">

								<table border="1" width="100%" class="table table-bordered">
									<colgroup>
										<col width="15%" />
										<col width="35%" />
										<col width="15%" />
										<col width="*" />
									</colgroup>
									<tr>
										<td>번호</td>
										<td>${ dto.num }</td>
										<td>작성자</td>
										<td>${ dto.id }</td>
									</tr>
									<tr>
										<td>작성일</td>
										<td>${ dto.postdate }</td>
										<td>조회수</td>
										<td>${ dto.visitcount }</td>
									</tr>
									<tr>
										<td>제목</td>
										<td colspan="3">${ dto.title }</td>
									</tr>
									<tr>
										<td>내용</td>
										<td colspan="3" height="100">${ dto.content }</td>
									</tr>
									<!-- 다운로드 처리 -->
										<tr>
											<td>첨부파일</td>
											<td><c:choose>
													<c:when
														test='${ ext == ".jpg" or ext == ".gif" or ext == ".jpg" }'>
														<img src="../Uploads/${ dto.sfile }"
															style="max-width: 300px" />
													</c:when>
													<c:when test='${ ext == ".mp3" or ext == ".wav" }'>
														<audio src="../Uploads/${ dto.sfile }" controls></audio>
													</c:when>
													<c:when
														test='${ ext == ".mp4" or ext == ".avi" or ext == ".wmv" }'>
														<video controls width="300px">
															<source src="../Uploads/${ dto.sfile } type=" video/webm" />
														</video>
													</c:when>
												</c:choose> <br>
												<a
														href="../databoard/download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&num=${ dto.num }">[Download]</a>
											</td>
											<td>다운로드수</td>
											<td>${ dto.downcount }</td>
										</tr>
									<tr>
										<td colspan="4" align="center"><c:if
												test="${ dto.id == id }">
												<button type="button"
													onclick="location.href='../databoard/edit.do?num=${ param.num }';">
													수정하기</button>
												<button type="button"
													onclick="location.href='../databoard/delete.do?num=${ param.num }';">
													삭제하기</button>
											</c:if>
											<button type="button"
												onclick="location.href='../databoard/list.do';">목록 바로가기</button>
										</td>
									</tr>
								</table>

							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- =========================================== javascript =========================================== -->
	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="../vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="../js/demo/datatables-demo.js"></script>

</body>

</html>