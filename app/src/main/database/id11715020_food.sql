-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3306
-- Thời gian đã tạo: Th7 15, 2020 lúc 04:40 AM
-- Phiên bản máy phục vụ: 10.3.16-MariaDB
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
-- Cơ sở dữ liệu: `id11715020_food`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `doan`
--

CREATE TABLE `doan` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `img` varchar(200) NOT NULL,
  `idLoai` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `doan`
--

INSERT INTO `doan` (`id`, `name`, `img`, `idLoai`) VALUES
('1', 'Cơm tấm', 'https://baogiadinhso.com/wp-content/uploads/2018/12/cach-nau-com-tam-suon-bi-cha.jpg', '1'),
('10', 'Cafe rang xay sữa', 'https://sevencafe.net/wp-content/uploads/2018/10/espresso.jpg', '2'),
('11', 'Cafe Cappuccino', 'https://cdn02.static-adayroi.com/0/2016/10/24/1477296657863_1527073.jpg', '2'),
('12', 'Cafe Latte', 'https://sevencafe.net/wp-content/uploads/2018/10/hotlatte.png', '2'),
('13', 'Nước ép cam', 'https://coffeefinance.vn/wp-content/uploads/2019/04/n%C6%B0%E1%BB%9Bc-%C3%A9p-cam.jpg', '2'),
('14', 'Nước chanh', 'https://sevencafe.net/wp-content/uploads/2018/10/nuocchanh.jpg', '2'),
('15', 'Nước ép cà rốt', 'https://sevencafe.net/wp-content/uploads/2018/10/nuoc-ep-ca-rot_master.png', '2'),
('16', 'Nước ép dưa hấu', 'http://www.dakhat.com/wp-content/uploads/2014/05/duahau.jpg', '2'),
('17', 'Trà sữa truyền thống', 'https://thptnguyenthaibinh.canteen.vn/image/cache/catalog/san-pham/tra-sua/tra-sua-truyen-thong-500x500.jpg', '2'),
('18', 'Sinh tố dâu', 'http://ganhhao.com.vn/wp-content/uploads/2018/02/sinh-to-dau-yogurt-600x600.jpg', '2'),
('19', 'Trà đào', 'https://cdn.dayphache.edu.vn/wp-content/uploads/2019/05/hong-tra-dao.jpg', '2'),
('2', 'Bún bò Huế', 'https://image.thanhnien.vn/980/uploaded/congthang/2019_10_09/anh_1_dzzf.jpg', '1'),
('20', 'Trà vãi', 'https://sevencafe.net/wp-content/uploads/2018/10/travai.jpg', '2'),
('3', 'Phở', 'https://thammyviennevada.com/wp-content/uploads/2019/08/an-pho-co-beo-khong-1.jpg', '1'),
('4', 'Bún đậu mắm tôm ', 'https://ameovat.com/wp-content/uploads/2018/11/cach-lam-bun-dau-mam-tom-01-600x343.jpg', '1'),
('5', 'Mì xào hải sản', 'https://cdn.netspace.edu.vn/news/2019/1/19/mi-xao-hai-san-6-1024-183533.jpg', '1'),
('6', 'Mì cay', 'https://nauankhongkho.com/wp-content/uploads/2016/10/image-mi-cay.jpg', '1'),
('7', 'Cơm chiên dương châu', 'https://cdn.netspace.edu.vn/news/2018/12/12/com-chien-duong-chau-5-1024-162720.jpg', '1'),
('8', 'Cao lầu', 'https://i.pinimg.com/originals/50/d3/34/50d334fb214c2c03a0594d81cd8c875c.jpg', '1'),
('9', 'Bún chả Hà Nội', 'https://amthucnamchau.org/wp-content/uploads/2017/03/cach-lam-bun-cha-thom-ngon-dung-chuan-ha-noi.jp', '1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `idKh` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `idRes` varchar(11) NOT NULL,
  `Total` int(11) NOT NULL,
  `date_receive` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `idKh`, `address`, `idRes`, `Total`, `date_receive`) VALUES
(90, 'admin', 'Đà nẵng', '2', 175000, '2005-11-20'),
(101, 'haha', 'Huế', '10', 20000, '2005-11-20'),
(102, 'thanh', 'Huế', '2', 15000, '2019-11-25'),
(103, 'thanh', 'Đà nẵng', '10', 330000, '2019-11-25'),
(104, 'cong', 'Đà Nẵng', '1', 354000, '2019-11-25'),
(105, 'cong', 'Huế', '10', 488000, '2019-11-25'),
(106, 'cong', 'Đà nẵng', '8', 124000, '2019-11-25'),
(107, 'cong', 'cong', '10', 150000, '2019-12-03'),
(108, 'cong', 'huế', '4', 55000, '2019-12-05'),
(109, 'cong', 'đấy', '1', 320000, '2019-12-05'),
(110, 'cong', 'adc', '1', 27000, '2019-12-05'),
(111, 'cong', 'Quảng Nam', '1', 96000, '2019-12-06'),
(112, 'cong', 'Huế', '1', 54000, '2019-12-06'),
(113, 'cong', 'Huế', '1', 27000, '2019-12-06'),
(114, 'cong', 'Huế', '1', 27000, '2019-12-06'),
(115, 'cong', 'Huế', '1', 27000, '2019-12-06'),
(116, 'cong', 'huế', '1', 111000, '2019-12-06'),
(117, 'cong', 'abc', '10', 45000, '2019-12-06'),
(118, 'thanh', 'huế', '1', 27000, '2019-12-06'),
(119, 'thanh', 'ĐN', '1', 27000, '2019-12-06'),
(120, 'cong', 'huế', '1', 27000, '2019-12-06'),
(121, 'cong', 'huế', '1', 144000, '2019-12-06'),
(122, 'cong', 'huế', '10', 45000, '2019-12-06'),
(123, 'cong', 'huế', '10', 45000, '2019-12-06'),
(124, 'cong', 'huế', '1', 135000, '2019-12-06'),
(125, 'cong', 'cong', '1', 42000, '2019-12-06'),
(126, 'cong', 'cong', '1', 42000, '2019-12-06'),
(127, 'cong', 'huế', '1', 27000, '2019-12-06'),
(128, 'cong', 'huế', '10', 95000, '2019-12-06'),
(129, 'cong', 'đn', '10', 45000, '2019-12-06'),
(130, 'cong', 'đn', '10', 70000, '2019-12-06'),
(131, 'cong', 'đn', '10', 73000, '2019-12-06'),
(132, 'cong', 'huế', '10', 45000, '2019-12-06'),
(133, 'cong', 'huế', '10', 155000, '2019-12-06'),
(134, 'cong', 'huế', '10', 75000, '2019-12-06'),
(135, 'thanh', 'huế', '1', 27000, '2019-12-06'),
(136, 'cong', 'huế', '1', 62000, '2019-12-07'),
(137, 'cong', 'huế', '1', 151000, '2019-12-07'),
(138, 'cong', 'huế', '1', 245000, '2019-12-07'),
(139, 'cong', 'huế', '1', 245000, '2019-12-07'),
(140, 'cong', 'huế', '10', 345000, '2019-12-07'),
(141, 'cong', 'huế', '10', 100000, '2019-12-07'),
(142, 'cong', 'Huế', '10', 258000, '2019-12-07'),
(143, 'cong', 'huế', '10', 344000, '2019-12-07'),
(144, 'cong', 'huế', '10', 160000, '2019-12-07'),
(145, 'thanh', 'huế', '1', 36000, '2019-12-07'),
(146, 'thanh', 'huế', '10', 153000, '2019-12-07'),
(147, 'thanh', 'huế', '1', 87000, '2019-12-07'),
(148, 'thanh', 'huế', '10', 73000, '2019-12-07'),
(149, 'thanh', 'huế', '10', 143000, '2019-12-07'),
(150, 'thanh', 'huế', '1', 86000, '2019-12-07'),
(151, 'thanh', 'huế', '10', 143000, '2019-12-07'),
(152, 'thanh', 'huế', '10', 138000, '2019-12-07'),
(153, 'thanh', 'huế', '10', 138000, '2019-12-07'),
(154, 'thanh', 'huế', '10', 364000, '2019-12-07'),
(155, 'thanh', 'huế', '10', 128000, '2019-12-07'),
(156, 'thanh', 'huế', '10', 73000, '2019-12-07'),
(157, 'thanh', 'huế', '10', 73000, '2019-12-07'),
(158, 'thanh', 'huế', '10', 73000, '2019-12-07'),
(159, 'thanh', 'huế', '10', 73000, '2019-12-07'),
(160, 'thanh', 'huế', '10', 73000, '2019-12-07'),
(161, 'thanh', 'huế', '10', 98000, '2019-12-07'),
(162, 'thanh', 'huế', '10', 292000, '2019-12-07'),
(163, 'thanh', 'huế', '10', 73000, '2019-12-07'),
(164, 'thanh', 'huế', '10', 101000, '2019-12-07'),
(165, 'thanh', 'huế', '10', 174000, '2019-12-07'),
(166, 'thanh', 'huế', '3', 246000, '2019-12-07'),
(167, 'thanh', 'huế', '1', 47000, '2019-12-07'),
(168, 'thanh', 'Huế', '10', 270000, '2019-12-11'),
(169, 'admin', 'Đà nẵng', '10', 195000, '2019-12-11'),
(170, 'thanh', 'abc', '6', 45000, '2019-12-11'),
(171, 'thanh', '7dao duy anh', '1', 25000, '2019-12-11'),
(172, 'admin', 'Huế', '1', 39000, '2019-12-15'),
(173, 'admin', 'Huế', '10', 210000, '2019-12-13'),
(174, 'thanh', '11 hà nội', '1', 50000, '2019-12-11'),
(175, 'admin', 'Huế', '1', 80000, '2019-12-12'),
(176, 'cong', 'hueê', '4', 105000, '2019-12-15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loai`
--

CREATE TABLE `loai` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loai`
--

INSERT INTO `loai` (`id`, `name`) VALUES
('1', 'Đồ Ăn'),
('2', 'Nước uống');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhahang`
--

CREATE TABLE `nhahang` (
  `id` varchar(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `anh` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nhahang`
--

INSERT INTO `nhahang` (`id`, `name`, `address`, `phone`, `anh`) VALUES
('1', 'Family Home Restaurant', '11/34 Nguyễn Tri Phương - TP Huế\r\n', '+84 98 372 79 40', 'https://media-cdn.tripadvisor.com/media/photo-s/11/45/43/2c/restaurant-by-night.jpg'),
('10', 'Highland Coffee', '84 Ngô Quyền - TP Huế', '+84 90 358 45 25', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRS9cSDJNB0KpMZ5Xq-KAlMRcqr2NC-GwF_uAkHIZ4f6sn400S&s'),
('2', 'Jalapeno Restaurant', '38 Phạm Ngũ Lão - TP Huế', '+84 90 358 38 12', 'https://media-cdn.tripadvisor.com/media/photo-p/13/2d/b3/c9/logo.jpg'),
('3', 'Chef Burger Hue', '36 Võ Thị Sáu - TP Huế', '+84 90 642 50 37', 'https://media-cdn.tripadvisor.com/media/photo-s/17/ed/90/c8/blue-cheese-burger.jpg'),
('4', 'Serene Cuisine Restaurant', '21/42 Nguyễn Công Trứ - TP Huế', '+84 54 3948 585', 'https://media-cdn.tripadvisor.com/media/photo-s/07/41/ac/41/getlstd-property-photo.jpg'),
('5', ' Spice Việt Restaurant', '15 Lý Thường Kiệt - TP Huế', '+84 234 3823 884', 'https://media-cdn.tripadvisor.com/media/photo-s/0f/73/e9/2e/getlstd-property-photo.jpg'),
('6', 'Bistro 34\'', '34 Đinh Tiên Hoàng - TP Huế', '+84 94 134 49 02', 'https://media-cdn.tripadvisor.com/media/photo-s/12/38/b6/2b/dsc-0959-largejpg.jpg'),
('7', 'Hot Tuna Restaurant', '37 Võ Thị Sáu TP - Huế', '+84 89 823 58 39', 'https://media-cdn.tripadvisor.com/media/photo-p/15/a7/83/10/proud-to-be-recognised.jpg'),
('8', 'Risotto Restaurant', '14 Nguyễn Công Trứ TP - Huế', '+84 122 226 2146', 'https://media-cdn.tripadvisor.com/media/photo-s/06/5f/20/05/risotto-restaurant.jpg'),
('9', 'Cafe on Thu Wheels', '3/34 Nguyễn Tri Phương - TP Huế \r\n\r\n', '+84 93 565 66 92', 'https://media-cdn.tripadvisor.com/media/photo-s/0f/d2/17/92/cafe-on-thu-wheels.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `res_food`
--

CREATE TABLE `res_food` (
  `idRes` varchar(11) NOT NULL,
  `idFood` varchar(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `res_food`
--

INSERT INTO `res_food` (`idRes`, `idFood`, `price`) VALUES
('1', '1', 12000),
('1', '2', 15000),
('1', '3', 20000),
('1', '5', 15000),
('1', '8', 25000),
('10', '13', 20000),
('10', '15', 25000),
('10', '16', 28000),
('10', '17', 25000),
('10', '18', 30000),
('10', '19', 25000),
('2', '2', 15000),
('2', '5', 20000),
('2', '6', 25000),
('2', '7', 18000),
('3', '1', 12000),
('3', '3', 20000),
('3', '7', 18000),
('3', '8', 25000),
('4', '2', 15000),
('4', '5', 15000),
('4', '6', 25000),
('4', '7', 18000),
('5', '1', 12000),
('5', '3', 20000),
('5', '4', 35000),
('5', '6', 25000),
('5', '7', 18000),
('6', '1', 12000),
('6', '2', 15000),
('6', '3', 18000),
('6', '4', 20000),
('6', '5', 20000),
('7', '1', 12000),
('7', '3', 20000),
('7', '4', 35000),
('7', '7', 18000),
('7', '8', 25000),
('8', '1', 12000),
('8', '3', 20000),
('8', '4', 35000),
('8', '5', 12000),
('8', '7', 18000),
('9', '10', 15000),
('9', '11', 18000),
('9', '12', 20000),
('9', '13', 25000),
('9', '14', 30000),
('9', '17', 28000),
('9', '20', 25000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `user` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`user`, `pass`, `name`, `phone`) VALUES
('', '', '', ''),
('abc', '1', '1', 'đáa'),
('admin', '1', 'Trương Chí Công', '123456789'),
('con', '1', '1', '1'),
('cong', '1', 'Trương Chí Công', '01627845641'),
('haha', '1', 'huhu', '123'),
('huhu', '1', '1', '1'),
('thanh', '1', 'Lâm Quốc Tuấn', '01234');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ttin_dhang`
--

CREATE TABLE `ttin_dhang` (
  `idFood` varchar(11) NOT NULL,
  `idDH` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `ttin_dhang`
--

INSERT INTO `ttin_dhang` (`idFood`, `idDH`, `count`, `price`) VALUES
('1', 166, 3, 12000),
('1', 167, 1, 12000),
('1', 170, 1, 12000),
('1', 172, 2, 12000),
('1', 175, 5, 12000),
('13', 158, 1, 20000),
('13', 160, 1, 20000),
('13', 161, 1, 20000),
('13', 162, 4, 20000),
('13', 163, 1, 20000),
('13', 164, 1, 20000),
('13', 165, 2, 20000),
('13', 168, 6, 20000),
('13', 169, 1, 20000),
('13', 173, 1, 20000),
('15', 158, 1, 25000),
('15', 159, 1, 25000),
('15', 160, 1, 25000),
('15', 161, 1, 25000),
('15', 162, 4, 25000),
('15', 163, 1, 25000),
('15', 164, 1, 25000),
('15', 165, 2, 25000),
('15', 168, 6, 25000),
('15', 169, 7, 25000),
('15', 173, 2, 25000),
('16', 158, 1, 28000),
('16', 160, 1, 28000),
('16', 161, 1, 28000),
('16', 162, 4, 28000),
('16', 163, 1, 28000),
('16', 164, 2, 28000),
('16', 165, 3, 28000),
('16', 173, 5, 28000),
('17', 161, 1, 25000),
('18', 143, 2, 30000),
('18', 144, 2, 30000),
('2', 167, 1, 15000),
('2', 170, 1, 15000),
('2', 172, 1, 15000),
('2', 174, 1, 15000),
('2', 176, 7, 15000),
('3', 166, 6, 20000),
('3', 167, 1, 20000),
('3', 170, 1, 18000),
('3', 174, 1, 20000),
('3', 175, 1, 20000),
('5', 97, 1, 15000),
('5', 104, 10, 15000),
('5', 108, 1, 15000),
('5', 109, 8, 15000),
('5', 174, 1, 15000),
('6', 91, 1, 25000),
('7', 93, 1, 18000),
('7', 94, 1, 18000),
('7', 166, 5, 18000),
('8', 94, 1, 25000),
('8', 171, 1, 25000);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `doan`
--
ALTER TABLE `doan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idLoai` (`idLoai`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nhahang`
--
ALTER TABLE `nhahang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `res_food`
--
ALTER TABLE `res_food`
  ADD PRIMARY KEY (`idRes`,`idFood`),
  ADD KEY `res_food_ibfk_2` (`idFood`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`user`);

--
-- Chỉ mục cho bảng `ttin_dhang`
--
ALTER TABLE `ttin_dhang`
  ADD PRIMARY KEY (`idFood`,`idDH`),
  ADD KEY `idDH` (`idDH`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=177;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
