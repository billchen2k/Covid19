<template>
  <div class="Flow">
    <div class="Flow-Heading">
      <covid-icon aria-hidden="true" />
      <page-header class="Flow-Heading-Title">
        {{ $t('新型コロナウイルス感染症が心配なときに') }}
      </page-header>
      <printer-button :wrapper-class="'Flow-PullRight'" to="/print/flow" />
    </div>
    <div>
      <div class="Flow-Card-Button-Wrapper">
        <a
          href="https://github.com/billchen2k/Covid19"
          target="_blank"
          rel="noopener noreferrer"
          class="Flow-Card-Button"
        >
          <span class="Flow-Card-Button-Description">
            {{ $t('欢迎访问我们的数据库实践项目') }}<br />
            {{ $t('Covid-19 疫情分析与管理系统') }}
          </span>
          {{ $t('想要获得项目的源代码') }}
          <v-icon class="Flow-Card-Button-ExternalLinkIcon" size="20">
            mdi-open-in-new
          </v-icon>
        </a>
      </div>
      <div class="only-pc" aria-hidden="true">
        <flow-pc />
      </div>
      <div class="only-sp">
        <flow-sp />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
  import Vue from 'vue'
  import { TranslateResult } from 'vue-i18n'
  import CovidIcon from '@/static/covid.svg'
  import PrinterButton from '@/components/PrinterButton.vue'
  import FlowPc from '@/components/flow/FlowPc.vue'
  import FlowSp from '@/components/flow/FlowSp.vue'
  import PageHeader from '@/components/PageHeader.vue'
  import TimeBarChart from '@/components/TimeBarChart.vue'
  export default Vue.extend({
    components: {
      CovidIcon,
      PageHeader,
      PrinterButton,
      FlowPc,
      FlowSp
    },
    head() {
      return {
        title: 'Covid-19 疫情分析与管理系统 | 数据库 2020 期末项目'
      }
    }
  })
</script>

<style lang="scss">
  .Flow {
    margin-bottom: 20px;

    &-Heading {
      display: flex;
      align-items: center;
      margin-bottom: 20px;

      > svg {
        width: 30px;
        height: 30px;

        > path:not(:first-of-type) {
          fill: $gray-2;
        }
      }

      &-Title {
        margin-left: 8px;
      }
    }

    &-Card-Button {
      @include button-text('md');
      @include font-size(20);

      width: 100%;
      max-width: 600px;
      font-weight: bold;
      display: inline-block;
      text-decoration: none;
      color: $green-1 !important;

      &-Wrapper {
        text-align: center;
        margin-bottom: 20px;
      }

      &:hover {
        color: $white !important;
      }

      &-ExternalLinkIcon {
        margin-left: 2px;
        color: $green-1 !important;
      }

      &-Description {
        margin-bottom: 12px;
        display: block;
        font-weight: normal;
        text-align: left;
      }
    }

    &-PullRight {
      margin: 0 0 0 auto;
    }
  }

  @include largerThan($medium) {
    .only-sp {
      @include visually-hidden;
    }

    .only-pc {
      display: block;
    }
  }

  @include lessThan($medium) {
    .only-pc {
      display: none;
    }
  }
  .DataBlock {
    margin: 20px -8px;

    .DataCard {
      @include largerThan($medium) {
        padding: 10px;
      }

      @include lessThan($small) {
        padding: 4px 8px;
      }
    }
  }
</style>
