<template>
	<div class="middle" :style="backgroundDiv">
	<section class="chart-container">
		<el-row>
			<el-col>
				<el-form :inline="true">
					<div >
						<h2 style="font-size: 25px;color: white; ">请输入：</h2>
						<h4 style="font-family:verdana,serif;font-size: 180%;color: white;">开始时间：</h4>
						<input class="input1" type="text" ref="input1"
							   style="font-family:verdana,serif;font-size:150%;color:green;margin-top:-85px"
							   v-model="defaultStartTime">
					</div>
					<div class="button" @click="wavesEffect" v-on:click="get1" style="background-color: #B0C4DE">
						<p style="font-family:verdana,serif;font-size:150%;color:green">确认</p>
						<div class="wavesbtn" ref="wavesbtn" ></div>
					</div>
					<!--<div style="margin-left: 500px;margin-top: 120px;font-size: 80px">
						<p >日志产生量图表</p>
						&lt;!&ndash;<p >（从开始时间起，统计接下来每10分钟日志产生量，共12次，总计两小时）</p>&ndash;&gt;
					</div>-->
				</el-form>

				<br>
				<div id="myChart" :style="{width: '1300px', height: '600px'}"></div>
			</el-col>
		</el-row>
	</section>
	</div>
</template>

<script>

	export default {
		data() {
			return {
				data: {},
				time: [],
				count: [],
				defaultStartTime: "2020-02-28 00:00:04",
				startTime: "",
				input2: "",
				backgroundDiv: {
					backgroundImage: "url(" + require("../../assets/dreamstime1.png") + ")"
				},
			}
		},
		mounted: function () {
			this.getList();
			this.drawLine();

		},
		methods: {
			get1() {
				var startTimeInput = this.$refs.input1.value;
				/*this.startTime = startTimeInput;*/
				this.getList(startTimeInput);
				/*this.drawLine();*/
				/*this.$set(this,startTime,this.$refs.input1.value);*/
			},
			/*发送接收数据*/
			getList(startTimeInput) {
				var startTime = startTimeInput;
				this.$http
						.get("http://127.0.0.1:8090//logCountStartByTime?startTime=" + startTime)
						.then(response => {
							this.data = response.data;
							var timeInput = [];
							var countInput = [];
							/*this.data = JSON.parse(JSON.stringify(result.data));*/
							for (var i = 0; i < this.data.length; i++) { //遍历每一条数据
								for (var key in this.data[i]) {
									timeInput.push(key);
									countInput.push(this.data[i][key]);
								}
							}
							this.drawLine(timeInput, countInput);
						})
						.catch(err => {
							console.log(err);
						});

			},
			drawLine(timeInput, countInput) {
				var keyArr = timeInput;
				var valueArr = countInput;
				var myChart = this.$echarts.init(document.getElementById('myChart'));//获取容器元素
				var option = {
					backgroundColor:'rgba(255, 239, 219, 0.3)',
					title: {
						text: '日志量统计',      //主标题
						left: 600,
						top: 0,
						textStyle:{
							color:'#191970',        //颜色
							fontStyle:'normal',     //风格
							fontWeight:'normal',    //粗细
							fontFamily:'Microsoft yahei',   //字体
							fontSize: 30,     //大小
							align:'right'   //水平对齐
						},
						subtext:'（从开始时间起，统计往后每10分钟的日志产生量，共12次，总计2小时）',      //副标题
						subtextStyle:{          //对应样式
							color:'#FF00FF',
							fontSize:14
						},
						itemGap:7
					},
					tooltip: {
						trigger: 'axis'
					},
					grid: {
						left: '6%',
						right: '6%',
						bottom: '6%',
						containLabel: true
					},
					legend: {
						data: ['日志数量', '数量'],
						left: '6%',
						top: 'top',
						itemWidth: 15,//图例图标的宽
						itemHeight: 15,//图例图标的高
						textStyle: {
							color: '#FFFAFA',
							fontSize: 20,
						}
					},
					toolbox: {
						show: true,
						feature: {
							magicType: {show: true, type: ['line', 'bar']},
						},
						right: '6%',
					},
					calculable: true,
					xAxis: [
						{
							name: "时间",
							nameTextStyle :{
								fontSize: 20,
								color: "#FFFAFA",
							},
							type: 'category',
							data: keyArr,
							splitLine: {show: false},//去除网格分割线
							axisTick: {//刻度
								show: false//不显示刻度线
							},
							axisLine: {//坐标线
								lineStyle: {
									type: 'solid',
									color: '#000079',//轴线的颜色
									width: '4'//坐标线的宽度
								}
							},
							axisLabel: {
								textStyle: {
									color: '#3a6186',//坐标值的具体的颜色
									fontSize : 15
								}
							},
							splitLine: {
								show: false//去掉分割线
							},
						}
					],
					yAxis: [
						{
							type: 'value',
							axisLine: {//线
								show: false
							},
							axisTick: {//刻度
								show: false
							},
							axisLabel: {
								textStyle: {
									color: '#3a6186',//坐标值的具体的颜色
									fontSize : 30
								}
							},
							splitLine: {
								lineStyle: {
									color: ['#e5323e'],//分割线的颜色
								}
							}
						}
					],
					series: [
						{
							name: '日志数量',
							type: 'bar',
							barWidth: 50,
							data: valueArr,
							itemStyle: {
								normal: {
									color: '#003366',//设置柱子颜色
									label: {
										show: true,//柱子上显示值
										position: 'top',//值在柱子上方显示
										textStyle: {
											color: '#003366',//值的颜色
											fontSize: 16,
										}
									}
								}
							},
						}/*,
                        {
                            name:'日志数量2',
                            type:'bar',
                            barWidth: 20,
                            data:[40, 50, 90, 110, 130, 160, 150],
                            itemStyle: {
                                normal: {
                                    color: '#ff4f76',//设置柱子颜色
                                    label: {
                                        show: true,//柱子上显示值
                                        position: 'top',//值在柱子上方显示
                                        textStyle: {
                                            color: '#ff4f76',//值的颜色
                                            fontSize:16,
                                        }
                                    }
                                }
                            },
                        }*/
					]
				};
				//防止越界，重绘canvas
				window.onresize = myChart.resize;
				myChart.setOption(option);//设置option
			},
			wavesEffect(e) {
				e = e || window.event;
				let position = e.target.getBoundingClientRect();
				let doc = document.documentElement;
				let div = document.createElement("div");
				div.className = "effect";
				this.$refs.wavesbtn.appendChild(div);

				let top = e.pageY - (position.top + window.pageYOffset) - doc.clientTop;
				let left =
						e.pageX - (position.left + window.pageXOffset) - doc.clientLeft;
				let dur = 750;
				div.style = `
        left:${left}px;
        top:${top}px;
        transform:scale(20);
        transition-duration: 1s;
        transition-timing-function: cubic-bezier(0.25, 0.46, 0.45, 0.94);`;
				setTimeout(() => {
					div.style = `
        transition-duration: 1s;
        transition-timing-function: cubic-bezier(0.25, 0.46, 0.45, 0.94);
        opacity:0;
        left:${left}px;
        top:${top}px;
        transform:scale(20);`;
					setTimeout(() => {
						this.$refs.wavesbtn.removeChild(div);
					}, dur);
				}, 100);
			}
		}
	}
</script>
<style scoped>
	.button {
		width: 150px;
		height: 50px;
		border-radius: 20px;
		line-height: 50px;
		text-align: center;
		border: 1px solid red;
		position: relative;
		overflow: hidden;
	}

	p {
		width: 100%;
		line-height: 10px;
		position: absolute;
		left: 0;
		top: 0;
		z-index: 1;
	}

	.wavesbtn {
		width: 100%;
		height: 100%;
		position: absolute;
		left: 0;
		top: 0;
	}
</style>
<style lang="scss" scoped>
	.effect {
		position: absolute;
		width: 20px;
		height: 20px;
		border-radius: 50%;
		transition: all 0.7s ease-out;
		background: #00ff00;
		transform: scale(0);
		transition-property: transform, opacity, -webkit-transform;
		opacity: 1;
		pointer-events: none;
	}
	.MyAchievement {
		display: flex;
		flex-direction: column;
		padding: 0px 90px;
	}

	.MyAchievement .MyAchievement-echart {
		width: 100%;
		height: 570px;
		border-radius: 10px;
		border: 1px solid #d3d9e9;
		box-shadow: 4px 6px 10px -2px #d3d9e9;
		background-color: #fff;
		display: flex;
		flex-direction: column;
	}

	.MyAchievement-echart .echart-title {
		width: 100%;
		height: 70px;
		background-color: #00abf7;
		border-top-left-radius: 10px;
		border-top-right-radius: 10px;
		font-size: 26px;
		color: #fff;
		text-align: center;
		line-height: 75px;
	}

	.MyAchievement-echart .echart-content {
		width: 100%;
		height: 500px;
		display: flex;
		// align-items: center;
		justify-content: center;
	}

	.echart-content #myChart {
		margin-top: 35px;
	}
	.active {
		background: #fd7522;
		border: 1px solid #fd7522;
		color: #fff;
	}
	.input1 {

		width: 300px;
		height: 30px;
		position: absolute;
		top: 170px;
		left: 120px;
	}
	.middle {
		height: 900px;
		width: 1500px;
		background: no-repeat center top;
		background-size: 100% 100%;
	}
</style>
