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

    <v-card>
      <v-toolbar dark color="red">
        <v-btn icon dark @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>
          <span v-if="loading" class="center">正在加载...  </span>
          <v-progress-circular indeterminate v-if="loading"></v-progress-circular>
          <span v-else>新增诊断</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>


      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="red darken-1" outlined text @click="dialog = false">取消</v-btn>
        <v-btn color="red darken-1" outlined text @click="">保存</v-btn>
      </v-card-actions>
    </v-card>

<!--    <v-snackbar-->
<!--      v-model="snackbar"-->
<!--      :timeout="3000"-->
<!--    >-->
<!--      {{ snackbar_text }}-->
<!--      <template v-slot:action="{ attrs }">-->
<!--        <v-btn-->
<!--          color="red"-->
<!--          text-->
<!--          v-bind="attrs"-->
<!--          @click="snackbar = false"-->
<!--        >-->
<!--          Close-->
<!--        </v-btn>-->
<!--      </template>-->
<!--    </v-snackbar>-->
  </v-dialog>


</template>

<script>
  export default {
    name: 'DiagnosisCreator',

    props: {
      patient_id: Number,
      doctor_id: Number
    },

    data(){
      return {
        dialog: false,
        loading: false
      }
    }
  }
</script>

<style scoped>

</style>
