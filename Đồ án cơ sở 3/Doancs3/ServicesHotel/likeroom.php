<?php
require("dbConnect.php");
$arrayThongBao  = array();
$idUser = $_POST['idUser'];
$idPhong = $_POST['idPhong'];
$query = "insert into phongyeuthich values(null,'$idUser','$idPhong')";
$data = mysqli_query($connect,$query);
if($data){
	$arrayThongBao['success'] = 1;
	$arrayThongBao['message'] = "Đã thêm phòng vào danh mục yêu thích của bạn!";
}else{
	$arrayThongBao['success'] = 0;
	$arrayThongBao['message'] = "Lỗi hệ thống!";
}
echo json_encode($arrayThongBao);
?>