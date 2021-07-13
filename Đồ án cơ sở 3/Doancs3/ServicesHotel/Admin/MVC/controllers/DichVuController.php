<?php
require_once("MVC/Models/dichvu.php");
class DichVuController{
	var $dichvu_model;
    public function __construct()
    {
        $this->dichvu_model = new dichvu();
    }
     public function list()
    {
        $data = $this->dichvu_model->All();
        require_once("MVC/Views/Admin/index.php");
        // require_once("MVC/Views/posts/list.php");
    }
    public function detail()
    {
        $id = isset($_GET['id']) ? $_GET['id'] : 1;
        $data = $this->dichvu_model->find($id);
        require_once("MVC/Views/Admin/index.php");
        //require_once("MVC/Views/posts/detail.php");
    }
    public function add()
    {
      
        require_once("MVC/Views/Admin/index.php");
        //require_once("MVC/Views/posts/add.php");
    }
    public function store()
    {
        $target_dir = "../image/dichvu/";  // thư mục chứa file upload

        $HinhAnh1 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh1"]["name"]); // link sẽ upload file lên

        $status_upload = move_uploaded_file($_FILES["HinhAnh1"]["tmp_name"], $target_file);

        if ($status_upload) { // nếu upload file không có lỗi 
            $linkfolder = "http://192.168.1.6/ServicesHotel/image/dichvu/";
            $HinhAnh1 =$linkfolder.basename($_FILES["HinhAnh1"]["name"]);
        }

        $HinhAnh2 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh2"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh2"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
               $linkfolder = "http://192.168.1.6/ServicesHotel/image/dichvu/";
            $HinhAnh2 =$linkfolder.basename($_FILES["HinhAnh2"]["name"]);
        }

        $HinhAnh3 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh3"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh3"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
               $linkfolder = "http://192.168.1.6/ServicesHotel/image/dichvu/";
            $HinhAnh3 = $linkfolder.basename($_FILES["HinhAnh3"]["name"]);
        }

         $HinhAnh4 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh4"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh4"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
               $linkfolder = "http://192.168.1.6/ServicesHotel/image/dichvu/";
            $HinhAnh4 =$linkfolder.basename($_FILES["HinhAnh4"]["name"]);
        }

        $TrangThai = 1;
        if (isset($_POST['TrangThai'])) {
            $TrangThai = $_POST['TrangThai'];
        }
        
        $data = array(
            'tenmonan' =>    $_POST['TenMonAn'],
            'giamonan'  =>   $_POST['GiaMonAn'],    
            'hinhanh' => $HinhAnh1,
            'hinhanh1' => $HinhAnh2,
            'hinhanh2' => $HinhAnh3,
            'hinhanh3' => $HinhAnh4,          
            'mota' =>  $_POST['MoTa'],
            'trangthai' => $TrangThai
            
        );
        foreach ($data as $key => $value) {
            if (strpos($value, "'") != false) {
                $value = str_replace("'", "\'", $value);
                $data[$key] = $value;
            }
        }

        $this->dichvu_model->store($data);
    }
    public function delete()
    {
        $id = $_GET['id'];
        $this->dichvu_model->delete($id);
    }
    public function edit()
    {
        $id = isset($_GET['id']) ? $_GET['id'] : 1;
       
        $data = $this->dichvu_model->find($id);
        require_once("MVC/Views/Admin/index.php");
        //require_once("MVC/Views/posts/edit.php");
    }
    public function update()
    {

        $target_dir = "../image/dichvu/";  // thư mục chứa file upload

        $HinhAnh1 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh1"]["name"]); // link sẽ upload file lên

        $status_upload = move_uploaded_file($_FILES["HinhAnh1"]["tmp_name"], $target_file);

        if ($status_upload) { // nếu upload file không có lỗi 
               $linkfolder = "http://192.168.1.6/ServicesHotel/image/dichvu/";
            $HinhAnh1 =$linkfolder.basename($_FILES["HinhAnh1"]["name"]);
        }

        $HinhAnh2 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh2"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh2"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
               $linkfolder = "http://192.168.1.6/ServicesHotel/image/dichvu/";
            $HinhAnh2 =$linkfolder.basename($_FILES["HinhAnh2"]["name"]);
        }

        $HinhAnh3 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh3"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh3"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
               $linkfolder = "http://192.168.1.6/ServicesHotel/image/dichvu/";
            $HinhAnh3 =$linkfolder.basename($_FILES["HinhAnh3"]["name"]);
        }

         $HinhAnh4 = "";
        $target_file = $target_dir . basename($_FILES["HinhAnh4"]["name"]); // link sẽ upload file lên
        $status_upload = move_uploaded_file($_FILES["HinhAnh4"]["tmp_name"], $target_file);
        if ($status_upload) { // nếu upload file không có lỗi 
               $linkfolder = "http://192.168.1.6/ServicesHotel/image/dichvu/";
            $HinhAnh4 =$linkfolder.basename($_FILES["HinhAnh4"]["name"]);
        }

        $TrangThai = 1;
        if (isset($_POST['TrangThai'])) {
            $TrangThai = $_POST['TrangThai'];
        }
       
        $data = array(
            'id'=>$_POST['MaMonAn'],
             'tenmonan' => $_POST['TenMonAn'],
            'giamonan' => $_POST['GiaMonAn'],
            'hinhanh' => $HinhAnh1,
            'hinhanh1' => $HinhAnh2,
            'hinhanh2' => $HinhAnh3,
            'hinhanh3' => $HinhAnh4,         
            'mota' => $_POST['MoTa'],
            'trangthai' => $TrangThai
        );
        foreach ($data as $key => $value) {
            if (strpos($value, "'") != false) {
                $value = str_replace("'", "\'", $value);
                $data[$key] = $value;
            }
        }
        if ($HinhAnh1 == "") {
            unset($data['hinhanh']);
        }
        if ($HinhAnh2 == "") {
            unset($data['hinhanh1']);
        }
        if ($HinhAnh3 == "") {
            unset($data['hinhanh2']);
        }
         if ($HinhAnh4 == "") {
            unset($data['hinhanh3']);
        }
       
       
        $this->dichvu_model->update($data);
    }
} 
 ?>