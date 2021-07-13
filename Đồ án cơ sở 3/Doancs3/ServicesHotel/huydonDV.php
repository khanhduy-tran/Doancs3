<?php
require('dbConnect.php');
$maphong = $_POST['mamonan'];
 $huydon = 1;
  $sqlUpdate = "update dondatmonan set huydon = '$huydon' where id = '$maphong'"; 
  $arrayThongBao = array();
  
  if(mysqli_query($connect,$sqlUpdate)){
  	$arrayThongBao['success'] = 1;
  	$arrayThongBao['message'] = "Bạn đã hủy đơn đặt phòng thành công!";
  }else{
  	$arrayThongBao['success'] = 0;
  	$arrayThongBao['message'] = "Lỗi hệ thống,vui lòng thực hiện lại";
  }
  echo json_encode($arrayThongBao);
 ?>