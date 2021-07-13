<?php
require('dbConnect.php');
$query = "select * from slide";
$data = mysqli_query($connect,$query);
$arraySlide = array();
while($row = mysqli_fetch_assoc($data)){
	array_push($arraySlide, new Slide($row['id'],$row['anhslide']));
}
echo json_encode($arraySlide);

 class Slide{
 	function Slide($id,$anhslide){
 		$this->id = $id;
 		$this->anhslide = $anhslide;
 	} 

 } 
 ?>