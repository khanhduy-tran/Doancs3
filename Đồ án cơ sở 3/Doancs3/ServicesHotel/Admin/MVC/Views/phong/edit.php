<?php if (isset($_COOKIE['msg'])) { ?>
    <div class="alert alert-success">
        <strong>Thông báo</strong> <?= $_COOKIE['msg'] ?>
    </div>
<?php } ?>
<hr>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
    <form action="?mod=sanpham&act=update" method="POST" role="form" enctype="multipart/form-data">

        <input type="hidden" name="MaSP" value="<?= $data['id'] ?>">
        
        <div class="form-group">
            <label for="">Tên Phòng</label>
            <input type="text" class="form-control" id="" placeholder="" name="TenPhong" value="<?=$data['tenphong']?>">
        </div>
        <div class="form-group">
            <label for="">Giá Phòng</label>
            <input type="text" class="form-control" id="" placeholder="" name="GiaPhong" value="<?=$data['giaphong']?>">
        </div>
       
        <div class="form-group">
            <label for="">Hình ảnh 1</label>
            <img src="<?=$data['hinhanhphong']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnh1" value="<?=$data['hinhanhphong']?>">
        </div>
         <div class="form-group">
            <label for="">Hình ảnh 2</label>
            <img src="<?=$data['hinhanh2']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnh2" value="<?=$data['hinhanh2']?>">
        </div>
         <div class="form-group">
            <label for="">Hình ảnh 3</label>
            <img src="<?=$data['hinhanh3']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnh3" value="<?=$data['hinhanh3']?>">
        </div>
         <div class="form-group">
            <label for="">Hình ảnh 4</label>
            <img src="<?=$data['hinhanh4']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnh4" value="<?=$data['hinhanh4']?>">
        </div>
         <div class="form-group">
            <label for="">Giảm Giá</label>
            <input type="text" class="form-control" id="" placeholder="" name="GiamGia" value="<?=$data['giamgia']?>">
        </div>
      
        <label for="">Mô tả</label>
        <div class="form-group">
            <textarea class="form-control" id="summernote" placeholder="" name="MoTa"><?=$data['mota']?></textarea>
        </div>

        <div class="form-group">
            <label for="cars">Loại phòng: </label>
            <select id="" name="LoaiPhong" class="form-control">
                <?php foreach ($data_lsp as $row) { ?>
                    <option <?= ($row['id'] == $data['id'])?'selected':''?> value="<?= $row['id'] ?>"><?= $row['tenloaiphong'] ?></option>
                <?php } ?>
            </select>
        </div>

        <div class="form-group">
            <label for="">Trạng thái</label>
            <input <?=($data['trangthai']==0)?'checked':''?> type="checkbox" id="" placeholder="" value="0" name="TrangThai"><em>(Check cho phép hiển thị phòng)</em>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
    </form>
    <script>
        $(document).ready(function() {
            $('#summernote').summernote();
             $('#summernote1').summernote();
              $('#summernote2').summernote();
        });
    </script>