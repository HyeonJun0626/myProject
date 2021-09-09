<template>
    <div>
        <!-- <form> -->
			<!-- <div class="form-group">
				<input type="file" id="userImg" name="userImg" accept=".gif, .jpg, .png">
			</div> -->
			<div class="form-group">
				<input type="text" id="userId" name="userId" placeholder="e-mail을 입력해주세요" autocomplete="off" v-model="userId">
			</div>
			<div class="form-group">
				<input type="password" id="userPw" name="userPw" placeholder="비밀번호를 입력해주세요" v-model="userPw">
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
            userNick: null,

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
                userPw: this.userPw
            })
            .then(function () {
                console.log('비동기 통신 성공');
                obj.$router.push({name: 'Sign'});
            })
            .catch(function(err) {
                console.log('비동기 통신 실패');
                console.log(err);
            });
        }
    }
    
}
</script>

<style>

</style>