<?php
require_once("model.php");
class Hoadonmonan extends Model
{
    var $table = "dondatmonan";
    var $contens = "id";
    function trangthai($id){
        $query = "select * from dondatmonan where trangthai = '$id' and huydon = 0  ORDER BY id DESC";

        require("result.php");

        return $data;
    }
    function chitiethoadon($id){
        $query = "select * from chitietdondatmonan where madondatmonan = '$id'";

        require("result.php");
        
        return $data;
    }
    function duyetchitiethoadon($id){
        $query = "update chitietdondatmonan set trangthai = 1 where madondatmonan = '$id'";
        $this->conn->query($query);
    }
   
    function xoahoadonchitiet($id){
        $query = "delete from chitietdondatmonan where madondatmonan = '$id'";
        $this->conn->query($query);
    }
}