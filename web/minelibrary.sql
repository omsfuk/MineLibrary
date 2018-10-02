create table user (
  id int(11) primary key auto_increment,
  username varchar(10) not null,
  password varchar(18) not null,
  max_borrow int(20) default 15,
  email varchar(100),
  phone varchar(11),
  qq varchar(15),
  reg_date date,
  roles varchar(255) default 'reader'
);


create table book (
  id int(11) primary key auto_increment,
  title varchar(255) not null,
  press varchar(255),
  isbn varchar(64),
  carrier varchar(255),
  subject varchar(255),
  desc text,
  clas varchar(64),
  `count` int(11) default 1,
  `date` date
);

create table borrow (
  id int(11) primary key auto_increment,
  user_id int(11),
  book_id int(11),
  start_date date,
  end_date date,
  returned boolean default false,
  renew_times tinyint default 0,
  key (returned, user_id, book_id)
);

create table comment (
  id int(11) primary key auto_increment,
  user_id int(11),
  book_id int(11),
  content varchar(1024),
  parent_id int(11),
  created datetime,
  deleted boolean default false,
  valid tinyint default 0,
  key(valid, deleted, book_id, id),
  key(valid, deleted, book_id)
);
# valid 字段
# 2 代表未审核
# 0 审核失败
# 1 审核通过

insert into user(username, password, roles) values('omsfuk', 'admin', 'reader');