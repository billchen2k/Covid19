<template>
  <v-dialog v-model="dialog" max-width="1300" transition="scale-transition">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        color="red darken-2"
        small
        text
        outlined
        v-bind="attrs"
        v-on="on"
      >
        <!-- <v-icon color="grey">mdi-chevron-right-box</v-icon> -->
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
          <span v-else>选择药物</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>

      <v-card-title>
        <v-row>
<!--          <v-col sm="2">选择药物</v-col>-->
          <v-col sm="5">
            <v-list nav dense>
              <v-list-item-group mandatory color="red">
                <v-list-item>
                  <v-list-item-icon><v-icon>mdi-pill</v-icon></v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title> {{ selectedMedicine.medicine_name || "未选择"}}</v-list-item-title>
                    <v-list-item-subtitle> {{selectedMedicine.manufacturer || "未选择"}} </v-list-item-subtitle>
                  </v-list-item-content>

                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-col>
          <v-col sm="7">
            <v-text-field prepend-icon="mdi-magnify" v-model="searchText"
                          placeholder="筛选药物"></v-text-field>
          </v-col>
        </v-row>

      </v-card-title>

      <v-card outlined class="scroll mx-3">

        <v-data-table
          dense
          :loading="loading"
          :items="medicineList"
          :headers="medicineHeaders"
          :items-per-page="200"
          :sort-desc="true"
          :search="searchText"
          item-key="medicine_id"
          loading-text="正在加载数据，请稍候"
          no-data-text="无匹配数据"
        >

          <template v-slot:item.curd="{ item }">
            <v-expand-x-transition>
              <v-btn v-if="selectedMedicine.medicine_id != item.medicine_id"
                     outlined small color="red darken-2"
                     @click="selectedMedicine = item"
              >
                <v-icon small class="mr-2">mdi-check-circle</v-icon>
                选定
              </v-btn>
              <v-btn v-else depressed dark small color="red darken-2">
                <v-icon small class="mr-2">mdi-check-underline-circle</v-icon>
                已选
              </v-btn>
            </v-expand-x-transition>

          </template>
        </v-data-table>

      </v-card>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="red darken-1" outlined text @click="dialog = false; selectedMedicine = {}">取消</v-btn>
        <v-btn color="red darken-1" outlined text :disabled="!selectedMedicine.medicine_id" @click="saveResult">保存</v-btn>
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
  import axios from 'axios';
  import Config from '../global/Config'

  export default {
    name: 'MedicinePicker',

    data() {
      return {
        dialog: false,
        snackbar_text: "",
        snackbar: false,
        loading: true,
        searchText: "",
        selectedMedicine: {},
        medicineList: [],
        medicineHeaders: [
          { text: "#", value: "medicine_id" },
          { text: "药品名称", value: "medicine_name" },
          { text: "制造商", value: "manufacturer" },
          { text: "简介", value: "introduction" },
          { text: "类型", value: "type" },
          { text: "操作", value: "curd", sortable: false }
        ]
      }
    },

    mounted() {
      this.fetchMedicine();
    },

    methods: {
      popSnack(msg) {
        this.snackbar_text = msg;
        this.snackbar = true;
      },

      fetchMedicine() {
        this.loading = true;
        axios.post(Config.apiurl +  '/medicine/getMedicineInfo', null, {params: {
            page: 1,
            size: 2500,
            name: this.searchText
          }})
        .then(response => {
          if (response.data.success) {
            this.medicineList = response.data.data;
          }
          else{
            throw new Error(response.data.message);
          }
        })
        .catch(error => {
          alert('获取药物失败：无法连接到服务器，刷新重试。\n' + error.message);
        })
        .finally(e => {
          this.loading = false;
        });

      },

      saveResult() {
        this.$emit('change', selectedMedicine);
        this.dialog = false;
      }
    },

    watch: {
      dialog: {
        handler() {
          if(this.dialog == true){
            this.medicineList = [];
            this.selectedMedicine = {};
            this.fetchMedicine();
          }
        }
      }
    }
  };
</script>

<style scoped>

  .scroll {
    overflow-y: scroll;
    height: 450px;
  }

</style>
