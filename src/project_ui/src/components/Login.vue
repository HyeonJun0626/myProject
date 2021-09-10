<template>
    <div class="container">
        <div class="content">
            <div class="banner">
                <img src="../assets/banner.jpg" alt="배너이미지">
            </div>
            <div class="login-page">
                <div class="login-content">
                    <div class="login-menu">
                        <div class="menu">
                            <div class="menu-btn" v-bind:class="{ 'active-color' : isSignin }" v-on:click="loginPage">L o g i n</div>
                        </div>
                        <div class="menu">
                            <div to="/Signup" class="menu-btn" v-bind:class="{ 'active-color' : isSignup }" v-on:click="signUpPage">Sign Up</div>
                        </div>
                    </div>
                <router-view></router-view>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {mapState} from "vuex"

export default {
    data() {
        return {
            isSignin: true,
            isSignup: false
        }
    },
    computed: {
        ...mapState(["isLogin", "isLoginError"])
    },
    updated() {
        if (this.$route.name == 'Sign') {
            this.isSignin = true;
            this.isSignup = false;
            // this.loginPage()
        }
        else if (this.$route.name == 'Signup'){
            this.isSignin = false;
            this.isSignup = true;
        }
    },
    methods: {
        loginPage() {
            this.$router.push({name:"Sign"}).catch(()=>{})
        },
        signUpPage() {
            this.$router.push({name:"Signup"}).catch(()=>{})
        }
    }
}
</script>

<style scoped>
    /* noonnu font */

    /* noonnu font end*/

    .container {
        height: 100%;
    }

    .content {
        display: flex;
        height: 100%;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
    }
    .banner {
        min-width: 460px;
        margin: 0 20px;
        border-radius: 15px;
        box-shadow: 4px 12px 30px 6px rgb(0 0 0 / 9%);
        overflow: hidden;
    }
    .banner:hover {
        box-shadow: 4px 12px 30px 6px rgb(0 0 0 / 15%);
        transition: .3s;
        transform: scale(1.01);
    }
    .login-page {
        background-color: #ffff;
        border-radius: 15px;
        box-shadow: 4px 12px 30px 6px rgb(0 0 0 / 9%);
        min-width: 470px;
        height: 627px;
    }

    .login-content {
        width: 400px;
        height: 100%;
        margin: 0 auto;
        padding: 40px 0;
        overflow: scroll;
        -ms-overflow-style: none;
    }

    .login-content::-webkit-scrollbar { 
        display: none;
        width: 0 !important;
    }

    .login-menu {
        text-align: center;
        display: flex;
        margin-bottom: 50px;
    }

    .menu {
        font-family: 'SEBANG_Gothic_Bold';
        width: 50%;
    }


    .active-color {
        color: rgb(255, 144, 100) !important;
        transition: .2s;
    }

    .menu-btn {
        text-decoration: none;
        color: #000000;
        font-weight: bold;
        font-size: 24px;
    }


</style>