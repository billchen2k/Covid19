## Covid-19 疫情分析与管理系统 - 数据库文档

----

### 一、连接信息

服务器地址：8.210.248.203

端口：3306

----

### 二、E-R图

本项目使用的数据库的 ER 图如下：

<img src="https://covid19.billc.io/er.png" alt="ER" style="zoom: 33%;" />

---

### 三、表具体信息

#### 1、patient

存储患者信息。

| 字段             | 数据类型     | 含义           |
| ---------------- | ------------ | -------------- |
| patient_id       | int          | 患者编号       |
| patient_name     | varchar(255) | 患者姓名       |
| patient_gender   | varchar(255) | 患者性别       |
| patient_birthday | datetime     | 患者生日       |
| onset_place      | varchar(255) | 发病地点       |
| onset_date       | datetime     | 发病日期       |
| confirm_date     | datetime     | 确诊日期       |
| status           | varchar(100) | 患者状态       |
| doctor_id        | int          | 主治医生编号   |
| hospital_id      | int          | 治疗医院编号   |
| is_doctor        | int          | 本人是否是医生 |

外键约束：

| 字段        | 数据类型 | 被参照关系 |
| ----------- | -------- | ---------- |
| doctor_id   | int      | doctor     |
| hospital_id | int      | hospital   |

#### 2、doctor

存储医生信息。

| 字段            | 数据类型     | 含义         |
| --------------- | ------------ | ------------ |
| doctor_id       | int          | 医生编号     |
| doctor_name     | varchar(255) | 医生姓名     |
| doctor_gender   | varchar(255) | 医生性别     |
| doctor_birthday | datetime     | 医生生日     |
| department      | varchar(255) | 所在部门     |
| hospital_id     | int          | 工作医院编号 |

外键约束：

| 字段        | 数据类型 | 被参照关系 |
| ----------- | -------- | ---------- |
| hospital_id | int      | hospital   |

#### 3、hospital

存储医院信息。

| 字段          | 数据类型     | 含义     |
| :------------ | ------------ | -------- |
| hospital_id   | int          | 医院编号 |
| hospital_name | varchar(255) | 医生名称 |
| address       | varchar(255) | 医生性别 |

#### 4、medicine

存储药品信息。

| 字段          | 数据类型     | 含义                  |
| :------------ | ------------ | --------------------- |
| medicine_id   | int          | 药品编号              |
| medicine_name | varchar(255) | 药品名称              |
| manufaturer   | varchar(255) | 生产厂家              |
| introduction  | varchar(255) | 药品介绍              |
| type          | varchar(255) | 药品类型（中药/西药） |

#### 5、diagnosis

存储诊断信息。

| 字段         | 数据类型     | 含义         |
| :----------- | ------------ | ------------ |
| diagnosis_id | int          | 诊断编号     |
| patient_id   | int          | 患者编号     |
| doctor_id    | int          | 诊治医生编号 |
| time         | datetime     | 诊断日期     |
| symptom      | varchar(255) | 患者症状     |
| temperature  | varchar(255) | 患者体温     |
| nucleic_acid | varchar(255) | 核酸检验结果 |

外键约束：

| 字段       | 数据类型 | 被参照关系 |
| ---------- | -------- | ---------- |
| doctor_id  | int      | doctor     |
| patient_id | int      | patient    |

#### 6、image

存储肺部成像信息。

| 字段                 | 数据类型     | 含义         |
| :------------------- | ------------ | ------------ |
| image_id             | int          | 成像编号     |
| patient_id           | int          | 患者编号     |
| doctor_id            | int          | 诊治医生编号 |
| image_picture        | varchar(255) | 成像图片     |
| conclusion           | varchar(255) | 结论         |
| conclusion_doctor_id | int          | 结论医生编号 |
| scanning_doctor_id   | int          | 扫描一生编号 |

外键约束：

| 字段       | 数据类型 | 被参照关系 |
| ---------- | -------- | ---------- |
| doctor_id  | int      | doctor     |
| patient_id | int      | patient    |

#### 7、prescription

存储处方信息。

| 字段              | 数据类型     | 含义         |
| :---------------- | ------------ | ------------ |
| prescription_id   | int          | 处方编号     |
| patient_id        | int          | 患者编号     |
| medicine_id       | int          | 药品编号     |
| dosage            | varchar(255) | 剂量         |
| usage             | varchar(255) | 用法         |
| doctor_id         | int          | 诊治医生编号 |
| prescription_date | datetime     | 处方开具日期 |

外键约束：

| 字段        | 数据类型 | 被参照关系 |
| ----------- | -------- | ---------- |
| doctor_id   | int      | doctor     |
| patient_id  | int      | patient    |
| medicine_id | int      | medicine   |

#### 8、user

存储用户信息。

| 字段                 | 数据类型     | 含义             |
| :------------------- | ------------ | ---------------- |
| user_id              | int          | 用户编号         |
| username             | varchar(255) | 用户名           |
| password             | varchar(255) | 密码             |
| email                | varchar(255) | 邮箱             |
| registration_date    | datetime     | 注册日期         |
| role                 | varchar(255) | 角色             |
| gender               | varchar(255) | 姓名             |
| password_change_date | datetime     | 上次密码修改日期 |
| registration_ip      | varchar(255) | 注册时IP地址     |

----

### 四、索引情况

#### 1、patient

| 名称             | 域                      | 类型   | 方式  |
| :--------------- | ----------------------- | ------ | ----- |
| index_hospital   | patient_id, hospital_id | NORMAL | BTREE |
| index_doctor     | patient_id, doctor_id   | NORMAL | BTREE |
| patient_hospital | hospital_id             | NORMAL | BTREE |
| patient_doctor   | doctor_id               | NORMAL | BTREE |
| index_patient    | patient_id              | NORMAL | BTREE |

#### 2、doctor

| 名称            | 域                     | 类型   | 方式  |
| :-------------- | ---------------------- | ------ | ----- |
| main            | doctor_id, hospital_id | NORMAL | BTREE |
| doctor_hospital | hospital_id            | NORMAL | BTREE |
| doctor_id       | doctor_id              | NORMAL | BTREE |

#### 3、diagnosis

| 名称              | 域         | 类型   | 方式  |
| :---------------- | ---------- | ------ | ----- |
| diagnosis_patient | patient_id | NORMAL | BTREE |
| diagnosis_doctor  | doctor_id  | NORMAL | BTREE |

#### 4、prescription

| 名称                  | 域          | 类型   | 方式  |
| :-------------------- | ----------- | ------ | ----- |
| prescription_doctor   | doctor_id   | NORMAL | BTREE |
| prescription_medicine | medicine_id | NORMAL | BTREE |
| prescription_patient  | patient_id  | NORMAL | BTREE |

