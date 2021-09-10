<template>
    <div>
        <!-- v-on:submit 후에 login 메서드를 실행하지 않는다 -->
        <!-- <form  @submit.prevent="login()">  -->
            <div class="form-group">
                <input type="text"  placeholder="ID를 입력해주세요" autocomplete="off" v-model="userId">
            </div>
            <div class="form-group">
                <input type="password"  placeholder="비밀번호를 입력해주세요" v-model="userPw">
            </div>
                <button type="button" class="btn btn-primary btn-block" v-on:click="login({userId, userPw})">로그인</button>
            <div class="btn-box">
                <a href="#" class="float-right">비밀번호 찾기</a>
            </div>
                <!-- <button type="button" class="btn btn-primary" v-on:click="moveMain">넘어가기</button> -->
            <div class="error-massage" v-if="isLoginError">
                <p class="m-0">아이디와 비밀번호를 확인하세요</p>
            </div>
        <!-- </form> -->
    </div>
</template>


<script>
import {mapState, mapActions} from "vuex"

export default {
    name: 'Sign',
    data () {
        return {
            userId: null,
            userPw: null
        }
    },
    computed: {
        ...mapState(["isLogin", "isLoginError"])
    },
    methods: {
        ...mapActions(["login"]),
    }
}
</script>

<style>
    input {
        width: 100%;
        height: 40px;
        margin-bottom: 40px;
        outline: none;
        border-style: none;
        border-bottom: 1px solid black;
    }

    .btn {
        background: linear-gradient(to left, #ff9966 14%, #ff9999 86%);
        margin: 10px 0;
        width: 100%;
        font-weight: bold;
        border-style: none;
    }

    .btn-box {
        margin-top: 150px;
    }

    .error-massage {
        font-size: 15px !important;
        color: red;
        text-align: center;
    }
</style>