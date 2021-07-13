<?php
require('dbConnect.php');
$user = $_POST['user'];
$pass = $_POST['pass'];
$query = "select * from user where taikhoan = '$user' and password = '$pass'"; 
$data = mysqli_query($connect,$query);
      $arrayUser = array();
$row = mysqli_fetch_assoc($data);

 if($row['taikhoan'] == $user && $row['password'] == $pass){
 	$arrayUser['success'] = 1;
 	$arrayUser['message'] = "Đăng nhập thành công";
 	$arrayUser['id'] = $row['id'];
 	$arrayUser['name'] = $row['name'];
 	$arrayUser['birthday'] = $row['birthday'];
 	$arrayUser['address'] = $row['address'];
 	$arrayUser['email'] = $row['email'];
 	$arrayUser['phone'] = $row['phone'];
 	$arrayUser['taikhoan'] = $row['taikhoan'];
 	$arrayUser['password'] = $row['password'];
 }else{
 		$arrayUser['success'] =0;
 	    $arrayUser['message'] = "Đăng nhập thất bại, vui lòng kiểm tra lại tài khoản và mật khẩu!";
 }
 echo json_encode($arrayUser); 
	
	
	class User{
		function User($id,$name,$birthday,$address,$email,$phone,$taikhoan,$password,$success,$message){
			$this->id = $id;
			$this->name = $name;
			$this->birthday = $birthday;
			$this->address = $address;
			$this->email = $email;
			$this->phone = $phone;
			$this->taikhoan = $taikhoan;
			$this->password = $password;
			$this->success = $success;
			$this->message = $message;
		}
		
	
	}
	 class Error1{
	  function Error1($success,$message){
			$this->success = $success;
			$this->message = $message;	
	} 
		}
	
		
 ?>
