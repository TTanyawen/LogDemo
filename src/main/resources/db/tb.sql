CREATE TABLE `nq_demos`.`t_op_log`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` varchar(52) NULL,
  `operation` varchar(255) NULL COMMENT '操作类型(select/update/delete)',
  `time` datetime NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
)