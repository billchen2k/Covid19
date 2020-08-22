import datetime
import json
import random
import time
import traceback
# import faker
import requests
# from tqdm import tqdm
import os

# Data source:
# https://raw.githubusercontent.com/BlankerL/DXY-COVID-19-Data/master/json/DXYArea-TimeSeries.json

# fake = faker.Factory.create("zh-CN")
# api = "http://8.210.248.203"
api = "http://localhost"
s = requests.session()
res = json.loads(s.post(api + "/user/logIn?identifier=admin&password=admin").text)
print(res)


logf = open('prescriptionimporter.log', 'a+')
def log(s, sender=''):
    global logf
    fs = "<{} {}>: {}".format(datetime.datetime.now().isoformat()[:-4], sender, s)
    print(fs)
    logf.write(fs + '\n')

def new_prescription(patient_id):
    medid = random.randint(1, 2064)
    resM = s.post(api + "/medicine/getMedicineInfoByID", data={
        "medicine_id": medid
    })
    resP = s.post(api + "/patient/getPatientInfoByID", data={
        "patient_id": patient_id
    })
    resD = s.post(api + "/doctor/getDoctorInfo", data={
        "hospital_id": json.loads(resP.text)['data']['hospital_id'],
        "page": 1,
        "size": 100
    })
    med = json.loads(resM.text)['data']
    dosage = random.choice(["一日一次", "一日两次", "一日三次", "四小时一次"]) + '，一次 '
    if med['medicine_name'].find('液') != -1:
        dosage = dosage + random.choice(['100mL', '150mL', '200mL', '250mL'])
    else:
        dosage = dosage + random.choice(['5g', '10g', '15g', '20g'])
    dosage = dosage + "。"
    if med['medicine_name'].find('口服') != -1 or med['medicine_name'].find('颗粒') != -1 or med['medicine_name'].find('片') != -1:
        usage = "口服"
    elif med['medicine_name'].find('注射') != -1:
        usage = "注射"
    else:
        usage = "外用"
    prescript = {
        "patient_id": patient_id,
        "medicine_id": medid,
        "dosage": dosage,
        "usage": usage,
        "doctor_id": random.choice(json.loads(resD.text)['data'])['doctor_id']
    }
    respre = s.post(api + '/prescription/createPrescription', data=prescript)
    log(json.loads(respre.text)['data'])

START_POSITION = 139159

for i in range(START_POSITION, 140342):
    for j in range(3):
        new_prescription(i)
