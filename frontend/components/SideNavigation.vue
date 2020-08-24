<template>
  <div class="SideNavigation">
    <div class="SideNavigation-HeadingContainer sp-flex">
      <v-icon
        class="SideNavigation-HeadingIcon pc-none"
        @click="openNavi"
      >
        mdi-menu
      </v-icon>
      <nuxt-link :to="localePath('/')" class="SideNavigation-HeadingLink">
        <div class="SideNavigation-Logo">
          <img src="/logo.svg"/>
        </div>
        <h1 class="SideNavigation-Heading">
          Covid-19 疫情分析与管理系统<br />
          Covid-19 Analysis & Manage System
        </h1>
      </nuxt-link>
      <div v-if="username!=''" class="SideNavigation-Copyright">您已登录为 {{ username }}。</div>
    </div>
    <v-divider class="SideNavigation-HeadingDivider" />
    <div class="sp-none" :class="{ open: isNaviOpen }">
      <v-icon
        class="SideNavigation-ListContainerIcon pc-none"
        @click="closeNavi"
      >
        mdi-close
      </v-icon>

<!--      <input v-model="uid">-->
<!--      <p>UID: {{uid}}</p>-->

      <v-list :flat="true">

        <v-container
          v-for="(item, i) in items"
          :key="i"
          class="SideNavigation-ListItemContainer"
          @click="closeNavi"
        >
          <ListItem v-if="(item.need_login && uid!='') || !item.need_login " :link="item.link" :icon="item.icon" :title="item.title" />
          <v-divider v-show="item.divider" class="SideNavigation-Divider" />
        </v-container>
      </v-list>

<!--      <div class="SideNavigation-LanguageMenu">-->
<!--        <LanguageSelector />-->
<!--      </div>-->

      <div class="SideNavigation-Footer">
        <div class="SideNavigation-SocialLinkContainer">
          <a href="https://github.com/billchen2k/Covid19">
            <img src="/github.png" alt="GitHub" />
          </a>
        </div>
        <small class="SideNavigation-Copyright">
          East China Normal University, 2020
        </small>
      </div>
    </div>
  </div>
</template>

<script>
import ListItem from '@/components/ListItem'
import Config from '~/components/global/Config'
import { EventBus} from '~/components/global/EventBus.js'
// import LanguageSelector from '@/components/LanguageSelector'
import axios from 'axios'

export default {
  components: {
    ListItem,
  },
  data() {
    return {
      uid: '',
      username: ''
    }
  },
  props: {
    isNaviOpen: {
      type: Boolean,
      required: true,
    },

  },
  computed: {
    items() {
      return [
        {
          icon: 'mdi-home',
          title: '首页',
          link: this.localePath('/')
        },
        {
          icon: 'mdi-chart-timeline-variant',
          title: '疫情概览',
          link: this.localePath('/statistics')
        },
        {
          icon: 'mdi-account-search',
          title: '病患查询',
          link: this.localePath('/query'),
          divider: true
        },
        // {
        //   icon: 'mdi-chart-bubble',
        //   title: '分析报告',
        //   link: this.localePath('/report'),
        //   divider: true
        // },
        {
          icon: 'mdi-pencil-circle-outline',
          title: '病患登记',
          link: this.localePath('/checkin'),
          need_login: true,
        },
        {
          icon: 'mdi-bed-empty',
          title: '病患管理',
          link: this.localePath('/manage'),
          need_login: true
        },
        {
          icon: 'mdi-doctor',
          title: this.uid == '' ? '医生登录' : '登出',
          link: this.uid == '' ? this.localePath('/login') : this.localePath('/logout'),
          divider: true
        },
        {
          icon: 'mdi-information-outline',
          title: '关于',
          link: this.localePath('/about'),
        },
      ]
    },
    isClass() {
      return item => (item.title.charAt(0) === '【' ? 'kerningLeft' : '')
    }
  },
  methods: {
    openNavi() {
      this.$emit('openNavi')
    },
    closeNavi() {
      this.$emit('closeNavi')
    },
    logIn() {
      console.log("Logging..." + username + password)
    },
  },
  mounted() {

    axios.defaults.withCredentials = true;
    this.$axios.defaults.withCredentials = true;

    if(localStorage.uid){
      this.uid = localStorage.uid
      this.username = localStorage.username
    }
    // EventBus.$on('login', (username, password) => {
    //   console.log("Logging ON..." + username + password)
    // })
    EventBus.$on('setUid', (uid) => {
      this.uid = uid
      localStorage.uid = uid
    })
    EventBus.$on('setUsername', (username) => {
      this.username = username
      localStorage.username = username
    })
  },
  watch: {
    uid(newUid) {
      localStorage.uid = newUid
    }
  }
}
</script>

<style lang="scss" scoped>
.SideNavigation {
  position: relative;
  height: 100%;
  background: $white;
  box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.85);
  &-HeadingContainer {
    padding: 1.25em 0 1.25em 1.25em;
    align-items: center;
    @include lessThan($small) {
      padding: 7px 0 7px 20px;
    }
  }
  &-HeadingIcon {
    margin-right: 16px;
  }
  &-HeadingLink {
    @include lessThan($small) {
      display: flex;
      align-items: center;
    }
    text-decoration: none;
  }
  &-ListContainerIcon {
    margin: 24px 16px 0;
  }
  &-ListItemContainer {
    padding: 2px 20px;
  }
  &-Logo {
    margin: 20px 16px 0 0;
    width: 110px;
    @include lessThan($small) {
      margin-top: 0;
    }
  }
  &-Heading {
    margin-top: 8px;
    font-size: 13px;
    color: #898989;
    padding: 0.5em 0;
    text-decoration: none;
    @include lessThan($small) {
      margin-top: 0;
    }
  }
  &-HeadingDivider {
    margin: 0px 20px 4px;
    @include lessThan($small) {
      display: none;
    }
  }
  &-Divider {
    margin: 12px 0;
  }
  &-LanguageMenu {
    padding: 0 20px;
    background: #fff;
  }
  &-Footer {
    padding: 20px;
    background-color: $white;
  }
  &-SocialLinkContainer {
    display: flex;
    & img {
      width: 30px;
      &:first-of-type {
        margin-right: 10px;
      }
    }
  }
  &-Copyright {
    display: block;
    margin-top: 10px;
    font-size: 12px;
    line-height: 1.2;
    color: $gray-2;
    font-weight: bold;
  }
}
.open {
  @include lessThan($small) {
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    display: block !important;
    width: 100%;
    z-index: z-index-of(opened-side-navigation);
    background-color: $white;
    height: 100%;
    overflow-y: scroll;
  }
}
@include largerThan($small) {
  .pc-none {
    display: none;
  }
}
@include lessThan($small) {
  .sp-flex {
    display: flex;
  }
  .sp-none {
    display: none;
  }
}
</style>
