<?php
require_once("connection.php");
class login
{
    var $conn;
    function __construct()
    {
        $conn_obj = new Connection();
        $this->conn = $conn_obj->conn;
    }
    function tk_sanpham($id){
        $query = "SELECT count(id) as Count FROM phong WHERE idphong = $id";

        return $this->conn->query($query)->fetch_assoc();
    }
     function tk_sanphammonan(){
        $query = "SELECT count(id) as Count FROM dichvudoan";

        return $this->conn->query($query)->fetch_assoc();
    }
     function tk_sanphamgiamgia(){
        $query = "SELECT count(id) as Count FROM phong where giamgia > 0";

        return $this->conn->query($query)->fetch_assoc();
    }
    function tk_thongbao(){
        $query = "SELECT count(id) as Count FROM dondatphong WHERE TrangThai = 0 and huydon = 0";

        return $this->conn->query($query)->fetch_assoc();
    }
    function tk_thongbao1(){
        $query = "SELECT count(id) as Count FROM dondatmonan WHERE TrangThai = 0 and huydon = 0";

        return $this->conn->query($query)->fetch_assoc();
    }
     function tk_dttuan(){
         $query = "SELECT SUM(giaphong) as Count FROM chitietdondatphong WHERE YEARWEEK(ngaydat) = YEARWEEK(NOW())And TrangThai = 1 ";

        return $this->conn->query($query)->fetch_assoc();
    }
     function tk_dttuan1(){
         $query = "SELECT SUM(giamonan) as Count FROM chitietdondatmonan WHERE YEARWEEK(ngaynhan) = YEARWEEK(NOW())And TrangThai = 1 ";

        return $this->conn->query($query)->fetch_assoc();
    }
    function tk_dtthang($m){
        $query = "SELECT SUM(giaphong) as Count FROM chitietdondatphong WHERE MONTH(ngaydat) = $m And TrangThai = 1";

        return $this->conn->query($query)->fetch_assoc();
    }
    function tk_dtthang1($m){
        $query = "SELECT SUM(giamonan) as Count FROM chitietdondatmonan WHERE MONTH(ngaynhan) = $m And TrangThai = 1";

        return $this->conn->query($query)->fetch_assoc();
    }

     function tk_dtnam($y){
        $query = "SELECT SUM(giaphong) as Count FROM chitietdondatphong WHERE YEAR(ngaydat) = $y And TrangThai = 1";

        return $this->conn->query($query)->fetch_assoc();
    }
     function tk_dtnam1($y){
        $query = "SELECT SUM(giamonan) as Count FROM chitietdondatmonan WHERE YEAR(ngaynhan) = $y And TrangThai = 1";

        return $this->conn->query($query)->fetch_assoc();
    }

    function tk_hdtuan(){
         $query = "SELECT count(giaphong) as Count FROM chitietdondatphong WHERE YEARWEEK(ngaydat) = YEARWEEK(NOW())And TrangThai = 1 ";

        return $this->conn->query($query)->fetch_assoc();
    }

    function tk_hdtuan1(){
         $query = "SELECT count(giamonan) as Count FROM chitietdondatmonan WHERE YEARWEEK(ngaynhan) = YEARWEEK(NOW())And TrangThai = 1 ";

        return $this->conn->query($query)->fetch_assoc();
    }
    function tk_hdthang($m){
        $query = "SELECT count(giaphong) as Count FROM chitietdondatphong WHERE MONTH(ngaydat) = $m And TrangThai = 1";

        return $this->conn->query($query)->fetch_assoc();
    }
    function tk_hdthang1($m){
        $query = "SELECT count(giamonan) as Count FROM chitietdondatmonan WHERE MONTH(ngaynhan) = $m And TrangThai = 1";

        return $this->conn->query($query)->fetch_assoc();
    }
     function tk_hdnam($y){
        $query = "SELECT count(giaphong) as Count FROM chitietdondatphong WHERE YEAR(ngaydat) = $y And TrangThai = 1";

        return $this->conn->query($query)->fetch_assoc();
    }
     function tk_hdnam1($y){
        $query = "SELECT count(giamonan) as Count FROM chitietdondatmonan WHERE YEAR(ngaynhan) = $y And TrangThai = 1";

        return $this->conn->query($query)->fetch_assoc();
    }
   
    function tk_nguoidung($id){
        $query = "SELECT count(id) as Count FROM user WHERE MaQuyen = $id";
        
        return $this->conn->query($query)->fetch_assoc();
    }
    function login_action($data)
    {
        $query = "SELECT * from user  WHERE taikhoan = '" . $data['taikhoan'] . "' AND password = '" . $data['matkhau'] . "'";

        $login = $this->conn->query($query)->fetch_assoc();
        if ($login !== NULL) {
            if($login['MaQuyen'] == 2){
                $_SESSION['isLogin_Admin'] = true;
                $_SESSION['login'] = $login;
            }else{
                if($login['MaQuyen'] == 3){
                $_SESSION['isLogin_Nhanvien'] = true;
                $_SESSION['login'] = $login;
                }
            }
            setcookie('msg1', 'Đăng nhập thành công', time() + 5);
            header('Location: ?mod=login');
        } else {
            setcookie('msg2', 'Đăng nhập không thành công vui lòng xem lại tài khoản và mật khẩu', time() + 5);
           header("location:javascript://history.go(-1)");
        }
        
    }
}
