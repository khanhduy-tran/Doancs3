<?php
require_once("MVC/models/hoadon.php");
class HoaDonController
{
    var $hoadon_model;
    public function __construct()
    {
        $this->hoadon_model = new Hoadon();
    }
    function list()
    {
        $data = array();
        if (isset($_GET['id'])) {
            $id = $_GET['id'];
            if ($id > 1) {
                $id = 0;
            }
            $data = $this->hoadon_model->trangthai($id);
        } else {
            $data = $this->hoadon_model->All();
        }
        require_once("MVC/Views/Admin/index.php");
    }
    function xetduyet()
    {
        $trangthai = 1;
        $id = $_GET['id'];
        $data = array('id' => $_GET['id'],
                       'trangthai' => $trangthai);
          $this->hoadon_model->update($data);
         $this->hoadon_model->duyetchitiethoadon($id);
      
    }
    function delete()
    {
        if (isset($_GET['id'])) {
            $this->hoadon_model->delete($_GET['id']);
            $this->hoadon_model->xoahoadonchitiet($_GET['id']);
        }
    }
    function chitiet()
    {
        $id = isset($_GET['id']) ? $_GET['id'] : 1;
        $data = $this->hoadon_model->chitiethoadon($id);
        require_once("MVC/Views/Admin/index.php");
    }
}
