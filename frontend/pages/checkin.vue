<template>
    <div>
      <page-header
        icon="mdi-pencil-circle-outline"
        title="病患登记"
        date=""
      />

      <v-card>
        <v-card-title>新患者登记</v-card-title>
        <v-card-subtitle>当前数据库内已有 {{totalPatientLength || '...'}} 位患者数据。</v-card-subtitle>
        <v-card-text>
          <v-card outlined class="px-6">
            <v-row>
              <v-col v-for="(item, index) in formItems" sm="12" md="6" lg="4">
                <v-select v-if="item.type == 'select'" :items="item.items" :prepend-icon="item.icon" :label="item.description"
                          color="red darken-2"
                          v-model="item.value"></v-select>

                <v-text-field v-else-if="item.type == 'text'" :label="item.description" :prepend-icon="item.icon" clearable
                              color="red darken-2"
                              v-model="item.value"></v-text-field>

                <DatePicker v-else-if="item.type == 'date'" type="date" :value="item.value" :prepend-icon="item.icon" :label="item.description"
                            v-on:change="(value) => item.value = value"></DatePicker>

                <v-list dense v-else-if="item.type == 'doctor'" nav>
                  <v-list-item-group mandatory color="red">
                    <v-list-item>
                      <v-list-item-icon><v-icon>{{item.icon}}</v-icon></v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title>{{item.value.doctor_name || '主治医生'}}</v-list-item-title>
                        <v-list-item-subtitle>{{item.value.department || '未选择'}}</v-list-item-subtitle>
                      </v-list-item-content>
                      <v-list-item-action>
                        <HospitalDoctorPicker allow_edit_hospital
                                              v-on:change="updateHospitalDoctor"></HospitalDoctorPicker>
                      </v-list-item-action>
                    </v-list-item>
                  </v-list-item-group>
                </v-list>

                <v-list dense v-else-if="item.type == 'hospital'" nav>
                  <v-list-item-group mandatory color="red">
                    <v-list-item>
                      <v-list-item-icon><v-icon>{{item.icon}}</v-icon></v-list-item-icon>
                      <v-list-item-content>
                        <v-list-item-title>{{item.value.hospital_name || '所在医院'}}</v-list-item-title>
                        <v-list-item-subtitle>{{item.value.address || '未选择'}}</v-list-item-subtitle>
                      </v-list-item-content>
                      <v-list-item-action>
                        <HospitalDoctorPicker allow_edit_hospital v-show="false"
                                              v-on:change="updateHospitalDoctor"></HospitalDoctorPicker>
                      </v-list-item-action>
                    </v-list-item>
                  </v-list-item-group>
                </v-list>

              </v-col>
            </v-row>
          </v-card>
          <v-card-actions class="mt-3">
            <v-spacer></v-spacer>
            <v-slide-x-reverse-transition mode="out-in">
              <strong v-if="showError" class="red--text mr-2 text-body2 text--darken-2">请完整填写所有信息。</strong>
            </v-slide-x-reverse-transition>
            <v-btn color="red darken-2" outlined
                   v-on:click="checkInPatient"
                   :disabled="loadingPatient"
            >
              <v-expand-x-transition mode="out-in">
                <div :key="loadingPatient">
                  <v-icon v-if="!loadingPatient" class="mr-3">mdi-account-check</v-icon>
                  <v-progress-circular v-else size="20" width="3" indeterminate></v-progress-circular>
                  {{loadingPatient ? '' : '登记患者'}}
                </div>
              </v-expand-x-transition>
            </v-btn>
          </v-card-actions>
        </v-card-text>
      </v-card>

      <v-row>
        <v-col sm="12" md="6">
          <v-card>
            <v-card-title>登记诊断记录</v-card-title>
            <v-card-subtitle>当前数据库内已有 {{totalDiagnosisLength || '...'}} 条诊断记录。</v-card-subtitle>

            <v-card-text class="pa-6">
              <v-btn depressed block large color="red darken-2" outlined dark
              @click="$router.push({path: localePath('/manage')})">
                跳转到「<v-icon small class="mx-1">mdi-bed-empty</v-icon>病患管理」来选择病人并登记
                <v-icon class="ml-2">mdi-arrow-right</v-icon>
              </v-btn>
            </v-card-text>

          </v-card>
        </v-col>

        <v-col sm="12" md="6">
          <v-card >
            <v-card-title>登记新处方</v-card-title>
            <v-card-subtitle>当前数据库内已有 {{totalPrescirptionLength || '...'}} 条处方记录。</v-card-subtitle>

            <v-card-text class="pa-6">
              <v-btn depressed block large color="red darken-2" outlined dark
                     @click="$router.push({path: localePath('/manage')})">
                跳转到「<v-icon small class="mx-1">mdi-bed-empty</v-icon>病患管理」来选择病人并登记
                <v-icon class="ml-2">mdi-arrow-right</v-icon>
              </v-btn>
            </v-card-text>

          </v-card>
        </v-col>

      </v-row>

<!--      <medicine-picker></medicine-picker>-->


      <v-snackbar
        vertical
        elevation="24"
        top
        v-model="snackbar"
        :timeout="30000"
        color="primary"
      >

          {{ snackbar_text }}

        <v-row>
          <v-spacer></v-spacer>

        </v-row>

        <template v-slot:action="{ attrs }">
          <patient-detail v-if="createdPatientId != -1" editable :patient_id="createdPatientId">
            <template v-slot:activator="slotProps">
              <v-btn
                color="white--text"
                text
                v-bind="slotProps.bind"
                v-on="slotProps.on"
              >
                <v-icon small class="mr-3">mdi-arrow-expand</v-icon> 展开病人详情...
              </v-btn>
            </template>
          </patient-detail>

          <v-btn class="ml-3"
            color="white"
            text
            v-bind="attrs"
            @click="snackbar = false"
          >
            确定
          </v-btn>
        </template>
      </v-snackbar>
    </div>
</template>

<script>
  import PageHeader from '../components/PageHeader'
  import DatePicker from '../components/picker/DatePicker'
  import HospitalDoctorPicker from '../components/picker/HospitalDoctorPicker'
  import MedicinePicker from '../components/picker/MedicinePicker'
  import Config from '../components/global/Config'
  import PatientDetail from '../components/patient/PatientDetail'
  import error from '../layouts/error'

  export default {
    name: 'checkin',
    components: { PatientDetail, MedicinePicker, HospitalDoctorPicker, DatePicker, PageHeader },
    data() {
      return {
        showError: false,
        snackbar_text: "",
        snackbar: false,
        totalPatientLength: null,
        totalPrescirptionLength: null,
        totalDiagnosisLength: null,
        loadingPatient: false,
        createdPatientId: -1,
        formItems: [
          {
            icon: "mdi-account",
            description: "姓名",
            model: "name",
            value: "",
            type: "text"
          },
          {
            icon: this.value == '男' ? "mdi-gender-female" : "mdi-gender-male",
            description: "性别",
            model: "gender",
            value: "",
            type: "select",
            items: ['男', '女']
          },
          {
            icon: "mdi-balloon",
            description: "生日",
            model: "birthday",
            value: "2000-01-01",
            type: "date"
          },
          {
            icon: "mdi-map-marker",
            description: "发病地点",
            model: "onset_place",
            value: "",
            type: "text"
          },
          {
            icon: "mdi-calendar-arrow-right",
            description: "发病日期",
            model: "onset_date",
            value: "2020-02-01",
            type: "date"
          },
          {
            icon: "mdi-calendar-alert",
            description: "确诊日期",
            model: "confirm_date",
            value: "2020-02-01",
            type: "date"
          },
          {
            icon: "mdi-doctor",
            description: "主治医生",
            model: "doctor",
            value: {},
            type: "doctor"
          },
          {
            icon: "mdi-hospital-building",
            description: "所在医院",
            model: "hospital",
            value: {},
            type: "hospital",
          },
          {
            icon: this.value == '已死亡' ? "mdi-emoticon-dead" : "mdi-emoticon-happy",
            description: "当前状态",
            model: "status",
            value: "",
            type: "select",
            items: ["治疗中", "已康复", "已死亡"]
          },
        ]
      }
    },

    computed: {
      new_patient() {
        let p = {
          is_doctor: 0
        };
        this.formItems.forEach(one => {
          if(one.model == 'hospital'){
            p.hospital_id = one.value.hospital_id;
          }
          else if(one.model == 'doctor'){
            p.doctor_id = one.value.doctor_id;
          }
          else if(one.model == 'gender'){
            p.gender = one.value == '男' ? 'M' : 'F';
          }
          else{
            p[one.model] = one.value;
          }
        });
        return p;
      }
    },

    methods: {
      popSnack(msg) {
        this.snackbar_text = msg;
        this.snackbar = true;
      },

      updateHospitalDoctor(e) {
        this.formItems.map(one => {
          if(one.model == 'hospital'){
            one.value = e.new_hospital;
            return one;
          }
          else if(one.model == 'doctor'){
            one.value = e.new_doctor;
            return one;
          }
          return one;
        })
      },
      updateCount() {
        this.$axios.$post(Config.apiurl + "/patient/getPatientInfo", null, {params: {
            page: 1,
            size: 0
          }}).then(response => {
          this.totalPatientLength = response.totalCount;
        })
          .catch(error => {
            alert('无法连接到服务器，刷新重试。\n' + error.message);
          });

        this.$axios.$post(Config.apiurl + "/prescription/getPrescriptionNumber", null, {params: {
            page: 1,
            size: 0
          }}).then(response => {
          this.totalPrescirptionLength = response.data;
        })
          .catch(error => {
            alert('无法连接到服务器，刷新重试。\n' + error.message);
          });

        this.$axios.$post(Config.apiurl + "/diagnosis/getDiagnosisNumber", null, {params: {
            page: 1,
            size: 0
          }}).then(response => {
          this.totalDiagnosisLength = response.data;
        })
          .catch(error => {
            alert('无法连接到服务器，刷新重试。\n' + error.message);
          });
      },

      checkInPatient() {
        let p = this.new_patient;
        console.log(p);
        for (let one in p) {
          if (p[one] === "" || p[one] == undefined){
            this.showError = true;
            setTimeout(() => {
              this.showError = false;
            }, 1500);
            return;
          }
        }
        this.loadingPatient = true;
        this.$axios.$post(Config.apiurl + '/patient/createPatient', null, {params: p})
        .then(response => {
          console.log(response);
          if(response.success){
            this.createdPatientId = response.data.patient_id;
            this.popSnack(`已创建病人${response.data.patient_name}。您可以立即展开病人以开始登记诊断和处方。`);

            this.updateCount();
          }
          else{
            throw new Error(response.message);
          }
        })
        .catch(error => {
          alert('创建失败。\n' + error.message);
        })
        .finally(e => {
          this.loadingPatient = false;
        })
        setTimeout(() => this.loadingPatient = false, 1000);
      }
    },




    mounted() {
      this.updateCount();
    }
  }
</script>

<style scoped>

</style>
