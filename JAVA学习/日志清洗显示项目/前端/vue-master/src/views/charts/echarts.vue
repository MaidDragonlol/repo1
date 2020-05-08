<template>
    <div class="middle" :style="backgroundDiv">
        <!--<img src="../../assets/bg4.jpg" style="width: auto; height: 40px;">-->
        <section class="chart-container">
            <el-row>
                <el-col>
                    <el-form :inline="true">
                        <div>
                            <h2 style="font-size: 25px;color: white; ">请输入：</h2>
                            <h4 style="font-family:verdana,serif;font-size: 150%;color: white;">开始时间：</h4>
                            <input class="input1" type="text" ref="input1"
                                   style="font-family:verdana,serif;font-size:150%;color:green;margin-top:-85px"
                                   v-model="defaultStartTime">
                            <h4 style="font-family:verdana,serif;font-size: 150%;color: white;">结束时间：</h4>
                            <input class="input2" type="text" ref="input2"
                                   style="font-family:verdana,serif;font-size:150%;color:green;margin-top:-70px"
                                   v-model="defaultEndTime">
                        </div>
                        <br>
                        <div class="button" @click="wavesEffect" v-on:click="get1" style="background-color: #B0C4DE">
                            <p style="font-family:verdana,serif;font-size:150%;color:green">确认</p>
                            <div class="wavesbtn" ref="wavesbtn"></div>
                        </div>
                        <br>
                    </el-form>
                    <div id="myChart" :style="{width: '1300px', height: '460px'}"></div>
                    <br>
                    <div id="myChart2" :style="{width: '1300px', height: '460px'}"></div>
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
                defaultStartTime: "2019-02-28 04:10:53",
                defaultEndTime: "2022-02-28 04:10:53",
                startTime: "",
                input2: "",
                backgroundDiv: {
                    backgroundImage: "url(" + require("../../assets/dreamstime3.png") + ")"
                },
            }
        },
        mounted: function () {
            /* this.everyJson();*/
            this.getList();
            this.drawLine();

        },
        methods: {
            get1() {
                var startTimeInput = this.$refs.input1.value;
                var endTimeInput = this.$refs.input2.value;
                /*this.startTime = startTimeInput;*/
                this.getList(startTimeInput, endTimeInput);
                /*this.drawLine();*/
                /*this.$set(this,startTime,this.$refs.input1.value);*/
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
            getList(startTimeInput, endTimeInput) {
                var startTime = startTimeInput;
                var endTime = endTimeInput;
                this.$http
                    .get("http://127.0.0.1:8090//OtaTimesByTime?startTime=" + startTime + "&endTime=" + endTime)
                    .then(response => {
                        this.data = response.data;
                        var yInput = [];
                        var xInput = [];
                        /*this.data = JSON.parse(JSON.stringify(result.data));*/
                        for (var i = 0; i < this.data.length; i++) { //遍历每一条数据
                            for (var key in this.data[i]) {
                                yInput.push(key);
                                xInput.push(this.data[i][key]);
                            }
                        }
                        this.drawLine(yInput, xInput);
                    })
                    .catch(err => {
                        console.log(err);
                    });
                this.$http
                    .get("http://127.0.0.1:8090//MethodTimesByTime?startTime=" + startTime + "&endTime=" + endTime)
                    .then(response => {
                        this.data = response.data;
                        var yInput = [];
                        var xInput = [];
                        /*this.data = JSON.parse(JSON.stringify(result.data));*/
                        for (var i = 0; i < this.data.length; i++) { //遍历每一条数据
                            for (var key in this.data[i]) {
                                yInput.push(key);
                                xInput.push(this.data[i][key]);
                            }
                        }
                        this.drawLine2(yInput, xInput);
                    })
                    .catch(err => {
                        console.log(err);
                    });

            },
            drawLine(yInput, xInput) {
                var keyArr = yInput;
                var valueArr = xInput;
                var myChart = this.$echarts.init(document.getElementById('myChart'));//获取容器元素
                var option = {
                    title: {
                        text: 'OTA统计',      //主标题
                        left: 600,
                        top: 0,
                        textStyle:{
                            color:'#0DB9F2',        //颜色
                            fontStyle:'normal',     //风格
                            fontWeight:'normal',    //粗细
                            fontFamily:'Microsoft yahei',   //字体
                            fontSize: 30,     //大小
                            align:'right'   //水平对齐
                        },
                        subtext:'（各种OTA平台使用产品产生日志数）',      //副标题
                        subtextStyle:{          //对应样式
                            color:'#F27CDE',
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
                        data: ['日志数量', '次数'],
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
                            name: "OTA",
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
                                    color: '#e7e7e7',//轴线的颜色
                                    width: '2'//坐标线的宽度
                                }
                            },
                            axisLabel: {
                                textStyle: {
                                    color: '#00FFFF',//坐标值的具体的颜色
                                    fontSize: 20 ,
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
                                    color: '#00FFFF',//坐标值的具体的颜色
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
                            name: '次数',
                            type: 'bar',
                            barWidth: 100,
                            data: valueArr,
                            itemStyle: {
                                normal: {
                                    color: '#00FFFF',//设置柱子颜色
                                    label: {
                                        show: true,//柱子上显示值
                                        position: 'top',//值在柱子上方显示
                                        textStyle: {
                                            color: '#00FFFF',//值的颜色
                                            fontSize: 20,
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
            drawLine2(yInput, xInput) {
                var keyArr = yInput;
                var valueArr = xInput;
                var myChart = this.$echarts.init(document.getElementById('myChart2'));//获取容器元素
                var option = {
                    title: {
                        text: '接口日志统计',      //主标题
                        left: 600,
                        top: 0,
                        textStyle:{
                            color:'#0DB9F2',        //颜色
                            fontStyle:'normal',     //风格
                            fontWeight:'normal',    //粗细
                            fontFamily:'Microsoft yahei',   //字体
                            fontSize: 30,     //大小
                            align:'right'   //水平对齐
                        },
                        subtext:'（各种接口产生日志数）',      //副标题
                        subtextStyle:{          //对应样式
                            color:'#F27CDE',
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
                        data: ['日志数量', '次数'],
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
                            name: "接口",
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
                                    color: '#e7e7e7',//轴线的颜色
                                    width: '2'//坐标线的宽度
                                }
                            },
                            axisLabel: {
                                textStyle: {
                                    color: '#00FFFF',//坐标值的具体的颜色
                                    fontSize: 12,
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
                                    color: '#00FFFF',//坐标值的具体的颜色
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
                            name: '次数',
                            type: 'bar',
                            barWidth: 100,
                            data: valueArr,
                            itemStyle: {
                                normal: {
                                    color: '#00FFFF',//设置柱子颜色
                                    label: {
                                        show: true,//柱子上显示值
                                        position: 'top',//值在柱子上方显示
                                        textStyle: {
                                            color: '#00FFFF',//值的颜色
                                            fontSize: 20,
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
    .input1 {

        width: 300px;
        height: 30px;
        position: absolute;
        top: 160px;
        left: 120px;
    }

    .input2 {

        width: 300px;
        height: 30px;
        position: absolute;
        top: 200px;
        left: 120px;
    }

    .middle {
        height: 1200px;
        width: 1600px;
        background: no-repeat center top;
        background-size: 100% 100%;
    }
</style>
