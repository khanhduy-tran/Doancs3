<?php
require('dbConnect.php');
$idMonAn = $_POST['idMonAn'];
$idUser = $_POST['idUser'];
$query = "select * from dichvuyeuthich where idMonAn = '$idMonAn' and idUser = '$idUser'";
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