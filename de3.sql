create database BtlSem2
go
use BtlSem2
go
CREATE TABLE Accounts(
	AccountId VARCHAR(36) PRIMARY KEY,
	UserName nVARCHAR(64) NOT NULL,
	Password VARCHAR(256),
	Picture VARCHAR(512),
	Email VARCHAR(64) UNIQUE NOT NULL,
    Gender bit,
    birthday date,
	Address NVARCHAR(256),
	Phone VARCHAR(64),
	Createdate DATE,
)
go
create table Roles(
    RoleId int PRIMARY key identity,
    RoleName varchar(255) not null,
	Createdate DATE,
)
GO
create table Account_Roles(
    Id int PRIMARY key identity,
    AccountId varchar(36) FOREIGN key references Accounts(AccountId) ON DELETE CASCADE,
    RoleId int references Roles(RoleId) ON DELETE CASCADE
)

Go
create table Languages (
	Id int PRIMARY key identity,
	name nvarchar(255) not null,
	canonical varchar(100) not null,
	image varchar(255) null,
	current_ bit,
	Createdate DATE,
)

go
CREATE TABLE PhongBans (
    Id INT  PRIMARY KEY,
    address nvarchar(50)  NULL,
    phoneRoom varchar(11)  NULL
);

go
CREATE TABLE PhongBansLanguages (
     Id INT  identity PRIMARY KEY,
    room_id int FOREIGN key references PhongBans(Id) ON DELETE CASCADE, 
	Language_id int  FOREIGN key references Languages(Id), 
	roomName NVARCHAR(255) UNIQUE NOT NULL,
	Createdate DATE
);


go
--giám đốc quản lý nhân viên
CREATE TABLE ChucVuNhanViens (
    MaChucVuNV varchar(30) primary key,
    Createdate DATE
);

go
CREATE TABLE ChucVuNhanViensLanguages (
    Id INT  identity PRIMARY KEY,
    ChucVuId varchar(30) FOREIGN key references ChucVuNhanViens(MaChucVuNV) ON DELETE CASCADE, 
	Language_id int  FOREIGN key references Languages(Id), 
	TenChucVu nvarchar(255) not null,
	Createdate DATE
);


go
CREATE TABLE TrinhDoHocVans (
    MaTrinhDo int IDENTITY(1,1) primary key,
    CreateDate DATE
);

go
CREATE TABLE TrinhDoHocVansLanguages (
    Id INT  identity PRIMARY KEY,
    MaTrinhDo int FOREIGN key references TrinhDoHocVans(MaTrinhDo) ON DELETE CASCADE, 
	Language_id int  FOREIGN key references Languages(Id), 
	TenTrinhDo nvarchar(255) not null,
	Createdate DATE
);


go
Create table NhanViens(
    MaNhanSu INT identity PRIMARY KEY,
    Ten NVARCHAR(255) not null,
    Tuoi INT,
    GioiTinh NVARCHAR(10),
    HinhAnh varchar(255),
    Std varchar(255),
    QueQuan nvarchar(255),
    NgaySinh DATE,
    MaCapBac varchar(30),
    MaPhong INT,
    MaTrinhDoHocVan int,
    FOREIGN KEY (MaCapBac) REFERENCES ChucVuNhanViens(MaChucVuNV),
    FOREIGN KEY (MaPhong) REFERENCES PhongBans(Id),
    FOREIGN KEY (MaTrinhDoHocVan) REFERENCES TrinhDoHocVans(MaTrinhDo),
);




-- chưa insert
go
CREATE TABLE Luongs (
    Id INT  identity PRIMARY KEY,
    MaNhanVien int NOT NULL,
    LuongToiThieu int  NOT NULL,
    HeSoLuong float  NULL,
    PhuCap float  NULL,
);
go
CREATE TABLE ChiTietLuongs (
    MaChiTietBangLuong int Identity primary key  NOT NULL,
    MaNhanVien int  NOT NULL,
    LuongCoBan float  NOT NULL,
    PhuCap float  NULL,
    TienThuong int  NULL,
    SoCong int null,
    TienPhat int  NULL,
    NgayNhanLuong datetime  NOT NULL,
    TongTienLuong varchar(30)  NULL
    FOREIGN KEY (MaNhanVien) REFERENCES NhanViens(MaNhanSu), 
);
go
-- /chấm công
CREATE TABLE ChamCong (
    Id INT  identity PRIMARY KEY,
    MaNhanSu INT,
    ngay DATETime not null,
    soCong float not null,
    FOREIGN KEY (MaNhanSu) REFERENCES NhanViens(MaNhanSu), 
);

-- công thức tính luong 
-- luong cơ bản(trong 1 tháng)  / 30(số ngày phải hoàn thành) * 21(ngày làm việc) + phụ cấp

-- insert data
go
INSERT INTO Accounts (AccountId, UserName, Password, Picture, Email, Gender)
VALUES 
('1', 'Nam', '$2a$12$jukAsGNTZPD0GRKkse1Gz.sQYQc8awziQFlD.R5fhckJT4ce91q3q', '', 'admin@gmail.com',1),
('2', 'Khôi', '$2a$12$60gUzsoFEIxVjx1PWnq46urTSjHBCUL6kdPvFRTVyNLWwHp7tCuUa', '', 'khoi@gmail.com',1),
('3', 'Việt', '$2a$12$HWieS/bs6vwthNUhhdl0/uw8usZp2Tt1C58oQXt.hNvbE//66DsCG', '', 'viet@gmail.com',1);

go
insert into Roles(RoleName) values ('ROLE_ADMIN'),
('ROLE_USER'),
('ROLE_MANAGER')

go
insert into Account_Roles( AccountId, RoleId) 
values( 1, 1),
( 2, 2),
(3, 3)

go

insert into Languages(name, canonical, image, current_)
values('Tiếng việt', 'vi', '', 1 ),
('Tiếng anh', 'en', '', 0)