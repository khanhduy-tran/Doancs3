<?php
require('dbConnect.php');
$mamonan = $_POST['mamonan'];

$sqlCheck1 = "select * from dondatmonan where id = '$mamonan'";

 $query1 = mysqli_query($connect,$sqlCheck1);

$row1 = mysqli_fetch_assoc($query1);

$sqlCheck2 = "select * from chitietdondatmonan where madondatmonan = '$mamonan'";

$query2 = mysqli_query($connect,$sqlCheck2);

$row2 = mysqli_fetch_assoc($query2);

$arrayCTDP = array();
$arrayCTDP['tenkhachhang'] = $row1['tenkhachhang'];
$arrayCTDP['diachi'] = $row1['diachi'];
$arrayCTDP['phone'] = $row1['phone'];

$arrayCTDP['tenmonan'] = $row2['tenmonan'];
$arrayCTDP['soluong'] = $row2['soluong'];
$arrayCTDP['giamonan'] = $row2['giamonan'];
$arrayCTDP['phongnhan'] = $row2['phongnhan'];
$arrayCTDP['ngaynhan']  = $row2['ngaynhan'];
$arrayCTDP['thoigiannhan']  = $row2['thoigiannhan'];
$arrayCTDP['trangthai']  = $row2['trangthai'];

echo json_encode($arrayCTDP);
 ?>