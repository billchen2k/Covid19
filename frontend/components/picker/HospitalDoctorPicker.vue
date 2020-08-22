<template>
  <v-dialog v-model="dialog" max-width="1000" transition="scale-transition" v-on:keypress.enter="saveResult">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        color="red darken-2"
        small
        text
        outlined
        v-bind="attrs"
        v-on="on"
      >
<!--        <v-icon color="grey">mdi-chevron-right-box</v-icon>-->
        选择...
      </v-btn>
    </template>
    <v-card>
    <v-toolbar dark color="red">
      <v-btn icon dark @click="dialog = false">
        <v-icon>mdi-close</v-icon>
      </v-btn>
      <v-toolbar-title>
        <span v-if="loading" class="center">正在加载...  </span>
        <v-progress-circular indeterminate v-if="loading"></v-progress-circular>
        <span v-else>选择{{allow_edit_hospital ? '医院及' : ''}}医生</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
<!--      <v-toolbar-items>-->
<!--        <v-btn v-if="this.editable" dark text @click="dialog = false"><v-icon class="pr-2">mdi-content-save</v-icon>保存修改</v-btn>-->
<!--      </v-toolbar-items>-->
    </v-toolbar>
    <v-card-text>
      <v-row>
        <v-col sm="6">

          <v-text-field :label="allow_edit_hospital ? '键入名称以筛选医院' : '已选定医院：'" prepend-icon="mdi-magnify"
                        placeholder="至少输入一些内容来进行搜索" v-model="searchHospitalText"
                        :disabled="!allow_edit_hospital"
          ></v-text-field>

          <v-card :loading="loadingHospital">
            <v-list dense nav>

              <v-list-item-group color="red darken-2" mandatory>
                <v-tooltip bottom>
                    <template v-slot:activator="{on, attrs}">
                      <v-list-item v-on="on" v-bind="attrs">
                        <v-list-item-icon>
                          <v-icon>mdi-hospital-building</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                          <v-list-item-title v-text="selected_hospital_model.hospital_name"></v-list-item-title>
                          <v-list-item-subtitle v-text="selected_hospital_model.address"></v-list-item-subtitle>
                        </v-list-item-content>
                      </v-list-item>
                    </template>
                  <span>已选择的医院</span>
                </v-tooltip>
            </v-list-item-group>


            </v-list>
            <v-divider></v-divider>
            <v-list dense class="scroll" height="450px">
              <div v-if="hospitals.length == 0" class="annotation px-3">无匹配数据，请键入正确的筛选条件，至少输入一些搜索内容。</div>
              <v-list-item-group v-model="selected_hospital" color="primary" mandatory>
                <v-list-item
                  v-for="(item, i) in hospitals"
                  v-on:click="selected_hospital_model = hospitals[i]"
                  :key="i">
                  <v-list-item-icon>
                    <v-icon>mdi-hospital-building</v-icon>
                  </v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title v-text="item.hospital_name"></v-list-item-title>
                    <v-list-item-subtitle v-text="item.address"></v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>


            <!--              <v-list dense nav>-->
<!--                <v-list-item-group v-model="selected_hospital" color="primary" mandatory>-->
<!--                  <v-virtual-scroll item-height="56" height="500" :items="hospitals">-->
<!--                    <template v-slot="{ item }">-->
<!--                      <v-list-item :key="item.hospital_id">-->
<!--                        <v-list-item-icon>-->
<!--                          <v-icon>mdi-hospital-building</v-icon>-->
<!--                        </v-list-item-icon>-->
<!--                        <v-list-item-content>-->
<!--                          <v-list-item-title v-text="item.hospital_name"></v-list-item-title>-->
<!--                          <v-list-item-subtitle v-text="'副标题'"></v-list-item-subtitle>-->
<!--                        </v-list-item-content>-->
<!--                      </v-list-item>-->
<!--                    </template>-->
<!--                  </v-virtual-scroll>-->
<!--                </v-list-item-group>-->

<!--              </v-list>-->
          </v-card>



        </v-col>
        <v-col sm="6">

          <v-text-field label="搜索医院内的医生" prepend-icon="mdi-magnify" placeholder="过滤科室及姓名"
                        v-model="searchDoctorText"
                        clearable
          ></v-text-field>

          <v-card :loading="loadingDoctor">
            <v-list dense nav>
              <v-list-item-group color="red darken-2" mandatory>

                <v-tooltip bottom>
                  <template v-slot:activator="{on, attrs}">
                    <v-list-item v-on="on" v-bind="attrs">
                      <v-list-item-icon>
                        <v-icon>mdi-doctor</v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>
                        <v-fade-transition mode="out-in">
                          <v-list-item-title :key="selected_doctor_model.doctor_name" v-text="selected_doctor_model.doctor_name"></v-list-item-title>
                        </v-fade-transition>
                        <v-fade-transition mode="out-in">
                        <v-list-item-subtitle :key="selected_doctor_model.department" v-text="selected_doctor_model.department"></v-list-item-subtitle>
                        </v-fade-transition>
                      </v-list-item-content>
                    </v-list-item>
                  </template>
                  <span>已选择的医生</span>
                </v-tooltip>
              </v-list-item-group>

            </v-list>
            <v-divider></v-divider>

            <v-list dense class="scroll" height="450px">
              <div v-if="doctors.length == 0" class="annotation px-3">正在加载数据...</div>
              <v-list-item-group v-model="selected_doctor" color="primary" mandatory>
                <v-list-item
                  v-for="(item, i) in doctors"
                  v-on:click="selected_doctor_model=doctors[i]"
                  :key="i">
                  <v-list-item-icon>
                    <v-icon>mdi-doctor</v-icon>
                  </v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title v-text="item.doctor_name"></v-list-item-title>
                    <v-list-item-subtitle v-text="item.department"></v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-col>
      </v-row>
    </v-card-text>

    <v-card-actions>
      <v-spacer></v-spacer>

      <v-btn color="red darken-1" outlined text @click="dialog = false">取消</v-btn>
      <v-btn color="red darken-1" outlined text @click="saveResult">保存</v-btn>
    </v-card-actions>

    </v-card>

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
  </v-dialog>

</template>

<script>
  import axios from 'axios'
  import Config from '../global/Config'

  export default {
    name: 'HospitalDoctorPicker',

    props: {
      doctor_id: {
        type: String,
        required: false,
        default: 0
      },
      hospital_id: {
        type: String,
        required: false,
        default: 0
      },
      allow_edit_hospital: {
        type: Boolean,
        required: false,
        default: true
      }
    },

    data() {
      return {
        dialog: false,
        loading: false,
        searchHospitalText: "",
        searchDoctorText: "",
        loadingHospital: true,
        loadingDoctor: true,
        timer: 0,
        snackbar: false,
        snackbar_text: "",
        mounting: true,
        selected_hospital: -1,
        selected_doctor: -1,
        selected_hospital_model: {},
        selected_doctor_model: {},
        hospitals: [],
        doctors: [],
        doctors_unfiltered: []
      }
    },

    methods: {
      popSnack(msg) {
        this.snackbar_text = msg;
        this.snackbar = true;
      },

      fetchHospital() {
        if (this.searchHospitalText == "") {
          this.popSnack("由于医院数量过多，请至少输入一个字以筛选医院。")
          return;
        }
        this.loadingHospital = true;
        axios.post(Config.apiurl + "/hospital/getHospitalInfo", null, {params: {
          name: this.searchHospitalText
          }})
        .then(response => {
          this.hospitals = response.data.data;
          this.loadingHospital = false;
        })
        .catch(error => {
          alert('无法连接到服务器，刷新重试。\n' + error.message);
        });
      },

      async fetchDoctor() {
        this.loadingDoctor = true;
        this.searchDoctorText = "";
        this.doctors = [];
        return axios.post(Config.apiurl + "/doctor/getDoctorInfo", null, {params: {
            hospital_id: this.selected_hospital_model.hospital_id,
            page: 1,
            size: 500
          }})
          .then(response => {
            this.doctors = this.doctors_unfiltered = response.data.data;
            this.loadingDoctor = false;
          })
          .catch(error => {
            alert('无法连接到服务器，刷新重试。\n' + error.message);
          });
      },

      saveResult() {
        this.$emit('change', {
          new_hospital: this.selected_hospital_model,
          new_doctor: this.selected_doctor_model
        });
        this.dialog = false;
      },

      deletePatient() {

      }
    },

    computed: {
      items () {
        return Array.from({ length: 1 }, (k, v) => v + 1)
      },
    },

    mounted() {
      axios.post(Config.apiurl + "/doctor/getDoctorInfoByID", null, {params: {
          doctor_id: this.doctor_id
        }}).then(response => {
        this.selected_doctor_model = response.data.data;
        axios.post(Config.apiurl + "/hospital/getHospitalInfoByID", null, {params: {
            hospital_id: this.selected_doctor_model.hospital_id
          }}).then(response => {
          this.selected_hospital_model = response.data.data;
          this.searchHospitalText = this.selected_hospital_model.hospital_name;
        });
      })
        .catch(error => {
          alert('无法连接到服务器，刷新重试。\n' + error.message);
        });
    },

    watch: {
      selected_hospital_model: {
        handler() {
          console.log(this.selected_hospital_model);
          this.fetchDoctor().then(res =>{
            if(this.mounting) {
              for(let i = 0; i < this.doctors.length; i++){
                if(this.doctors[i].doctor_id == this.selected_doctor_model.doctor_id) {
                  this.selected_doctor = i;
                  break;
                }
              }
            }
            if(!this.mounting){
              this.selected_doctor = 0;
              this.selected_doctor_model = this.doctors[0];
            }
            this.mounting = false;
          });
        }
      },

      searchHospitalText: {
        handler() {
          clearTimeout(this.timer);
          this.timer = setTimeout(this.fetchHospital, 300);
        }
      },

      searchDoctorText: {
        handler() {
          this.doctors = this.doctors_unfiltered.filter(one =>
            one.doctor_name.includes(this.searchDoctorText) || one.department.includes(this.searchDoctorText));
        }
      },
    }
  }
</script>

<style scoped>
  .scroll {
    overflow-y: scroll
  }

  .annotation{
    font-size: 80%;
    color: grey;
  }
</style>
