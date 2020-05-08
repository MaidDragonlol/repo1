<template>
    <section class="chart-container">
        <el-row>
            <el-col :span="120">
                <h2>传入数据实验</h2>
                <h3>数据: {{ this.data }}</h3>
            </el-col>
        </el-row>
    </section>
</template>

<script>

    export default {
        data() {
            return {
                data: {}
            };
        },

        methods: {
            getList() {
                this.$http
                    .get("http://localhost:8080//logCountStartByTime")
                    .then(result => {
                        this.data = result.data.toJSON();
                    })
                    .catch(err => {
                        console.log(err);
                    });
            },
        },

        mounted: function () {
            this.getList();
        },
        updated: function () {
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
