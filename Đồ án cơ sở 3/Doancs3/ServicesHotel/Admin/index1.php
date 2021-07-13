<?php
session_start();

if ((isset($_SESSION['isLogin_Admin']) && $_SESSION['isLogin_Admin'] == true) || (isset($_SESSION['isLogin_Nhanvien']) && $_SESSION['isLogin_Nhanvien'] == true)) {

if (isset($_SESSION['isLogin_Admin']) && $_SESSION['isLogin_Admin'] == true) {
    $mod = isset($_GET['mod']) ? $_GET['mod'] : "login";
    $act = isset($_GET['act']) ? $_GET['act'] : "admin";
    switch ($mod) {
        case 'danhmuc':
            require_once('MVC/controllers/DanhmucController.php');
            $controller_obj = new DanhmucController();
            switch ($act) {
                case 'list':
                    $controller_obj->list();
                    break;
                case 'add':
                    $controller_obj->add();
                    break;
                case 'store':
                    $controller_obj->store();
                    break;
                case 'detail':
                    $controller_obj->detail();
                    break;
                case 'delete':
                    $controller_obj->delete();
                    break;
                case 'edit':
                    $controller_obj->edit();
                    break;
                case 'update':
                    $controller_obj->update();
                    break;
                default:
                    $controller_obj->list();
                    break;
            }
            break;
        case 'banner':
            require_once('MVC/controllers/BannerController.php');
            $controller_obj = new BannerController();
            switch ($act) {
                case 'list':
                    $controller_obj->list();
                    break;
                case 'add':
                    $controller_obj->add();
                    break;
                case 'store':
                    $controller_obj->store();
                    break;
                case 'detail':
                    $controller_obj->detail();
                    break;
                case 'delete':
                    $controller_obj->delete();
                    break;
                case 'edit':
                    $controller_obj->edit();
                    break;
                case 'update':
                    $controller_obj->update();
                    break;
                default:
                    $controller_obj->list();
                    break;
            }
            break;

          

        case 'nguoidung':
            require_once('MVC/controllers/NguoiDungController.php');
            $controller_obj = new NguoiDungController();
            switch ($act) {
                case 'list':
                    $controller_obj->list();
                    break;
                case 'detail':
                    $controller_obj->detail();
                    break;
                case 'add':
                    $controller_obj->add();
                    break;
                case 'store':
                    $controller_obj->store();
                    break;
                case 'delete':
                    $controller_obj->delete();
                    break;
                case 'edit':
                    $controller_obj->edit();
                    break;
                case 'update':
                    $controller_obj->update();
                    break;
                default:
                    $controller_obj->list();
                    break;
            }
            break;

            

        case 'sanpham':
            require_once('MVC/controllers/SanphamController.php');
            $controller_obj = new SanphamController();
            switch ($act) {
                 case 'detail':
                    $controller_obj->detail();
                    break;
                case 'list':
                    $controller_obj->list();
                    break;
                case 'add':
                    $controller_obj->add();
                    break;
                case 'store':
                    $controller_obj->store();
                    break;
                case 'delete':
                    $controller_obj->delete();
                    break;
                case 'edit':
                    $controller_obj->edit();
                    break;
                case 'update':
                    $controller_obj->update();
                    break;
                default:
                    $controller_obj->list();
                    break;
            }
            break;
            case 'dichvu':
            require_once('MVC/controllers/DichVuController.php');
            $controller_obj = new DichVuController();
            switch ($act) {
                 case 'detail':
                    $controller_obj->detail();
                    break;
                case 'list':
                    $controller_obj->list();
                    break;
                case 'add':
                    $controller_obj->add();
                    break;
                case 'store':
                    $controller_obj->store();
                    break;
                case 'delete':
                    $controller_obj->delete();
                    break;
                case 'edit':
                    $controller_obj->edit();
                    break;
                case 'update':
                    $controller_obj->update();
                    break;
                default:
                    $controller_obj->list();
                    break;
            }
            break;
        
          
        
        case 'hoadon':
            require_once('MVC/controllers/HoadonController.php');
            $controller_obj = new HoadonController();
            switch ($act) {
                case 'list':
                    $controller_obj->list();
                    break;
                case 'chitiet':
                    $controller_obj->chitiet();
                    break;
                case 'delete':
                    $controller_obj->delete();
                    break;
                case 'xetduyet':
                    $controller_obj->xetduyet();
                    break;
                default:
                    $controller_obj->list();
                    break;
            }
            break;
            case 'hoadonmonan':
            require_once('MVC/controllers/HoadonmonanController.php');
            $controller_obj = new HoaDonMonAnController();
            switch ($act) {
                case 'list':
                    $controller_obj->list();
                    break;
                case 'chitiet':
                    $controller_obj->chitiet();
                    break;
                case 'delete':
                    $controller_obj->delete();
                    break;
                case 'xetduyet':
                    $controller_obj->xetduyet();
                    break;
                default:
                    $controller_obj->list();
                    break;
            }
            break;
        
            case 'login':
                require_once('MVC/controllers/LoginController.php');
                $controller_obj = new LoginController();
                switch ($act) {
                    case 'admin':
                        $controller_obj->admin();
                        break;
                    default:
                        $controller_obj->admin();
                        break;
                }
                break;
        default:
            header('location: ?mod=login');
            // require_once('MVC/controllers/LoginController.php');
            // $controller_obj = new LoginController();
            // $controller_obj->admin();
            // break;
    }
} else {
    if (isset($_SESSION['isLogin_Nhanvien']) && $_SESSION['isLogin_Nhanvien'] == true) {
        $mod = isset($_GET['mod']) ? $_GET['mod'] : "login";
        $act = isset($_GET['act']) ? $_GET['act'] : "admin";
        switch ($mod) {
           
            case 'danhmuc':
                require_once('MVC/controllers/DanhmucController.php');
                $controller_obj = new DanhmucController();
                switch ($act) {
                    case 'list':
                        $controller_obj->list();
                        break;
                    case 'detail':
                        $controller_obj->detail();
                        break;
                    default:
                        $controller_obj->list();
                        break;
                }
                break;
            case 'sanpham':
                require_once('MVC/controllers/SanphamController.php');
                $controller_obj = new SanphamController();
                switch ($act) {
                    case 'list':
                        $controller_obj->list();
                        break;
                    case 'detail':
                        $controller_obj->detail();
                        break;
                    default:
                        $controller_obj->list();
                        break;
                }
                break;
                  
          
                case 'login':
                    require_once('MVC/controllers/LoginController.php');
                    $controller_obj = new LoginController();
                    switch ($act) {
                        case 'admin':
                            $controller_obj->admin();
                            break;
                        default:
                            $controller_obj->admin();
                            break;
                    }
                    break;
            case 'hoadon':
                require_once('MVC/controllers/HoadonController.php');
                $controller_obj = new HoadonController();
                switch ($act) {
                    case 'list':
                        $controller_obj->list();
                        break;
                    case 'chitiet':
                        $controller_obj->chitiet();
                        break;
                    case 'delete':
                        $controller_obj->delete();
                        break;
                    case 'xetduyet':
                        $controller_obj->xetduyet();
                        break;
                    default:
                        $controller_obj->list();
                        break;
                }
                break;
            default:
            header('location: ?mod=login');
                // require_once('MVC/controllers/LoginController.php');
                // $controller_obj = new LoginController();
                // $controller_obj->admin();
                // break;
        }
    }
}
}else{
    require_once('MVC/controllers/LoginController.php');
        $controller_obj = new LoginController();
            $controller_obj->login_action();
}
