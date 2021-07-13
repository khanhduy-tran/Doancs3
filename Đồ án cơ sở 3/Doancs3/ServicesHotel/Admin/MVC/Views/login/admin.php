 <!-- Begin Page Content -->
 <div class="container-fluid">

   <!-- Page Heading -->
   <div class="d-sm-flex align-items-center justify-content-between mb-4">
     <h1 class="h3 mb-0 text-gray-800">Thống Kê</h1>
   </div>

   <!-- Content Row -->
   <div class="row">

     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Danh thu phòng theo tuần</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_dttuan['Count']) ?> VNĐ</div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Danh thu món ăn theo tuần</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_dttuan1['Count']) ?> VNĐ</div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <!-- Earnings (Monthly) Card Example -->
     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Danh thu phòng tháng này</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_countM['Count']) ?> VNĐ</div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

      <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Danh thu món ăn tháng này</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_countM1['Count']) ?> VNĐ</div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <!-- Earnings (Monthly) Card Example -->
     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Danh thu phòng năm nay</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_countM['Count']) ?> VNĐ</div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

      <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Danh thu món ăn năm nay</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_countM1['Count']) ?> VNĐ</div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

      <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Hoá đơn phòng theo tuần</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_countW['Count']) ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Hoá đơn món ăn theo tuần</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_countW1['Count']) ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

      <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Hoá đơn phòng theo tháng</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_hdthang['Count']) ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

      <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Hoá đơn món ăn theo tháng</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_hdthang1['Count']) ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

      <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Hoá đơn phòng theo năm</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_hdnam['Count']) ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-success shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Hoá đơn món ăn phòng theo năm</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= number_format($data_hdnam1['Count']) ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <!-- Earnings (Monthly) Card Example -->
     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-primary shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Khách hàng</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= $data_nguoidung['Count'] ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-calendar fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <!-- Earnings (Monthly) Card Example -->
     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-primary shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Nhân viên</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= $data_nhanvien['Count'] ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-calendar fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>


   </div>

   <div class="row">

   <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-info shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Phòng Standard</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?=$data_tksp1['Count']?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-info shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
             <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Phòng Superior</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?=$data_tksp2['Count']?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <!-- Earnings (Monthly) Card Example -->
     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-info shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
             <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Phòng Deluxe</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?=$data_tksp3['Count']?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

      <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-info shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
             <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Phòng Giảm Giá</div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?=$data_tkspgiamgia['Count']?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>

     <!-- Pending Requests Card Example -->
     <div class="col-xl-3 col-md-6 mb-4">
       <div class="card border-left-warning shadow h-100 py-2">
         <div class="card-body">
           <div class="row no-gutters align-items-center">
             <div class="col mr-2">
               <div class="text-xs font-weight-bold text-warning text-uppercase mb-1"><a href="?mod=hoadon&id=0">Hóa đơn chưa duyệt</a></div>
               <div class="h5 mb-0 font-weight-bold text-gray-800"><?= $data_hd['Count'] ?></div>
             </div>
             <div class="col-auto">
               <i class="fas fa-comments fa-2x text-gray-300"></i>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>

 </div>
 <!-- /.container-fluid -->