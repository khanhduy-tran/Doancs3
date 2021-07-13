<?php
require('dbConnect.php');
$idPhong = $_POST['idPhong'];
$idUser = $_POST['idUser'];
$query = "select * from phongyeuthich where idPhong = '$idPhong' and idUser = '$idUser'";
$data = mysqli_query($connect,$query);
$row = mysqli_fetch_assoc($data);
$arrayThongBao = array();
if($row != null){
	$arrayThongBao['success'] =1;
}else{
	$arrayThongBao['success'] = 0;
}
echo json_encode($arrayThongBao);
?>