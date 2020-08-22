<template>
    <div>
      <page-header
        icon="mdi-pencil-circle-outline"
        title="病患登记"
        date=""
      />

      <v-card>
        <v-card-title>新患者登记</v-card-title>
        <v-card-subtitle>当前数据库内已有 {{totalPatientLength}} 位患者数据。</v-card-subtitle>
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


    </div>
</template>

<script>
  import PageHeader from '../components/PageHeader'
  import DatePicker from '../components/picker/DatePicker'
  import HospitalDoctorPicker from '../components/picker/HospitalDoctorPicker'
  export default {
    name: 'checkin',
    components: { HospitalDoctorPicker, DatePicker, PageHeader },
    data() {
      return {
        totalPatientLength: 0,
        loadingPatient: false,
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
            value: "",
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
            value: "",
            type: "date"
          },
          {
            icon: "mdi-calendar-alert",
            description: "确诊日期",
            model: "confirm_date",
            value: "",
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

      checkInPatient() {
        let p = this.new_patient;
        this.loadingPatient = true;
        console.log(p);
        setTimeout(() => this.loadingPatient = false, 1000);
      }
    }
  }
</script>

<style scoped>

</style>
