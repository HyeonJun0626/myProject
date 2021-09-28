<template>
    <div class="form-box" v-on:keyup.enter="login({userId, userPw})">
        <!-- v-on:submit 후에 login 메서드를 실행하지 않는다 -->
        <!-- <form  @submit.prevent="login()">  -->
            <div class="form-group">
                <input type="text" placeholder="ID를 입력해주세요" autocomplete="off" autofocus v-model="userId">
            </div>
            <div class="form-group">
                <input type="password" placeholder="비밀번호를 입력해주세요" v-model="userPw">
            </div>
            <div class="error-massage">
                <p class="m-0">{{ErrorInfo}}</p>
            </div>
            <!-- <div class="error-massage" v-if="ErrorInfo === 'PwError' ">
                <p class="m-0">비밀번호를 확인하세요.</p>
            </div> -->
                <button type="button" class="btn btn-primary btn-block" v-on:click="login({userId, userPw})">로그인</button>
            <div class="btn-box">
                <a href="#" class="float-right">비밀번호 찾기</a>
            </div>
                <!-- <button type="button" class="btn btn-primary" v-on:click="moveMain">넘어가기</button> -->
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
        ...mapState(["ErrorInfo"])
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
        margin-bottom: 10px;
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
        color: #ffff;
    }
    .btn:hover {
        box-shadow: 1px 1px 8px 2px #ff99669d;
    }

    .btn-box {
        margin-top: 150px;
    }

    .error-massage {
        font-size: 15px !important;
        color: red;
        text-align: center;
        margin-bottom: 30px;
    }

</style>