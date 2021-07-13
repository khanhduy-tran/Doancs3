<a href="?mod=banner&act=add" type="button" class="btn btn-primary"><i class="fa fa-address-card-o" aria-hidden="true"></i>&nbsp;Thêm mới</a>
<?php if (isset($_COOKIE['msg'])) { ?>
  <div class="alert alert-success">
    <strong>Thông báo</strong> <?= $_COOKIE['msg'] ?>
  </div>
<?php } ?>
<hr>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Hình Ảnh</th>
     
      <th scope="col"></th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
    <?php foreach ($data as $row) { ?>
      <tr>
        <td><?= $row['id'] ?></td>
        <td><img src="<?= $row['anhslide'] ?>" width = "150px" height = "100px"></td>
        
        <td>
          <a href="?mod=banner&act=detail&id=<?= $row['id'] ?>" class="btn btn-success"><i class="fa fa-eye" aria-hidden="true"></i></a>
          
        </td>
        <td>
           <?php if (isset($_SESSION['isLogin_Admin']) && $_SESSION['isLogin_Admin'] == true) { ?>
          <a href="?mod=banner&act=edit&id=<?= $row['id'] ?>" class="btn btn-warning"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
         
          <?php }?>
        </td>
        <td>
           <?php if (isset($_SESSION['isLogin_Admin']) && $_SESSION['isLogin_Admin'] == true) { ?>
          
          <a href="?mod=banner&act=delete&id=<?= $row['id'] ?>" onclick="return confirm('Bạn có thật sự muốn xóa ?');" type="button" class="btn btn-danger"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
          <?php }?>
        </td>
      </tr>
    <?php } ?>
</table>
<script>
  $(document).ready(function() {
    $('#dataTable').DataTable();
  });
</script>