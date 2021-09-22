<template>
    <div class="main-container">
        <header-ui></header-ui>
        <div class="content-container">
            <div class="input-container">
                <div class="img-container">
                    <div class="image-input">
                        <label class="input-btn" for="img">
                            <div class="input-icon">
                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                                    <path d="M8 0a1 1 0 0 1 1 1v6h6a1 1 0 1 1 0 2H9v6a1 1 0 1 1-2 0V9H1a1 1 0 0 1 0-2h6V1a1 1 0 0 1 1-1z"/>
                                </svg>
                            </div>
                        </label>
                    </div>
                    <div class="preview-container" v-if="previewImg">
                        <div class="preview-img-box" v-for="(item, idx) in previewImg" v-bind:key="idx">
                            <img v-bind:src="item">
                        </div>
                    </div>
                    <div class="preview-container" v-if="image && previewImg == ''">
                        <div class="preview-img-box" v-for="(item, idx) in image" v-bind:key="idx">
                            <img  v-bind:src="'http://localhost:9000/' + item.storedImgPath">
                        </div>
                    </div>
                    
                    <!-- <input type="file" class="form-control" id="file" multiple name="file" placeholder="이미지를 등록하세요" accept=".gif, .jpg, .png"> -->
                    <input type="file" name="img" id="img" class="form-control" accept="image/*" multiple v-on:change="imgIn" style="display:none;">
                </div>
                <div v-if="cnt" v-bind="cnt" class="count">{{cnt}}개 파일 선택 됨</div>
                <div class="form-group">
                    <textarea name="content" id="content" cols="30" rows="10" class="form-control" style="resize:none;" v-model="content" placeholder="text ..."></textarea>
                </div>
                <div class="row">
                    <div class="col-md-6" v-if="this.boardSeq == null">
                        <button type="button" class="btn btn-success btn-block" v-on:click="boardInsert">글 등록</button>
                    </div>
                    <div class="col-md-6" v-else-if="this.boardSeq != null">
                        <button type="button" class="btn btn-success btn-block" v-on:click="boardUpdate">수정하기</button>
                    </div>
                    <div class="col-md-6">
                        <button type="button" class="btn btn-secondary btn-block" v-on:click="moveMain">취소</button>
                    </div>
                </div>
            </div>
        </div>
        <footer-ui></footer-ui>
    </div>
</template>

<script>
import Header from '../components/Header.vue'
import Footer from '../components/Footer.vue'
import {mapState} from 'vuex'
export default {
    components: {
        'header-ui': Header,
        'footer-ui': Footer
    },
    data() {
        return {
            cnt: '',
            content: '',
            image : null,
            previewImg: '',
            boardSeq: null,
            boardUserSeq: null
        }
    },
    computed: {
        ...mapState(["userInfo"])
    },
    mounted() {
        this.$store.dispatch("getUserInfo")
        if (this.$route.query) {
            this.boardSeq = this.$route.query.boardSeq
            this.boardUserSeq = this.$route.query.userSeq
        }
        if (this.boardUserSeq == this.userInfo.userSeq) {
            let obj = this
            obj.$axios.get("http://localhost:9000/board/getReWriteBoard", {
                params: {
                    boardSeq: obj.boardSeq
                }
            })
            .then(function (res) {
                obj.content = res.data.content
                obj.image = res.data.imgList
            })
            .catch(function (err) {
                console.log(err)
                console.log('컨텐츠 요청 실패')
            })
        }
    },
    methods: {
        boardInsert() {
            let formData = new FormData();
            // console.log("test : " + this.image);
            // console.log("test : " + this.image.length);
            if (this.image != null && this.content != null) {
                for (let i = 0; i < this.image.length; i++) {
                    formData.append("image", this.image[i]);
                    console.log('for문 안의 form : '+formData)
                }
                // console.log('for문 밖의 form : '+formData)
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
                obj.$router.replace({ path: '/mypage' });
            })
            .catch(function(err) {
                console.log("통신 실패");
                console.log(err);
            });
            }
        },
        boardUpdate() {
            let formData = new FormData();
            console.log("test : " + this.image);
            console.log("test : " + this.image.length);
            if (this.image != null && this.content != null) {
                for (let i = 0; i < this.image.length; i++) {
                    formData.append("image", this.image[i]);
                    // console.log('for문 안의 form : '+formData[i])
                }
                // console.log('for문 밖의 form : '+formData)
            let obj = this
            obj.$axios.post('http://localhost:9000/board/boardUpdate', formData, {
                params: {
                    boardSeq: obj.$route.query.boardSeq,
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
                obj.$router.replace({ path: '/mypage' });
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
            this.cnt = this.image.length
            console.log(image.target.files)
                var cntlen = this.image.length
                var previewImgUrl = []
                for (let i = 0; i < cntlen; i++) {
                    previewImgUrl[i] = (URL.createObjectURL(image.target.files[i]))
                }
                this.previewImg = previewImgUrl
        },
        moveMain() {
            this.$router.push({ name: 'MyPage' });
        }
    },
}
</script>

<style scoped>
    .main-container {
        width: 100%;
        height: 100vh;
        background-color: rgb(250, 250, 250);
    }

    .content-container {
        max-width: 935px;
        position: relative;
        display: flex;
        flex-wrap: wrap;
        margin: 0 auto;
        padding-top: 50px;
    }
    .input-container {
        width: 500px;
        margin-top: 90px;
        margin-left: auto;
        margin-right: auto;
    }

    .img-container {
        display: flex;
        /* width: 500px; */
        margin-bottom: 30px;
        overflow: scroll;
    }

    .image-input {
        width: 150px;
        height: 150px;
        background-color: #ffff;
        border: 1px solid #cfcfcf;
        text-align: center;
    }

    .input-btn {
        width: 150px;
        height: 150px;
        margin: 0;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
    }

    .input-icon {
        width: 25px;
        height: 25px;
    }

    .count {
        font-size: 14px;
    }

    .preview-container {
        display: flex;
        /* flex-wrap: nowrap; */
        /* width: 350px; */
        /* overflow: scroll; */
    }

    .preview-img-box {
        width: 150px;
        height: 150px;
        margin-left: 15px;
        overflow: hidden;
        border: 1px solid #cfcfcf;

    }

    .preview-img-box img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }



</style>
