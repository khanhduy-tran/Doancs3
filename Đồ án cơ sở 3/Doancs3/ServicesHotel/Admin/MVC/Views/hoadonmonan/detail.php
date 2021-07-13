<?php if(isset($data) and $data != null){ ?>
<a href="?mod=hoadonmonan&act=xetduyet&id=<?= $data['0']['madondatmonan'] ?>" class="btn btn-success">Duyệt hóa đơn</a>
<a href="?mod=hoadonmonan&act=delete&id=<?= $data['0']['madondatmonan'] ?>" onclick="return confirm('Bạn có thật sự muốn xóa ?');" type="button" class="btn btn-danger">Xóa</a>
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
            <th scope="col">Tên Món Ăn</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Giá Tiền</th>
            <th scope="col">Phòng Nhận</th>
            <th scope="col">Ngày Nhận</th>
            <th scope="col">Thời Gian Nhận</th>

        </tr>
    </thead>
    <tbody>
        <?php foreach ($data as $row) { ?>
            <tr>
                <td><?= $row['tenmonan'] ?></td>
                <td><?= $row['soluong'] ?></td>
                <td><?= number_format($row['giamonan']) ?> VNĐ</td>
                <td><?= $row['phongnhan'] ?></td>
                     <td><?= $row['ngaynhan'] ?></td>
                <td><?= $row['thoigiannhan'] ?></td>
                
            </tr>
        <?php } ?>
</table>
<script>
    $(document).ready(function() {
        $('#dataTable').DataTable();
    });
</script>