<template>
    <div>
      <page-header
        icon="mdi-account-search"
        title="病患查询"
        date=""
      />

      <v-expansion-panels class="query-selector">
        <v-expansion-panel>
          <v-expansion-panel-header>条件筛选</v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-list-item
              v-for="color in ['primary', 'secondary', 'info', 'success', 'warning', 'error']"
              :key="color"
              v-ripple="{ class: `${color}--text` }"
            >
              <v-list-item-title>Item with "{{ color }}" class</v-list-item-title>
            </v-list-item>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
          </v-expansion-panel-content>
        </v-expansion-panel>

      </v-expansion-panels>

        <v-card>
          <v-card-title>
            病患数据
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              label="快速过滤"
              single-line
              calculate-widths
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            dense
            multi-sort
            calculate-widths
            :search="search"
            :loading="loading"
            :headers="patients.headers"
            :items="patients.data"
            :items-per-page="100"
            :sort-by="confirm_date"
            :sort-desc="true"
            loading-text="正在加载数据，请稍候"
            class="cardTable"
          >
            <template v-slot:item.detail="{ item }">

              <v-icon
                medium
                color="red"
                @click="displayDetail(item)"
              >mdi-arrow-expand</v-icon>
              展开
            </template>
<!--            <template v-slot:item="{ item }">-->
<!--              <v-icon-->
<!--                small-->
<!--                @click="displayDetail(item.patient_id)"-->
<!--              >mdi-arrow-expand</v-icon>-->
<!--            </template>-->
          </v-data-table>
        </v-card>



    </div>
</template>

<script lang="js">

  import PageHeader from '../components/PageHeader'
  import TextCard from '../components/TextCard'
  import axios from '../.nuxt/axios'
  import Config from '../components/global/Config'

  export default {
    name: 'query',
    components: {
      PageHeader,
      TextCard
    },

    data() {
      return{
        loading: true,
        search: "",
        rawdata: {},
        patients: {
          headers: [
            { text: '#', value: 'patient_id' },
            {
              text: '姓名',
              align: 'start',
              sortable: false,
              value: 'name',
            },
            { text: '性别', value: 'gender' },
            { text: '生日', value: 'birthday' },
            { text: '发病地点', value: 'onset_place' },
            { text: '发病日期', value: 'onset_date' },
            { text: '确诊日期', value: 'confirm_date' },
            { text: '确诊医院', value: 'hospital_name' },
            { text: '负责医生', value: 'doctor_name' },
            { text: '患者状态', value: 'status' },
            {
              text: '详细信息',
              align: 'center',
              sortable: false,
              value: 'detail',
            },
          ],
          data: [
            {
              patient_id: '1',
              name: '赵慧',
              gender: '女',
              birthday: '1990-01-01',
              onset_place: '上海市',
              onset_date: '2020-01-01',
              confirm_date: '2020-01-02',
              hospital_name: '上海市第一医院',
              doctor_name: '杨淑珍',
              status: '已治愈'
            }
          ]
        }
      }
    },

    methods: {
      fetchData: (name, gender, onset_place) => {
        // axios().post(url)
      },

      displayDetail(patientID){
        console.log(patientID)
      }
    },

    mounted() {
      let test = {
        patient_id: '1',
        name: '张三',
        gender: '女',
        birthday: '1990-01-01',
        onset_place: '上海市',
        onset_date: '2020-01-01',
        confirm_date: '2020-01-02',
        hospital_name: '上海市第一医院',
        doctor_name: '李四',
        status: '已治愈'
      }
      this.loading = false;
      console.log(Config.apiurl);
    }
  }
</script>

<style scoped>

  .query-selector {
    margin-bottom:  20px;
  }
</style>
