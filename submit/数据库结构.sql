/*
 Navicat Premium Data Transfer

 Source Server         : Covid19
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 8.210.248.203:3306
 Source Schema         : covid19

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 27/08/2020 00:08:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for diagnosis
-- ----------------------------
DROP TABLE IF EXISTS `diagnosis`;
CREATE TABLE `diagnosis` (
  `diagnosis_id` int unsigned NOT NULL AUTO_INCREMENT,
  `patient_id` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `symptom` varchar(255) DEFAULT NULL,
  `temperature` varchar(255) DEFAULT NULL,
  `nucleic_acid` varchar(255) DEFAULT NULL COMMENT '阴性/阳性',
  PRIMARY KEY (`diagnosis_id`),
  KEY `diagnosis_patient` (`patient_id`),
  KEY `diagnosis_doctor` (`doctor_id`),
  CONSTRAINT `diagnosis_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `diagnosis_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4075859 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `doctor_id` int NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `doctor_gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `doctor_birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '形如“2000-01-31”',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门',
  `hospital_id` int NOT NULL COMMENT '所在医院',
  PRIMARY KEY (`doctor_id`,`hospital_id`) USING BTREE,
  KEY `main` (`doctor_id`,`hospital_id`) USING BTREE,
  KEY `doctor_hospital` (`hospital_id`),
  KEY `doctor_id` (`doctor_id`),
  CONSTRAINT `doctor_hospital` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hospital_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hospital
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `hospital_id` int NOT NULL AUTO_INCREMENT,
  `hospital_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hospital_id`),
  KEY `main` (`hospital_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=541 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int DEFAULT NULL,
  `doctor_id` int DEFAULT NULL COMMENT '扫描医生',
  `image_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片链接',
  `conclusion` varchar(255) DEFAULT NULL COMMENT '扫描结论',
  `conclusion_doctor_id` int DEFAULT NULL COMMENT '结论医生',
  `scanning_doctor_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`image_id`) USING BTREE,
  KEY `image_doctor_id` (`doctor_id`),
  KEY `image_patient_id` (`patient_id`),
  CONSTRAINT `image_doctor_id` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `image_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for medicine
-- ----------------------------
DROP TABLE IF EXISTS `medicine`;
CREATE TABLE `medicine` (
  `medicine_id` int NOT NULL AUTO_INCREMENT,
  `medicine_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '厂商',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '药品详细介绍',
  `type` varchar(255) DEFAULT NULL COMMENT '中药/西药',
  PRIMARY KEY (`medicine_id`) USING BTREE,
  KEY `medicine_name` (`medicine_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2065 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `patient_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `patient_gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `patient_birthday` datetime DEFAULT NULL,
  `onset_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发病地点',
  `onset_date` datetime DEFAULT NULL COMMENT '发病时间',
  `confirm_date` datetime DEFAULT NULL COMMENT '确诊时间',
  `status` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '住院状态，死亡/痊愈/治疗中',
  `doctor_id` int NOT NULL COMMENT '主治大夫',
  `hospital_id` int NOT NULL COMMENT '治疗医院',
  `is_doctor` int DEFAULT NULL COMMENT '是否是医生。如果是则填入医生 ID',
  PRIMARY KEY (`patient_id`,`doctor_id`,`hospital_id`,`patient_name`) USING BTREE,
  KEY `index_hospital` (`patient_id`,`hospital_id`) USING BTREE,
  KEY `index_doctor` (`patient_id`,`doctor_id`) USING BTREE,
  KEY `patient_hospital` (`hospital_id`),
  KEY `patient_doctor` (`doctor_id`),
  KEY `index_patient` (`patient_id`),
  CONSTRAINT `patient_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `patient_hospital` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hospital_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=140357 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for prescription
-- ----------------------------
DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription` (
  `prescription_id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int DEFAULT NULL,
  `medicine_id` int DEFAULT NULL,
  `dosage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '如每日一次，一次X粒',
  `usage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '药物具体使用方法',
  `doctor_id` int NOT NULL,
  `prescription_date` datetime DEFAULT NULL,
  PRIMARY KEY (`prescription_id`) USING BTREE,
  KEY `prescription_doctor` (`doctor_id`),
  KEY `prescription_medicine` (`medicine_id`),
  KEY `prescription_patient` (`patient_id`),
  CONSTRAINT `prescription_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `prescription_medicine` FOREIGN KEY (`medicine_id`) REFERENCES `medicine` (`medicine_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `prescription_patient` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=421188 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `registration_date` datetime DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `password_change_date` datetime DEFAULT NULL,
  `registration_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10005 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
