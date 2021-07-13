<?php if(isset($data) and $data != null){ ?>
<a href="?mod=hoadon&act=xetduyet&id=<?= $data['0']['madondatphong'] ?>" class="btn btn-success">Duyệt hóa đơn</a>
<a href="?mod=hoadon&act=delete&id=<?= $data['0']['madondatphong'] ?>" onclick="return confirm('Bạn có thật sự muốn xóa ?');" type="button" class="btn btn-danger">Xóa</a>
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
            <th scope="col">Tên Phòng</th>
            <th scope="col">Đơn Giá</th>
            <th scope="col">Loại</th>
            <th scope="col">Mã Phòng</th>
            <th scope="col">Ngày Đặt</th>
            <th scope="col">Ngày Trả</th>
        </tr>
    </thead>
    <tbody>
        <?php foreach ($data as $row) { ?>
            <tr>
                <td><?= $row['tenphong'] ?></td>
                <td><?= number_format($row['giaphong']) ?> VNĐ</td>
                <td><?= $row['loaiphong'] ?></td>
                     <td><?= $row['maphong'] ?></td>
                <td><?= $row['ngaydat'] ?></td>
                <td><?= $row['ngaytra']  ?></td>
            </tr>
        <?php } ?>
</table>
<script>
    $(document).ready(function() {
        $('#dataTable').DataTable();
    });
</script>