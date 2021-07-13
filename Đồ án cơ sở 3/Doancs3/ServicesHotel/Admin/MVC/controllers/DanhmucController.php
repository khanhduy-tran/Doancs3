<?php
require_once("MVC/Models/danhmuc.php");
class DanhmucController
{
	var $danhmuc_model;
	function __construct()
	{
		$this->danhmuc_model = new Danhmuc();
	}

	public function list()
	{
		$data = array();
		$data = $this->danhmuc_model->All(); 
		require_once("MVC/Views/Admin/index.php");
		//require_once('MVC/views/categories/list.php');
	}

	public function add()
	{
		require_once("MVC/Views/Admin/index.php");
		//require_once('MVC/views/categories/add.php');
	}
	public function store()
	{
		$target_dir = "../image/loaiphong/";  // thư mục chứa file upload

        $HinhAnhLoaiPhong = "";
        $target_file = $target_dir . basename($_FILES["HinhAnhLoaiPhong"]["name"]); // link sẽ upload file lên

        $status_upload = move_uploaded_file($_FILES["HinhAnhLoaiPhong"]["tmp_name"], $target_file);

        if ($status_upload) { // nếu upload file không có lỗi 
            $HinhAnhLoaiPhong =   basename($_FILES["HinhAnhLoaiPhong"]["name"]);
        }
        $linkfolder = "http://192.168.1.6/ServicesHotel/image/loaiphong/";
        $linkanh = $linkfolder . $HinhAnhLoaiPhong;

		$data = array(
			'tenloaiphong' => $_POST['TenDM'],
			'hinhanhloaiphong' => $linkanh
		);
		foreach ($data as $key => $value) {
            if (strpos($value, "'") != false) {
                $value = str_replace("'", "\'", $value);
                $data[$key] = $value;
            }
        }
		$this->danhmuc_model->store($data);
	}
	public function detail()
	{
		$id = isset($_GET['id']) ? $_GET['id'] : 5;
		$data = $this->danhmuc_model->find($id);
		require_once("MVC/Views/Admin/index.php");
		//require_once('MVC/views/categories/detail.php');
	}
	public function delete()
	{
		if (isset($_GET['id'])) {
			$this->danhmuc_model->delete($_GET['id']);
		}
	}
	public function edit()
	{
		$id = isset($_GET['id']) ? $_GET['id'] : 5;
		$data = $this->danhmuc_model->find($id);
		require_once("MVC/Views/Admin/index.php");
		//require_once('MVC/views/categories/edit.php');
	}
	public function update()
	{
	$target_dir = "../image/loaiphong/";  // thư mục chứa file upload

        $HinhAnhLoaiPhong = "";
        $target_file = $target_dir . basename($_FILES["HinhAnhLoaiPhong"]["name"]); // link sẽ upload file lên

        $status_upload = move_uploaded_file($_FILES["HinhAnhLoaiPhong"]["tmp_name"], $target_file);

        if ($status_upload) { // nếu upload file không có lỗi 
        	$link = "http://192.168.1.6/ServicesHotel/image/loaiphong/";
            $HinhAnhLoaiPhong =$link . basename($_FILES["HinhAnhLoaiPhong"]["name"]);
        }
      
      

		$data = array(
			'id' => $_POST['MaDM'],
			'tenloaiphong' => $_POST['TenDM'],
			'hinhanhloaiphong' =>  $HinhAnhLoaiPhong
		);
		foreach ($data as $key => $value) {
            if (strpos($value, "'") != false) {
                $value = str_replace("'", "\'", $value);
                $data[$key] = $value;
            }
        }
		$this->danhmuc_model->update($data);
	}
}
