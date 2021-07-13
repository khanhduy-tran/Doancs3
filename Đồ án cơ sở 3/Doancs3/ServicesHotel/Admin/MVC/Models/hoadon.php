<?php
require_once("model.php");
class Hoadon extends Model
{
    var $table = "dondatphong";
    var $contens = "id";
    function trangthai($id){
        $query = "select * from dondatphong where trangthai = '$id' and huydon = 0 ORDER BY id DESC";

        require("result.php");

        return $data;
    }
    function chitiethoadon($id){
        $query = "select * from chitietdondatphong where madondatphong = '$id'";

        require("result.php");
        
        return $data;
    }
    function duyetchitiethoadon($id){
        $query = "update chitietdondatphong set trangthai = 1 where madondatphong = '$id'";
        return $this->conn->query($query);
    }
   
    function xoahoadonchitiet($id){
        $query = "delete from chitietdondatphong where madondatphong = '$id'";
        return $this->conn->query($query);
    }
}