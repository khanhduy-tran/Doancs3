<?php
require('dbConnect.php');
$select = "select tenphong from phong where trangthai = 0";
$data = mysqli_query($connect,$select);
while($row = mysqli_fetch_assoc($data)){
	$output[] = $row;
} 
echo json_encode($output);
 ?>