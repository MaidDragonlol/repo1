<template>
  <section class="chart-container">
    <el-row>
      <el-col :span="24">
          <h2>航线次数及出错次数</h2>
        <div id="chartLine" style="width:110%; height:400px;"></div>
      </el-col>
      <el-col :span="12">
          <h2>航线错误率统计图表（百分比）</h2>
        <div id="chartPie" style="width:100%; height:400px;"></div>
      </el-col>
        <el-col :span="12">
            <h2>航线延时统计</h2>
            <div id="chart3" style="width:100%; height:400px;"></div>
        </el-col>
    </el-row>
  </section>
</template>

<script>
  import echarts from 'echarts'

  export default {
    data() {
      return {
        chartColumn: null,
        chartBar: null,
        chartLine: null,
        chartPie: null
      }
    },

    methods: {

      drawLineChart() {
        this.chartLine = echarts.init(document.getElementById('chartLine'));
        this.chartLine.setOption({
          tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
              type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          legend: {
            data: ['航线出现次数', '航线报错次数']
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'value'
          },
          yAxis: {
            type: 'category',
            data: ['SSA-MOX', 'SSB-MOX', 'SSC-MOX', 'SSD-MOX', 'SSE-MOX', 'SSF-MOX', 'SSG-MOX', 'SSE-MOX', 'SSF-MOX', 'SSG-MOX']
          },
          series: [
            {
              name: '航线出现次数',
              type: 'bar',
              stack: '总量',
              label: {
                show: true,
                position: 'insideRight'
              },
              data: [320, 302, 301, 334, 390, 330, 320, 390, 330, 320]
            },
            {
              name: '航线报错次数',
              type: 'bar',
              stack: '总量',
              label: {
                show: true,
                position: 'insideRight'
              },
              data: [120, 132, 101, 134, 90, 230, 210, 132, 101, 134]
            },

          ]
        });
      },
      drawLineChart2() {
        this.chartPie = echarts.init(document.getElementById('chartPie'));
        this.chartPie.setOption({
            xAxis: {
                type: 'category',
                data: ['SSA-MOX', 'SSB-MOX', 'SSC-MOX', 'SSD-MOX', 'SSE-MOX', 'SSF-MOX', 'SSG-MOX', 'SSE-MOX', 'SSF-MOX', 'SSG-MOX']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: [20, 29, 34, 34, 4, 23, 45, 67, 96, 34],
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(220, 220, 220, 0.8)'
                }
            }]

        });
      },

        drawChart3() {

            this.chartPie = echarts.init(document.getElementById('chart3'));
            this.chartPie.setOption({
                angleAxis: {
                },
                radiusAxis: {
                    type: 'category',
                    data: ['SSA-MOX', 'SSB-MOX', 'SSC-MOX', 'SSD-MOX', 'SSE-MOX', 'SSF-MOX', 'SSG-MOX', 'SSE-MOX', 'SSF-MOX', 'SSG-MOX'],
                    z: 10
                },
                polar: {
                },
                series: [{
                    type: 'bar',

                    data: [20, 29, 34, 34, 4, 23, 45, 67, 96, 34],
                    coordinateSystem: 'polar',
                    name: 'A',
                    stack: 'a'
                }],
                legend: {
                    show: true,
                    data: ['A']
                }

            });
        },
      drawCharts() {
        this.drawLineChart()
        this.drawLineChart2()
          this.drawChart3()
      },
    },

    mounted: function () {
      this.drawCharts()
    },
    updated: function () {
      this.drawCharts()
    }
  }
</script>

<style scoped>
  .chart-container {
    width: 100%;
    float: left;
  }
  /*.chart div {
      height: 400px;
      float: left;
  }*/

  .el-col {
    padding: 30px 20px;
  }
</style>
