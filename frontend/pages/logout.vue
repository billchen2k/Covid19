<template>
    <div>
      <page-header
        icon="mdi-doctor"
        title="正在登出..."
        date=""
      />
      <TextCard title="">
        <v-col cols="12" align="center">
          <scale-loader color="#C70000" />
          请稍候
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

  export default {
    name: 'logout',

    components : {
      PageHeader,
      TextCard,
      ScaleLoader
    },
    data() {
      return{
        username: '',
        password: '',
        showpwd: false
      }
    },
    mounted() {
      if(localStorage.uid){
        axios.get(Config.apiurl + "/user/logOut").then(response => {
          console.log(response.data)
        })
        setTimeout(() => {
          // localStorage.uid = ''
          console.log("LOGOUT")
          EventBus.$emit('setUid', '')
          EventBus.$emit('setUsername', '')

          this.$router.push({
            path: this.localePath('/')
          })
        }, 1000)
      }
    }
  }
</script>

<style scoped>

</style>
