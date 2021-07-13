<?php if (isset($_COOKIE['msg'])) { ?>
    <div class="alert alert-success">
        <strong>Thông báo</strong> <?= $_COOKIE['msg'] ?>
    </div>
<?php } ?>
<hr>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
    <form action="?mod=nguoidung&act=update" method="POST" role="form" enctype="multipart/form-data">
        <input type="hidden" name="MaND" value="<?= $data['id']?>">
    
          
           <div class="form-group">
               <label for="">Tên Tài Khoản</label>
               <input type="text" class="form-control" id="" placeholder="" name="TaiKhoan" value="<?= $data['taikhoan']?>">
           </div>

           <div class="form-group">
               <label for="">Mật Khẩu</label>
               <input type="Password" class="form-control" id="" placeholder="" name="MatKhau" value="<?= $data['password']?>">
           </div>

               <div class="form-group">
               <label for="">Họ Tên</label>
               <input type="text" class="form-control" id="" placeholder="" name="HoTen" value="<?= $data['name']?>">
           </div>
         
             <div class="form-group">
               <label for="">Ngày Sinh</label>
               <input type="date" class="form-control" id="" placeholder="" name="birthday" value="<?= $data['birthday']?>">
           </div>
           <div class="form-group">
               <label for="">Số Điện Thoại</label>
               <input type="text" class="form-control" id="" placeholder="" name="SDT" value="<?= $data['phone']?>">
           </div>
           <div class="form-group">
               <label for="">Địa chỉ</label>
               <input type="text" class="form-control" id="" placeholder="" name="DiaChi" value="<?= $data['address']?>">
           </div>

           
           <div class="form-group">
               <label for="">Email</label>
               <input type="Email" class="form-control" id="" placeholder="" name="Email" value="<?= $data['email']?>">
           </div>
           <div class="form-group">
           <div class="form-group">
               <label for="">Mã quyền</label>
               <select id="" name="MaQuyen" class="form-control">
                    <option <?= ($data['MaQuyen'] == '1')?'selected':''?> value="1"> Khách hàng</option>
                    <option <?= ($data['MaQuyen'] == '2')?'selected':''?> value="2"> Quản trị viên</option>
                    <option <?= ($data['MaQuyen'] == '3')?'selected':''?> value="3"> Nhân viên</option>
               </select>
           </div>
           </div>
           <button type="submit" class="btn btn-primary">Create</button>
    </form>
    </tbody>
</table>