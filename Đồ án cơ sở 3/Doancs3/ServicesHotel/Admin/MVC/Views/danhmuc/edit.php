<?php if (isset($_COOKIE['msg'])) { ?>
    <div class="alert alert-success">
        <strong>Thông báo</strong> <?= $_COOKIE['msg'] ?>
    </div>
<?php } ?>
<hr>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
    <form action="?mod=danhmuc&act=update" method="POST" role="form" enctype="multipart/form-data">
        
            <input type="hidden" class="form-control" id="" placeholder="" name="MaDM" value="<?= $data['id'] ?>">
        <div class="form-group">
            <label for="">Tên Loại Phòng</label>
            <input type="text" class="form-control" id="" placeholder="" name="TenDM" value="<?= $data['tenloaiphong'] ?>">
        </div>
         <div class="form-group">
            <label for="">Hình ảnh Loại Phòng</label>
            <img src="<?=$data['hinhanhloaiphong']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnhLoaiPhong" value="<?=$data['hinhanhloaiphong']?>">
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </form>
</table>