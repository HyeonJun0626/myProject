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
                            <router-link to="/" class="menu-btn" v-bind:class="{ 'active-color' : isSignin }">L o g i n</router-link>
                        </div>
                        <div class="menu">
                            <router-link to="/Signup" class="menu-btn" v-bind:class="{ 'active-color' : isSignup }">Sign Up</router-link>
                        </div>
                    </div>
                <router-view></router-view>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            isSignin: true,
            isSignup: false
        }
    },
    updated() {
        if (this.$route.name == 'Sign') {
            this.isSignin = true;
            this.isSignup = false;
        }
        else if (this.$route.name == 'Signup'){
            this.isSignin = false;
            this.isSignup = true;
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