<?php
require('dbConnect.php');
$query = "select * from loaiphong";
$data = mysqli_query($connect,$query);
$arrayLoaiPhong = array();
while($row = mysqli_fetch_assoc($data)){
	array_push($arrayLoaiPhong, new LoaiPhong($row['id'],$row['tenloaiphong'],$row['hinhanhloaiphong']));
}
echo json_encode($arrayLoaiPhong);

 class LoaiPhong{
 	function LoaiPhong($id,$tenloaiphong,$hinhanhloaiphong){
 		$this->id = $id;
 		$this->tenloaiphong = $tenloaiphong;
 		$this->hinhanhloaiphong = $hinhanhloaiphong;
 	} 

 } 
 ?>