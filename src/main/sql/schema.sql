-- shujukuchushihuajiaoben

-- chuangjianshujuku
CREATE DATABASE seckill;

-- shiyongshujuku
USE seckill;

-- chuangjianmiaoshahujukubiao
-- zhixingguochengzhong baocuo(ERROR 1067(42000)),shiyinwei sql_mode, keyi zai zhi xing yuju qian zhixing
-- SET sql_mode = '';   huo zhe yichu sql_mode zhong de "NO_ZERO_IN_DATE,NO_ZERO_DATE"
CREATE TABLE seckill (
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'shangpinmingchengid',
`name` varchar(120) NOT NULL COMMENT 'shangpinmingcheng',
`number` int NOT NULL COMMENT 'kucunshuliang',
`start_time` timestamp NOT NULL COMMENT 'miaoshakaishishijian',
`end_time` timestamp NOT NULL COMMENT 'miaoshjieshushijian',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'chuangjianshijian',
PRIMARY KEY (seckill_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT 'miaoshkucunbiao';


-- init data
insert into seckill
    (name, number, start_time, end_time)
values
    ('1000killphone6', 100, '2017-07-16 00:00:00', '2017-07-17 00:00:00'),
    ('500killipad2', 100, '2017-07-16 00:00:00', '2017-07-17 00:00:00'),
    ('300killxiaomi4', 100, '2017-07-16 00:00:00', '2017-07-17 00:00:00'),
    ('200killhongminote', 100, '2017-07-16 00:00:00', '2017-07-17 00:00:00');

-- miaosha chenggong mingxibiao
-- yonghu denglu renzheng xiangguanxinxi
CREATE TABLE success_killed (
`seckill_id` bigint NOT NULL COMMENT 'miaoshashangpinid',
`user_phone` bigint NOT NULL COMMENT 'yonghushoujihao',\
`state` tinyint NOT NULL DEFAULT -1 COMMENT 'zhuangtai,-1:wuxiao,0:chengong,1:yifukuan',
`create_time` timestamp NOT NULL COMMENT 'chuangjianshijian',
 PRIMARY KEY (seckill_id, user_phone),
 KEY idx_create_time (create_time)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'miaoshachenggongmingxibiao';