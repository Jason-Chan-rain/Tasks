<template>
    <div class="small">
        <line-chart v-if="loaded" :chart-data="chartData" :styles="chartStyles" :options="chartOptions"></line-chart>
    </div>
</template>

<script>
    import moment from 'moment'
    import LineChart from './LineChart.js'
    import Utils from './Utils.js'

    let color = Chart.helpers.color;
    let timeFormat = 'H:mm:ss'

    export default {
        name: "Temperature",
        components: {
            LineChart
        },
        data: () => ({
            loaded: false,
            chartData: null,
            chartOptions: {
                responsive: true,
                maintainAspectRatio: false,
                title: {
                    display: true,
                    text: 'Temperature',
                    fontColor: color(window.chartColors.yellow).alpha(0.5).rgbString(),
                    fontSize: 18
                },
                legend: {
                    display: false
                },
                scales: {
                    xAxes: [{
                        type: 'time',
                        display: true,
                        time: {
                            format:  timeFormat,
                        },
                        ticks: {
                            source: true,

                            fontSize: 12,
                        },
                        gridLines: {
                            color: color(window.chartColors.grey).alpha(0.5).rgbString(),
                            drawTicks: true}
                    }],
                    yAxes: [{
                        ticks: {
                            //max: 30,
                            //min: 24
                        },
                        gridLines: {
                            color: color(window.chartColors.grey).alpha(0.5).rgbString(),
                            drawTicks: true}

                    }]
                },
            },
            dataCollection: [],
            timeSets: [],
            temperature: []
        }),
        mounted () {
            this.fillData()
            window.setInterval(() => {
                setTimeout(this.fillData(), 0)
            }, 30000)
        },
        methods: {
            async fillData () {
                //this.loaded = false
                try {
                    await this.$http
                        //.get('http://193.112.210.168/onenetApi/devices/547398737/datapoints?limit=50&datastream_id=3303_0_5700')
                        .get('/onenetApi/devices/547398737/datapoints?limit=50&datastream_id=3303_0_5700')
                        .then( response => (this.dataCollection = response.data))
                    //console.log(JSON.stringify(this.dataCollection.data.datastreams[0].datapoints[0].value))

                    this.temperature = []
                    this.timeSets = []

                    for (let item in this.dataCollection.data.datastreams[0].datapoints) {
                        console.log(this.dataCollection.data.datastreams[0].datapoints[item].at)
                        this.timeSets.push(
                            moment(this.dataCollection.data.datastreams[0].datapoints[item].at)
                            )
                        this.temperature.push(this.dataCollection.data.datastreams[0].datapoints[item].value)
                    }
                    console.log(this.temperature)
                    this.loaded = true
                } catch (e) {
                    console.error(e)
                }
                this.chartData = {
                    labels: this.timeSets,
                    datasets: [
                        {
                            label: 'Temperature',
                            data: this.temperature,

                            backgroundColor: color(window.chartColors.green).alpha(0.5).rgbString(),
                            borderColor: window.chartColors.green,
                            pointRadius: 3,
                            fill: true,
                        }
                    ]
                }
            },
        },
        computed: {
            chartStyles () {
                return {
                    height: `${this.height}px`,
                    position: 'relative'
                }
            }
        }
    }
</script>

<style xml:lang="scss" scoped>
    .small {
        width: inherit;
        height: inherit;
        margin: auto 1vw;
    }
</style>
