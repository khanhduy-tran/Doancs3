<?php
require('dbConnect.php');
$matkhaumoi = $_POST['matkhaumoi'];
$idUser = $_POST['idUser'];
$query = "update user set password = '$matkhaumoi' where id = '$idUser'";
$data = mysqli_query($connect,$query);
$arrayThongBao = array();
if($data){
	$arrayThongBao['success'] = 1;
	$arrayThongBao['message'] = "Đổi mật khẩu thành công!";
 }else{
 	$arrayThongBao['success'] = 0;
	$arrayThongBao['message'] = "Đổi mật khẩu thất bại!";
 }
 echo json_encode($arrayThongBao);
 ?>
