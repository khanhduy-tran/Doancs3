<?php
require('dbConnect.php');

 $tenkhachkhang = $_POST['tenKH'];
 $diachi = $_POST['diachi'];
 $email = $_POST['email'];
 $phone = $_POST['phone'];
 $idKhachHang = $_POST['idKH']; 
  $trangthai = 0;
  $huydon = 0;
  $arrayThongBao = array();

   	$sqlInsertKH = "insert into dondatmonan values (null,'$tenkhachkhang','$diachi','$email','$phone','$idKhachHang','$huydon','$trangthai')";

   	$query1 = mysqli_query($connect,$sqlInsertKH);

   	if($query1){
   		$idDonDatMonAn = $connect->insert_id;
   		$arrayThongBao['success'] = 1;
   		$arrayThongBao['mahoadon'] = $idDonDatMonAn;
   		$arrayThongBao['email'] = $email;
   		
   		
   	}else{
   		$arrayThongBao['success'] = 0;   		
   	}
   	echo json_encode($arrayThongBao);
   	
 ?>