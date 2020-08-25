<template>
    <div>
      <page-header
        icon="mdi-account-search"
        title="病患查询"
        date=""
      />

      <v-expansion-panels class="query-selector">
        <v-expansion-panel>
          <v-expansion-panel-header>
            条件筛选
            <v-spacer></v-spacer>
            <transition name="fade">
              <span v-if="loading==false" class="annotation">
               当前共有 {{patients.total_length}} 条有效数据
              </span>
            </transition>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-row>
              <v-col sm="4">
                <v-select
                  v-model="conditions.status"
                  :items="['不限', '已死亡', '已康复', '治疗中']"
                  label="病情状态"
                  color="red darken-2"
                  outlined
                ></v-select>
              </v-col>
              <v-col sm="4">
                <v-select
                  :items="['不限', '男', '女']"
                  label="性别"
                  v-model="conditions.gender"
                  color="red darken-2"
                  outlined
                ></v-select>
              </v-col>

              <v-col sm="4">
                <v-menu
                  ref="menu"
                  v-model="menu"
                  :close-on-content-click="false"
                  :return-value.sync="conditions.onset_date"
                  transition="scale-transition"
                  offset-y
                  max-width="290px"
                  min-width="290px"
                >
                  <template v-slot:activator="{ on, attrs }">
                    <v-text-field
                      outlined
                      v-model="date"
                      label="发病日期"
                      readonly
                      clearable
                      v-bind="attrs"
                      v-on="on"
                      color="red darken-2"
                    ></v-text-field>
                  </template>
                  <v-date-picker
                    v-model="date"
                    type="date"
                    no-title
                    scrollable
                  >
                    <v-spacer></v-spacer>
                    <v-btn text color="red darken-2" @click="$refs.menu.save([]); date=''; menu = false;">清空</v-btn>
                    <v-btn text color="red darken-2" @click="menu = false">取消</v-btn>
                    <v-btn text color="red darken-2" @click="$refs.menu.save(date); menu = false;">确定</v-btn>
                  </v-date-picker>
                </v-menu>
              </v-col>
            </v-row>

            <v-row>
              <v-col sm="4">
                <v-text-field
                  v-model="conditions.patient_name"
                  filled
                  label="病人姓名"
                  color="red darken-2"
                ></v-text-field>
              </v-col>
              <v-col sm="4">
                <v-text-field
                  filled
                  v-model="conditions.onset_place"
                  label="发病地点"
                  color="red darken-2"
                ></v-text-field>
              </v-col>
              <v-col sm="4">
                <v-btn
                  outlined
                  x-large
                  block
                  color="red darken-2"
                  v-on:click="patients.data = []; fetchData();"
                >查询</v-btn>
              </v-col>
            </v-row>

          </v-expansion-panel-content>
        </v-expansion-panel>

      </v-expansion-panels>

        <v-card>
          <v-card-title>
            病患数据
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              prepend-icon="mdi-magnify"
              label="快速过滤姓名或地点"
              single-line
              color="red darken-2"
              calculate-widths
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            dense disable-sort
            calculate-widths
            color="red darken-2"
            :options.sync="options"
            :loading="loading"
            :headers="patients.headers"
            :items="patients.data"
            :server-items-length="patients.total_length"
            :items-per-page="100"
            :sort-desc="true"
            item-key="patient_id"
            loading-text="正在加载数据，请稍候"
            no-data-text="无匹配数据"
            class="cardTable"
          >
            <template v-slot:item.detail="{ item }">
              <patient-detail
                :patient_id="item.patient_id"
              ></patient-detail>
            </template>

          </v-data-table>

        </v-card>

      <v-row>
        <v-col sm="10"></v-col>
        <v-col sm="2">
          <v-text-field v-model="options.page" type="number"
                        label="跳转页面"></v-text-field>
        </v-col>
      </v-row>

    </div>
</template>

<script lang="js">

  import PageHeader from '../components/PageHeader'
  import TextCard from '../components/TextCard'
  import axios from 'axios'
  import Config from '../components/global/Config'
  import { EventBus } from '../components/global/EventBus'
  import PatientDetail from '../components/patient/PatientDetail'
  export default {
    name: 'query',
    components: {
      PatientDetail,
      PageHeader,
      TextCard
    },

    data() {
      return{
        options: {},
        loading: true,
        search: "",
        date: "",
        menu: false,
        timer: -1,
        rawdata: {},
        conditions: {
          gender: "",
          patient_name: "",
          onset_place: "",
          onset_date: "",
          status: ""
        },
        expanded_patient_id: -1,

        patients: {
          total_length: 0,
          headers: [
            { text: '#', value: 'patient_id' },
            {
              text: '姓名',
              align: 'start',
              sortable: false,
              value: 'patient_name',
            },
            { text: '性别', value: 'gender' },
            { text: '生日', value: 'patient_birthday' },
            { text: '发病地点', value: 'onset_place' },
            { text: '发病日期', value: 'onset_date' },
            { text: '确诊日期', value: 'confirm_date' },
            { text: '确诊医院', value: 'hospital_name' },
            { text: '负责医生', value: 'doctor_name' },
            { text: '患者状态', value: 'status' },
            { text: '详细信息', align: 'center', sortable: false, value: 'detail'},
          ],
          data: [
            {}
          ]
        }
      }
    },

    methods: {

      dataFilter: (datainput) => datainput.map(one => {
          one.confirm_date = one.confirm_date.substring(0, 10);
          one.onset_date = one.onset_date.substring(0, 10);
          one.patient_birthday = one.patient_birthday.substring(0, 10);
          one.onset_place = one.onset_place.length > 6 ? one.onset_place.substring(0,5) + "..." : one.onset_place;
          one.hospital_name = one.hospital_name.length > 6 ? one.hospital_name.substring(0, 5) + "..." : one.hospital_name;
          one.gender = one.patient_gender == 'F' ? "女" : "男";
          return one;
        }),

      fetchData() {
        this.loading = true;
        this.patients.data = [];
        const { sortBy, sortDesc, page, itemsPerPage } = this.options
        let queryParams = {
          page: page,
          size: itemsPerPage == -1 ? 100 : itemsPerPage,
          patient_name: this.search,
          onset_place: this.search
        };
        if(!["不限", ""].includes(this.conditions.status)) {
          queryParams.status = this.conditions.status;
        }
        if(!["不限", ""].includes(this.conditions.gender)){
          queryParams.gender = this.conditions.gender == "男"  ? "M" : "F";
        }
        queryParams.patient_name = this.conditions.patient_name || this.search;
        queryParams.onset_place = this.conditions.onset_place;
        queryParams.onset_date = this.conditions.onset_date;
        axios.post(Config.apiurl + '/patient/getPatientInfo', null, {params: queryParams})
          .then(response => {
            if(response.data.success == true){
              console.log(response.data.data);
              this.rawdata = response.data.data;
              this.patients.total_length = Number(response.data.totalCount);
              this.patients.data = this.dataFilter(this.rawdata)
            }
            else{
              throw new Error(response.data.message)
            }
          })
          .catch(error => {
            alert('无法连接到服务器，刷新重试。\n' + error.message);
          })
        .finally(() => this.loading = false);
      },

      displayDetail(patientID){
        console.log(patientID)
      }
    },
    watch: {
      options: {
        handler () {
          this.fetchData();
        }
      },

      search: {
        handler() {
          this.loading = true;
          this.conditions.patient_name = "";
          clearTimeout(this.timer);
          this.timer = setTimeout(() => {
            this.fetchData();
          }, 300);
          // this.fetchData();
        }
      },
      expanded_patient_id: {
        handler() {

        }
      }
    },
    mounted() {

    }
  }

</script>

<style>

  .query-selector {
    margin-bottom:  20px;
  }

  /* Animation */

  .fade-enter-active, .fade-leave-active {
    transition: opacity .15s;
  }
  .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
  }

  .annotation {
    font-size: 80%;
    color: #7F828B;
  }

</style>
