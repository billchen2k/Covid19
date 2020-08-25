<template>
  <v-dialog v-model="dialog" max-width="700" transition="scale-transition">
    <template v-slot:activator="{ on: dialog }">
      <v-tooltip left>
        <template v-slot:activator="{ on: tooltip }">
          <v-btn fab small dark color="red darken-2"
                 v-on="{...dialog, ...tooltip}"
          >
            <v-icon>mdi-text-box-plus</v-icon>
          </v-btn>
        </template>
        <span>新增处方</span>
      </v-tooltip>
    </template>

    <v-card>
      <v-toolbar dark color="red">
        <v-btn icon dark @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>
          <span v-if="loading" class="center">正在处理...  </span>
          <v-progress-circular indeterminate v-if="loading" size="24" class="ml-2"></v-progress-circular>
          <span v-else>新增处方</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>

      <v-card-text>
        <v-row>
          <v-col sm="5" class="pb-0" >
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
                <span>处方医师</span>
              </v-tooltip>
            </v-list>

          </v-col>
          <v-col sm="7" class="pb-0" >
            <v-list nav dense class="ma-0">
              <v-tooltip bottom>
                <template v-slot:activator="{on, attrs}">
                  <v-list-item-group mandatory color="red" class="ma-0">
                    <v-list-item v-on="on" v-bind="attrs">
                      <v-list-item-icon>
                        <v-icon>mdi-pill</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-fade-transition mode="out-in">
                          <v-list-item-title :key="formItems.medicine.medicine_name || 0" v-text="formItems.medicine.medicine_name || '未选择'"></v-list-item-title>
                        </v-fade-transition>
                        <v-fade-transition mode="out-in">
                          <v-list-item-subtitle :key="formItems.medicine.manufacturer || 0" v-text="formItems.medicine.manufacturer || '未选择'"></v-list-item-subtitle>
                        </v-fade-transition>
                      </v-list-item-content>
                      <v-list-item-action v-if="!loading">
                        <medicine-picker v-model="formItems.medicine" ></medicine-picker>
                      </v-list-item-action>
                    </v-list-item>
                  </v-list-item-group>
                </template>
                <span>药品</span>
              </v-tooltip>
            </v-list>
          </v-col>

          <v-col sm="5">
            <v-select color="red darken-2" :items="['注射用', '外用', '口服', '其他']" label="用药方式" prepend-icon="mdi-needle"
                      v-model="formItems.usage"
            ></v-select>
          </v-col>
          <v-col sm="7">
            <v-card outlined>
              <v-slide-y-transition leave-absolute mode="out-in">
                <v-card-text :key="formItems.medicine.introduction"><strong>药品说明：</strong>{{formItems.medicine.introduction || '未选择'}}</v-card-text>
              </v-slide-y-transition>
            </v-card>
          </v-col>
          <v-col sm="12">
            <v-textarea color="red darken-2" hide-details prepend-icon="mdi-scale" label="用量" placeholder="一日 ... 次，一次 ...。" clearable
                          filled
                          v-model="formItems.dosage"></v-textarea>
            <v-row class="ma-3 mr-1">
              <v-spacer></v-spacer>
              <span class="text-body-2 grey--text pa-1">处方模板：</span>
              <span v-for="(item, index) in prescriptionTemplates">
                  <v-btn class="ml-2" small outlined color="red darken-2"
                         @click="formItems.dosage = (formItems.dosage || '') + item.content"
                  >{{item.title}}</v-btn>
              </span>
            </v-row>

          </v-col>
        </v-row>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="red darken-1" outlined text @click="dialog = false">取消</v-btn>
        <v-btn color="red darken-1" outlined text @click="createPrescription">保存</v-btn>
      </v-card-actions>
    </v-card>

    <v-snackbar v-model="snackbar" :timeout="3000">
      {{ snackbar_text }}
      <template v-slot:action="{ attrs }">
        <v-btn color="red" text v-bind="attrs" @click="snackbar = false">
          CLOSE
        </v-btn>
      </template>
    </v-snackbar>

  </v-dialog>
</template>

<script>
  import MedicinePicker from './MedicinePicker'
  import HospitalDoctorPicker from './HospitalDoctorPicker'
  import Config from '../global/Config'

  export default {
    name: 'PrescriptionCreator',
    components: { HospitalDoctorPicker, MedicinePicker },
    props: {
      patientModel: Object
    },

    data(){
      return {
        dialog: false,
        loading: false,
        snackbar: false,
        snackbar_text: "",
        formItems: {
          medicine: {},
          doctor: {},
          dosage: "",
          usage: ""
        },
        prescriptionTemplates: [
          {title: "1 / d", content: "一日一次，每次 。"},
          {title: "2 / d", content: "一日两次，每次 。"},
          {title: "3 / d", content: "一日三次，每次 。"},
          {title: "1 / 4h", content: "每四时一次，每次 。"},
          {title: "必要服", content: "必要时服用。"},
          {title: "遵医嘱", content: "服用时请注意遵循护士及主治医生的建议。"},
          {title: "可加量", content: "症状过于严重时可适当增加剂量。"},

        ]
      }
    },

    methods: {
      updateDoctor(e) {
        this.formItems.doctor = e.new_doctor;
      },

      createPrescription(){
        console.log(this.formItems);
        console.log(this.new_prescription);
        this.loading = true;
        let p = this.new_prescription;
        this.$axios.$post(Config.apiurl + '/prescription/createPrescription', null, {params: p})
          .then(response => {
            if(response.success) {
              this.$emit('change', 'Created!');
              this.snackbar_text = "成功创建处方";
              this.snackbar = true;
              this.dialog = false;
            }
            else{
              throw new Error((response.message));
            }
          })
          .catch(error => {
            alert("新建诊断失败：" + error.message);
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

    computed: {
      new_prescription() {
        return {
          doctor_id: this.formItems.doctor.doctor_id,
          dosage: this.formItems.dosage,
          usage: this.formItems.usage,
          medicine_id: this.formItems.medicine.medicine_id,
          patient_id: this.patientModel.patient_id
        }
      }
    },

    mounted() {

    }
  }
</script>

<style scoped>

</style>
