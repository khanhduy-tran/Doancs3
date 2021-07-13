<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
    <h2>Mã Phòng: <?= $data['id'] ?></h2>
    <h2>Tên Phòng: <?= $data['tenphong'] ?></h2>
    <h2>Giá Phòng: <?= $data['giaphong'] ?></h2>
    <h2>Hình Ảnh Phòng:</h2>
    <img src="<?= $data['hinhanhphong'] ?>" width="200px" height = 200px>
    <h2>Hình Ảnh 2:</h2>
    <img src="<?= $data['hinhanh2'] ?>"width="200px" height = 200px>
    <h2>Hình Ảnh 3:</h2>
    <img src="<?= $data['hinhanh3'] ?>"width="200px" height = 200px>
    <h2>Hình Ảnh 4:</h2>
    <img src="<?= $data['hinhanh4'] ?>"width="200px" height = 200px>
    <h2>Giá Mới: <?= $data['giamoi'] ?></h2>
    <h2>Giảm Giá: <?= $data['giamgia'] ?></h2>
    <h2>Mô tả: <?= $data['mota'] ?></h2>
    <h2>ID Phòng: <?= $data['idphong'] ?></h2>
    <h2>Trạng Thái: <?= $data['trangthai'] ?></h2>
</table>