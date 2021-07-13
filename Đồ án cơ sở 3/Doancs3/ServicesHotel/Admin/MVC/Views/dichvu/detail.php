<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
    <h2>Mã Món Ăn: <?= $data['id'] ?></h2>
    <h2>Tên Món Ăn: <?= $data['tenmonan'] ?></h2>
    <h2>Giá Tiền: <?= $data['giamonan'] ?></h2>
    <h2>Hình Ảnh 1:</h2>
    <img src="<?= $data['hinhanh'] ?>" width="200px" height = "200px">
    <h2>Hình Ảnh 2:</h2>
    <img src="<?= $data['hinhanh1'] ?>" width="200px" height = "200px">
    <h2>Hình Ảnh 3:</h2>
    <img src="<?= $data['hinhanh2'] ?>" width="200px" height = "200px">
    <h2>Hình Ảnh 4:</h2>
    <img src="<?= $data['hinhanh3'] ?>" width="200px" height = "200px">
    <h2>Mô tả: <?= $data['mota'] ?></h2>
    <h2>Trạng Thái: <?= $data['trangthai'] ?></h2>
</table>