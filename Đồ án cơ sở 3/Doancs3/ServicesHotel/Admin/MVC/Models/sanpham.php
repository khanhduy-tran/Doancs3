<?php
require("model.php");
class sanpham extends model
{
    var $table = "phong";
    var $contens = "id";
    
    function loaisp(){
        $query = "select * from loaiphong";
        require("result.php");
        return $data;
    }
   
}
