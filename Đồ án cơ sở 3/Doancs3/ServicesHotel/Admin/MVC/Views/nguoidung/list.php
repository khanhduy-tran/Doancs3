<?php if (isset($_SESSION['isLogin_Admin']) && $_SESSION['isLogin_Admin'] == true) { ?>
<a href="?mod=nguoidung&act=add" type="button" class="btn btn-primary"><i class="fa fa-address-card-o" aria-hidden="true"></i>&nbsp;Thêm mới</a>
<?php } ?>
<?php if (isset($_COOKIE['msg'])) { ?>
  <div class="alert alert-success">
    <strong>Thông báo</strong> <?= $_COOKIE['msg'] ?>
  </div>
<?php } ?>
<hr>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
  <thead>
    <tr>
      <th scope="col">Mã Người Dùng</th>
      <th scope="col">Tài khoản</th>
      <th scope="col">SDT</th>
      <th scope="col">Email</th>
      <th scope="col">Địa chỉ</th>
      <th scope="col">Quyền hạn</th>
      <th></th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <?php foreach ($data as $row) { ?>
      <tr>
        <th scope="row"><?= $row['id'] ?></th>
        <td><?= $row['taikhoan'] ?></td>
        <td><?= $row['phone'] ?></td>
        <td><?= $row['email'] ?></td>
        <td><?= $row['address'] ?></td>
        <td>
          <?php
          if ($row['MaQuyen'] == 2) {
            echo 'Quản trị viên';
          } else {
            if ($row['MaQuyen'] == 1) {
              echo 'Khách hàng';
            } else {
              echo 'Nhân viên';
            }
          }
          ?>
        </td>
        <td>
          <a href="?mod=nguoidung&act=detail&id=<?= $row['id'] ?>" type="button" class="btn btn-success"><i class="fa fa-eye" aria-hidden="true"></i></a>
       
        </td>
        <td> <?php if (isset($_SESSION['isLogin_Admin']) && $_SESSION['isLogin_Admin'] == true) { ?>
          <a href="?mod=nguoidung&act=edit&id=<?= $row['id'] ?>" type="button" class="btn btn-warning "><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
        
          <?php }?></td>
          <td> <?php if (isset($_SESSION['isLogin_Admin']) && $_SESSION['isLogin_Admin'] == true) { ?>
         
          <a href="?mod=nguoidung&act=delete&id=<?= $row['id'] ?>" onclick="return confirm('Bạn có thật sự muốn xóa ?');" type="button" class="btn btn-danger"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
          <?php }?></td>
      </tr>
    <?php } ?>
  </tbody>
</table>
<script>
  $(document).ready(function() {
    $('#dataTable').DataTable();
  });
</script>