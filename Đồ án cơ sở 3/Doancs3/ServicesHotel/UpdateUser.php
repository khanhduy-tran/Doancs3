<?php
require('dbConnect.php');
$id = $_POST['id'];
$ten = $_POST['ten'];
$ngaysinh = $_POST['ngaysinh'];
$diachi = $_POST['diachi'];
$email = $_POST['email'];
$dienthoai = $_POST['dienthoai'];
$arrayThongBao = array();
$query = "update user set name = '$ten',birthday = STR_TO_DATE('$ngaysinh','%d-%m-%Y'),address = '$diachi',email = '$email',phone = '$dienthoai' where id = '$id'";
$data = mysqli_query($connect,$query);

if($data){
	$arrayThongBao['success'] =1;
	$arrayThongBao['message'] = "Cập nhật thông tin cá nhân thành công!";
}else{
	$arrayThongBao['success'] =0;
	$arrayThongBao['message'] = "Lỗi hệ thống";
} 
echo json_encode($arrayThongBao);
 ?>