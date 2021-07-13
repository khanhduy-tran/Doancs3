<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
  <?php if (isset($_COOKIE['msg'])) { ?>
    <div class="alert alert-warning">
      <strong>Thông báo</strong> <?= $_COOKIE['msg'] ?>
    </div>
  <?php } ?>
  <form action="?mod=sanpham&act=store" method="POST" role="form" enctype="multipart/form-data">
   
 
    <div class="form-group">
      <label for="">Tên Phòng</label>
      <input type="text" class="form-control" id="" placeholder="" name="TenPhong">
    </div>
    <div class="form-group">
      <label for="">Giá Phòng</label>
      <input type="text" class="form-control" id="" placeholder="" name="GiaPhong">
    </div>
    
    <div class="form-group">
      <label for="">Hình ảnh 1 </label>
      <input type="file" class="form-control" id="" placeholder="" name="HinhAnh1">
    </div>
    <div class="form-group">
      <label for="">Hình ảnh 2</label>
      <input type="file" class="form-control" id="" placeholder="" name="HinhAnh2">
    </div>
    <div class="form-group">
      <label for="">Hình ảnh 3</label>
      <input type="file" class="form-control" id="" placeholder="" name="HinhAnh3">
    </div>
     <div class="form-group">
      <label for="">Hình ảnh 4 </label>
      <input type="file" class="form-control" id="" placeholder="" name="HinhAnh4">
    </div>
     <div class="form-group">
      <label for="">Giảm Giá</label>
      <input type="text" class="form-control" id="" placeholder="" name="GiamGia">
    </div>
    
    <label for="">Mô tả</label>
    <div class="form-group">
      <textarea class="form-control" id="summernote" placeholder="" name="MoTa"></textarea>
    </div>
       <div class="form-group">
      <label for="cars">Loại phòng: </label>
      <select id="" name="LoaiPhong" class="form-control">
        <?php foreach ($data_lsp as $row) { ?>
          <option value="<?= $row['id'] ?>"><?= $row['tenloaiphong'] ?></option>
        <?php } ?>
      </select>
    </div>
 
    <div class="form-group">
      <label for="">Trạng thái</label>
      <input type="checkbox" id="" placeholder="" value="0" name="TrangThai"><em>(Check cho phép hiện thị phòng)</em>
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
  </form>
  <script>
    $(document).ready(function() {
      $('#summernote').summernote();
        $('#summernote1').summernote();
          $('#summernote2').summernote();
    });
  </script>
</table>