# Covid19 API


**简介**:Covid19 API

**HOST**:covid19api.billc.io


**Version**:1.0


**接口路径**:/v2/api-docs


[TOC]






# 医生控制器


## 创建医生信息


**接口地址**:`/doctor/createDoctor`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以创建医生信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|birthday|医生生日|query|false|string||
|department|所在部门|query|false|string||
|gender|医生性别|query|false|string||
|hospital_id|所在医院|query|false|string||
|name|医生姓名|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 删除医生信息


**接口地址**:`/doctor/deleteDoctorByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以删除医生的信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|doctor_id|医生ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据医生除ID外属性批量获取医生信息


**接口地址**:`/doctor/getDoctorInfo`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据病人除ID外属性获取病人信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||
|size|size|query|true|integer(int32)||
|birthday|医生生日|query|false|string||
|department|所在部门|query|false|string||
|gender|医生性别|query|false|string||
|hospital_id|所在医院|query|false|string||
|name|医生姓名|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«ArrayList«Doctor 医生实体»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|array|Doctor 医生实体|
|&emsp;&emsp;department|部门|string||
|&emsp;&emsp;doctor_birthday|生日|string||
|&emsp;&emsp;doctor_gender|性别|string||
|&emsp;&emsp;doctor_id|医生 ID|integer(int64)||
|&emsp;&emsp;doctor_name|姓名|string||
|&emsp;&emsp;hospital_id|所在医院|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"department": "",
			"doctor_birthday": "",
			"doctor_gender": "",
			"doctor_id": 0,
			"doctor_name": "",
			"hospital_id": ""
		}
	],
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据医生ID获取医生信息


**接口地址**:`/doctor/getDoctorInfoByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据医生ID获取医生信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|doctor_id|医生ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«HashMap«object,object»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新医生信息


**接口地址**:`/doctor/updateDoctor`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以更新医生信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|birthday|医生生日|query|false|string||
|department|所在部门|query|false|string||
|doctor_id|医生ID|query|false|string||
|gender|医生性别|query|false|string||
|hospital_id|所在医院|query|false|string||
|name|医生姓名|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


# 医院控制器


## 创建医院信息


**接口地址**:`/hospital/createHospital`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以创建医院信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|address|医院地址|query|false|string||
|name|医院名称|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 删除医院信息


**接口地址**:`/hospital/deleteHospitalByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以删除医院的信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|hospital_id|医院ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据医院除ID外属性获取医院信息


**接口地址**:`/hospital/getHospitalInfo`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据医院除ID外属性获取医院信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|address|医院地址|query|false|string||
|name|医院名称|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«ArrayList«Hospital 医院实体»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|array|Hospital 医院实体|
|&emsp;&emsp;address|医院地址|string||
|&emsp;&emsp;hospital_id|医院 ID|integer(int64)||
|&emsp;&emsp;hospital_name|医院名称|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"address": "",
			"hospital_id": 0,
			"hospital_name": ""
		}
	],
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据医院ID获取医院信息


**接口地址**:`/hospital/getHospitalInfoByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据医院ID获取医院信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|hospital_id|医院ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«Hospital 医院实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|Hospital 医院实体|Hospital 医院实体|
|&emsp;&emsp;address|医院地址|string||
|&emsp;&emsp;hospital_id|医院 ID|integer(int64)||
|&emsp;&emsp;hospital_name|医院名称|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"address": "",
		"hospital_id": 0,
		"hospital_name": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新医院信息


**接口地址**:`/hospital/updateHospital`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以更新医院信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|address|医院地址|query|false|string||
|hospital_id|医院ID|query|false|string||
|name|医院名称|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


# 处方控制器


## 根据处方除ID外属性获取处方信息


**接口地址**:`/prescription/getPrescriptionInfo`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据处方除ID外属性获取处方信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||
|size|size|query|true|integer(int32)||
|doctor_id|医生ID|query|false|string||
|dosage|剂量|query|false|string||
|medicine_id|药品ID|query|false|string||
|patient_id|病人ID|query|false|string||
|usage|用法|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«ArrayList«Prescription 用药实体»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|array|Prescription 用药实体|
|&emsp;&emsp;doctor_id|医生|string||
|&emsp;&emsp;dosage|剂量|string||
|&emsp;&emsp;medicine_id|药品 ID|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;prescription_id|用药 ID|integer(int64)||
|&emsp;&emsp;usage|药物使用方法|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"doctor_id": "",
			"dosage": "",
			"medicine_id": "",
			"patient_id": "",
			"prescription_id": 0,
			"usage": ""
		}
	],
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据处方ID获取处方信息


**接口地址**:`/prescription/getPrescriptionInfoByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据处方ID获取处方信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|prescription_id|处方ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«Prescription 用药实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|Prescription 用药实体|Prescription 用药实体|
|&emsp;&emsp;doctor_id|医生|string||
|&emsp;&emsp;dosage|剂量|string||
|&emsp;&emsp;medicine_id|药品 ID|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;prescription_id|用药 ID|integer(int64)||
|&emsp;&emsp;usage|药物使用方法|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"doctor_id": "",
		"dosage": "",
		"medicine_id": "",
		"patient_id": "",
		"prescription_id": 0,
		"usage": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据病人ID获取用药信息


**接口地址**:`/prescription/getPrescriptionInfoByPatientID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据病人ID获取用药信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|patient_id|病人ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«Prescription 用药实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|Prescription 用药实体|Prescription 用药实体|
|&emsp;&emsp;doctor_id|医生|string||
|&emsp;&emsp;dosage|剂量|string||
|&emsp;&emsp;medicine_id|药品 ID|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;prescription_id|用药 ID|integer(int64)||
|&emsp;&emsp;usage|药物使用方法|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"doctor_id": "",
		"dosage": "",
		"medicine_id": "",
		"patient_id": "",
		"prescription_id": 0,
		"usage": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据病人 ID 获取处方信息


**接口地址**:`/prescription/getPrescriptionInfoByPatientId`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据处方除ID外属性获取处方信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||
|size|size|query|true|integer(int32)||
|patient_id|病人ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«ArrayList«Prescription 用药实体»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|array|Prescription 用药实体|
|&emsp;&emsp;doctor_id|医生|string||
|&emsp;&emsp;dosage|剂量|string||
|&emsp;&emsp;medicine_id|药品 ID|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;prescription_id|用药 ID|integer(int64)||
|&emsp;&emsp;usage|药物使用方法|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"doctor_id": "",
			"dosage": "",
			"medicine_id": "",
			"patient_id": "",
			"prescription_id": 0,
			"usage": ""
		}
	],
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 获取当前数据库处方数量


**接口地址**:`/prescription/getPrescriptionNumber`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以获取当前数据库处方数量


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«Prescription 用药实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|Prescription 用药实体|Prescription 用药实体|
|&emsp;&emsp;doctor_id|医生|string||
|&emsp;&emsp;dosage|剂量|string||
|&emsp;&emsp;medicine_id|药品 ID|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;prescription_id|用药 ID|integer(int64)||
|&emsp;&emsp;usage|药物使用方法|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"doctor_id": "",
		"dosage": "",
		"medicine_id": "",
		"patient_id": "",
		"prescription_id": 0,
		"usage": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


# 用户控制器


## 获取已登录用户信息


**接口地址**:`/user/getUserInfo`


**请求方式**:`GET`


**请求数据类型**:`*`


**响应数据类型**:`*/*`


**接口描述**:可以获取当前已登录的用户信息


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«User 用户实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|User 用户实体|User 用户实体|
|&emsp;&emsp;email|邮箱|string||
|&emsp;&emsp;gender|性别|string||
|&emsp;&emsp;password|密码|string||
|&emsp;&emsp;password_change_date|密码修改日期|string||
|&emsp;&emsp;registration_date|注册时间|string||
|&emsp;&emsp;registration_ip|注册时间|string||
|&emsp;&emsp;role|用户角色|string||
|&emsp;&emsp;user_id|用户 ID|integer(int64)||
|&emsp;&emsp;username|用户名|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"email": "",
		"gender": "",
		"password": "",
		"password_change_date": "",
		"registration_date": "",
		"registration_ip": "",
		"role": "",
		"user_id": 0,
		"username": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 登录接口


**接口地址**:`/user/logIn`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:用于登录用户。每一个 session 的有效期为 30 天，如果用户长时间没有启动应用应当在启动时发送一次登录请求。


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|identifier|认证名，可以为用户名或者邮箱，后端会自动验证|query|false|string||
|password|经过 SHA1 加密之后的密码字符串|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«User 用户实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|User 用户实体|User 用户实体|
|&emsp;&emsp;email|邮箱|string||
|&emsp;&emsp;gender|性别|string||
|&emsp;&emsp;password|密码|string||
|&emsp;&emsp;password_change_date|密码修改日期|string||
|&emsp;&emsp;registration_date|注册时间|string||
|&emsp;&emsp;registration_ip|注册时间|string||
|&emsp;&emsp;role|用户角色|string||
|&emsp;&emsp;user_id|用户 ID|integer(int64)||
|&emsp;&emsp;username|用户名|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"email": "",
		"gender": "",
		"password": "",
		"password_change_date": "",
		"registration_date": "",
		"registration_ip": "",
		"role": "",
		"user_id": 0,
		"username": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 登出接口


**接口地址**:`/user/logOut`


**请求方式**:`GET`


**请求数据类型**:`*`


**响应数据类型**:`*/*`


**接口描述**:


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 注册新用户的接口


**接口地址**:`/user/signUp`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:token_id 字段暂时未用上


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|email|注册邮箱|query|false|string||
|gender|以整数形式表示的性别。0 - 女；1 - 男； 2 - 其他|query|false|string||
|password|密码，应该为原文，加密由后端实现|query|false|string||
|username|用户名，长度在 3 - 30 之间|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新邮箱


**接口地址**:`/user/updateEmail`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:对当前登录的用户起作用，必须先登录才能使用。


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|newEmail|新的邮箱|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新除用户名密码、邮箱以外的其他信息


**接口地址**:`/user/updateOtherInfo`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:参数均为非必要的，有哪个参数就更新哪个字段。
和其他接口一样必须先登录才能使用。


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|newGender|新的性别，0 女 1 男 2 其他|query|false|string||
|newRole|新的角色，用户 user 或管理员 admin|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新用户密码


**接口地址**:`/user/updatePassword`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:对当前登录的用户起作用，必须先登录才能使用。


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|newPass|新密码，传入原文|query|false|string||
|oldPass|旧密码，传入原文|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新用户名


**接口地址**:`/user/updateUsername`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:对当前登录的用户起作用，必须先登录才能使用。


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|newUsername|新的用户名|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


# 用药控制器


## 创建用药信息


**接口地址**:`/prescription/createPrescription`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以创建用药信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|doctor_id|医生ID|query|false|string||
|dosage|剂量|query|false|string||
|medicine_id|药品 ID|query|false|string||
|patient_id|病人 ID|query|false|string||
|usage|药物使用方法|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 删除用药信息


**接口地址**:`/prescription/deletePrescriptionByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以删除用药的信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|prescription_id|用药ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新用药信息


**接口地址**:`/prescription/updatePrescription`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以更新用药信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|doctor_id|医生ID|query|false|string||
|dosage|剂量|query|false|string||
|medicine_id|药品ID|query|false|string||
|patient_id|病人ID|query|false|string||
|prescription_id|用药ID|query|false|string||
|usage|用法|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


# 病人控制器


## 创建病人信息


**接口地址**:`/patient/createPatient`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以创建病人信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|birthday|病人生日|query|false|string||
|confirm_date|确诊时间|query|false|string||
|doctor_id|主治大夫|query|false|string||
|gender|病人性别|query|false|string||
|hospital_id|治疗医院|query|false|string||
|is_doctor|是否是医生|query|false|string||
|name|病人名称|query|false|string||
|onset_date|发病时间|query|false|string||
|onset_place|发病地点|query|false|string||
|status|住院状态|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 删除病人信息


**接口地址**:`/patient/deletePatientByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以删除病人的信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|patient_id|病人ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据病人除ID外属性批量获取病人信息


**接口地址**:`/patient/getPatientInfo`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据病人除ID外属性获取病人信息。具有分页功能。


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||
|size|size|query|true|integer(int32)||
|birthday|病人生日|query|false|string||
|confirm_date|确诊时间|query|false|string||
|doctor_id|主治大夫|query|false|string||
|gender|病人性别|query|false|string||
|hospital_id|治疗医院|query|false|string||
|is_doctor|是否是医生|query|false|string||
|onset_date|发病时间|query|false|string||
|onset_place|发病地点|query|false|string||
|patient_name|病人名称|query|false|string||
|status|住院状态|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«ArrayList«Patient 病人实体»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|array|Patient 病人实体|
|&emsp;&emsp;confirm_date|确诊时间|string||
|&emsp;&emsp;doctor_id|主治大夫|string||
|&emsp;&emsp;hospital_id|治疗医院 ID|string||
|&emsp;&emsp;is_doctor|是否为医生|string||
|&emsp;&emsp;onset_date|发病时间|string||
|&emsp;&emsp;onset_place|发病地点|string||
|&emsp;&emsp;patient_birthday|生日|string||
|&emsp;&emsp;patient_gender|性别|string||
|&emsp;&emsp;patient_id|患者 ID|integer(int64)||
|&emsp;&emsp;patient_name|姓名|string||
|&emsp;&emsp;status|住院状态|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"confirm_date": "",
			"doctor_id": "",
			"hospital_id": "",
			"is_doctor": "",
			"onset_date": "",
			"onset_place": "",
			"patient_birthday": "",
			"patient_gender": "",
			"patient_id": 0,
			"patient_name": "",
			"status": ""
		}
	],
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据病人ID获取病人信息


**接口地址**:`/patient/getPatientInfoByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据病人ID获取病人信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|patient_id|病人ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«HashMap«string,string»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新病人信息


**接口地址**:`/patient/updatePatient`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以更新病人信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|confirm_date|确诊时间|query|false|string||
|doctor_id|主治大夫|query|false|string||
|hospital_id|治疗医院|query|false|string||
|is_doctor|是否是医生|query|false|string||
|onset_date|发病时间|query|false|string||
|onset_place|发病地点|query|false|string||
|patient_birthday|病人生日|query|false|string||
|patient_gender|病人性别|query|false|string||
|patient_id|病人ID|query|false|string||
|patient_name|病人名称|query|false|string||
|status|住院状态|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


# 肺部影像控制器


## 创建图像信息


**接口地址**:`/image/createImage`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以创建图像信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|conclusion|结论|query|false|string||
|conclusion_doctor_id|结论医生|query|false|string||
|image_picture|肺部影像图片|query|false|string||
|patient_id|病人ID|query|false|string||
|scanning_doctor_id|扫描医生 ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 删除图像信息


**接口地址**:`/image/deleteImageByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以删除图像的信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|image_id|图像ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据图像除ID外属性获取图像信息


**接口地址**:`/image/getImageInfo`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据图像除ID外属性获取图像信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|conclusion|结论|query|false|string||
|conclusion_doctor_id|结论医生|query|false|string||
|image_picture|肺部影像图片|query|false|string||
|patient_id|病人ID|query|false|string||
|scanning_doctor_id|扫描医生 ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«ArrayList«Image 肺部影像实体»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|array|Image 肺部影像实体|
|&emsp;&emsp;conclusion|结论|string||
|&emsp;&emsp;conclusion_doctor_id|结论医生|string||
|&emsp;&emsp;image_id|肺部影像 ID|integer(int64)||
|&emsp;&emsp;image_picture|肺部影像图片|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;scanning_doctor_id|扫描医生 ID|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"conclusion": "",
			"conclusion_doctor_id": "",
			"image_id": 0,
			"image_picture": "",
			"patient_id": "",
			"scanning_doctor_id": ""
		}
	],
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据图像ID获取图像信息


**接口地址**:`/image/getImageInfoByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据图像ID获取图像信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|image_id|肺部成像ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«Image 肺部影像实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|Image 肺部影像实体|Image 肺部影像实体|
|&emsp;&emsp;conclusion|结论|string||
|&emsp;&emsp;conclusion_doctor_id|结论医生|string||
|&emsp;&emsp;image_id|肺部影像 ID|integer(int64)||
|&emsp;&emsp;image_picture|肺部影像图片|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;scanning_doctor_id|扫描医生 ID|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"conclusion": "",
		"conclusion_doctor_id": "",
		"image_id": 0,
		"image_picture": "",
		"patient_id": "",
		"scanning_doctor_id": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新图像信息


**接口地址**:`/image/updateImage`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以更新图像信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|image_id|image_id|query|true|integer(int64)||
|conclusion|结论|query|false|string||
|conclusion_doctor_id|结论医生|query|false|string||
|image_picture|肺部影像图片|query|false|string||
|patient_id|病人ID|query|false|string||
|scanning_doctor_id|扫描医生 ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


# 药品控制器


## 创建药品信息


**接口地址**:`/medicine/createMedicine`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以创建药品信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|introduction|详细介绍|query|false|string||
|manufacturer|生产厂家|query|false|string||
|name|药品名称|query|false|string||
|type|类型|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 删除药品信息


**接口地址**:`/medicine/deleteMedicineByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以删除药品的信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|medicine_id|药品ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据药品除ID外属性获取药品信息


**接口地址**:`/medicine/getMedicineInfo`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据药品除ID外属性获取药品信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||
|size|size|query|true|integer(int32)||
|introduction|详细介绍|query|false|string||
|manufacturer|生产厂家|query|false|string||
|name|药品名称|query|false|string||
|type|类型|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«ArrayList«Medicine 药品实体»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|array|Medicine 药品实体|
|&emsp;&emsp;introduction|药品详细介绍|string||
|&emsp;&emsp;manufacturer|厂商|string||
|&emsp;&emsp;medicine_id|药品 ID|integer(int64)||
|&emsp;&emsp;medicine_name|名称|string||
|&emsp;&emsp;type|类型|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"introduction": "",
			"manufacturer": "",
			"medicine_id": 0,
			"medicine_name": "",
			"type": ""
		}
	],
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据药品ID获取药品信息


**接口地址**:`/medicine/getMedicineInfoByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据药品ID获取药品信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|medicine_id|药品ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«Medicine 药品实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|Medicine 药品实体|Medicine 药品实体|
|&emsp;&emsp;introduction|药品详细介绍|string||
|&emsp;&emsp;manufacturer|厂商|string||
|&emsp;&emsp;medicine_id|药品 ID|integer(int64)||
|&emsp;&emsp;medicine_name|名称|string||
|&emsp;&emsp;type|类型|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"introduction": "",
		"manufacturer": "",
		"medicine_id": 0,
		"medicine_name": "",
		"type": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新药品信息


**接口地址**:`/medicine/updateMedicine`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以更新药品信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|medicine_id|medicine_id|query|true|integer(int64)||
|introduction|详细介绍|query|false|string||
|manufacturer|生产厂家|query|false|string||
|name|药品名称|query|false|string||
|type|类型|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


# 诊断控制器


## 创建诊断信息


**接口地址**:`/diagnosis/createDiagnosis`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以创建诊断信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|doctor_id|医生 ID|query|false|string||
|nucleic_acid|核酸检测结果|query|false|string||
|patient_id|病人ID|query|false|string||
|symptom|症状|query|false|string||
|temperature|体温|query|false|string||
|time|诊断时间|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 删除诊断信息


**接口地址**:`/diagnosis/deleteDiagnosisByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以删除诊断信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|diagnosis_id|诊断ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据诊断除ID外属性获取诊断信息


**接口地址**:`/diagnosis/getDiagnosisInfo`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据诊断除ID外属性获取诊断信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|page|page|query|true|integer(int32)||
|size|size|query|true|integer(int32)||
|doctor_id|医生 ID|query|false|string||
|nucleic_acid|核酸检测结果|query|false|string||
|patient_id|病人ID|query|false|string||
|symptom|症状|query|false|string||
|temperature|体温|query|false|string||
|time|诊断时间|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«ArrayList«Diagnosis 诊断实体»»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|array|Diagnosis 诊断实体|
|&emsp;&emsp;diagnosis_id|诊断 ID|integer(int64)||
|&emsp;&emsp;doctor_id|医生 ID|string||
|&emsp;&emsp;nucleic_acid|核酸检测结果|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;symptom|症状|string||
|&emsp;&emsp;temperature|体温|string||
|&emsp;&emsp;time|诊断时间|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": [
		{
			"diagnosis_id": 0,
			"doctor_id": "",
			"nucleic_acid": "",
			"patient_id": "",
			"symptom": "",
			"temperature": "",
			"time": ""
		}
	],
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 根据诊断ID获取诊断信息


**接口地址**:`/diagnosis/getDiagnosisInfoByID`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以根据诊断ID获取诊断信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|diagnosis_id|诊断ID|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«Diagnosis 诊断实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|Diagnosis 诊断实体|Diagnosis 诊断实体|
|&emsp;&emsp;diagnosis_id|诊断 ID|integer(int64)||
|&emsp;&emsp;doctor_id|医生 ID|string||
|&emsp;&emsp;nucleic_acid|核酸检测结果|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;symptom|症状|string||
|&emsp;&emsp;temperature|体温|string||
|&emsp;&emsp;time|诊断时间|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"diagnosis_id": 0,
		"doctor_id": "",
		"nucleic_acid": "",
		"patient_id": "",
		"symptom": "",
		"temperature": "",
		"time": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 获取当前数据库诊断数量


**接口地址**:`/diagnosis/getDiagnosisNumber`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以获取当前数据库诊断数量


**请求参数**:


暂无


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体«Prescription 用药实体»|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|Prescription 用药实体|Prescription 用药实体|
|&emsp;&emsp;doctor_id|医生|string||
|&emsp;&emsp;dosage|剂量|string||
|&emsp;&emsp;medicine_id|药品 ID|string||
|&emsp;&emsp;patient_id|病人 ID|string||
|&emsp;&emsp;prescription_id|用药 ID|integer(int64)||
|&emsp;&emsp;usage|药物使用方法|string||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {
		"doctor_id": "",
		"dosage": "",
		"medicine_id": "",
		"patient_id": "",
		"prescription_id": 0,
		"usage": ""
	},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```


## 更新诊断信息


**接口地址**:`/diagnosis/updateDiagnosis`


**请求方式**:`POST`


**请求数据类型**:`application/json`


**响应数据类型**:`*/*`


**接口描述**:可以更新诊断信息


**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| -------- | -------- | ----- | -------- | -------- | ------ |
|diagnosis_id|diagnosis_id|query|true|integer(int64)||
|doctor_id|医生 ID|query|false|string||
|nucleic_acid|核酸检测结果|query|false|string||
|patient_id|病人ID|query|false|string||
|symptom|症状|query|false|string||
|temperature|体温|query|false|string||
|time|诊断时间|query|false|string||


**响应状态**:


| 状态码 | 说明 | schema |
| -------- | -------- | ----- |
|200|OK|标准返回结果体|
|401|权限不足||
|403|禁止访问||
|404|无法找到||
|500|出现未知异常||


**响应参数**:


| 参数名称 | 参数说明 | 类型 | schema |
| -------- | -------- | ----- |----- |
|code|返回代码|integer(int64)|integer(int64)|
|data|结果数据|object||
|message|错误消息|string||
|requestid|请求ID|string||
|success|是否成功|boolean||
|timestamp|时间戳|string||
|totalCount|总数信息（仅在分页查询时需要用）|string||


**响应示例**:
```javascript
{
	"code": 0,
	"data": {},
	"message": "",
	"requestid": "",
	"success": true,
	"timestamp": "",
	"totalCount": ""
}
```