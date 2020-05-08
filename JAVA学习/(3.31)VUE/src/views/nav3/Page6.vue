<template>
	<section class="chart-container">
		<el-row>
			<el-col :span="12">
				<div id="chartColumn" style="width:100%; height:400px;"></div>
			</el-col>
			<el-col :span="12">
				<div id="chartBar" style="width:100%; height:400px;"></div>
			</el-col>
			<el-col :span="12">
				<div id="chartLine" style="width:100%; height:400px;"></div>
			</el-col>
			<el-col :span="12">
				<div id="chartPie" style="width:100%; height:400px;"></div>
			</el-col>
			<el-col :span="24">
				<a href="http://echarts.baidu.com/examples.html" target="_blank" style="float: right;">more>></a>
			</el-col>
		</el-row>
	</section>
</template>

<script>
	import echarts from 'echarts'
	var weatherIcons = {
		'Sunny': ROOT_PATH + 'data/asset/img/weather/sunny_128.png',
		'Cloudy': ROOT_PATH + 'data/asset/img/weather/cloudy_128.png',
		'Showers': ROOT_PATH + 'data/asset/img/weather/showers_128.png'
	};

	var seriesLabel = {
		normal: {
			show: true,
			textBorderColor: '#333',
			textBorderWidth: 2
		}
	}
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

			drawColumnChart() {
				this.chartColumn = echarts.init(document.getElementById('chartColumn'));
				this.chartColumn.setOption({
					title: {
						text: 'Weather Statistics'
					},
					tooltip: {
						trigger: 'axis',
						axisPointer: {
							type: 'shadow'
						}
					},
					legend: {
						data: ['City Alpha', 'City Beta', 'City Gamma']
					},
					grid: {
						left: 100
					},
					toolbox: {
						show: true,
						feature: {
							saveAsImage: {}
						}
					},
					xAxis: {
						type: 'value',
						name: 'Days',
						axisLabel: {
							formatter: '{value}'
						}
					},
					yAxis: {
						type: 'category',
						inverse: true,
						data: ['Sunny', 'Cloudy', 'Showers'],
						axisLabel: {
							formatter: function (value) {
								return '{' + value + '| }\n{value|' + value + '}';
							},
							margin: 20,
							rich: {
								value: {
									lineHeight: 30,
									align: 'center'
								},
								Sunny: {
									height: 40,
									align: 'center',
									backgroundColor: {
										image: weatherIcons.Sunny
									}
								},
								Cloudy: {
									height: 40,
									align: 'center',
									backgroundColor: {
										image: weatherIcons.Cloudy
									}
								},
								Showers: {
									height: 40,
									align: 'center',
									backgroundColor: {
										image: weatherIcons.Showers
									}
								}
							}
						}
					},
					series: [
						{
							name: 'City Alpha',
							type: 'bar',
							data: [165, 170, 30],
							label: seriesLabel,
							markPoint: {
								symbolSize: 1,
								symbolOffset: [0, '50%'],
								label: {
									formatter: '{a|{a}\n}{b|{b} }{c|{c}}',
									backgroundColor: 'rgb(242,242,242)',
									borderColor: '#aaa',
									borderWidth: 1,
									borderRadius: 4,
									padding: [4, 10],
									lineHeight: 26,
									// shadowBlur: 5,
									// shadowColor: '#000',
									// shadowOffsetX: 0,
									// shadowOffsetY: 1,
									position: 'right',
									distance: 20,
									rich: {
										a: {
											align: 'center',
											color: '#fff',
											fontSize: 18,
											textShadowBlur: 2,
											textShadowColor: '#000',
											textShadowOffsetX: 0,
											textShadowOffsetY: 1,
											textBorderColor: '#333',
											textBorderWidth: 2
										},
										b: {
											color: '#333'
										},
										c: {
											color: '#ff8811',
											textBorderColor: '#000',
											textBorderWidth: 1,
											fontSize: 22
										}
									}
								},
								data: [
									{type: 'max', name: 'max days: '},
									{type: 'min', name: 'min days: '}
								]
							}
						},
						{
							name: 'City Beta',
							type: 'bar',
							label: seriesLabel,
							data: [150, 105, 110]
						},
						{
							name: 'City Gamma',
							type: 'bar',
							label: seriesLabel,
							data: [220, 82, 63]
						}
					]
				});
			},


			drawCharts() {
				this.drawColumnChart()

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
