<template>
    <div class="row">
        <div class="col-md-6 mx-auto text-left">
            <div class="form-group">
                <label for="file">이미지 : </label>
                <input type="file" class="form-control" id="file" name="file" placeholder="이미지를 등록하세요" accept=".gif, .jpg, .png">
            </div>
            <div class="form-group">
                <label for="creatorId">사용자ID : </label>
                <input type="text" class="form-control" id="creatorId" name="creatorId" placeholder="ID를 입력하세요" v-model="creatorId">
            </div>
            <div class="form-group">
                <label for="contents">글 내용 : </label>
                <textarea name="contents" id="contents" cols="30" rows="10" class="form-control" v-model="contents"></textarea>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <button type="button" class="btn btn-success btn-block" v-on:click="boardInsert">글 등록</button>
                </div>
                <div class="col-md-6">
                    <button type="button" class="btn btn-secondary btn-block" v-on:click="moveMain">취소</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            file: '',
            creatorId: '',
            contents: ''
        }
    },
    methods: {
        // axios를 사용하여 서버랑 통신
        boardInsert() {
            let obj = this;
            obj.$axios.post('http://localhost:9000/vue/axiosBoardInsert', {
                title: this.title,
                contents: this.contents,
                creatorId: this.creatorId
            })
            .then(function() {
                console.log('비동기 통신 성공');
                obj.$router.push({ name: 'Main' });
            })
            .catch(function(err) {
                console.log("비동기 통신 실패");
                console.log(err);
            });
        },
        // 리스트 화면으로 이동
        moveMain() {
            this.$router.push({ name: 'Main' });
        }
    },
}
</script>

<style>

</style>
