<!DOCTYPE html>
<html>
<head>
   <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
  <title>Quản Trị Viên Đà Nẵng Hotel</title>
  <link rel="stylesheet" type="text/css" href="public/css/dangnhap.css">
</head>
<body>
  <form action="index1.php" method="post">
  
  <div class="container">
     <?php if (isset($_COOKIE['msg1'])) { ?>
                    <div  class="alert alert-success fade in">                  
                        <button  class="close"></button>
                        <i class="fa-fw fa fa-check"></i>
                        <strong>Success!</strong> <?= $_COOKIE['msg1'] ?>
                    </div>
                     <?php } ?>

                    <?php if (isset($_COOKIE['msg2'])) { ?>
                    <div  class="alert alert-danger fade in">
                           
                        <button  class="close"></button>
                        <i class="fa-fw fa fa-times"></i>
                        <strong>Error!</strong><?= $_COOKIE['msg2'] ?>
                      
                    </div>
                      <?php } ?>
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="taikhoan" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="matkhau" required>

    <button type="submit">Login</button>
    <label>
      <input type="checkbox" checked="checked" name="remember"> Remember me
    </label>
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <button type="button" class="cancelbtn">Cancel</button>
    <span class="psw">Forgot <a href="#">password?</a></span>
  </div>
</form>
</body>
</html>