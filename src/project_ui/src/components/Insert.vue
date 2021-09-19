<template>
    <div class="row">
        <div class="col-md-6 mx-auto text-left">
            <div class="form-group">
                <label for="img">이미지 : </label>
                <!-- <input type="file" class="form-control" id="file" multiple name="file" placeholder="이미지를 등록하세요" accept=".gif, .jpg, .png"> -->
                <input type="file" name="img" id="img" class="form-control" accept="image/*" multiple v-on:change="imgIn" placeholder="이미지를 등록하세요">
            </div>
            <div class="form-group">
                <label for="content">글 내용 : </label>
                <textarea name="content" id="content" cols="30" rows="10" class="form-control" v-model="content"></textarea>
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
import {mapState} from 'vuex'
export default {
    data() {
        return {
            content: '',
            image : null,
        }
    },
    computed: {
        ...mapState(["userInfo"])
    },
    mounted() {
        this.$store.dispatch("getUserInfo")
    },
    methods: {
        boardInsert() {
            let formData = new FormData();

            // console.log("test : " + this.image);
            // console.log("test : " + this.image.length);
            if (this.image != null && this.content != null) {
                for (let i = 0; i < this.image.length; i++) {
                    formData.append("image", this.image[i]);
                }
            let obj = this
            obj.$axios.post('http://localhost:9000/board/insert', formData, {
                params: {
                    userSeq: this.$store.state.userInfo.userSeq,
                    userNick: this.$store.state.userInfo.userNick,
                    content: this.content
                },  
                headers: {
                    "Content-Type": "multipart/form-data",
                }
            })
            .then(function() {
                console.log('등록 성공');
                obj.$router.push({ name: 'MyPage' });
            })
            .catch(function(err) {
                console.log("통신 실패");
                console.log(err);
            });
            }
        },
        imgIn(image) {
            // this.image = image.target.files[0]
            // console.log(image.target.files)
            this.image = image.target.files;
            console.log(image.target.files)
        },
        moveMain() {
            this.$router.push({ name: 'MyPage' });
        }
    },
}
</script>

<style scoped>


</style>
