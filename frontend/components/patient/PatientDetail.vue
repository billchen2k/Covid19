<template>
  <div>
    <v-dialog v-model="dialog" fullscreen hide-overlay transition="scale-transition" origin="center right">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="red darken-2"
          small
          text
          v-bind="attrs"
          v-on="on"
        >
          <v-icon small>mdi-arrow-expand</v-icon> 展开
        </v-btn>
      </template>
      <v-card>
        <v-toolbar dark color="red darken-2">
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title>
            <span v-if="loading">正在加载...  </span>
            <v-progress-circular indeterminate v-if="loading"></v-progress-circular>
            <span v-else>病人详情</span>
          </v-toolbar-title>
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn v-if="this.editable" dark text @click="dialog = false">保存修改</v-btn>
          </v-toolbar-items>
        </v-toolbar>

          <v-row class="ma-2">
            <v-col sm="3">
              <v-card>
                <v-card-title>病人简历</v-card-title>
                <v-list dense>
<!--                    <v-subheader>ID: {{patient_id}}）</v-subheader>-->
                    <template v-for="(item, index) in detailListItems">
                      <v-list-item two-line>
                        <v-list-item-avatar>
                          <v-icon>{{item.icon}}</v-icon>
                        </v-list-item-avatar>
                        <v-list-item-content>
                          <v-list-item-title>
                            <v-slide-x-reverse-transition>
                              <v- v-if="!item.editing">{{item.value}}</v->
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
                        <v-list-item-action v-if="editable && item.editable">
                          <v-btn icon v-on:click="detailListItems.forEach(one =>{if(one !== item) {one.editing = false}}); item.editing = !item.editing">
                            <v-icon color="grey" v-if="!item.editing"
                            >mdi-pencil-box</v-icon>
                            <v-icon color="green light-2" v-else>mdi-checkbox-marked</v-icon>
                          </v-btn>
                        </v-list-item-action>
                      </v-list-item>
                      <v-divider v-if="item.divider"></v-divider>
                    </template>

                  </v-list>

              </v-card>

            </v-col>


            <v-col sm="9">
              <v-card>
                <v-card-title>诊断记录</v-card-title>
                <v-fade-transition>
                  <v-data-table
                    calculate-widths
                    :loading="loadingDiagnosis"
                    :headers="diagnosisHeaders"
                    :items="patientDatail.diagnosis"
                    :items-per-page="100"
                    :sort-desc="true"
                    item-key="diagnosis_id"
                    loading-text="正在加载数据，请稍候"
                    no-data-text="无匹配数据"
                  >
                  </v-data-table>
                </v-fade-transition>
              </v-card>

            </v-col>
          </v-row>

      </v-card>
    </v-dialog>
  </div>

</template>

<script>

  import axios from 'axios'
  import Config from '../global/Config'
  import DatePicker from '../picker/DatePicker'

  export default {

    name: 'PatientDetail',
    components: { DatePicker },
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
        dialog: false,
        loading: true,
        loadingDiagnosis: true,
        patientDatail: {
          data: {},
          diagnosis: [
            {}
          ],
        },
        diagnosisHeaders: [
          { text: '#', value: 'diagnosis_id' },
          {
            text: '诊断医生',
            value: 'doctor_name',
          },
          { text: '诊断时间', value: 'time' },
          { text: '体温', value: 'temperature' },
          { text: '核酸检测', value: 'nucleic_acid' },
          { text: '症状', value: 'symptom' },
        ],
        detailListItems: [
          {
            icon: "",
            description: "正在加载……",
            value: "……",
            editing: false
          }
        ]
      }
    },
    methods: {
      fillList() {
        this.detailListItems = [
          {
            icon: "mdi-account",
            description: "病人姓名",
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


      fetchDiagnosis() {
        this.loadingDiagnosis = true;
        axios.post(Config.apiurl + '/diagnosis/getDiagnosisInfo', null, {params: {
            page: 1,
            size: 200,
            patient_id: this.patient_id
          }})
          .then(response => {
            this.patientDatail.diagnosis = response.data.data;
            this.patientDatail.diagnosis.forEach(one => {
              one.nucleic_acid = one.nucleic_acid == 1 ? "阳性" : "阴性";
              one.doctor_name = "正在加载"
              axios.post(Config.apiurl + '/doctor/getDoctorInfoByID', null, {params: {
                  doctor_id: one.doctor_id
                }}).then(response => {
                one.doctor_name = response.data.data.doctor_name;
              })
            })
          })
          .finally(() => {
            this.loadingDiagnosis = false;
            console.error(this.patientDatail.diagnosis);
          })
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
            this.fetchDiagnosis();
          }
          else {
            // Dialog closed
            this.$emit('close', 'Dialog closed');
          }
        }
      }
    }
  }
</script>

<style scoped>

</style>
