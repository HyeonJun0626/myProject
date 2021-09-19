<template>
    <div class="modal-wrap" v-if="modal" v-on:click.self="clickModal(false)">
        <div class="modal-body">
            <div class="preview-box">
                <img v-if="previewImgUrl" :src="previewImgUrl">
                <!-- <img v-bind:src="previewImgUrl"> -->
                <label class="image-btn" for="img"></label>
            </div>
            <div class="form-group">
                <input style="display:none" type="file" name="img" id="img" class="form-control" accept="image/*" v-on:change="imgIn" placeholder="이미지를 등록하세요">
            </div>
            <div class="form-group">
                <label for="userNick">닉네임: {{userInfo.userNick}}</label>
                <input type="text" name="userNick" id="userNick" class="form-control" v-model="reNick" autocomplete="off">
            </div>
            <button type="button" class="btn btn-success btn-block" v-on:click="profileInsert">수정하기</button>
            <button type="button" class="btn btn-success btn-block" v-on:click="clickModal(false)">닫기</button>
            <div class="gonyang">
                <p class="m-0">Gonyang</p>
            </div>
        </div>
    </div>
</template>

<script>
import {mapState, mapActions, mapGetters} from 'vuex'

export default {
    data() {
        return {
            image: null,
            reNick: null,
            previewImgUrl: null
            // previewImgUrl: this.userInfo.profileImg

        }
    },
    computed: {
        ...mapState (['userInfo', 'modalOpen']),
    },
    methods: {
        ...mapActions(['getUserInfo', 'clickModal']),
        ...mapGetters(['modal', 'myUserProfileImg']),
        profileInsert() {
            if(this.image != "") {
                console.log(this.image)
            }
            let formData = new FormData()
            formData.append("image", this.image)
            // formData.append("userSeq", this.$store.state.userInfo.userSeq)
            let obj = this
            obj.$axios.post('http://localhost:9000/board/profileInsert', formData,{
                params: {
                    userSeq: this.$store.state.userInfo.userSeq,
                    userNick: this.reNick
                },  
                headers: {
                    "Content-Type": "multipart/form-data",
                }
            })
            .then(function() {
                console.log("통신 성공")
                obj.getInfo()
                // obj.closeInput()
                obj.clickModal(false)
            })
            .catch(function(err) {
                console.log(err)
                console.log("통신 실패")
            })
        },
        closeInput() {
            this.$router.replace({path: '/mypage'})
        },
        getInfo() {
            this.$store.dispatch('getUserInfo')
        },
        imgIn(image) {
            this.image = image.target.files[0]
            console.log(image.target.files)
            var reader = new FileReader()
            reader.onload = e => {
                this.previewImgUrl = e.target.result
            }
            reader.readAsDataURL(this.image)
    }
}
}
</script>

<style scoped>
    .modal-wrap {
        z-index: 11;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 65%);
    }

    .modal-body {
        position: fixed;
        display: flex;
        flex-direction: column;
        max-width: 400px;
        min-width: 320px;
        height: 450px;
        padding: 28px 16px;
        top: 50%;
        left: 50%;
        transform:translate(-50%, -50%);
        background-color: rgb(250, 250, 250);
        border-radius: 4px;
    }

    .preview-box {
        position: relative;
        width: 150px;
        height: 150px;
        overflow: hidden;
        border-radius: 180px;
        border: 1px solid rgb(221, 221, 221);
        align-self: center;
    }

    .preview-box:hover {
        box-shadow: 1px 1px 5px 1px lightgray;
    }

    .preview-box img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .image-btn {
        /* display: inline-block; */
        width: 100%;
        height: 100%;
        cursor: pointer;
    }

    .gonyang {
        font-size: 12px;
        margin-top: 8px;
        align-self: center;
        color: rgb(63, 63, 63);
    }
</style>