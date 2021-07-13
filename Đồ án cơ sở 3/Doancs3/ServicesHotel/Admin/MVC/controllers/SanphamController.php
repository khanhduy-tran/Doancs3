<?php
require_once("MVC/Models/sanpham.php");
class SanphamController
{
    var $sanpham_model;
    public function __construct()
    {
        $this->sanpham_model = new sanpham();
    }
    public function list()
    {
        $data = $this->sanpham_model->All();
        require_once("MVC/Views/Admin/index.php");
        // require_once("MVC/Views/posts/list.php");
    }
    public function detail()
    {
        $id = isset($_GET['id']) ? $_GET['id'] : 1;
        $data = $this->sanpham_model->find($id);
        require_once("MVC/Views/Admin/index.php");
        //require_once("MVC/Views/posts/detail.php");
    }
    public function add()
    {
        $data_lsp = $this->sanpham_model->loaisp();
        require_once("MVC/Views/Admin/index.php");
        //require_once("MVC/Views/posts/add.php");
    }
    public function store()
    {
        $target_dir = "../image/phong/";  // thư mục chứa file upload

        $HinhAnh1 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh1"]["name"]); // link sẽ upload file lên

        $status_upload = move_uploaded_file($_FILES["HinhAnh1"]["tmp_name"], $target_file);

        if ($status_upload) { // nếu upload file không có lỗi 
            $linkfolder = "http://192.168.1.6/ServicesHotel/image/phong/";
            $HinhAnh1 = $linkfolder.basename($_FILES["HinhAnh1"]["name"]);
        }

        $HinhAnh2 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh2"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh2"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi
        $linkfolder = "http://192.168.1.6/ServicesHotel/image/phong/"; 
            $HinhAnh2 = $linkfolder . basename($_FILES["HinhAnh2"]["name"]);
        }

        $HinhAnh3 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh3"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh3"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
            $linkfolder = "http://192.168.1.6/ServicesHotel/image/phong/";
            $HinhAnh3 = $linkfolder . basename($_FILES["HinhAnh3"]["name"]);
        }

         $HinhAnh4 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh4"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh4"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
            $linkfolder = "http://192.168.1.6/ServicesHotel/image/phong/";
            $HinhAnh4 = $linkfolder.basename($_FILES["HinhAnh4"]["name"]);
        }

         

        $TrangThai = 1;
        if (isset($_POST['TrangThai'])) {
            $TrangThai = $_POST['TrangThai'];
        }
        $giaphong = $_POST['GiaPhong'];
        $giamgia = $_POST['GiamGia'];
        $a = (int)$giaphong;
        $b = (int)$giamgia;
        if($b > 0){
            $phantram = (100 - $b) / 100;
            $giamoi = $a * $phantram;
        }

        
        $data = array(
            'tenphong' =>    $_POST['TenPhong'],
            'giaphong'  =>   $_POST['GiaPhong'],
        
            'hinhanhphong' => $HinhAnh1,
            'hinhanh2' => $HinhAnh2,
            'hinhanh3' => $HinhAnh3,
            'hinhanh4' => $HinhAnh4,
            'giamoi' => $giamoi,
            'giamgia' => $_POST['GiamGia'],
            'mota' =>  $_POST['MoTa'],
            'idphong' => $_POST['LoaiPhong'],
            'trangthai' => $TrangThai
            
        );
        foreach ($data as $key => $value) {
            if (strpos($value, "'") != false) {
                $value = str_replace("'", "\'", $value);
                $data[$key] = $value;
            }
        }

        $this->sanpham_model->store($data);
    }
    public function delete()
    {
        $id = $_GET['id'];
        $this->sanpham_model->delete($id);
    }
    public function edit()
    {
        $id = isset($_GET['id']) ? $_GET['id'] : 1;
        $data_lsp = $this->sanpham_model->loaisp();
        $data = $this->sanpham_model->find($id);
        require_once("MVC/Views/Admin/index.php");
        //require_once("MVC/Views/posts/edit.php");
    }
    public function update()
    {

        $target_dir = "../image/phong/";  // thư mục chứa file upload

        $HinhAnh1 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh1"]["name"]); // link sẽ upload file lên

        $status_upload = move_uploaded_file($_FILES["HinhAnh1"]["tmp_name"], $target_file);

        if ($status_upload) { // nếu upload file không có lỗi 
            $linkfolder = "http://192.168.1.6/ServicesHotel/image/phong/";
            $HinhAnh1 = $linkfolder. basename($_FILES["HinhAnh1"]["name"]);
        }

        $HinhAnh2 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh2"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh2"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
            $linkfolder = "http://192.168.1.6/ServicesHotel/image/phong/";
            $HinhAnh2 =$linkfolder. basename($_FILES["HinhAnh2"]["name"]);
        }

        $HinhAnh3 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh3"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh3"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
            $linkfolder = "http://192.168.1.6/ServicesHotel/image/phong/";
            $HinhAnh3 = $linkfolder.basename($_FILES["HinhAnh3"]["name"]);
        }

         $HinhAnh4 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh4"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh4"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
            $linkfolder = "http://192.168.1.6/ServicesHotel/image/phong/";
            $HinhAnh4 = $linkfolder. basename($_FILES["HinhAnh4"]["name"]);
        }

        $giaphong = $_POST['GiaPhong'];
        $giamgia = $_POST['GiamGia'];
        $a = (int)$giaphong;
        $b = (int)$giamgia;
        if($b > 0){
            $phantram = (100 - $b) / 100;
            $giamoi = $a * $phantram;
        }
         
        $TrangThai = 1;
        if (isset($_POST['TrangThai'])) {
            $TrangThai = $_POST['TrangThai'];
        }
       
        $data = array(
            'id'=>$_POST['MaSP'],
             'tenphong' => $_POST['TenPhong'],
            'giaphong' => $_POST['GiaPhong'],

            'hinhanhphong' => $HinhAnh1,
            'hinhanh2' => $HinhAnh2,
            'hinhanh3' => $HinhAnh3,
            'hinhanh4' => $HinhAnh4,

            'giamoi' => $giamoi,
            'giamgia' => $_POST['GiamGia'],
            'mota' => $_POST['MoTa'],
            'idphong' => $_POST['LoaiPhong'],
            'trangthai' => $TrangThai
        );
        foreach ($data as $key => $value) {
            if (strpos($value, "'") != false) {
                $value = str_replace("'", "\'", $value);
                $data[$key] = $value;
            }
        }
        if ($HinhAnh1 == "") {
            unset($data['hinhanhphong']);
        }
        if ($HinhAnh2 == "") {
            unset($data['hinhanh2']);
        }
        if ($HinhAnh3 == "") {
            unset($data['hinhanh3']);
        }
         if ($HinhAnh4 == "") {
            unset($data['hinhanh4']);
        }
       
       
        $this->sanpham_model->update($data);
    }
}
