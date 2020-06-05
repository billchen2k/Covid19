<template>
    <div>
      <v-btn v-on:click="uid = uid + 1">
        查询 {{uid}}
      </v-btn>

    </div>
</template>

<script lang="js">
  import UserController from '../components/controller/UserController'

  var uid = UserController.uid

  export default {
    name: 'query',


    data() {
      return{
        uid: uid
      }
    },

    methods: {
      update () {
        UserController.uid = UserController.uid + 1
      }
    }
  }
</script>

<style scoped>

</style>
