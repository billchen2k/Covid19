<template>
  <v-menu
    ref="menu"
    v-model="menu"
    :close-on-content-click="false"
    :return-value.sync="selectedDate"
    transition="scale-transition"
    offset-y
    max-width="290px"
    min-width="290px"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-text-field
        :dense="dense ? true : false"
        :outlined="outlined"
        :prepend-icon="prependIcon"
        :hide-details="dense ? true : false"
        v-model="date"
        :label="label"
        readonly
        :clearable="dense ? false : true"
        v-bind="attrs"
        v-on="on"
        color="red darken-2" :class="dense ? 'py-2' : ''"
      ></v-text-field>
    </template>
    <v-date-picker
      v-model="date"
      :type="type"
      scrollable
      @input="menu = false"
      locale="zh-CN"
      color="red darken-2"
    >
<!--      <v-spacer></v-spacer>-->
<!--      <v-btn text color="red darken-2" @click="$refs.menu.save([]); date=''; menu = false;">清空</v-btn>-->
<!--      <v-btn text color="red darken-2" @click="menu = false">取消</v-btn>-->
<!--      <v-btn text color="red darken-2" @click="$refs.menu.save(date); menu = false;">确定</v-btn>-->
    </v-date-picker>
  </v-menu>
</template>

<script>
  export default {
    name: 'datePicker',

    props: {
      label: {
        type: String,
        required: true
      },
      prependIcon: String,
      outlined: String,
      dense: {
        type: Boolean,
        required: false,
        default: false
      },
      type: {
        type: String,
        default: "date"
      },
      value: {
        type: String
      }
    },

    data() {
      return {
        menu: false,
        date: "",
        selectedDate: ""
      }
    },

    methods: {
      returnDate(){
        this.$emit("change", this.selectedDate);
      }
    },

    watch: {
      selectedDate: {
        handler() {
          this.returnDate();
        }
      }
    },

    mounted() {
      this.date = this.value;
    }

  }
</script>

<style scoped>

</style>
