-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 13, 2021 lúc 10:40 AM
-- Phiên bản máy phục vụ: 10.4.10-MariaDB
-- Phiên bản PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bookinghotel`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdondatmonan`
--

CREATE TABLE `chitietdondatmonan` (
  `id` int(11) NOT NULL,
  `madondatmonan` int(11) NOT NULL,
  `mamonan` int(11) NOT NULL,
  `tenmonan` varchar(150) NOT NULL,
  `soluong` int(11) NOT NULL,
  `giamonan` int(11) NOT NULL,
  `phongnhan` varchar(150) NOT NULL,
  `ngaynhan` date NOT NULL,
  `thoigiannhan` time NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdondatmonan`
--

INSERT INTO `chitietdondatmonan` (`id`, `madondatmonan`, `mamonan`, `tenmonan`, `soluong`, `giamonan`, `phongnhan`, `ngaynhan`, `thoigiannhan`, `trangthai`) VALUES
(82, 76, 1, 'Bánh Pizza Đế Thanh Long', 2, 400000, 'Phong 104', '2021-05-25', '09:10:00', 1),
(83, 76, 1, 'Bánh Pizza Đế Thanh Long', 2, 400000, 'Phong 104', '2021-05-25', '09:10:00', 1),
(84, 76, 1, 'Bánh Pizza Đế Thanh Long', 2, 400000, 'Phong 104', '2021-05-25', '09:10:00', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdondatphong`
--

CREATE TABLE `chitietdondatphong` (
  `id` int(11) NOT NULL,
  `madondatphong` int(11) NOT NULL,
  `maphong` int(11) NOT NULL,
  `tenphong` varchar(1000) NOT NULL,
  `giaphong` int(11) NOT NULL,
  `loaiphong` int(11) NOT NULL,
  `ngaydat` date NOT NULL,
  `ngaytra` date NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdondatphong`
--

INSERT INTO `chitietdondatphong` (`id`, `madondatphong`, `maphong`, `tenphong`, `giaphong`, `loaiphong`, `ngaydat`, `ngaytra`, `trangthai`) VALUES
(660, 653, 1, 'Phong 101', 500000, 1, '2021-05-27', '2021-05-29', 1),
(661, 662, 42, 'Phong 104', 500000, 1, '2021-05-29', '2021-05-31', 0),
(662, 662, 42, 'Phong 104', 500000, 1, '2021-05-29', '2021-05-31', 0),
(663, 662, 42, 'Phong 104', 500000, 1, '2021-05-29', '2021-05-31', 0),
(664, 663, 41, 'Phong 103', 350000, 1, '2021-07-05', '2021-07-07', 1),
(665, 664, 69, 'Phong 101', 350000, 1, '2021-07-01', '2021-07-02', 0),
(666, 664, 69, 'Phong 101', 350000, 1, '2021-07-01', '2021-07-02', 0),
(667, 665, 73, 'Phong 131', 690000, 3, '2021-07-18', '2021-07-20', 0),
(668, 665, 73, 'Phong 131', 690000, 3, '2021-07-18', '2021-07-20', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dichvudoan`
--

CREATE TABLE `dichvudoan` (
  `id` int(11) NOT NULL,
  `tenmonan` varchar(255) NOT NULL,
  `giamonan` varchar(255) NOT NULL,
  `hinhanh` varchar(255) NOT NULL,
  `hinhanh1` varchar(150) NOT NULL,
  `hinhanh2` varchar(150) NOT NULL,
  `hinhanh3` varchar(150) NOT NULL,
  `mota` longtext NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dichvudoan`
--

INSERT INTO `dichvudoan` (`id`, `tenmonan`, `giamonan`, `hinhanh`, `hinhanh1`, `hinhanh2`, `hinhanh3`, `mota`, `trangthai`) VALUES
(1, 'Bánh Pizza Đế Thanh Long', '200000', 'https://vcdn-vnexpress.vnecdn.net/2020/02/21/HN-PizzaThanhlong-10-2047-1582260592.jpg', 'https://media.baodautu.vn/Images/hongphuc/2020/02/17/IMG_0614.jpg', 'https://newsmd1fr.keeng.net/tiin/archive/images/20200218/091206_pizza_thanh_long_ruot_do_1.jpg', 'https://image.thanhnien.vn/1024/uploaded/congthang/2020_02_22/anh5_uzge.jpg', 'Bánh có đường kính 22 cm, có đế làm bằng thanh long tươi, phần nhân bên trên giữ nguyên như các loại pizza thông thường. Điều khác biệt là loại bánh này được cửa hàng trợ giá, giảm 50% còn 55.000 đồng để kích cầu tăng sản lượng thanh long tiêu thụ. ', 0),
(2, 'Strongbow', '200000', 'http://192.168.1.6/ServicesHotel/image/dichvu/strongbow-la-gi-uong-strongbow-co-say-khong-va-nhung-luu-y-khi-su-dung-strongbow-202103090942124635.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/strongbow-la-gi-uong-strongbow-co-say-khong-va-nhung-luu-y-khi-su-dung-strongbow-202103090942289107.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/strongbow-la-gi-uong-strongbow-co-say-khong-va-nhung-luu-y-khi-su-dung-strongbow-202103090942437530.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/strongbow-la-gi-uong-strongbow-co-say-khong-va-nhung-luu-y-khi-su-dung-strongbow-202011290906341083.jpg', 'những quả táo chín mọng được trải qua quá trình lên men tự nhiên mang đến men say thuần khiết, hài hoà và đầy cuốn hút. Với một chút ngọt dịu, thanh mát kết hợp cùng vị chát nhẹ đặc trưng của táo cùng độ men vừa phải (từ 3-8%) đủ để lâng lâng hứng khởi, Cider trở thành thức uống hội tụ đầy đủ tinh hoa một cách chọn lọc từ thiên nhiên.', 0),
(10, 'Rượu Vang Đỏ', '1950000', 'http://192.168.1.6/ServicesHotel/image/dichvu/ruou1.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/ruou2.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/ruou3.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/ruou1.jpg', 'Rượu vang đỏ hay còn gọi là rượu nho đỏ là loại rượu vang có màu đỏ thẫm. Rượu nho đỏ được làm từ những trái nho có màu đỏ thẫm, tím đen, sau khi ép nước, tách vỏ sẽ được đem ủ lên men và tạo ra thức uống đó là rượu vang. Quá trình sản xuất rượu vang thì mỗi một nhà sản xuất, giống nho, loại rượu sẽ có những cách sản xuất riêng mang đến thức uống đặc trưng khác nhau.', 0),
(11, 'Rượu Chivas18', '1150000', 'http://192.168.1.6/ServicesHotel/image/dichvu/Ruou-Chivas-18-nam-1100x1100h.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/ma-qa-code-chivas-18-nam-1-1100x1100h.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/chivas-18-nam-mau-moi-ruou-ngoai-gia-si.vn-1100x1100h.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/ma-qa-code-chivas-18-nam-1.png', 'Chivas regal 18 Year old hay còn gọi với tên thân quen Rượu Chivas 18 là dòng sản phẩm tầm trung nhưng lại có chất lượng cao, mang đến cho bạn những trải nghiệm về rượu whisky rất thú vị vì nguyên liệu được lựa chọn kỹ càng bằng tay của những người dân Scotland, mang đến một hương thơm ngát thật khác biệt của gỗ và mùi vị hoa quả khô được tinh kết.', 0),
(12, 'Tôm Hùm Alaska', '199000', 'http://192.168.1.6/ServicesHotel/image/dichvu/21105680_780172328774221_2854715046442827041_n_2a4454ff80fb455cb29f9afc27e5f031_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/48240838_720593934962531_1589005176440619008_o_77f9f5ec4d624eb3b762de067723de81_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/52659798_819386015078198_6963486955824742400_o_0588e13783694762b4f38260aaac8206_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/h2_cook_lobster_beauty_1kuk_small.png', 'Tôm hùm Alaska chín được xông nhiệt từ những con còn sống theo tiêu chuẩn Châu Âu. Được nhập từ Canada về tới TPHCM chất lượng cam kết Tôm có thịt dai, ngọt đậm, thịt trắng, đặc biệt ở phần đầu có nhiều gạch, rất béo, phần càng thì ăn thịt rất ngon.', 0),
(13, 'Chả Ghẹ Farci', '150000', 'http://192.168.1.6/ServicesHotel/image/dichvu/cha_ghe_8e1152daa7ed42d9b29c74aa25a694cb_small.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/z2170396646149_c705c76e934843818ac3883e7bd84388_afbaca77101a48c8a347014ecda5add3_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/z2170396764204_32c3a8ce83517a1de9441182e7c9eba9_2364c7a02c1f4c85b9660c1a486eec56_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/z2170397471736_116c61b280e9e4d54c48154151742288_36acb6d2a90f4eb5971f1c9252372370_small.png', 'Chả Ghẹ Farci( khay 420gr ) tươi ngon và tiện lợi nên được nhiều khách yêu thích. Có thể mua bảo quản tủ đông ăn dần mà chất lượng vẫn tươi ngon nhé. Thành phần gồm có :- Thịt ghẹ, mai ghẹ, tôm, mực, thịt cá đổng, bún tàu, hành tây, dầu thực vật, cà rốt, hành lá, muối, tiêu, đường, chất điều vị.', 0),
(14, 'Crawfish Tôm Hùm Đất', '725000', 'http://192.168.1.6/ServicesHotel/image/dichvu/66787086_1082374961961516_5907000053913932186_n_fc2cfd61ce28429c9f2cb5faf410263a_small.jpg', 'http://192.168.1.6/ServicesHotel/image/dichvu/600x700_86f5c409ab7c4814bbf483c484c48733_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/dep_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/naaaaaa_small.png', 'Crawfish hay còn gọi là Tôm hùm đất là một loại tôm hùm nhỏ. Thịt tôm tập trung ở phần thân, chắc dai và ngọt, gạch Crawfish ăn béo rất ngon. Đây là loại tôm được nhiều khách hàng tại Việt Nam ưa chuộng.', 0),
(15, 'Ốc Bulot Pháp', '350000', 'http://192.168.1.6/ServicesHotel/image/dichvu/dsc09169_8d77c3f48ee34ef9a6763bd0c51377b9_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/z2516590076087_8a910fa0acc0e26b9f24c1233a734cfe_8aa8b22c7a1742bbb74753c4a0bdec2b_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/65679604_2386633928097589_3960796012620021760_o_ce7e950b9e9d41c5a781016a5d0575d8_small.png', 'http://192.168.1.6/ServicesHotel/image/dichvu/89447616_140956670739896_811716804257775616_n_bd636ffdd70e4388b65e911c479a1e2b_small.jpg', 'Ốc bulot được đánh bắt ở vùng biển Normandy- Pháp, chính vùng biển này tạo nên hương vị đặc biệt với phần thịt giòn giòn, dai dai, ngọt đậm hơn so các loại ốc bulot ở những nơi khác', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dichvuyeuthich`
--

CREATE TABLE `dichvuyeuthich` (
  `id` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idMonAn` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dichvuyeuthich`
--

INSERT INTO `dichvuyeuthich` (`id`, `idUser`, `idMonAn`) VALUES
(1, 15, 1),
(2, 15, 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dondatmonan`
--

CREATE TABLE `dondatmonan` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(250) NOT NULL,
  `diachi` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `phone` varchar(250) NOT NULL,
  `idKhachHang` int(11) NOT NULL,
  `huydon` int(11) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dondatmonan`
--

INSERT INTO `dondatmonan` (`id`, `tenkhachhang`, `diachi`, `email`, `phone`, `idKhachHang`, `huydon`, `trangthai`) VALUES
(76, 'Trần Khánh Duy', 'Quảng Ngãi', 'khanhduyboyka@gmail.com', '0329334542', 15, 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dondatphong`
--

CREATE TABLE `dondatphong` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(150) NOT NULL,
  `diachi` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `idKhachHang` int(11) NOT NULL,
  `huydon` int(11) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dondatphong`
--

INSERT INTO `dondatphong` (`id`, `tenkhachhang`, `diachi`, `email`, `phone`, `idKhachHang`, `huydon`, `trangthai`) VALUES
(653, 'Trần Khánh Duy', 'Quảng Ngãi', 'khanhduyboyka@gmail.com', '0329334542', 13, 0, 1),
(662, 'Trần Khánh Duy', 'Quảng Ngãi', 'khanhduyboyka@gmail.com', '0329334542', 15, 0, 0),
(663, 'Trần Khánh Duy', 'Quảng Ngãi', 'khanhduyboyka@gmail.com', '0329334542', 15, 0, 1),
(664, 'Trần Khánh Duy', 'Quảng Ngãi', 'khanhduyboyka@gmail.com', '0329334542', 15, 1, 0),
(665, 'Trần Khánh Duy', 'Quảng Ngãi', 'khanhduyboyka@gmail.com', '0329334542', 15, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaiphong`
--

CREATE TABLE `loaiphong` (
  `id` int(11) NOT NULL,
  `tenloaiphong` varchar(200) NOT NULL,
  `hinhanhloaiphong` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaiphong`
--

INSERT INTO `loaiphong` (`id`, `tenloaiphong`, `hinhanhloaiphong`) VALUES
(1, 'Phòng Standard', 'http://192.168.1.6/ServicesHotel/image/loaiphong/standard.jpg'),
(2, 'Phòng Superior', 'http://192.168.1.6/ServicesHotel/image/loaiphong/superior.jpg'),
(3, 'Phòng Deluxe', 'http://192.168.1.6/ServicesHotel/image/loaiphong/deluxe.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phong`
--

CREATE TABLE `phong` (
  `id` int(11) NOT NULL,
  `tenphong` varchar(200) NOT NULL,
  `giaphong` int(15) NOT NULL,
  `hinhanhphong` varchar(200) NOT NULL,
  `hinhanh2` varchar(150) NOT NULL,
  `hinhanh3` varchar(150) NOT NULL,
  `hinhanh4` varchar(150) NOT NULL,
  `giamoi` int(11) NOT NULL,
  `giamgia` int(11) NOT NULL,
  `mota` longtext NOT NULL,
  `idphong` int(11) NOT NULL,
  `trangthai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phong`
--

INSERT INTO `phong` (`id`, `tenphong`, `giaphong`, `hinhanhphong`, `hinhanh2`, `hinhanh3`, `hinhanh4`, `giamoi`, `giamgia`, `mota`, `idphong`, `trangthai`) VALUES
(57, 'Phong 119', 1900000, 'http://192.168.1.6/ServicesHotel/image/phong/Superior-Double-2.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/Superior-570x305.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/MG_0090.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/05-1.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(59, 'Phong 121', 2510000, 'http://192.168.1.6/ServicesHotel/image/phong/Deluxe-570x305.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/04-1.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/05-2.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/Deluxe-Double-3-1.jpg', 2259000, 10, 'Nội thất hiện đại, không gian ấm cúng, phòng Deluxe với hai giường ngủ cỡ lớn là sự lựa chọn hoàn hảo giành cho bạn trong những chuyến đi xa cùng gia đình hoặc bạn bè. Đặc biệt, các trang thiết bị cao cấp trong phòng có thể đáp ứng mọi nhu cầu của bạn, khiến kỳ nghỉ của bạn trở nên tuyệt vời hơn bao giờ hết.', 1, 0),
(69, 'Phong 101', 350000, 'http://192.168.1.6/ServicesHotel/image/phong/hinh1.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/262683112.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/262682559.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/258481354.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(70, 'Phong 120', 450000, 'http://192.168.1.6/ServicesHotel/image/phong/1b32aa1fe1de0fff6ca79a416476bb81.png', 'http://192.168.1.6/ServicesHotel/image/phong/7b7c38ce4a89b16b29ea40431dfd7d04.png', 'http://192.168.1.6/ServicesHotel/image/phong/290dc38ac947e4b20170fd7119f330d0.png', 'http://192.168.1.6/ServicesHotel/image/phong/bb6d9b06a87e321053ad1731878d885c.png', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(71, 'Phong 130', 450000, 'http://192.168.1.6/ServicesHotel/image/phong/230635458.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/230635454.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/230635190.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/193711037.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 3, 0),
(72, 'Phong 122', 235000, 'http://192.168.1.6/ServicesHotel/image/phong/224809465.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/224809437.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/224809430.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/224809415.jpg', 188000, 20, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(73, 'Phong 131', 345000, 'http://192.168.1.6/ServicesHotel/image/phong/11088582_19121114540085585527.png', 'http://192.168.1.6/ServicesHotel/image/phong/11088582_19121114480085583809.png', 'http://192.168.1.6/ServicesHotel/image/phong/232910040.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/232910032.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 3, 0),
(74, 'Phong 123', 234000, 'http://192.168.1.6/ServicesHotel/image/phong/6ea672804c3f148196788d450a87f97d.png', 'http://192.168.1.6/ServicesHotel/image/phong/2100001_17042321000052561865.png', 'http://192.168.1.6/ServicesHotel/image/phong/f70aae8093be4ae7264e2761f0e967a6.png', 'http://192.168.1.6/ServicesHotel/image/phong/2100001_17102612180058348079.png', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(75, 'Phong 110', 200000, 'http://192.168.1.6/ServicesHotel/image/phong/296026166.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/2c6f840dedf303383109ef09f330e7f6.png', 'http://192.168.1.6/ServicesHotel/image/phong/268710425.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710432.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(76, 'Phong 124', 256000, 'http://192.168.1.6/ServicesHotel/image/phong/268710346.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710338.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710344.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710434.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(77, 'Phong 102', 234000, 'http://192.168.1.6/ServicesHotel/image/phong/268710320.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710429.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710380.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710331.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(78, 'Phong 132', 435000, 'http://192.168.1.6/ServicesHotel/image/phong/268710402.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710343.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710390.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/268710413.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 3, 0),
(79, 'Phong 133', 546000, 'http://192.168.1.6/ServicesHotel/image/phong/c5b185a0e283bdcbead72faa6f34dc77.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/270113290.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/270111810.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/189698415.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 3, 0),
(80, 'Phong 125', 215000, 'http://192.168.1.6/ServicesHotel/image/phong/189697167.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/6944959_19040922400073631921.png', 'http://192.168.1.6/ServicesHotel/image/phong/189697177.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/189698793.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 2, 0),
(81, 'Phong 134', 456000, 'http://192.168.1.6/ServicesHotel/image/phong/189698412.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/189698401.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/189698126.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/181939439.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 3, 0),
(82, 'Phong 126', 256000, 'http://192.168.1.6/ServicesHotel/image/phong/6944959_19040922370073631871.png', 'http://192.168.1.6/ServicesHotel/image/phong/6944959_19040922360073631844.png', 'http://192.168.1.6/ServicesHotel/image/phong/6944959_19040922370073631863.png', 'http://192.168.1.6/ServicesHotel/image/phong/6944959_19040922360073631836.png', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(83, 'Phong 135', 465000, 'http://192.168.1.6/ServicesHotel/image/phong/279556353.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/267132_15030611450025862599.png', 'http://192.168.1.6/ServicesHotel/image/phong/267132_15030611370025862400.png', 'http://192.168.1.6/ServicesHotel/image/phong/267132_15030611240025861777.png', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 3, 0),
(84, 'Phong 103', 145000, 'http://192.168.1.6/ServicesHotel/image/phong/95ba37a9b31fbe079061d2b2efe414c4.png', 'http://192.168.1.6/ServicesHotel/image/phong/d907fe9be89c4610ebc12524b981d8aa.png', 'http://192.168.1.6/ServicesHotel/image/phong/c2147a254711c9b0dd8ad7b709c45931.png', 'http://192.168.1.6/ServicesHotel/image/phong/f9727c3b3e14c916c81ae536cbf81392.png', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(85, 'Phong 104', 230000, 'http://192.168.1.6/ServicesHotel/image/phong/187893784.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/193408146.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/200211749.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/284728037.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(86, 'Phong 136', 457000, 'http://192.168.1.6/ServicesHotel/image/phong/263035518.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/263035537.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/263035533.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/263043008.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 3, 0),
(87, 'Phong 127', 301000, 'http://192.168.1.6/ServicesHotel/image/phong/140719165.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/140719173.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/140719161.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/140720309.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 2, 0),
(88, 'Phong 128', 254000, 'http://192.168.1.6/ServicesHotel/image/phong/260623110.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/260623194.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/260623478.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/260623183.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 1, 0),
(89, 'Phong 129', 212000, 'http://192.168.1.6/ServicesHotel/image/phong/167789891.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/271333451.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/271216397.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/250245475.jpg', 0, 0, 'Ấm cúng, giản dị và tiện nghi, phòng Superior với thiết kế trang nhã và không gian yên tĩnh, là tất cả những gì bạn mong đợi khi đến với một khách sạn 5 sao. Bạn có thể lựa chọn phòng ngủ 01 giường đôi hoặc 02 giường đơn tùy theo nhu cầu sử dụng.', 2, 0),
(90, 'Phong 137', 453000, 'http://192.168.1.6/ServicesHotel/image/phong/205949438.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/205949502.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/205949472.jpg', 'http://192.168.1.6/ServicesHotel/image/phong/209381781.jpg', 0, 0, '<p>test</p>', 3, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phongyeuthich`
--

CREATE TABLE `phongyeuthich` (
  `id` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idPhong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phongyeuthich`
--

INSERT INTO `phongyeuthich` (`id`, `idUser`, `idPhong`) VALUES
(1, 15, 57),
(4, 15, 58),
(7, 15, 59),
(9, 15, 61),
(10, 15, 67),
(11, 15, 41),
(12, 15, 73);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `slide`
--

CREATE TABLE `slide` (
  `id` int(11) NOT NULL,
  `anhslide` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `slide`
--

INSERT INTO `slide` (`id`, `anhslide`) VALUES
(4, 'http://192.168.1.6/ServicesHotel/image/slide/images.jpg'),
(5, 'http://192.168.1.6/ServicesHotel/image/slide/thiet-ke-noi-that-khach-san-3-533x400.jpg'),
(6, 'http://192.168.1.6/ServicesHotel/image/slide/khach-san-da-lat-view-dep-cho-tam-hon-lang-man-bay-bong-1.jpg'),
(7, 'http://192.168.1.6/ServicesHotel/image/slide/khach-san-Citadines-Bayfront-Nha-Trang-ivivu-11.jpg'),
(8, 'http://192.168.1.6/ServicesHotel/image/slide/163451006.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `birthday` date NOT NULL,
  `address` varchar(250) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `taikhoan` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `MaQuyen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `name`, `birthday`, `address`, `email`, `phone`, `taikhoan`, `password`, `MaQuyen`) VALUES
(1, 'Tran Khanh Duy', '2021-05-13', 'Quang Ngai', 'khanhduyboyka1@gmail.com', '0329334542', 'khanhduytran', 'eLQgjAfT', 1),
(13, 'Trần Thị Hạnh Hòa', '2001-04-03', 'Đà Nẵng', 'hhoahi2001@gmail.com', '0774539469', 'hhoahi', 'hhoahi', 1),
(14, 'Quý trâm', '2021-05-25', 'gia lai', 'quytram@gmail.com', '1234567', 'quytram', '123', 1),
(15, 'Trần Khánh Duy', '2000-08-29', 'Quảng Ngãi', 'khanhduyboyka@gmail.com', '0329334542', 'tester', 'Vzokk7Jd', 2),
(16, 'duy', '2021-05-28', 'rew', 'khanhduy123@gmail.com', '423', 'test123', '12345', 2),
(17, 'fds', '2021-07-25', 'fdsa', 'test@gmail.com', '0329334542', 'tessst', '12345', 3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdondatmonan`
--
ALTER TABLE `chitietdondatmonan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `chitietdondatphong`
--
ALTER TABLE `chitietdondatphong`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `dichvudoan`
--
ALTER TABLE `dichvudoan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `dichvuyeuthich`
--
ALTER TABLE `dichvuyeuthich`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `dondatmonan`
--
ALTER TABLE `dondatmonan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `dondatphong`
--
ALTER TABLE `dondatphong`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaiphong`
--
ALTER TABLE `loaiphong`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `phong`
--
ALTER TABLE `phong`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `phongyeuthich`
--
ALTER TABLE `phongyeuthich`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `slide`
--
ALTER TABLE `slide`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdondatmonan`
--
ALTER TABLE `chitietdondatmonan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT cho bảng `chitietdondatphong`
--
ALTER TABLE `chitietdondatphong`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=669;

--
-- AUTO_INCREMENT cho bảng `dichvudoan`
--
ALTER TABLE `dichvudoan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `dichvuyeuthich`
--
ALTER TABLE `dichvuyeuthich`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `dondatmonan`
--
ALTER TABLE `dondatmonan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT cho bảng `dondatphong`
--
ALTER TABLE `dondatphong`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=666;

--
-- AUTO_INCREMENT cho bảng `loaiphong`
--
ALTER TABLE `loaiphong`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `phong`
--
ALTER TABLE `phong`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT cho bảng `phongyeuthich`
--
ALTER TABLE `phongyeuthich`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `slide`
--
ALTER TABLE `slide`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
