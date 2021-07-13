<?php
require('dbConnect.php');
$idUser = $_POST['idUser'];
$sqlCheck = "select id,tenkhachhang,email from dondatphong where idKhachHang = '$idUser' and huydon = 0";
$arrayDonPhong = array();
$data = mysqli_query($connect,$sqlCheck);

 while($row = mysqli_fetch_assoc($data)){
 	array_push($arrayDonPhong,new DonPhong($row['id'],$row['tenkhachhang'],$row['email']));
 }
 echo json_encode($arrayDonPhong);
class DonPhong{
	function DonPhong($madonphong,$tenkhachhang,$emailkhachhang){
		$this->madonphong = $madonphong;
		$this->tenkhachhang = $tenkhachhang;
		$this->emailkhachhang = $emailkhachhang;
	}
} 
 ?>