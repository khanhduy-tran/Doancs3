<?php if (isset($_COOKIE['msg'])) { ?>
    <div class="alert alert-success">
        <strong>Thông báo</strong> <?= $_COOKIE['msg'] ?>
    </div>
<?php } ?>
<hr>
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
    <form action="?mod=dichvu&act=update" method="POST" role="form" enctype="multipart/form-data">

        <input type="hidden" name="MaMonAn" value="<?= $data['id'] ?>">
        
        <div class="form-group">
            <label for="">Tên Món Ăn</label>
            <input type="text" class="form-control" id="" placeholder="" name="TenMonAn" value="<?=$data['tenmonan']?>">
        </div>
        <div class="form-group">
            <label for="">Giá Món Ăn</label>
            <input type="text" class="form-control" id="" placeholder="" name="GiaMonAn" value="<?=$data['giamonan']?>">
        </div>
       
        <div class="form-group">
            <label for="">Hình ảnh 1</label>
            <img src="<?=$data['hinhanh']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnh1" value="<?=$data['hinhanh']?>">
        </div>
         <div class="form-group">
            <label for="">Hình ảnh 2</label>
            <img src="<?=$data['hinhanh1']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnh2" value="<?=$data['hinhanh1']?>">
        </div>
         <div class="form-group">
            <label for="">Hình ảnh 3</label>
            <img src="<?=$data['hinhanh2']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnh3" value="<?=$data['hinhanh2']?>">
        </div>
         <div class="form-group">
            <label for="">Hình ảnh 4</label>
            <img src="<?=$data['hinhanh3']?>" height="200px" width="200px">
            <input type="file" class="form-control" id="" placeholder="" name="HinhAnh4" value="<?=$data['hinhanh3']?>">
        </div>
        
      
        <label for="">Mô tả</label>
        <div class="form-group">
            <textarea class="form-control" id="summernote" placeholder="" name="MoTa"><?=$data['mota']?></textarea>
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