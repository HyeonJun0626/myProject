<template>
    <div class="header">
        <div class="navi">
            <div class="logo">
                <a href="/main" class="brand-name">Gonyang</a>
            </div>
            <div class="user-info">
                <div type="button" class="header-profile-box" v-if="myUserInfo">
                    <img v-bind:src="myUserInfo.profileImg" alt="헤더프로필">
                </div>
                <span class="hidden-box">
                    <div type="button" class="hidden-btn" v-on:click="moveMyPage">
                        <span> 내 피드</span>
                    </div>
                    <div type="button" class="hidden-btn" v-on:click="logout">
                        로그아웃
                    </div>
                </span>
            </div>
        </div>
    </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
export default {
    data() {
        return {
        }
    },
    computed: {
        ...mapGetters(['myUserInfo']),
    },
    methods: {
        ...mapActions(['logout']),
        moveMyPage() {
            this.$router.push({name:"MyPage"}).catch(()=>{})
        }
    },
}
</script>

<style scoped>
    @font-face {
        font-family: 'GmarketSansBold';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }
    .header {
        position: fixed;
        top: 0;
        width: 100%;
        height: 50px;
        background-color: #ffff;
        border-bottom: 1px solid lightgray;
        box-shadow: 1px 2px 5px lightgray;
        /* display: flex; */
        align-items: center;
        justify-content: center;
        z-index: 10;
    }

    .navi {
        margin: 0 auto;
        width: 100%;
        max-width: 935px;
        height: 100%;
        justify-content: space-between;
        display: flex;
    }

    .logo {
        display: flex;
        align-items: center;
    }

    .brand-name {
        font-family: 'GmarketSansBold';
        font-size: 24px;
        text-decoration: none;
        color: rgb(255, 144, 100);
    }

    .brand-name:hover {
        color: #000000;
    }

    .user-info {
        /* display: flex; */
        /* align-items: center;
        text-align: center; */
        position: relative;
        padding: 5px 0;
    }
    .user-info:hover .hidden-box {
        display: block;
    }


    .header-profile-box {
        width: 40px;
        height: 40px;
        border-radius: 180px;
        overflow: hidden;
        box-shadow: 0 0 1px 1px #f2f2f2;
        border: 1px solid rgb(221, 221, 221);
    }
    .header-profile-box img {
        width: 100%;
        height: 100%;
        margin: auto;
        display: block;
        object-fit: cover;
    }

    .hidden-box {
        position: absolute;
        display: none;
        bottom: -60px;
        right: 5px;
        width: 120px;
        text-align: center;
        border: 1px solid #f2f2f2;
        background-color: #ffff;
        z-index: 10;
    }
    .hidden-btn {
        font-size: 14px;
        padding: 4px 16px;
        border-bottom: 1px solid #f2f2f2;
    }
    .hidden-btn:hover {
        background-color: #f8f8f8;
    }

    .logout {
        font-size: 30px;
    }

</style>