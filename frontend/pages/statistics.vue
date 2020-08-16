<template>
  <div class="MainPage">
    <page-header
      :icon="headerItem.icon"
      :title="headerItem.title"
      :date="headerItem.date"
    />

    <v-row class="BigDataBlock">

      <v-col cols="12" md="40" class="DataCard">
        <monitoring-consultation-desk-report-chart
          :title="$t('疫情较严重国家确诊人数')"
          title-id="monitoring-number-of-reports-to-covid19-consultation-desk"
          chart-id="monitoring-consultation-desk-report-chart"
          :chart-data="chartData"
          :date="date"
          :labels="labels"
          :data-labels="dataLabels"
          :unit="$t('人')"
          url="https://catalog.data.metro.tokyo.lg.jp/dataset/t000010d0000000070"
        />
      </v-col>
    </v-row>

    <v-row class="DataBlock">
      <v-col cols="12" md="6" class="DataCard">
        <svg-card
          :title="$t('国内目前疫情情况分布')"
          :title-id="'details-of-confirmed-cases'"
          :date="Data.inspections_summary.date"
        >
          <confirmed-cases-table
            :aria-label="$t('目前确诊案例情况分布')"
            v-bind="confirmedCases"
          />
        </svg-card>
      </v-col>
      <v-col cols="12" md="6" class="DataCard">
        <time-bar-chart
          :title="$t('湖北省武漢市确诊人数')"
          :title-id="'number-of-confirmed-cases'"
          :chart-id="'time-bar-chart-patients'"
          :chart-data="patientsGraph"
          :date="Data.patients.date"
          :unit="$t('人')"
          :url="
            'https://catalog.data.metro.tokyo.lg.jp/dataset/t000010d0000000068'
          "
        />
      </v-col>
      <v-col cols="12" md="6" class="DataCard">
        <data-table
          :title="$t('最新确诊患者信息')"
          :title-id="'attributes-of-confirmed-cases'"
          :chart-data="patientsTable"
          :chart-option="{}"
          :date="Data.patients.date"
          :info="sumInfoOfPatients"
          :url="
            'https://catalog.data.metro.tokyo.lg.jp/dataset/t000010d0000000068'
          "
        />
      </v-col>

      <v-col cols="12" md="6" class="DataCard">
        <metro-bar-chart
          :title="$t('都営地下鉄の利用者数の推移')"
          :title-id="'predicted-number-of-toei-subway-passengers'"
          :chart-id="'metro-bar-chart'"
          :chart-data="metroGraph"
          :chart-option="metroGraphOption"
          :date="metroGraph.date"
        />
      </v-col>
      <v-col cols="12" md="6" class="DataCard">
        <agency-bar-chart
          :title="$t('都庁来庁者数の推移')"
          :title-id="'agency'"
          :chart-id="'agency'"
          :url="''"
          :unit="'人'"
        />
      </v-col>
    </v-row>
  </div>
</template>


<script>
import PageHeader from '@/components/PageHeader.vue'
import TimeBarChart from '@/components/TimeBarChart.vue'
import MetroBarChart from '@/components/MetroBarChart.vue'
import AgencyBarChart from '@/components/AgencyBarChart.vue'
import TimeStackedBarChart from '@/components/TimeStackedBarChart.vue'
import WhatsNew from '@/components/WhatsNew.vue'
import StaticInfo from '@/components/StaticInfo.vue'
import Data from '@/data/data.json'
import MetroData from '@/data/metro.json'
import DataTable from '@/components/DataTable.vue'
import formatGraph from '@/utils/formatGraph'
import formatTable from '@/utils/formatTable'
import formatConfirmedCases from '@/utils/formatConfirmedCases'
import MonitoringConsultationDeskReportChart from '@/components/MonitoringConsultationDeskReportChart.vue'
// import News from '@/data/news.json'
import SvgCard from '@/components/SvgCard.vue'
import ConfirmedCasesTable from '@/components/ConfirmedCasesTable.vue'

export default {
  components: {
    PageHeader,
    TimeBarChart,
    MetroBarChart,
    AgencyBarChart,
    TimeStackedBarChart,
    WhatsNew,
    StaticInfo,
    DataTable,
    SvgCard,
    ConfirmedCasesTable,
    MonitoringConsultationDeskReportChart
  },
  data() {

    const [
      China,
      England,
      labels,
      Japan,
      Brazil,
      Russia,
      Italy
    ] = Data.confirmedglobal.data.reduce(
      (res, data) => {
        res[0].push(data['China'])
        res[1].push(data['England'])
        res[2].push(data['time'])
        res[3].push(data['Japan'])
        res[4].push(data['Brazil'])
        res[5].push(data['Russia'])
        res[6].push(data['Italy'])
        return res
      },
      [[], [], [], [], [], [], []]
    )
    const chartData = [China, England, Japan, Brazil, Russia, Italy]
    const dataLabels = [this.$t('中国'), this.$t('英国'), this.$t('日本'), this.$t('巴西'), this.$t('俄罗斯'), this.$t('意大利')]
    const date = Data.querents.date

    // 感染者数グラフ
    const patientsGraph = formatGraph(Data.patients_summary.data)
    // 陽性患者の属性
    const patientsTable = formatTable(Data.patients.data)
    // 陽性患者の属性 ヘッダー翻訳
    for (const header of patientsTable.headers) {
      header.text =
        header.value === '退院' ? this.$t('离院※') : this.$t(header.value)
    }
    // 陽性患者の属性 中身の翻訳
    for (const row of patientsTable.datasets) {
      row['居住地'] = this.$t(row['居住地'])
      row['性別'] = this.$t(row['性別'])

      if (row['年代'] === '10歳未満') {
        row['年代'] = this.$t('未満10岁')
      } else {
        const age = row['年代'].substring(0, 4)
        //row['年代'] = this.$t('{age}代', { age })
      }
    }
    // 退院者グラフ
    const dischargesGraph = formatGraph(Data.discharges_summary.data)

    // 相談件数
    const contactsGraph = formatGraph(Data.contacts.data)
    // 帰国者・接触者電話相談センター相談件数
    const querentsGraph = formatGraph(Data.querents.data)
    // 都営地下鉄の利用者数の推移
    const metroGraph = MetroData
    // metroGraph ツールチップ title文字列
    // this.$t を使うため metroGraphOption の外側へ
    const metroGraphTooltipTitle = (tooltipItems, _) => {
      const label = tooltipItems[0].label
      return this.$t('期間: {duration}', {
        // duration = label = '2月10日~14日' | '2月17日~21日' | '2月25日~28日'
        duration: this.$t(label)
      })
    }
    // metroGraph ツールチップ label文字列
    // this.$t を使うため metroGraphOption の外側へ
    const metroGraphTooltipLabel = (tooltipItem, data) => {
      const currentData = data.datasets[tooltipItem.datasetIndex]
      const percentage = `${currentData.data[tooltipItem.index]}%`

      return this.$t('{duration}の利用者数との相対値: {percentage}', {
        // duration = metroGraph.base_period = '1月20日~1月24日'
        duration: this.$t(metroGraph.base_period),
        percentage
      })
    }
    // 検査実施日別状況
    const inspectionsGraph = [
      Data.inspections_summary.data['都内'],
      Data.inspections_summary.data['その他']
    ]
    const inspectionsItems = [
      this.$t('都内発生（疑い例・接触者調査）'),
      this.$t('その他（チャーター便・クルーズ船）')
    ]
    const inspectionsLabels = Data.inspections_summary.labels
    // 死亡者数
    // #MEMO: 今後使う可能性あるので一時コメントアウト
    // const fatalitiesTable = formatTable(
    //   Data.patients.data.filter(patient => patient['備考'] === '死亡')
    // )
    // 検査陽性者の状況
    const confirmedCases = formatConfirmedCases(Data.main_summary)

    const sumInfoOfPatients = {
      lText: patientsGraph[
        patientsGraph.length - 1
      ].cumulative.toLocaleString(),
      sText: this.$t('累计至 6/20', {
        date: patientsGraph[patientsGraph.length - 1].label
      }),
      unit: this.$t('人')
    }

    const data = {

      chartData,
      date,
      labels,
      dataLabels,
      Data,
      patientsTable,
      patientsGraph,
      dischargesGraph,
      contactsGraph,
      querentsGraph,
      metroGraph,
      inspectionsGraph,
      inspectionsItems,
      inspectionsLabels,
      confirmedCases,
      sumInfoOfPatients,
      headerItem: {
        icon: 'mdi-chart-timeline-variant',
        title: 'Covid-19 疫情分析与管理系统',
        date: Data.lastUpdate
      },
      metroGraphOption: {
        responsive: true,
        legend: {
          display: true
        },
        scales: {
          xAxes: [
            {
              position: 'bottom',
              stacked: false,
              gridLines: {
                display: true
              },
              ticks: {
                fontSize: 10,
                maxTicksLimit: 20,
                fontColor: '#808080'
              }
            }
          ],
          yAxes: [
            {
              stacked: false,
              gridLines: {
                display: true
              },
              ticks: {
                fontSize: 12,
                maxTicksLimit: 10,
                fontColor: '#808080',
                callback(value) {
                  return value.toFixed(2) + '%'
                }
              }
            }
          ]
        },
        tooltips: {
          displayColors: false,
          callbacks: {
            title: metroGraphTooltipTitle,
            label: metroGraphTooltipLabel
          }
        }
      }
    }
    return data
  },
  head() {
    return {
      title: 'Covid-19 疫情分析与管理系统 | 数据库 2020 期末项目'
    }
  }
}
</script>

<style lang="scss" scoped>
.MainPage {
  .BigDataBlock {
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
}
</style>
