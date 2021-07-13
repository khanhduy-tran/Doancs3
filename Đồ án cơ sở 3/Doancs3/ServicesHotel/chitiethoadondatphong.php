<?php
require('dbConnect.php');

use PHPMailer\PHPMailer\PHPMailer;                                    
use PHPMailer\PHPMailer\Exception;
$madonphong = $_POST['madonphong'];
$maphong = $_POST['maphong'];
$tenphong = $_POST['tenphong'];
$giaphong = $_POST['giaphong'];
$loaiphong = $_POST['loaiphong'];
$ngaydat =  $_POST['ngaydat'];
$ngaytra =  $_POST['ngaytra'];
$email = $_POST['email'];
$arrayThongBao = array();

$trangthai = 0;

$sqlInsertCTDH = "insert into chitietdondatphong values(null,'$madonphong','$maphong','$tenphong','$giaphong','$loaiphong',STR_TO_DATE('$ngaydat','%d-%m-%Y'),STR_TO_DATE('$ngaytra','%d-%m-%Y'),'$trangthai')";

   		$result1 = mysqli_query($connect,$sqlInsertCTDH);

   		if($result1){
   			       include 'public1/libs/vendor/library.php';

 	               include 'public1/libs/vendor/autoload.php';

                     $mail = new PHPMailer(true);                              // Passing `true` enables exceptions
                     try {
                    //Server settings
                    //Server settings
                    $mail->CharSet = "UTF-8";
                    $mail->SMTPDebug = 0;                                 // Enable verbose debug output
                    $mail->isSMTP();                                      // Set mailer to use SMTP
                    $mail->Host = SMTP_HOST;  // Specify main and backup SMTP servers
                    $mail->SMTPAuth = true;                               // Enable SMTP authentication
                    $mail->Username = SMTP_UNAME;                 // SMTP username
                    $mail->Password = SMTP_PWORD;                           // SMTP password
                    $mail->SMTPSecure = 'ssl';                            // Enable TLS encryption, `ssl` also accepted
                    $mail->Port = SMTP_PORT;                                    // TCP port to connect to
                    //Recipients
                    $mail->setFrom(SMTP_UNAME, "Hệ thống Đà Nẵng Hotel");
                    $mail->addAddress($email, 'Tên người nhận');     // Add a recipient | name is option
                    $mail->addReplyTo(SMTP_UNAME, 'Tên người trả lời');
//                    $mail->addCC('CCemail@gmail.com');
//                    $mail->addBCC('BCCemail@gmail.com');
                    $mail->isHTML(true);                                  // Set email format to HTML
                    $mail->Subject = "Đặt phòng thành công!";

                    $mail->Body ="Cảm ơn quý khách đã đặt phòng tại Đà Nẵng Hotel <br>". "Mã đơn đặt phòng của quý khách là :". $madonphong . "<br>" . "Tên Phòng :" . $tenphong . "<br>" . "Giá phòng :" . $giaphong . "đ" ."<br>" . "Loại phòng :" . $loaiphong . "<br>" . "Ngày đặt phòng:" . $ngaydat . "<br>" . "Ngày trả phòng :" . $ngaytra . "<br>" . "Đà Nẵng Hotel hân hạnh phục vụ quý khách";
                      //None HTML
                    $result = $mail->send();

                    if ($result) {
                    	$arrayThongBao['success'] = 1;
                    	$arrayThongBao['message'] = "Đặt phòng thành công";
                    }else{
                    	$arrayThongBao['success'] = 0;
                    	$arrayThongBao['message'] = "Có lỗi xảy ra trong quá trình gửi mail";
                    }

                } catch (Exception $e) {

                $arrayThongBao['success'] = 0;
                $arrayThongBao['message'] = "Lỗi hệ thống";
                }
   		}else{
   			$arrayThongBao['success'] = 0;
   			$arrayThongBao['message'] = "Lỗi đặt phòng";
   		} 
   		echo json_encode($arrayThongBao);
 ?>