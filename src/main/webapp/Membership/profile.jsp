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

<!-- Custom fonts for this template-->
<link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../css/sb-admin-2.min.css" rel="stylesheet">

<script>
	function validateForm(form) {
		// 패스워드를 입력하지 않은 경우
		if (!form.password.value) {
			alert("패스워드를 입력하세요.");
			form.password.focus();
			return false;
		}
		// 패스워드가 영문자와 숫자로만 구성되어 있는지 확인
		var regex = /^[a-zA-Z0-9]*$/;
		if (!regex.test(form.password.value)) {
			alert("패스워드는 영문자와 숫자만 포함되어야 합니다.");
			form.password.focus();
			return false;
		}
		// 이름을 입력하지 않은 경우
		if (!form.name.value) {
			alert("이름을 입력하세요.");
			form.name.focus();
			return false;
		}
		// 이메일을 입력하지 않은 경우
		if (!form.email.value) {
			alert("이메일을 입력하세요.");
			form.email.focus();
			return false;
		}
		// 전화번호를 입력하지 않은 경우
		if (!form.tel.value) {
			alert("전화번호를 입력하세요.");
			form.tel.focus();
			return false;
		}
	}
</script>

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
					
					<h3 class="m-0 font-weight-bold text-primary">&nbsp;프로필</h6>
					
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
											src="../img/undraw_profile.svg">
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
				<div class="container-fluid"></div>
				<!-- /.container-fluid -->

				<div class="container">

					<div class="card o-hidden border-0 shadow-lg my-5">
						<div class="card-body p-0">
							<!-- Nested Row within Card Body -->
							<div class="row">
								<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
								<div class="col-lg-7">
									<div class="p-5">
										<div class="text-center">
											<h1 class="h4 text-gray-900 mb-4">프로필 정보</h1>
										</div>
										<form action="../membership/editProfile.do" method="post"
											name="editProfileForm" class="user"
											onsubmit="return validateForm(this);">
											<div class="form-group">
												<input type="text" name="id" maxlength='15'
													class="form-control form-control-user"
													id="exampleInputEmail" value="${ id }" readonly>
											</div>
											<div class="form-group">
												<input type="text" name="password" maxlength='15'
													class="form-control form-control-user"
													id="exampleInputEmail" value="${ password }">
											</div>
											<div class="form-group">
												<input type="text" name="name" maxlength='30'
													class="form-control form-control-user"
													id="exampleInputEmail" value="${ name }">
											</div>
											<div class="form-group">
												<input type="email" name="email" maxlength='30'
													class="form-control form-control-user"
													id="exampleInputEmail" value="${ email }">
											</div>
											<div class="form-group">
												<input type="text" name="tel" maxlength='15'
													class="form-control form-control-user"
													id="exampleInputEmail" value="${ tel }">
											</div>
											<input type="submit"
												class="btn btn-primary btn-user btn-block"
												value="정보 수정" />
										</form>
										<hr>
										<form action="../membership/delete.do" method="post" class="user"
											onsubmit=true>
											<input type="hidden" name="id" value="${ id }" />
                                        	<input type="submit" class="btn btn-google btn-user btn-block" value="회원 탈퇴" />
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>

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

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">Ã</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.jsp">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- =========================================== javascript =========================================== -->
	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>

</body>

</html>