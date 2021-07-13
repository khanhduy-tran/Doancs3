<?php 
    require_once("MVC/Models/login.php");
    class LoginController {
        var $login_model;
        public function __construct()
        {
            $this->login_model = new login();
        }
        // public function login()
        // {
        //     require_once("MVC/Views/login/login.php");
        // }
        // public function login_action()
        // {
        //     $this->login_model->login_action();
        // }
         function login_action()
    {
        $taikhoan = $_POST['taikhoan'];
        $matkhau = $_POST['matkhau'];
        if (strpos($taikhoan, "'") != false) {
            $taikhoan = str_replace("'", "\'", $taikhoan);
        }
        $data = array(
            'taikhoan' => $taikhoan,
            'matkhau' => $matkhau,
        );
        $this->login_model->login_action($data);
    }
        public function admin()
        {
            $data_tksp1 = $this->login_model->tk_sanpham(1);
            $data_tksp2 = $this->login_model->tk_sanpham(2);
            $data_tksp3 = $this->login_model->tk_sanpham(3);
            $data_tkspgiamgia =$this->login_model->tk_sanphamgiamgia();
           
           $data_tkspmonan = $this->login_model->tk_sanphammonan();

            $data_hd = $this->login_model->tk_thongbao();
             $data_hd1 = $this->login_model->tk_thongbao1();

            $data_dttuan=$this->login_model->tk_dttuan();
             $data_dttuan1=$this->login_model->tk_dttuan1();


            $m = date("m");

            $data_countM = $this->login_model->tk_dtthang($m);
            $data_countM1 = $this->login_model->tk_dtthang1($m);

            $y = "20".date("y");

            $data_countY = $this->login_model->tk_dtnam($y);
            $data_countY1 = $this->login_model->tk_dtnam1($y);

           

            $data_countW=$this->login_model->tk_hdtuan();
            $data_countW1=$this->login_model->tk_hdtuan1();

            $data_hdthang= $this->login_model->tk_hdthang($m);
            $data_hdthang1= $this->login_model->tk_hdthang1($m);

            $data_hdnam=$this->login_model->tk_hdnam($y);
            $data_hdnam1=$this->login_model->tk_hdnam1($y);

            $data_nguoidung = $this->login_model->tk_nguoidung(1);

            $data_nhanvien = $this->login_model->tk_nguoidung(3);
            require_once("MVC/Views/Admin/index.php");
        }
        // public function logout()
        // {
        //     $this->login_model->logout();
        // }
    }
?>