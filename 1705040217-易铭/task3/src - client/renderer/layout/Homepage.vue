<template>
    <div class="page-container">
        <md-app md-waterfall md-mode="overlap">
            <md-app-toolbar class="md-primary md-large">
                <div class="md-toolbar-row">
                    <md-button class="md-icon-button" @click="menuVisible = !menuVisible">
                        <md-icon>menu</md-icon>
                    </md-button>

                    <span class="md-title"><strong>Q5 CALCULATOR</strong></span>
                </div>
            </md-app-toolbar>

            <md-app-drawer :md-active.sync="menuVisible">
                <md-toolbar class="md-transparent" md-elevation="0">
                    Navigation
                </md-toolbar>

                <md-list>
                    <md-ripple>
                        <md-list-item @click="toTable">
                            <md-icon>move_to_inbox</md-icon>
                            <span class="md-list-item-text">Table</span>
                        </md-list-item>
                    </md-ripple>

                    <md-ripple>
                        <md-list-item @click="toCalculator">
                            <md-icon>folder</md-icon>
                            <span class="md-list-item-text">Calculator</span>
                        </md-list-item>
                    </md-ripple>

                    <md-ripple>
                        <md-list-item @click="info = true">
                            <md-icon>error</md-icon>
                            <span class="md-list-item-text">info</span>

                            <md-dialog-alert
                                    :md-active.sync="info"
                                    md-title="Hi!"
                                    md-content="I am <strong>Yi Ming (1705040217)</strong> who comes from <strong>IoT class 2</strong>. I made this with <strong>electron</strong> and <strong>vue</strong>. Hope you like it!" />

                        </md-list-item>
                    </md-ripple>
                </md-list>

            </md-app-drawer>



            <md-app-content>

                    <div class="centered-container" >
                        <transition name="slide-fade">
                            <router-view id="container" class="slide-fade"></router-view>
                        </transition>
                    </div>

            </md-app-content>
        </md-app>
    </div>
</template>

<style lang="scss" scoped>

    #container{
        width: inherit;
        height: inherit;
    }
    .md-app {
        height: 100vh;
        width: 100vw;
        .md-app-scroller {
            margin-top: 0;
        }
        .md-app-content {
            height: fit-content;
            width: content-box;
        }
    }

    .centered-container {
        display: flex;
        align-items: center;
        justify-content: center;
        position: relative;
        height: 100vh;
        width: inherit;
        margin: auto;
    }

    // Demo purposes only
    .md-drawer {
        width: 230px;
        max-width: calc(100vw - 125px);
    }
</style>

<script>
    import Table from "../components/Table";

    export default {
        name: 'Homepage',
        components: {
            Table
        },
        data: () => ({
            menuVisible: false,
            info: false,
            bgColor: false
        }),
        methods: {
            toTable() {
                this.$router.push('/table');
                this.bgColor=true;
            },
            toCalculator() {
                this.$router.push('/calculator');
            }
        }

    }
</script>
