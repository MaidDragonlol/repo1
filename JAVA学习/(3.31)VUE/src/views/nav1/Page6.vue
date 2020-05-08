<template>
    <section class="chart-container">
        <el-row>
            <el-col :span="120">
                <h2>当前日志总数：645</h2>
                <div id="chartPie" style="width:100%; height:600px;"></div>
            </el-col>
            <el-col :span="120">
                <h2>传入数据实验</h2>
                <div id="title" style="width:100%; height:600px;"></div>
            </el-col>
            <el-col :span="24">
                <a href="http://echarts.baidu.com/examples.html" target="_blank" style="float: right;">more>></a>
            </el-col>
        </el-row>
    </section>
</template>

<script>
    import echarts from 'echarts';

    export default {
        data() {
            return {
                chartColumn: null,
                chartBar: null,
                chartLine: null,
                chartPie: null,
                mylist: [{id: 1, title: 'Hello', content: 'This is content.'},]
            }
        },

        methods: {
            getList() {
                this.title = document.getElementById("title");
                axios.get('http://localhost:8090//logCountStartByTime')
                    .then(res => {
                        this.mylist = res.data.data.list;
                        this.title.write("Test");
                        this.title.write(this.mylist);
                        console.log(this.mylist);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            },
            drawPieChart() {
                this.chartPie = echarts.init(document.getElementById('chartPie'));
                this.chartPie.setOption({
                    title: {
                        text: '日志总体概况',
                        subtext: '总计：645',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['报错日志数', '成功日志数']
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: [
                                {value: 335, name: '报错日志数'},
                                {value: 310, name: '成功日志数'},

                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            },

            drawCharts() {
                this.drawPieChart()
            },
        },

        mounted: function () {
            this.drawCharts()
            this.getList();
        },
        updated: function () {
            this.drawCharts();
            this.getList();
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
