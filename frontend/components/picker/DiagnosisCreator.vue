<template>
  <v-dialog v-model="dialog" max-width="600" transition="scale-transition">
    <template v-slot:activator="{ on: dialog }">
      <v-tooltip left>
        <template v-slot:activator="{ on: tooltip }">
          <v-btn fab small dark color="red darken-2"
                 v-on="{...dialog, ...tooltip}"
          >
            <v-icon>mdi-thermometer-plus</v-icon>
          </v-btn>
        </template>
        <span>新增诊断</span>
      </v-tooltip>
    </template>

    <v-card :loading="loading">
      <v-toolbar dark color="red">
        <v-btn icon dark @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>
          <span v-if="loading" class="center">正在加载...  </span>
          <v-progress-circular indeterminate v-if="loading" size="32" class="ml-2"></v-progress-circular>
          <span v-else>新增诊断</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>

      <v-card-text>
        <v-row>
          <v-col sm="6">
            <v-list nav dense class="ma-0">
                <v-tooltip bottom>
                  <template v-slot:activator="{on, attrs}">
                    <v-list-item-group mandatory color="red" class="ma-0">
                      <v-list-item v-on="on" v-bind="attrs">
                        <v-list-item-icon>
                          <v-icon>mdi-doctor</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                          <v-fade-transition mode="out-in">
                            <v-list-item-title :key="formItems.doctor.doctor_name" v-text="formItems.doctor.doctor_name || '未选择'"></v-list-item-title>
                          </v-fade-transition>
                          <v-fade-transition mode="out-in">
                            <v-list-item-subtitle :key="formItems.doctor.department" v-text="formItems.doctor.department || '未选择'"></v-list-item-subtitle>
                          </v-fade-transition>
                        </v-list-item-content>
                        <v-list-item-action v-if="!loading">
                          <HospitalDoctorPicker :hospital_id="patientModel.hospital_id" :doctor_id="patientModel.doctor_id"
                                                v-on:change="updateDoctor"
                          ></HospitalDoctorPicker>
                        </v-list-item-action>
                      </v-list-item>
                    </v-list-item-group>

                  </template>
                  <span>诊断医师</span>
                </v-tooltip>
            </v-list>

          </v-col>
          <v-col sm="6">
            <v-select color="red darken-2" hide-details prepend-icon="mdi-virus"
                      :items="['阴性','阳性']" label="核酸检测结果" v-model="formItems.nucleic_acid">
              <template v-slot:selection="{ item, index }">
<!--                <span-->
<!--                  v-if="item"-->
<!--                  class="grey&#45;&#45;text caption"-->
<!--                >检测结果为：</span>-->
                <v-chip dark color="red" v-if="item == '阳性'">
                  <span>{{ item }}</span>
                </v-chip>
                <v-chip dark color="green" v-else>
                  <span>{{ item }}</span>
                </v-chip>
              </template>
            </v-select>
          </v-col>
          <v-col sm="12">
            <v-textarea color="red darken-2" filled prepend-icon="mdi-heart-pulse" label="病患症状及诊断说明"
                        clearable hide-details v-model="formItems.symptom"></v-textarea>
            <v-row class="ma-3 mr-1">
              <v-spacer></v-spacer>
              <span class="text-body-2 grey--text pa-1">诊断模板：</span>
              <span v-for="(item, index) in diagnosisTemplates">
                  <v-btn class="ml-2" small outlined color="red darken-2"
                         @click="formItems.symptom = item.content"
                  >{{item.title}}</v-btn>
              </span>
            </v-row>

          </v-col>

          <v-col sm="6">
            <v-text-field color="red darken-2" hide-details prepend-icon="mdi-thermometer" type="number" label="体温" v-model="formItems.temperature"></v-text-field>
          </v-col>
          <v-col sm="6">
            <date-time-picker v-model="formItems.datetime" label="诊断时间"></date-time-picker>
          </v-col>
        </v-row>
      </v-card-text>



      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="red darken-1" outlined text @click="dialog = false">取消</v-btn>
        <v-btn color="red darken-1" outlined text @click="createDiagnosis">保存</v-btn>
      </v-card-actions>

      <v-snackbar v-model="snackbar" :timeout="3000">
        {{ snackbar_text }}
        <template v-slot:action="{ attrs }">
          <v-btn color="red" text v-bind="attrs" @click="snackbar = false">
            CLOSE
          </v-btn>
        </template>
      </v-snackbar>

    </v-card>
  </v-dialog>


</template>

<script>

  import HospitalDoctorPicker from './HospitalDoctorPicker'
  import DateTimePicker from './DateTimePicker'
  import axios from 'axios'
  import Config from '../global/Config'

  export default {
    name: 'DiagnosisCreator',
    components: { DateTimePicker, HospitalDoctorPicker},
    props: {
      patientModel: Object,
    },

    data(){
      return {
        dialog: false,
        loading: false,
        doctor_id: 0,
        hospital_id: 0,
        snackbar: false,
        snackbar_text: "",
        menu: false,
        formItems: {
          doctor: {},
          nucleic_acid: [],
          symptom: "",
          temperature: "36.5",
          datetime: new Date()
        },
        diagnosisTemplates: [
          { title: "已治愈", content: "病情稳定，情况良好。" },
          { title: "已确诊", content: "已确诊为新冠肺炎，病状较轻，应注意控制以避免病情恶化。" },
          { title: "恶化中", content: "呼吸略有困难，体温较高，应特别关注病情，有突发情况及时处理。" },
          { title: "改善中", content: "病情略有好转，发烧、呼吸困难等症状减轻，需要进一步确定情况。" },
          { title: "极严重", content: "状态极其危险，应作为重点监护对象，保持密切关注，辅助生命维持设备。" }
        ]
      }
    },

    computed: {
      new_diagnosis() {
        return {
          doctor_id: this.formItems.doctor.doctor_id,
          patient_id: this.patientModel.patient_id,
          symptom: this.formItems.symptom,
          time: this.formItems.datetime.toISOString().substring(0,19).replace('T', ' '),
          temperature: this.formItems.temperature,
          nucleic_acid: this.formItems.nucleic_acid == "阳性" ? 1 : 0
        }
      }
    },

    methods: {
      updateDoctor(e) {
        this.formItems.doctor = e.new_doctor;
      },

      popSnack(msg) {
        this.snackbar_text = msg;
        this.snackbar = true;
      },

      createDiagnosis(){
        console.log(this.formItems);
        console.log(this.new_diagnosis);
        this.loading = true;
        let d = this.new_diagnosis;
        this.$axios.$post(Config.apiurl + '/diagnosis/createDiagnosis', null, {params: d})
        .then(response => {
          if(response.success) {
            this.$emit('change', 'Created!');
            this.popSnack("成功创建处方。");
            this.dialog = false;
          }
          else{
            throw new Error((response.message));
          }
        })
        .catch(error => {
          alert("新建处方失败：" + error.message);
        })
        .finally(e => {
          this.loading = false;
        })
      }
    },

    watch: {
      dialog: {
        handler(){
          this.formItems.doctor = {
            doctor_id: this.patientModel.doctor_id,
            doctor_name: this.patientModel.doctor_name,
            department: this.patientModel.department
          };
        }
      }
    },

    mounted() {

    }
  }
</script>

<style scoped>

</style>
