<?php
	require("dbConnect.php");

	$fullname = $_POST['fullname'];

	$birthday = $_POST['birthday'];

	$address = $_POST['address'];

	$email = $_POST['email'];

	$phonenumber = $_POST['phonenumber'];

	$user = $_POST['user'];

	$password = $_POST['password'];

	$response = array();

	$sqlCheck = "select email,phone,taikhoan from user";

	$result = mysqli_query($connect,$sqlCheck);

	while($rows = mysqli_fetch_assoc($result)){

		if($rows['email'] == $email){
			$response['success'] = 0;
			$response['message'] = "Địa chỉ email đã có người sử dụng";
		}
		elseif($rows['phone'] == $phonenumber){
			$response['success'] = 0;
			$response['message'] = "Số điện thoại đã có người sử dụng";
		}
		elseif($rows['taikhoan'] == $user){
			$response['success'] = 0;
			$response['message'] = "Tên tài khoản đã có người sử dụng";
		}

	else{

		$sqlInsert = "INSERT INTO user(name,birthday,address,email,phone,taikhoan,password) VALUES ('$fullname',STR_TO_DATE('$birthday','%d-%m-%Y'),'$address','$email','$phonenumber','$user','$password')";

		$resultInsert = mysqli_query($connect,$sqlInsert);

		if($resultInsert){

			$response['success'] = 1;
			$response['message'] = "Đăng ký tài khoản thành công";
			
		}else{
			$response['success'] = 0;
			$response['message'] = "Lỗi hệ thống";
		}
	}
}
	echo json_encode($response);

 ?>