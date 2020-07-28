import json
import random
import time

import faker
import requests

fake = faker.Factory.create("zh-CN")

api = "http://45.77.26.112/"
api = "http://localhost/"
s = requests.session()
res = json.loads(s.post(api + "user/logIn?identifier=admin&password=admin").text)

with open('DXYArea-TimeSeries.json', 'r') as f:
	data = json.load(f)

china = []
for one in data:
	if (one['countryName'] == '中国'):
		one['updateTime'] = time.strftime(
			"%Y-%m-%d", time.localtime(one['updateTime'] // 1000))
		china.append(one)


def new_hostital(address, name):
	p = {
		'address': address,
		'name': name
	}
	res = s.post(api + 'hospital/createHospital', data=p)
	print(json.loads(res.text))


departments = ['神经内科', '呼吸内科', '心血管科', '消化内科', '肾内科', '血液科', '内分泌科',
               '传染科', '神经外科', '耳鼻喉科', '口腔科', '肛肠科', '骨科', '皮肤科', '妇科', '肿瘤外科',
               '泌尿外科', '生殖科', '麻醉科', '精神科', '康复科', '体检科', '普通外科', '血液科', '风湿代谢科', '中医科', '疼痛科', '预防保健科', '肝胆胰脾外科',
               '传染科', '传染科', '传染科', '传染科', '传染科', ]

def new_doctor():
	profile = fake.profile()
	doctor = {
		'birthday': profile['birthdate'].strftime("%Y-%m-%d"),
		'department': random.choice(departments),
		'gender': profile['sex'],
		'hospital_id': random.randint(0, 539),
		'name': profile['name']
	}
	res = s.post(api + 'doctor/createDoctor', data=doctor)
	print(json.loads(res.text)['data'])


# for i in range(9000):
#     print("\r{} / 9000".format(i))
#     new_doctor()

city = set()
citytimeline = {}

for one in china:
	try:
		for two in one['cities']:
			city.add(two['cityName'])
			citytimeline[two['cityName']] = []
	except:
		pass
for one in china:
	try:
		for two in one['cities']:
			citytimeline[two['cityName']].insert(0, {
				'city': two['cityName'],
				'province': one['provinceName'],
				"date": one['updateTime'],
				'currentConfirmedCount': two['currentConfirmedCount'],
				'confirmedCount': two['confirmedCount'],
				'suspectedCount': two['suspectedCount'],
				'curedCount': two['curedCount'],
				'deadCount': two['deadCount']
			})
	except:
		pass

status = ['治疗中', '治疗中', '治疗中', '治疗中', '已康复', '已康复', '已康复', '已康复', '已康复', '已康复', '已康复', '已康复', '已康复', '已康复']


def new_patient(city, province, date, status):
	profile = fake.profile()
	patient = {
		'birthday': profile['birthdate'].strftime("%Y-%m-%d"),
		'confirm_date': date,
		'doctor_id': random.randint(0, 9146),
		'gender': 1 if profile['sex'] == 'M' else 0,
		# 'hospital_id': city + "第一人民医院",
		'hospital_id': city + "第一人民医院" if (city.find('人员') == -1 and city.find('境外') == -1 and city.find('监狱') == -1)
		else "中心医院",
		'name': profile['name'],
		'onset_date': date,
		'onset_place': province + city,
		'is_doctor': 0,
		'status': status
	}
	print(patient)
	res = s.post(api + 'patient/createPatient', data=patient)
	print(json.loads(res.text)['data'])

diagnosis = [
	{
		"temp": random.randint(360, 370) / 10,
		"words": "病情稳定，情况良好。",
		"dna": 0
	},
	{
		"temp": random.randint(370, 380) / 10,
		"words": "轻微发烧症状，呼吸略有困难，需要进一步确定情况。",
		"dna": 0
	},
	{
		"temp": random.randint(370, 380) / 10,
		"words": "呼吸略有困难，应当辅助药物治疗，并保证已经被隔离。",
		"dna": 1
	},
	{
		"temp": random.randint(380, 390) / 10,
		"words": "温度较高，应特别关注病情，有突发情况及时处理。",
		"dna": 1
	},
	{
		"temp": random.randint(390, 395) / 10,
		"words": "状态很危险，应辅助呼吸治疗，必要时转入重点监护病房。",
		"dna": 1
	},
	{
		"temp": random.randint(395, 412) / 10,
		"words": "状态极其危险，应作为重点监护对象，保持密切关注，辅助生命维持设备。",
		"dna": 1
	},
]

for one in citytimeline.values():
	for i, today in enumerate(one):
		if i == 0:
			newCount = today['confirmedCount']
			newDead = today['deadCount']
		else:
			newCount = today['confirmedCount'] - one[i - 1]['confirmedCount']
			newDead = today['deadCount'] - one[i - 1]['deadCount']
		for t in range(newCount - newDead):
			new_patient(today['city'], today['province'], today['date'], random.choice(status))
		for t in range(newDead):
			new_patient(today['city'], today['province'], today['date'], '已死亡')
