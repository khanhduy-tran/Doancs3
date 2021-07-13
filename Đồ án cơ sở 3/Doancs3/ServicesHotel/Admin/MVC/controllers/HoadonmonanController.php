<?php
require_once("MVC/models/hoadonmonan.php");
class HoaDonMonAnController
{
    var $hoadonmonan_model;
    public function __construct()
    {
        $this->hoadonmonan_model = new Hoadonmonan();
    }
    function list()
    {
        $data = array();
        if (isset($_GET['id'])) {
            $id = $_GET['id'];
            if ($id > 1) {
                $id = 0;
            }
            $data = $this->hoadonmonan_model->trangthai($id);
        } else {
            $data = $this->hoadonmonan_model->All();
        }
        require_once("MVC/Views/Admin/index.php");
    }
    function xetduyet()
    {
        $trangthai = 1;
        $id = $_GET['id'];
        $data = array('id' => $_GET['id'],
                       'trangthai' => $trangthai);
          $this->hoadonmonan_model->update($data);
         $this->hoadonmonan_model->duyetchitiethoadon($id);
      
    }
    function delete()
    {
        if (isset($_GET['id'])) {
            $this->hoadonmonan_model->delete($_GET['id']);
            $this->hoadonmonan_model->xoahoadonchitiet($_GET['id']);
        }
    }
    function chitiet()
    {
        $id = isset($_GET['id']) ? $_GET['id'] : 1;
        $data = $this->hoadonmonan_model->chitiethoadon($id);
        require_once("MVC/Views/Admin/index.php");
    }
}
