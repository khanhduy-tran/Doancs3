<a href="?mod=hoadonmonan&id=1" type="button" class="btn btn-primary">Đã duyệt</a>
<a href="?mod=hoadonmonan&id=0" type="button" class="btn btn-primary">Chưa duyệt</a>
<?php if (isset($_COOKIE['msg'])) { ?>
  <div class="alert alert-success">
    <strong>Thông báo</strong> <?= $_COOKIE['msg'] ?>
  </div>
<?php } ?>
<hr>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
  <thead>
    <tr>
      <th scope="col">Tên Khách Hàng</th>
      <th scope="col">Địa chỉ</th>
      <th scope="col">Email</th>
      <th scope="col">Phone</th>
      <th scope="col">Trạng thái</th>
      <th></th>
      <th></th>
    </tr>
  </thead>
  <tbody>
    <?php foreach ($data as $row) { ?>
      <tr>
        <td><?= $row['tenkhachhang'] ?></td>
        <td><?= $row['diachi'] ?></td>
        <td><?= $row['email'] ?></td>
        <td><?= $row['phone'] ?></td>
        <td><?php if($row['trangthai']==0){
            echo 'Chưa xét duyệt';
        }else{
            echo 'Đã xét duyệt';
        }
        ?></td>
        <td>
          <a href="?mod=hoadonmonan&act=chitiet&id=<?= $row['id'] ?>" class="btn btn-success" ><i class="fa fa-eye" aria-hidden="true"></i></a>
         
        </td>
        <td>
           <a href="?mod=hoadonmonan&act=delete&id=<?= $row['id'] ?>" onclick="return confirm('Bạn có thật sự muốn xóa ?');" type="button" class="btn btn-danger"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
        </td>
      </tr>
    <?php } ?>
</table>
<script>
  $(document).ready(function() {
    $('#dataTable').DataTable();
  });
</script>