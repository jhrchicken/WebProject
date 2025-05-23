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
	function idCheck(form) {
		form.action = "../membership/idcheck.do";
		form.submit();
		return false;
	}
	function validateForm(form) {
		// 아이디를 입력하지 않은 경우
		if (!form.id.value) {
			alert("아이디를 입력하세요.");
			form.id.focus();
			return false;
		}
		// 아이디가 영문자와 숫자로만 구성되어 있는지 확인
		var regex = /^[a-zA-Z0-9]*$/;
		if (!regex.test(form.id.value)) {
			alert("아이디는 영문자와 숫자만 포함되어야 합니다.");
			form.id.focus();
			return false;
		}
		// 비밀번호를 입력하지 않은 경우
		if (!form.password.value) {
			alert("패스워드를 입력하세요.");
			form.password.focus();
			return false;
		}
		// 비밀번호가 영문자와 숫자로만 구성되어 있는지 확인
		if (!regex.test(form.password.value)) {
			alert("비밀번호는 영문자와 숫자만 포함되어야 합니다.");
			form.password.focus();
			return false;
		}
		// 비밀번호 일치 여부 확인
		if (form.password.value != form.repeat.value) {
			alert("패스워드가 일치하지 않습니다. 패스워드를 다시 입력해주세요.");
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

<body class="bg-gradient-primary">

	<div class="container">

		<!-- ======================================== Content ======================================== -->
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">회원가입</h1>
							</div>
							<form action="../membership/register.do" method="post"
								class="user" onsubmit="return validateForm(this);">
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="text" name="id" maxlength='15'
											class="form-control form-control-user" id="exampleInputEmail"
											placeholder="ID">
									</div>
									<div class="col-sm-6">
										<button onclick="return idCheck(this.form);"
											class="btn btn-primary btn-user btn-block" name="test">중복확인</button>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" name="password" maxlength='15'
											class="form-control form-control-user"
											id="exampleInputPassword" placeholder="Password">
									</div>
									<div class="col-sm-6">
										<input type="password" name="repeat" maxlength='15'
											class="form-control form-control-user"
											id="exampleRepeatPassword" placeholder="Repeat Password">
									</div>
								</div>
								<div class="form-group">
									<input type="text" name="name" maxlength='30'
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Name">
								</div>
								<div class="form-group">
									<input type="email" name="email" maxlength='30'
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Email">
								</div>
								<div class="form-group">
									<input type="text" name="tel" maxlength='15'
										class="form-control form-control-user" id="exampleInputEmail"
										placeholder="Telephone Number">
								</div>
								<input type="submit" class="btn btn-primary btn-user btn-block"
									value="회원 등록하기" />
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="../membership/forgotpassword.do">비밀번호
									찾기</a>
							</div>
							<div class="text-center">
								<a class="small" href="../membership/login.do">로그인</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="../js/sb-admin-2.min.js"></script>

</body>

</html>