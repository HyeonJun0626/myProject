<template>
    <div class="signup-container">
        <!-- <form> -->
			<!-- <div class="form-group">
				<input type="file" id="userImg" name="userImg" accept=".gif, .jpg, .png">
			</div> -->
			<div class="form-group">
				<input type="text" id="userId" name="userId" placeholder="e-mail을 입력해주세요" autocomplete="off" v-model="userId">
                <p class="m-0">{{this.ErrorInfo}}</p>
			</div>
			<div class="form-group">
				<input type="password" id="userPw" name="userPw" placeholder="비밀번호를 입력해주세요" v-model="userPw">
			</div>
			<!-- <div class="form-group">
				<input type="password" id="userPw1" name="userPw1" placeholder="비밀번호를 다시 입력해주세요" v-model="userPw1">
			</div> -->
			<div class="form-group">
				<input type="text" id="userName" name="userName" placeholder="이름을 입력하세요" autocomplete="off" v-model="userName">
			</div>
			<div class="form-group">
				<input type="tel" id="userPhone" name="userPhone" placeholder="연락처를 입력하세요" v-model="userPhone">
			</div>
			<div class="form-group">
				<input type="text" id="userNick" name="userNick" placeholder="닉네임을 입력해주세요" autocomplete="off" v-model="userNick">
            </div>
			<button type="button" class="btn btn-primary btn-block" v-on:click="signUp">가입하기</button>
        <!-- </form> -->
    </div>
</template>

<script>
export default {
    name: 'Signup',
    data() {
        return {
            userId: null,
            userPw: null,
            userPw1: null,
            userNick: null,
            userName: null,
            userPhone: null,
            ErrorInfo: null
        }
    },
    methods: {
        signUp() {
            let obj = this;
            obj.$axios.post('http://localhost:9000/user/signup',
            {
                userImg: this.userImg,
                userId: this.userId,
                userNick: this.userNick,
                userPw: this.userPw,
                userName: this.userName,
                userPhone: this.userPhone
            })
            .then(function (res) {
                console.log('통신 성공');
                obj.ErrorInfo = res.data
                if (res.data == true) {
                    obj.$router.push({name: 'Sign'});
                }
            })
            .catch(function(err) {
                console.log('통신 실패');
                console.log(err);
                if(err.response.status == 500) {
                    obj.ErrorInfo = "중복된 닉네임 입니다."
                }
            });
        },
    },
    computed: {

    }
    
}
</script>

<style>
    .signup-container {
        overflow: scroll;
        -ms-overflow-style: none;
        height: 300px;
    }
    .signup-container::-webkit-scrollbar { 
        display: none;
        width: 0 !important;
    }
</style>