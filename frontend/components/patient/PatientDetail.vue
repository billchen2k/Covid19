<template>
  <div>
    <v-dialog v-model="dialog" fullscreen hide-overlay transition="scale-transition" origin="center right">
      <template v-slot:activator="{ on, attrs }">
        <slot name="activator" v-bind:on="on" v-bind:attrs="attrs">
          <v-btn
            color="green"
            small
            text
            v-bind="attrs"
            v-on="on"
          >
            <v-icon class="mr-1" small>mdi-arrow-expand</v-icon> 展开
          </v-btn>
        </slot>
      </template>
      <v-card>

        <v-toolbar dark :color="editable ? 'red darken-2' : 'green'">
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title>
            <span v-if="loading">正在加载...  </span>
            <v-progress-circular indeterminate v-if="loading"></v-progress-circular>
            <span v-else>患者档案</span>
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>

            <v-dialog v-if="this.editable" v-model="confirm_delete_dialog" persistent max-width="360">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  text dark
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon class="pr-2">mdi-delete</v-icon>删除
                </v-btn>
              </template>
              <v-card>
                <v-card-title>确定删除病人「{{patientDatail.data.patient_name}}」吗？</v-card-title>
                <v-card-text>此操作无法撤销。</v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="red darken-1" text @click="confirm_delete_dialog = false">取消</v-btn>
                  <v-btn color="red darken-1" text @click="deletePatient">确定</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <v-dialog v-if="this.editable" v-model="confirm_dialog" persistent max-width="290">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  text dark
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon class="pr-2">mdi-content-save</v-icon>保存修改
                </v-btn>
              </template>
              <v-card>
                <v-card-title>确定保存更改吗？</v-card-title>

                <v-card-text>所有更改将立即生效。</v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="red darken-1" text @click="confirm_dialog = false">取消</v-btn>
                  <v-btn color="red darken-1" text @click="updatePatient">确定</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar-items>
        </v-toolbar>

        <v-row class="ma-2">
          <v-col lg="3" md="4" sm="12">
          <v-fade-transition leave-absolute mode="out-in">
            <v-row :key="loading" v-if="!loading" class="mb-6">
              <v-col sm="4">
                <span :class="'avatar ' + (patientDatail.data.status == '已死亡' ? '' : (patientDatail.data.status == '已康复' ? 'green' : 'red'))">{{patientDatail.data.patient_name.substring(0,1)}}</span>
              </v-col>
              <v-col sm="8" class="pt-0 pl-6">
                <div class="text-name">{{patientDatail.data.patient_name}}</div>
                <div class="text-annotation">{{patientDatail.data.hospital_name}}，{{patientDatail.data.status}}</div>
              </v-col>
            </v-row>
          </v-fade-transition>




            <v-card :loading="loading">
<!--              <v-card-title>病人简历</v-card-title>-->
              <v-list dense>
<!--                    <v-subheader>ID: {{patient_id}}）</v-subheader>-->
                  <template v-for="(item, index) in detailListItems">
                    <v-list-item two-line>
                      <v-list-item-avatar>
                        <v-fade-transition leave-absolute mode="out-in">
                          <v-icon :key="item.icon">{{item.icon}}</v-icon>
                        </v-fade-transition>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-title>
                          <v-slide-x-reverse-transition leave-absolute >
                            <span v-if="!item.editing">
                              <span v-if="item.model.includes('doctor')">
                                {{item.value}} （{{patientDatail.data.department}}）
                              </span>
                              <span v-else>{{item.value}}</span>
                            </span>
                            <v-select
                              v-else-if="item.model.includes('gender')"
                              :items="['男', '女']" dense class="py-2" label="选择性别" hide-details
                              v-model="item.value"
                              v-on:change="$data['patientDatail']['data'][item.model] = item.value;
                                item.icon = patientDatail.data.patient_gender == '男' ? 'mdi-gender-male' : 'mdi-gender-female'"
                              color="red darken-2"
                            >
                            </v-select>
                            <v-select
                              v-else-if="item.model.includes('status')"
                              :items="['治疗中', '已康复', '已死亡']" dense class="py-2" label="选择治疗状态" hide-details
                              v-model="item.value"
                              v-on:change="$data['patientDatail']['data'][item.model] = item.value;
                              item.icon = patientDatail.data.status == '已死亡' ? 'mdi-emoticon-dead' : 'mdi-emoticon-happy';"
                              color="red darken-2"
                            >
                            </v-select>
                            <date-picker label="选择生日" dense :value="item.value"
                                        v-else-if="item.model.includes('birth')"
                                        v-on:change="(value) => {$data['patientDatail']['data'][item.model] = value;item.value = value;}"
                            ></date-picker>
                            <date-picker :label="'选择' + item.description" dense :value="item.value"
                                         v-else-if="item.model.includes('date')"
                                         v-on:change="(value) => {$data['patientDatail']['data'][item.model] = value;item.value = value;}"
                            ></date-picker>
                            <v-text-field v-else dense hide-details
                                           v-model="item.value"  color="red darken-2"
                                          v-on:change="$data['patientDatail']['data'][item.model] = item.value"
                                           v-on:keydown.enter="item.editing = false"
                                           class="py-2" :value="item.value" :label="'编辑'"></v-text-field>
                          </v-slide-x-reverse-transition>
                        </v-list-item-title>
                        <v-list-item-subtitle>{{item.description}}</v-list-item-subtitle>
                      </v-list-item-content>
                      <v-list-item-action>
                        <v-btn  v-if="editable && item.editable" icon v-on:click="detailListItems.forEach(one =>{if(one !== item) {one.editing = false}}); item.editing = !item.editing">
                          <v-icon color="grey" v-if="!item.editing"
                          >mdi-pencil-box</v-icon>
                          <v-icon color="green light-2" v-else>mdi-checkbox-marked</v-icon>
                        </v-btn>
                        <hospital-doctor-picker v-else-if="item.model.includes('doctor') && editable"
                                                :hospital_id="patientDatail.data.hospital_id"
                                                :doctor_id="patientDatail.data.doctor_id"
                                                v-on:change="updateHospitalDoctor"
                                                :allow_edit_hospital="true"
                        ></hospital-doctor-picker>
                      </v-list-item-action>
                    </v-list-item>
                    <v-divider v-if="item.divider"></v-divider>
                  </template>
                </v-list>
            </v-card>

            <prescription-card class="my-6" :editable="editable" :patient_id="patient_id" ref="prescriptionCard"></prescription-card>

          </v-col>

          <v-col lg="9" md="8" sm="12">

            <diagnosis-card :editable="editable" :patient_id="patient_id" ref="diagnosisCard"
                            :patient-model="patientDatail.data"
            ></diagnosis-card>

          </v-col>
        </v-row>

      </v-card>
    </v-dialog>

    <v-snackbar
      v-model="snackbar"
      :timeout="3000"
    >
      {{ snackbar_text }}
      <template v-slot:action="{ attrs }">
        <v-btn
          color="red"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>

  </div>

</template>

<script>

  import axios from 'axios'
  import Config from '../global/Config'
  import DatePicker from '../picker/DatePicker'
  import HospitalDoctorPicker from '../picker/HospitalDoctorPicker'
  import PrescriptionCard from './PrescriptionCard'
  import DiagnosisCard from './DiagnosisCard'

  export default {

    name: 'PatientDetail',
    components: { DiagnosisCard, PrescriptionCard, HospitalDoctorPicker, DatePicker },
    props: {
      patient_id: {
        type: Number,
        required: true
      },

      editable: {
        type: Boolean,
        required: false,
        default: false
      }
    },
    data() {
      return {
        temperatureLineChartData: {},
        dialog: false,
        confirm_dialog: false,
        confirm_delete_dialog: false,
        loading: true,
        snackbar: false,
        snackbar_text: "",
        loadingDiagnosis: true,
        patientDatail: {
          data: {},
        },
        detailListItems: [],
      }
    },
    methods: {
      popSnack(msg) {
        this.snackbar_text = msg;
        this.snackbar = true;
      },

      fillList() {
        this.detailListItems = [
          {
            icon: "mdi-account",
            description: "姓名",
            model: "patient_name",
            value: this.patientDatail.data.patient_name,
            editing: false,
            editable: true
          },
          {
            icon: this.patientDatail.data.patient_gender == '男' ? "mdi-gender-male" : "mdi-gender-female",
            description: "性别",
            model: "patient_gender",
            value: this.patientDatail.data.patient_gender,
            editing: false,
            editable: true
          },
          {
            icon: "mdi-balloon",
            description: "生日",
            model: "patient_birthday",
            value: this.patientDatail.data.patient_birthday,
            editing: false,
            editable: true
          },
          {
            icon: "mdi-map-marker",
            description: "发病地点",
            model: "onset_place",
            value: this.patientDatail.data.onset_place,
            editing: false,
            editable: true
          },
          {
            icon: "mdi-calendar-arrow-right",
            description: "发病日期",
            model: "onset_date",
            value: this.patientDatail.data.onset_date,
            editing: false,
            editable: true
          },
          {
            icon: "mdi-calendar-alert",
            description: "确诊日期",
            model: "confirm_date",
            value: this.patientDatail.data.confirm_date,
            editing: false,
            editable: true,
            divider: true
          },
          {
            icon: "mdi-doctor",
            description: "主治医生",
            model: "doctor_name",
            value: this.patientDatail.data.doctor_name,
            editing: false,
            editable: false
          },
          {
            icon: "mdi-hospital-building",
            description: "所在医院",
            model: "hospital_name",
            value: this.patientDatail.data.hospital_name,
            editing: false,
            editable: false
          },
          {
            icon: this.patientDatail.data.status == '已死亡' ? "mdi-emoticon-dead" : "mdi-emoticon-happy",
            description: "当前状态",
            model: "status",
            value: this.patientDatail.data.status,
            editing: false,
            editable: true
          },
        ]
      },

      fetchDetail() {
        this.loading = true;
        axios.post(Config.apiurl + '/patient/getPatientInfoByID', null, {params: {
          patient_id: this.patient_id
          }})
          .then(response => {
            if(response.data.success == true){
              let rawdata = response.data.data;
              rawdata.patient_gender = rawdata.patient_gender == 'F' ? '女' : '男';
              rawdata.patient_birthday = new Date(rawdata.patient_birthday).toISOString().substring(0, 10);
              rawdata.onset_date = new Date(rawdata.onset_date).toISOString().substring(0, 10);
              rawdata.confirm_date = new Date(rawdata.confirm_date).toISOString().substring(0, 10);
              this.patientDatail.data = rawdata;
            }
            else{
              throw new Error(response.data.message)
            }
          })
          .catch(error => {
            alert('无法连接到服务器，刷新重试。\n' + error.message);
          })
          .finally(() => {
            this.loading = false;
            this.fillList();
          });
      },


      updateHospitalDoctor(e) {
        this.patientDatail.data.hospital_id = e.new_hospital.hospital_id;
        this.patientDatail.data.hospital_name = e.new_hospital.hospital_name;
        this.patientDatail.data.doctor_id = e.new_doctor.doctor_id;
        this.patientDatail.data.doctor_name = e.new_doctor.doctor_name;
        this.patientDatail.data.department = e.new_doctor.department;
        this.fillList();
      },

      updatePatient() {
        this.loading = true;
        this.confirm_dialog = false;
        let new_patient = this.patientDatail.data;
        new_patient.patient_gender = new_patient.patient_gender == '男' ? 'M' : 'F';
        axios.post(Config.apiurl + '/patient/updatePatient', null, { params: new_patient})
        .then(response => {
          if(response.data.success == true){
            this.popSnack("已保存。");
            this.dialog = false;
          }
          else if(response.data.code == 401){
            this.popSnack("登录信息已失效，请重新登录。");
            this.$router.push({
              path: this.localePath('/login')
            });
          }
        })
        .catch(error => {
          alert('无法连接到服务器，刷新重试。\n' + error.message);
        })
        .finally(e => {
          this.$emit('save', 'Dialog closed');
          this.loading = false;
        })

      },

      deletePatient() {
        this.dialog = false;
      }
    },

    mounted() {

    },

    computed: {

    },

    watch: {
      dialog: {
        handler() {
          if(this.dialog == true){
            this.fetchDetail();
            // this.$refs.prescreptionCard.fetchPrescription();
            // this.$refs.diagnosisCard.fetchPrescription();
            // this.fetchDiagnosis();
          }
          else {
            // Dialog closed
            // this.$emit('close', 'Dialog closed');
          }
        }
      },

      confirm_dialog: {
        handler() {
          this.detailListItems.forEach(one => one.editing = false);
        }
      }
    }
  }
</script>

<style scoped>

  .text-name {
    font-size: 280%;
    font-weight: bold;
    color: #47494E;
  }

  .text-annotation {
    font-size: 90%;
    font-width: bold;
    color: #47494E;
  }

  .avatar {
    padding: 20px 25px;
    border: #47494E 2px solid;
    background-color: #47494E;
    border-radius: 5%;
    font-size: 350%;
    font-weight: bold;
    color: #F8F9FA;
    transition-duration: 0.5s;
  }

</style>
