<?php
require("dbConnect.php");
$arrayThongBao  = array();
$idUser = $_POST['idUser'];
$idMonAn = $_POST['idMonAn'];
$query = "insert into dichvuyeuthich values(null,'$idUser','$idMonAn')";
$data = mysqli_query($connect,$query);
if($data){
	$arrayThongBao['success'] = 1;
	$arrayThongBao['message'] = "Đã thêm món ăn vào danh mục yêu thích của bạn!";
}else{
	$arrayThongBao['success'] = 0;
	$arrayThongBao['message'] = "Lỗi hệ thống!";
}
echo json_encode($arrayThongBao);
?>