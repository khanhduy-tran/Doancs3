<?php
session_start(); 
require('dbConnect.php');
$phonenumber = $_POST['phonenumber'];
$query='select * from user';

$data = mysqli_query($connect,$query);

class User{
		function User($phone,$otp){
			$this->Phone = $phone;
			$this->Otp = $otp;
		}
	}
class Error{
	function Error($code,$message){
		$this->Code = $code;
		$this->Message = $message;
	}
}	
	$array= array();
	$error = array();

$row = mysqli_fetch_assoc($data);

require("SpeedSMSAPI.php");

 if($row['phone'] != $phonenumber){
 	
 	$smsAPI = new SpeedSMSAPI("wi75GM_zEDSKERSIjygTU3y7mJIXKaRq");

 	$phones = ["$phonenumber"]; 
 	
 	$otp = rand(1000,9999);

 	$_SESSION['otp'] = $otp;
/* tối đa 100 số cho 1 lần gọi API */
$content = "Mã xác nhận của bạn là: " . $otp;
$type = SMS_TYPE_CSKH;
/**
sms_type có các giá trị như sau:
2: tin nhắn gửi bằng đầu số ngẫu nhiên
3: tin nhắn gửi bằng brandname
4: tin nhắn gửi bằng brandname mặc định (Verify hoặc Notify)
5: tin nhắn gửi bằng app android
*/
 $sender = "Hệ thống HotelBooking";
/**
brandname là tên thương hiệu hoặc số điện thoại đã đăng ký với SpeedSMS
*/

$response = $smsAPI->sendSMS($phones, $content, $type, $sender);

/**hàm sendSMS sẽ trả về một mảng như sau:*/
// [
//    "status"=>"success", "code"=> "00", 
//    "data"=>[
//     "tranId"=>123456, "totalSMS"=>2,     
//      "totalPrice"=>500, "invalidPhone"=>[] 
//       ]
// ]

// // Trong trường hợp gửi sms bị lỗi, hàm sendSMS sẽ trả về mảng như sau:
// [
// "status"=>"error", "code"=>"error code", "message" => "error description",
// "invalidPhone"=>["danh sách sdt lỗi"]
// ]
if($response == true){
	array_push($array,new User($phones,$otp));
}else{
	array_push($error,new Error(0,'lỗi hệ thống'));
}
 }else{
 	array_push($error,new Error(0,'số điện thoại đã được đăng kí'));
 } 

 json_encode($array);
 json_encode($error);
 ?>