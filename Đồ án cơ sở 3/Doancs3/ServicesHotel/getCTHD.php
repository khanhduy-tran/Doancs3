<?php
require('dbConnect.php');
$maphong = $_POST['maphong'];

$sqlCheck1 = "select * from dondatphong where id = '$maphong'";

 $query1 = mysqli_query($connect,$sqlCheck1);

$row1 = mysqli_fetch_assoc($query1);

$sqlCheck2 = "select * from chitietdondatphong where madondatphong = '$maphong'";

$query2 = mysqli_query($connect,$sqlCheck2);

$row2 = mysqli_fetch_assoc($query2);

$arrayCTDP = array();
$arrayCTDP['tenkhachhang'] = $row1['tenkhachhang'];
$arrayCTDP['diachi'] = $row1['diachi'];
$arrayCTDP['phone'] = $row1['phone'];

$arrayCTDP['tenphong'] = $row2['tenphong'];
$arrayCTDP['giaphong'] = $row2['giaphong'];
$arrayCTDP['ngaydat'] = $row2['ngaydat'];
$arrayCTDP['ngaytra'] = $row2['ngaytra'];
$arrayCTDP['trangthai']  = $row2['trangthai'];
echo json_encode($arrayCTDP);
 ?>