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
                    $mail->setFrom(SMTP_UNAME, "H??? th???ng ???? N???ng Hotel");
                    $mail->addAddress($email, 'T??n ng?????i nh???n');     // Add a recipient | name is option
                    $mail->addReplyTo(SMTP_UNAME, 'T??n ng?????i tr??? l???i');
//                    $mail->addCC('CCemail@gmail.com');
//                    $mail->addBCC('BCCemail@gmail.com');
                    $mail->isHTML(true);                                  // Set email format to HTML
                    $mail->Subject = "?????t m??n ??n th??nh c??ng!";

                    $mail->Body ="C???m ??n qu?? kh??ch ???? ?????t m??n ??n t???i ???? N???ng Hotel <br>". "M?? ????n ?????t m??n c???a qu?? kh??ch l?? :". $madondatmonan . "<br>" . "T??n M??n :" . $tenmonan . "<br>" . "S??? L?????ng :" . $soluong ."<br>" . "T???ng ti???n :" . $giamonan . "<br>" . "S??? Ph??ng Nh???n M??n:" . $phongnhan . "<br>" . "Ng??y Nh???n M??n :" . $ngaynhan . "<br>" ."Gi??? Nh???n M??n: ". $gionhan . "<br>". "???? N???ng Hotel h??n h???nh ph???c v??? qu?? kh??ch";
                   
                     $mail->send();
                    
                        $arrayThongBao['success'] = 1;
                        $arrayThongBao['message'] = "?????t m??n ??n th??nh c??ng!";
                       
                    
                } catch (Exception $e) {
                $arrayThongBao['success'] = 0;
                $arrayThongBao['message'] = "L???i h??? th???ng";
                }

                }
                    
        else{
            $arrayThongBao['success'] = 0;
            $arrayThongBao['message'] = "L???i c??u l???nh";
        } 

        echo json_encode($arrayThongBao);
 ?>