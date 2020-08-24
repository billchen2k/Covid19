<template>

  <div>
    <page-header
      icon="mdi-doctor"
      title="医生登录"
      date=""
    />
    <TextCard title="">

      <v-col cols="12" align="center" >
        <v-col cols="8">

          <v-text-field
            v-model="username"
            label="用户名"
            prepend-icon="mdi-account"
            color="red darken-2"


          ></v-text-field>


          <v-text-field
            label="密码"
            v-model="password"
            prepend-icon="mdi-lock"
            :append-icon="showpwd ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showpwd ? 'text' : 'password'"
            @click:append="showpwd = !showpwd"
            color="red darken-2"
          ></v-text-field>

          <v-col cols="12" align="right">

              <v-progress-circular
                v-if="loading"
                indeterminate
              ></v-progress-circular>

<!--            <v-col cols="4" v-if="loading">-->
<!--              <scale-loader color="#C70000" />-->
<!--            </v-col>-->
            <span v-if="errormsg != ''" style="color:#C70000; margin: 2px">{{ errormsg }}</span>
            <v-btn outlined
                   color="#C70000"
                   width="200"

                   v-on:click="logIn()">

              登录
            </v-btn>
          </v-col>


        </v-col>
      </v-col>


    </TextCard>

  </div>
</template>

<script lang="js">

  import ScaleLoader from 'vue-spinner/src/ScaleLoader.vue'
  import PageHeader from '../components/PageHeader'
  import TextCard from '../components/TextCard'
  import { EventBus} from '../components/global/EventBus.js'
  import Config from '../components/global/Config'
  import axios from 'axios'
  import Cookies from 'js-cookie'

  export default {
    name: 'query',
    components : {
      PageHeader,
      TextCard,
      ScaleLoader
    },

    data() {
      return{
        username: '',
        password: '',
        showpwd: false,
        errormsg: "",
        loading: false
      }
    },

    methods: {
      logIn(){
        this.loading = true
        this.errormsg = ""
        var url = Config.apiurl + "/user/logIn"
        axios.defaults.withCredentials = true;
        this.$axios.defaults.withCredentials = true;
        axios.post(url, null, {params:{
            identifier: this.username,
            password: this.password
          }}).then(response => {
          console.log(response.data)
          if(response.data.success){
            EventBus.$emit('setUid', response.data.data.user_id)
            EventBus.$emit('setUsername', response.data.data.username)
            this.$router.push({
              path: this.localePath('/')
            })
          }
          else{
            this.errormsg = response.data.message;
          }
          this.loading = false;
        })
          .catch(error => {
            console.error("Fail to reach server.")
            this.errormsg = "无法连接到服务器。"
            this.loading = false
          })
      }
    }
  }

</script>

<style scoped>

</style>
