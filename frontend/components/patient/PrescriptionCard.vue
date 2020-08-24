<template>
  <v-card :loading="loading">
    <v-card-title>处方信息</v-card-title>
    <v-card-text>该病人当前的正在使用的处方如下。</v-card-text>
    <v-card-text>
      <v-list dense three-line>
        <template v-for="(item, index) in prescriptionItems">
          <v-list-item v-on:click="">
            <v-list-item-avatar>
              <span class="medicine-avatar rounded">{{item.medicine_name.substring(0,1)}}</span>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{item.medicine_name}}</v-list-item-title>
              <v-list-item-subtitle>{{item.usage + '用，' + item.dosage}}</v-list-item-subtitle>
              <v-list-item-subtitle>{{item.manufacturer}}</v-list-item-subtitle>
            </v-list-item-content>
            <v-list-item-action>

            </v-list-item-action>
          </v-list-item>
        </template>
      </v-list>
    </v-card-text>
  </v-card>

</template>

<script>
  import axios from 'axios'
  import Config from '../global/Config'

  export default {
    name: 'PrescriptionCard',

    data() {
      return {
        loading: true,
        prescriptionItems: [],
      }
    },

    props: {
      patient_id: String,
      editable: Boolean
    },

    mounted() {
      this.fetchPrescription();
    },

    methods: {
      fetchPrescription(){
        this.loadingPrescirption = true;
        axios.post(Config.apiurl + '/prescription/getPrescriptionInfoByPatientID', null, {
          params: {
            patient_id: this.patient_id
          }
        })
          .then(response => {
            this.prescriptionItems = response.data.data;
          })
          .catch(error => {
            alert('获取处方失败：无法连接到服务器，刷新重试。\n' + error.message);
          })
          .finally(() => {
            this.loading = false;
          });
      }
    }
  }
</script>

<style scoped>

  .medicine-avatar {
    font-size: 130%;
    font-weight: bold;
    color: white;
    padding: 6px 9px;
    border: #ff8383 10px solid;
    background-color: #ff8383;
    border-radius: 50%;
  }

</style>
