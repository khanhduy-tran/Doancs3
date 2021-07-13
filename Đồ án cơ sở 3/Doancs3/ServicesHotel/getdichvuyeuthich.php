<?php
require('dbConnect.php');
$arrayMonAn = array();
$idUser = $_POST['idUser'];
$query = "select r.* from dichvudoan r LEFT JOIN dichvuyeuthich b ON b.idMonAn = r.id where b.idUser = '$idUser'";
$data = mysqli_query($connect,$query);
while($row = mysqli_fetch_assoc($data)){
	array_push($arrayMonAn, new MonAn($row['id'], 
	$row['tenmonan'], 
	$row['giamonan'], 
	$row['hinhanh'], 
	$row['hinhanh1'], 
	$row['hinhanh2'], 
	$row['hinhanh3'], 
	$row['mota'], 
	$row['trangthai']));
}
echo json_encode($arrayMonAn);
class MonAn{
 	function MonAn($id,$tenmonan,$giamonan,$hinhanh,$hinhanh1,$hinhanh2,$hinhanh3,$mota,$trangthai){
 		$this->id = $id;
 		$this->tenmonan = $tenmonan;
 		$this->giamonan = $giamonan;
 		$this->hinhanh = $hinhanh;
 		$this->hinhanh1 = $hinhanh1;
 		$this->hinhanh2 = $hinhanh2;
 		$this->hinhanh3 =  $hinhanh3;
 		$this->mota = $mota;
 		$this->trangthai = $trangthai;
 	}
 }
?>