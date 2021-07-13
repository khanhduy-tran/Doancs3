<?php
require('dbConnect.php');
$arrayPhongTrong = array();
$query = "SELECT r.*
FROM phong r
LEFT JOIN chitietdondatphong b ON b.maphong = r.id
WHERE b.maphong IS NULL
OR (b.ngaytra >= NOW() AND b.ngaydat >=  NOW())
OR (b.ngaytra <=  NOW() AND b.ngaytra <=  NOW())";

$data = mysqli_query($connect,$query);
while($row = mysqli_fetch_assoc($data)){
	array_push($arrayPhongTrong, new Phong($row['id'],
										$row['tenphong'],
										$row['giaphong'],
									    $row['hinhanhphong'],
									    $row['hinhanh2'],
									    $row['hinhanh3'],
									    $row['hinhanh4'],
									    $row['giamoi'],
									    $row['giamgia'],
										$row['mota'],
										$row['idphong'],
										$row['trangthai']));
}
 echo json_encode($arrayPhongTrong);



class Phong{
    function Phong($id,$tenphong,$giaphong,$hinhanhphong,$hinh2,$hinh3,$hinh4,$giamoi,$giamgia,$mota,$idphong,$trangthai){
        $this->id = $id;
        $this->tenphong = $tenphong;
        $this->giaphong = $giaphong;
        $this->hinhanhphong = $hinhanhphong;
        $this->hinh2 = $hinh2;
        $this->hinh3= $hinh3;
        $this->hinh4 = $hinh4;
        $this->giamoi = $giamoi;
        $this->giamgia = $giamgia;
        $this->mota = $mota;
        $this->idphong = $idphong;
        $this->trangthai = $trangthai;
    }
} 
?>