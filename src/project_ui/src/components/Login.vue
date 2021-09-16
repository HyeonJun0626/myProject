<template>
    <div class="container">
        <div class="content">
            <div class="banner">
                <img src="@/assets/고양이배너.jpeg" alt="배너이미지">
            </div>
            <div class="login-page">
                <div class="login-content">
                    <div class="login-menu">
                        <div class="menu" type="button">
                            <div class="menu-btn" v-bind:class="{ 'active-color' : isSignin }" v-on:click="loginPage">L o g i n</div>
                        </div>
                        <div class="menu" type="button">
                            <div class="menu-btn" v-bind:class="{ 'active-color' : isSignup }" v-on:click="signUpPage">Sign Up</div>
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
    // updated() {
    //     if (this.$router.name == "Sign") {
    //         this.isSignin = true;
    //         this.isSignup = false;
    //         // this.loginPage()
    //     }
    //     else if (this.$router.name == "Signup"){
    //         this.isSignin = false;
    //         this.isSignup = true;
    //     }
    // },
    mounted() {
        this.goPage()         
    },
    methods: {
        loginPage() {
            this.isSignin = true;
            this.isSignup = false;
            this.$router.push({name:"Sign"})
        },
        signUpPage() {
            this.isSignin = false;
            this.isSignup = true;
            this.$router.push({name:"Signup"})
        },
        goPage() {
            if (this.isLogin === true) {
            this.$router.push({name: "Main"})
            }
        }

    }
}
</script>

<style scoped>

    @media (max-width: 680px) {
        .banner {
            display: none;
        }
    }
    .container {
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
    }

    .content {
        display: flex;
        width: 100%;
        height: 100%;
        flex-wrap: wrap;
        justify-content: center;
        align-items: center;
    }
    .banner {
        width: 460px;
        height: 500px;
        margin: 0 20px;
        border-radius: 15px;
        box-shadow: 4px 12px 30px 6px rgb(0 0 0 / 9%);
        overflow: hidden;
        text-align: center;
    }
    .banner img {
        width: 100%;
        height: 100%;
        display: block;
        object-fit: cover;
        margin: auto;
    }
    .banner:hover {
        box-shadow: 4px 12px 30px 6px rgb(0 0 0 / 15%);
        transition: .3s;
        transform: scale(1.01);
    }
    .login-page {
        width: 460px;
        background-color: #ffff;
        border-radius: 15px;
        box-shadow: 4px 12px 30px 6px rgb(0 0 0 / 9%);
        /* min-width: 470px; */
        height: 500px;
    }

    .login-content {
        width: 400px;
        height: 100%;
        margin: 0 auto;
        padding: 40px 0;
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