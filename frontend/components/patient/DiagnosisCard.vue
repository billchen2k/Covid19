<template>
  <div>
    <temperature-chart :diagnosis-items="diagnosisItems"></temperature-chart>

    <v-card>
      <v-card-title>诊断记录
        <v-spacer></v-spacer>
        <diagnosis-creator v-if="editable" :patient-model="patientModel"
                           v-on:change="fetchDiagnosis"
        ></diagnosis-creator>
      </v-card-title>
      <v-data-table
        calculate-widths
        :loading="loading"
        :headers="diagnosisHeaders"
        :items="diagnosisItems"
        :items-per-page="20"
        :sort-desc="true"
        multi-sort
        item-key="diagnosis_id"
        loading-text="正在加载数据，请稍候"
        no-data-text="无匹配数据"
      >
        <template v-slot:item.doctor_name="{ item }">
          <v-fade-transition mode="out-in">
            <div :key="item.doctor_name">
              {{item.doctor_name}}
            </div>
          </v-fade-transition>

        </template>

        <template v-slot:item.nucleic_acid="{ item }">
          <v-chip :color="item.nucleic_acid == '阳性' ? 'error' : 'green'" text-color="white" >
            {{item.nucleic_acid}}
          </v-chip>
        </template>

        <template v-slot:item.curd="{ item }" v-if="editable">
          <v-dialog v-model="confirm_dialog[item.diagnosis_id]" persistent max-width="500">
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon color="grey" @click="" v-bind="attrs" v-on="on"
                     :disabled="loading"
              ><v-icon>mdi-trash-can</v-icon></v-btn>
            </template>
            <v-card>
              <v-card-title>确定删除记录于 {{item.time}} 的诊断吗？</v-card-title>
              <v-card-text><p>该诊断记录由{{item.doctor_name}}医生登记。该操作无法撤销。</p></v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="red darken-1" text @click="confirm_dialog[item.diagnosis_id] = false">取消</v-btn>
                <v-btn color="red darken-1" text @click="deleteDiagnosis(item.diagnosis_id)">确定</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </template>

      </v-data-table>
    </v-card>
  </div>

</template>

<script>
  import axios from 'axios'
  import Config from '../global/Config'
  import TemperatureChart from './TemperatureChart'
  import DiagnosisCreator from '../picker/DiagnosisCreator'

  export default {
    name: 'DiagnosisCard',
    components: { DiagnosisCreator, TemperatureChart },
    props: {
      editable: Boolean,
      patient_id: String,
      patientModel: Object
    },

    data() {
      return {
        loading: true,
        confirm_dialog: {},
        diagnosisHeaders: [
          { text: '#', value: 'diagnosis_id' },
          { text: '诊断医生', value: 'doctor_name', },
          { text: '诊断时间', value: 'time' },
          { text: '体温', value: 'temperature' },
          { text: '核酸检测', value: 'nucleic_acid' },
          { text: '症状', value: 'symptom' }
        ],
        diagnosisItems: [],
        temperatureLineChartData: [],
      }
    },

    methods: {
      fetchDiagnosis() {
        this.loading = true;
        axios.post(Config.apiurl + '/diagnosis/getDiagnosisInfo', null, {params: {
            page: 1,
            size: 250,
            patient_id: this.patient_id
          }})
          .then(response => {
            let diagnosisData = response.data.data;
            diagnosisData.map(one => one.doctor_name="加载中...");
            this.diagnosisItems = diagnosisData;
            this.diagnosisItems.map(async function(one) {
              one.nucleic_acid = one.nucleic_acid == 1 ? "阳性" : "阴性";
              let res = await axios.post(Config.apiurl + '/doctor/getDoctorInfoByID', null, {params: {
                  doctor_id: one.doctor_id
                }});
              one.doctor_name = res.data.data.doctor_name;
              return one;
            })
          })
          .finally(() => {
            this.loading = false;
          })
      },

      deleteDiagnosis(id) {
        this.loading = true;
        this.confirm_dialog[id] = false;
        axios.post(Config.apiurl + '/diagnosis/deleteDiagnosisByID', null, {
          params: {
            diagnosis_id: id
          }
        })
        .then(response => {
          this.$emit('snack', '诊断删除成功');
          for (let i = 0; i < this.diagnosisItems.length; i++){
            if (this.diagnosisItems[i].diagnosis_id == id){
              this.$delete(this.diagnosisItems, i);
            }
          }
        })
        .catch(error => {
          alert('诊断删除失败：无法连接到服务器，刷新重试。\n' + error.message);
        })
        .finally(() => {
          this.loading = false;
        });
      }
    },

    mounted() {
      this.fetchDiagnosis();
      if(this.editable) {
        this.diagnosisHeaders.push({ text: '操作', value: 'curd'})
      }
    }
  }
</script>

<style scoped>

</style>
