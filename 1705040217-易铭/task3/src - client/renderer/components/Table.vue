<template>
    <div class="table">
        <div class="md-layout md-gutter">
            <div class="md-layout-item">
                <div id="0" class="card" @click="click(0)">
                    <div class="side" v-if="cards[0].bool">
                        <vue-playing-card :signature="cards[0].card"></vue-playing-card>
                    </div>
                    <div class="side">
                        <vue-playing-card cover></vue-playing-card>
                    </div>
                </div>
            </div>
            <div class="md-layout-item">
                <div id="1" class="card" @click="click(1)">
                    <div class="side" v-if="cards[1].bool">
                        <vue-playing-card :signature="cards[1].card"></vue-playing-card>
                    </div>
                    <div class="side">
                        <vue-playing-card cover></vue-playing-card>
                    </div>
                </div>
            </div>
            <div class="md-layout-item">
                <div id="2" class="card" @click="click(2)">
                    <div class="side" v-if="cards[2].bool">
                        <vue-playing-card :signature="cards[2].card"></vue-playing-card>
                    </div>
                    <div class="side">
                        <vue-playing-card cover></vue-playing-card>
                    </div>
                </div>
            </div>
        </div>
        <div class="board">
            <md-card class="boardCard">
                <md-card-header style="text-align: center">
                    <md-card-header-text>Player1 : Player2</md-card-header-text>
                </md-card-header>
                <md-card-header style="text-align: center">
                    <md-card-header-text>{{this.player1}}({{this.rate1}}%) : {{this.player2}}({{this.rate2}}%)</md-card-header-text>
                </md-card-header>
                <md-button @click="shuffle" >Shuffle</md-button>
                <md-button @click="getWinner" >GetWinner</md-button>
                <md-button @click="restart" >Restart</md-button>
            </md-card>
        </div>
        <div class="md-layout md-gutter">
            <div class="md-layout-item">
                <div id="3" class="card" @click="click(3)">
                    <div class="side" v-if="cards[3].bool">
                        <vue-playing-card :signature="cards[3].card"></vue-playing-card>
                    </div>
                    <div class="side">
                        <vue-playing-card cover></vue-playing-card>
                    </div>
                </div>
            </div>
            <div class="md-layout-item">
                <div id="4" class="card" @click="click(4)">
                    <div class="side" v-if="cards[4].bool">
                        <vue-playing-card :signature="cards[4].card"></vue-playing-card>
                    </div>
                    <div class="side">
                        <vue-playing-card cover></vue-playing-card>
                    </div>
                </div>
            </div>
            <div class="md-layout-item">
                <div id="5" class="card" @click="click(5)">
                    <div class="side" v-if="cards[5].bool">
                        <vue-playing-card :signature="cards[5].card"></vue-playing-card>
                    </div>
                    <div class="side">
                        <vue-playing-card cover></vue-playing-card>
                    </div>
                </div>
            </div>
        </div>
        <md-dialog-alert
                :md-active.sync="confirm"
                md-title="Winner:"
                :md-content=this.winner ></md-dialog-alert>
    </div>

</template>

<script>
    import Qs from 'qs'

    export default {
        name: "Table",
        components: {
        },
        computed: {
            rate1: function (){
                return this.round === 0 ? 0 : this.player1 * 100.00 /this.round;
            },

            rate2: function (){
                return this.round === 0 ? 0 : this.player2 * 100.00 /this.round;
            }
        },
        data () {
            return {
                round: 0,
                confirm: false,
                player1: 0,
                player2: 0,
                winner: null,
                player1Type: null,
                player2Type: null,
                cards: [
                    {
                        card: null,
                        bool: false,
                    },
                    {
                        card: null,
                        bool: false,
                    },
                    {
                        card: null,
                        bool: false,
                    },
                    {
                        card: null,
                        bool: false,
                    },
                    {
                        card: null,
                        bool: false,
                    },
                    {
                        card: null,
                        bool: false,
                    },
                ],
            }
        },
        mounted () {
            this.shuffle();
            if(this.cards[0].bool && this.cards[1].bool && this.cards[2].bool && this.cards[3].bool && this.cards[4].bool && this.cards[5].bool)
                this.getWinner();
        },
        methods: {
            restart() {
                this.shuffle();
                for (let i = 0; i<6; i++){
                    this.cards[i].card=null;
                    this.cards[i].bool=false;
                    console.log("=> " + this.cards[i].card + " " + this.cards[i].bool);
                }

            },
            shuffle() {
                this.$http
                    .get("http://localhost:8081/shuffle");
            },
            async getCard() {
                let flower;
                let value;
                await this.$http
                    .get("http://localhost:8081/getSingleCard")
                    .then( response => {
                        console.log(response.data)
                        flower = response.data.flower;
                        value = response.data.number;
                        switch (flower) {
                            case 0: flower = 'd';break;
                            case 1: flower = 'c';break;
                            case 2: flower = 'h';break;
                            case 3: flower = 's';break;
                        }
                        switch (value) {
                            case 11: value = 'j';break;
                            case 12: value = 'q';break;
                            case 13: value = 'k';break;
                            case 14: value = 'a';break;
                            default: value = value.toString();
                        }
                }).catch( error => {
                    alert(error)
                });
                return  value + flower;
            },
            async click(card) {

                this.cards[card].card = await this.getCard();
                this.cards[card].bool = !this.cards[card].bool;

                for (let i = 0; i<6; i++)
                console.log("=> " + this.cards[i].card + " " + this.cards[i].bool);

                //this.$refs.cardA.$children[0].style.transform = "rotateY(180deg)";
            },
            async getWinner() {
                this.round += 1;
                let players = [];
                for (let j=0; j<2; j++) {
                    let player = {};
                    player.id = j+1;
                    player.cards = [];
                    for (let i=j*3; i<(j+1)*3; i++) {
                        let card = {};
                        let str = this.cards[i].card.toString().split('');
                        let value = str[0];
                        let flower = str[1];
                        switch (flower) {
                            case 'd':
                                card.flower = 0;
                                break;
                            case 'c':
                                card.flower = 1;
                                break;
                            case 'h':
                                card.flower = 2;
                                break;
                            case 's':
                                card.flower = 3;
                                break;
                        }
                        switch (value) {
                            case 'j':
                                card.number = 11;
                                break;
                            case 'q':
                                card.number = 12;
                                break;
                            case 'k':
                                card.number = 13;
                                break;
                            case 'a':
                                card.number = 14;
                                break;
                            default:
                                card.number = value;
                        }
                        player.cards.push(card);
                    }
                    players.push(player)
                }
                console.log(players)
                await this.$http
                    .post("http://localhost:8081/getWinner",players,{headers:{'Content-Type': 'application/json'}})
                    .then( response => {
                        console.log(response.data)
                        this.winner = response.data.data.winner;
                        this.player1Type = response.data.data['1'];
                        this.player2Type = response.data.data['2'];
                        if(this.winner === '1'){
                            this.player1 += 1;
                        } else if (this.winner === '2'){
                            this.player2 += 1;
                        } else {
                            this.player1 += 1;
                            this.player2 += 1;
                        }
                        this.confirm = true;
                    }).catch( error => {
                        alert(error)
                    });
            }
        }
    }
</script>

<style scoped>
    .table {
        background-color: green;
        width: inherit;
        height: inherit;
    }
    .card {
        width: fit-content;
        height: fit-content;
        position: relative;
        perspective: 5000px;
    }
    .card:hover .side:nth-child(1) {
        transform: rotateY(180deg);
    }
    .card:hover .side:nth-child(2) {
        transform: rotateY(0deg);
    }
    .side {
        transition: 0.25s;
        transform-style: preserve-3d;
        backface-visibility: hidden;
        width: inherit;
        height: inherit;
    }
    .side:nth-child(1) {
        //background-color: red;
    }
    .side:nth-child(2) {
        position: absolute;
        //background-color: green;
        left: 0;
        top: 0;
        transform: rotateY(180deg);
    }
    .board{
        margin: 2vh;
    }
    .boardCard{
        border-radius: 10px;
    }
    .md-card.md-theme-default{
        background-color: darkgreen;
    }
</style>
