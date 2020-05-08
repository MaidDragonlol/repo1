<template>
    <section class="chart-container">
        <el-row>
            <el-col>
                <el-form :inline="true">
                    <h4>请输入开始时间：</h4>
                    <input type="text" ref="input1">
                    <a href="javascript:void(0)" v-on:click="get1">开始时间</a>
                    <br>
                    <span>{{ startTime }}</span>
                </el-form>
                <div id="myChart" :style="{width: '1500px', height: '460px'}"></div>
                <h3>数据: {{ this.data }}</h3>
                <h3>数据key: {{ this.time }}</h3>
                <h3>数据value: {{ this.count }}</h3>
            </el-col>
        </el-row>
    </section>

</template>

<script>

    export default {
        data() {
            return {
                data: {},
                time: [],
                count: [],
                startTime: "",
                input2: ""
            }
        },
        mounted: function () {
            /* this.everyJson();*/
            this.getList();
            this.drawLine();

        },
        methods: {
            get1: function () {
                this.startTime = this.$refs.input1.value;
            },
            /*/!*测试实验*!/
            everyJson() {
                /!* var datas = [{"2018-04": 0}, {"2018-03": 1}, {"2018-02": 0}, {"2018-01": 0}, {"2017-12": 0}, {"2017-11": 0}, {"2017-10": 0}, {"2017-09": 0}, {"2017-08": 0}, {"2017-07": 0}, {"2017-06": 0}, {"2017-05": 0}]
                *!/
                var datas = [{"2019-02-28 04:10:53": 0}, {"2019-02-28 04:20:53": 0}, {"2019-02-28 04:30:53": 0}, {"2019-02-28 04:40:53": 0}, {"2019-02-28 04:50:53": 0}, {"2019-02-28 05:00:53": 0}, {"2019-02-28 05:10:53": 0}, {"2019-02-28 05:20:53": 0}, {"2019-02-28 05:30:53": 0}, {"2019-02-28 05:40:53": 0}, {"2019-02-28 05:50:53": 0}, {"2019-02-28 06:00:53": 0}];
                for (var i = 0; i < datas.length; i++) { //遍历每一条数据
                    for (var key in datas[i]) {
                        this.time.push(key);
                        this.count.push(datas[i][key]);
                    }
                }
            },*/
            /*发送接收数据*/
            getList() {
                this.$http
                    .post("http://localhost:8090//logCountStartByTime?startTime=" + this.startTime)
                    .then(result => {
                        this.data = result.data;
                        /*this.data = JSON.parse(JSON.stringify(result.data));*/
                        for (var i = 0; i < this.data.length; i++) { //遍历每一条数据
                            for (var key in this.data[i]) {
                                this.time.push(key);
                                this.count.push(this.data[i][key]);
                            }
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    });
            },
            drawLine() {

                var keyArr = this.time;
                var valueArr = this.count;
                var myChart = this.$echarts.init(document.getElementById('myChart'));//获取容器元素
                var option = {
                    title: {text: "OK"},
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
                        data: ['日志数量', '日志数量2'],
                        left: '6%',
                        top: 'top',
                        itemWidth: 15,//图例图标的宽
                        itemHeight: 15,//图例图标的高
                        textStyle: {
                            color: '#3a6186',
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
                            type: 'category',
                            data: keyArr,
                            splitLine: {show: false},//去除网格分割线
                            axisTick: {//刻度
                                show: false//不显示刻度线
                            },
                            axisLine: {//坐标线
                                lineStyle: {
                                    type: 'solid',
                                    color: '#e7e7e7',//轴线的颜色
                                    width: '2'//坐标线的宽度
                                }
                            },
                            axisLabel: {
                                textStyle: {
                                    color: '#3a6186',//坐标值的具体的颜色
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
                                }
                            },
                            splitLine: {
                                lineStyle: {
                                    color: ['#e7e7e7'],//分割线的颜色
                                }
                            }
                        }
                    ],
                    series: [
                        {
                            name: '日志数量',
                            type: 'bar',
                            barWidth: 20,
                            data: valueArr,
                            itemStyle: {
                                normal: {
                                    color: '#00abf7',//设置柱子颜色
                                    label: {
                                        show: true,//柱子上显示值
                                        position: 'top',//值在柱子上方显示
                                        textStyle: {
                                            color: '#00abf7',//值的颜色
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
            }
        }
    }
</script>

<style lang="scss" scoped>
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
</style>
