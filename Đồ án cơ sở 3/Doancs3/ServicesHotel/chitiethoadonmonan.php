<?php
require('dbConnect.php');
use PHPMailer\PHPMailer\PHPMailer;                                    
use PHPMailer\PHPMailer\Exception;

$email = $_POST['email'];
$madondatmonan = $_POST['madonhang'];
$mamonan = $_POST['mamonan'];
 $tenmonan = $_POST['tenmonan'];
 $soluong = $_POST['soluong'];
 $giamonan = $_POST['giamonan'];
 $phongnhan = $_POST['phongnhan'];
 $ngaynhan = $_POST['ngaynhan'];
 $gionhan = $_POST['gionhan'];

 $arrayThongBao = array();
    $trangthai = 0;
    $huydon = 0;

$sqlInsertCTMA = "INSERT INTO chitietdondatmonan(id,madondatmonan,mamonan,tenmonan,soluong,giamonan,phongnhan,ngaynhan,thoigiannhan,trangthai) values (null,'$madondatmonan','$mamonan','$tenmonan','$soluong','$giamonan','$phongnhan',STR_TO_DATE('$ngaynhan','%d-%m-%Y'),STR_TO_DATE('$gionhan','%H:%i'),'$trangthai')";

       
        if( mysqli_query($connect,$sqlInsertCTMA)){

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
                    $mail->Subject = "Đặt món ăn thành công!";

                    $mail->Body ="Cảm ơn quý khách đã đặt món ăn tại Đà Nẵng Hotel <br>". "Mã đơn đặt món của quý khách là :". $madondatmonan . "<br>" . "Tên Món :" . $tenmonan . "<br>" . "Số Lượng :" . $soluong ."<br>" . "Tổng tiền :" . $giamonan . "<br>" . "Số Phòng Nhận Món:" . $phongnhan . "<br>" . "Ngày Nhận Món :" . $ngaynhan . "<br>" ."Giờ Nhận Món: ". $gionhan . "<br>". "Đà Nẵng Hotel hân hạnh phục vụ quý khách";
                   
                     $mail->send();
                    
                        $arrayThongBao['success'] = 1;
                        $arrayThongBao['message'] = "Đặt món ăn thành công!";
                       
                    
                } catch (Exception $e) {
                $arrayThongBao['success'] = 0;
                $arrayThongBao['message'] = "Lỗi hệ thống";
                }

                }
                    
        else{
            $arrayThongBao['success'] = 0;
            $arrayThongBao['message'] = "Lỗi câu lệnh";
        } 

        echo json_encode($arrayThongBao);
 ?>