use master
go
create database WebBanHang

use WebBanHang
go
if OBJECT_ID('Accounts') is not null
drop table Accounts
create table Accounts
(
	Id int identity not null,
	Username varchar(50) not null,
	Password varchar(50) not null,
	Type varchar(20) not null,
	Status nvarchar(50) null,
	Email varchar(100) null,
	FullName NVarchar(100) not null,
	Address Nvarchar(200) null,
	constraint pk_Accounts primary key (Id),
)
if OBJECT_ID('Categories') is not null
drop table Categories
create table Categories
(
	Id int identity not null ,
	FatherId int  null,
	Name NVarchar(100) not null,
	Description Nvarchar(200) null,
	SortNo int null,
	Status nvarchar(50) null,
	Type nvarchar(50) null,
	constraint pk_Categories primary key (Id),
	CONSTRAINT FK_Categories FOREIGN KEY (FatherId) REFERENCES Categories(Id)
)

if OBJECT_ID('Carts') is not null
drop table Carts
create table Carts
(
	Id int not null,
	AccountId int not null,
	TotalProducts int not null,
	TotalPrice int not null,
	constraint pk_Carts primary key (Id),
	CONSTRAINT FK_Carts_Accounts FOREIGN KEY (AccountId) REFERENCES Accounts(Id)
)
if OBJECT_ID('Products') is not null
drop table Products
create table Products
(
	Id int identity not null,
	CategoryId int not null,
	Code varchar(255) not null,
	Name Varchar(255) not null,
	UnitPrice int null,
	Image text null,
	Description text null,
	Status varchar(100) null,
	constraint pk_Products primary key (Id),
	CONSTRAINT FK_Products_Categories FOREIGN KEY (CategoryId) REFERENCES Categories(Id)
)
if OBJECT_ID('Details') is not null
drop table Details
create table Details
(
	Id int not null,
	CartId int not null,
	ProductId int not null,
	Added int not null,
	constraint pk_Details primary key (Id),
	CONSTRAINT FK_Details_Products FOREIGN KEY (ProductId) REFERENCES Products(Id),
	CONSTRAINT FK_Details_Carts FOREIGN KEY (CartId) REFERENCES Carts(Id)
)
--Insert Account
insert into Accounts (Username,Password,Type,Status,Email,FullName,Address) values('admin','admin','admin',null,null,'admin',null)
insert into Accounts (Username,Password,Type,Status,Email,FullName,Address) values('guest','guest','guest',null,null,'guest',null)
insert into Accounts (Username,Password,Type,Status,Email,FullName,Address) values('sonph','sonph','customer',null,null,'son phan',null)
--Insert Category
insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(null,N'Điện Thoại',null,null,null,null)
insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(null,N'Máy Tính Bảng',null,null,null,null)
insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(null,N'Phụ Kiện',null,null,null,null)
	--su dung identity --> tu dong tang id+1
	insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(1,N'Apple',null,null,null,null)
	insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(1,N'Samsung',null,null,null,null)
	insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(1,N'Sony',null,null,null,null)	
	insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(1,N'Oppo',null,null,null,null)

	insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(2,N'Ipad',null,null,null,null)
	insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(2,N'GalaxyTab',null,null,null,null)

	insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(3,N'Phụ kiện điện thoại',null,null,null,null)
	insert into Categories (FatherId,Name,Description,SortNo,Status,Type) values(3,N'Phụ kiện máy tính bảng',null,null,null,null)
--Insert Product
insert into Products(CategoryId,Code,Name,UnitPrice,Image,Description,Status) values(4,'566a','iphone',null,'data:image/jpeg;base64,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',null,NULL)



delete from products where id=2