<?php
require("dbConnect.php");

$tenkhachhang = $_POST['tenkhachhang'];

$diachi = $_POST['diachi'];

$email = $_POST['email1'];

$phone = $_POST['phone'];

$ngaydat =  $_POST['ngaydat'];

$idKhachHang = $_POST['idKhachHang'];

$maphong = $_POST['maphong'];

$newDate = date("Y-m-d",strtotime($ngaydat));

$arrayThongBao = array();

    $trangthai = 0;
    $huydon = 0;

$slqCheckDate = "select ngaydat,ngaytra from chitietdondatphong where ngaydat <= '$newDate' and ngaytra >= '$newDate' and maphong = '$maphong'";

 $data = mysqli_query($connect,$slqCheckDate);

 $row = mysqli_fetch_assoc($data);

   if($row != null){
   	$ngaydatuser = $row['ngaydat'];

   	$ngaytrauser = $row['ngaytra'];

   	$arrayThongBao['success'] = 0;
   	$arrayThongBao['message'] = "Đã có người đặt phòng này từ ngày:". $ngaydatuser . " đến ngày " . $ngaytrauser;
   }
   else{

   	$sql = "INSERT INTO dondatphong VALUES (null,'$tenkhachhang','$diachi','$email','$phone','$idKhachHang','$huydon','$trangthai')";

    $result = mysqli_query($connect,$sql);

   	if($result){

   		$idDonDatPhong = $connect->insert_id;
      $arrayThongBao['success'] = 1;
      $arrayThongBao['madonphong'] = $idDonDatPhong;
      $arrayThongBao['email'] = $email;
   		
   	}else{
   		$arrayThongBao['success'] = 0;
      $arrayThongBao['message'] = "Lỗi hệ thống";
   		
   	}
   } 
  
   echo json_encode($arrayThongBao);
 
 
 ?>