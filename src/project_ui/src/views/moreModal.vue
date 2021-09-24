<template>
    <div class="modal-wrap" v-on:click.self="clickModal(false)">
        <div class="modal-container">
            <div class="modal-content">
                <div class="modal-item" v-if="modalSeq != userInfo.userSeq && follow == 0" v-on:click="addFollow(modalSeq)">팔로우</div>
                <div class="modal-item danger-btn" v-if="modalSeq != userInfo.userSeq  && follow == 1" v-on:click="disFollow">팔로우 취소</div>
                <div class="modal-item" v-if="modalSeq == userInfo.userSeq" v-on:click="moveReWrite">수정하기</div>
                <div class="modal-item danger-btn" v-if="modalSeq == userInfo.userSeq" v-on:click="deleteBoard">삭제하기</div>
                <div class="modal-item danger-btn" v-on:click="clickModal(false)">취소</div>
            </div>
        </div>
    </div>
</template>
<script>
import {mapState, mapActions, mapGetters} from 'vuex'
export default {
    data() {
        return {

        }
    },
    mounted() {
    },
    computed: {
        ...mapState(['userInfo', 'followCheck', 'modalBoardSeq', 'followList']),
        ...mapGetters(['modalSeq']),
        follow() {
            let obj = this
            var cnt = 0
            this.followList.forEach(function(follow) {
                if (follow.userSeq == obj.modalSeq) {
                    cnt++
                }
            })
            return cnt;
        },
        disFollowIndex() {
            return this.followList.indexOf(this.modaSeq)
        }
    },
    methods: {
        ...mapActions(['clickModal']),

        deleteBoard() {
            let obj = this
            console.log('boardSeq1 : '+obj.modalBoardSeq)
            let delBoardSeq = obj.modalBoardSeq
            console.log('boardSeq2 : '+delBoardSeq)
            obj.$axios.post("http://localhost:9000/board/deleteBoard", {}, {
                params: {
                    delBoardSeq
                }
            })
            .then(function () {
                console.log('삭제 성공')
                obj.$router.go()
                console.log('innerSeq : '+delBoardSeq)
            })
            .catch(function (err) {
                console.log(err)
                console.log('삭제 실패')
                console.log('innerSeq : '+delBoardSeq)
            })
        },
        moveReWrite() {
            this.$router.push({name: 'Insert', query:{boardSeq: this.modalBoardSeq, userSeq: this.modalSeq}})
            this.clickModal(false)
        },
        addFollow(modalSeq) {
            if (this.follow == 0) {
                let obj = this
                let seq = modalSeq
                obj.$axios.post("http://localhost:9000/user/addFollow", {}, {
                    params: {
                        fromUserSeq: obj.userInfo.userSeq,
                        toUserSeq: seq,
                    }
                })
                .then(function(res) {
                    console.log('팔로우 요청 성공')
                    obj.$store.commit('addFollow', res.data)
                })
                .catch(function (err) {
                    console.log(err)
                    console.log('팔로우 요청 실패')
                })
            }
        },
        disFollow() {
            let obj = this
            obj.$axios.post("http://localhost:9000/user/disFollow", {}, {
                params: {
                    toUserSeq: obj.modalSeq,
                    fromUserSeq: obj.userInfo.userSeq
                }
            })
            .then(function() {
                console.log('팔로우 취소 성공')
                obj.$store.commit('disFollow', obj.disFollowIndex)
            })
            .catch(function (err) {
                console.log(err)
                console.log('팔로우 취소 실패')
            })
        },

    

}
}
</script>

<style scoped>
    .modal-wrap {
        z-index: 11;
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 65%);
    }

    .modal-container {
        position: fixed;
        display: flex;
        max-width: 250px;
        min-width: 250px;
        /* height: 350px; */
        /* padding: 28px 16px; */
        top: 50%;
        left: 50%;
        transform:translate(-50%, -50%);
        background-color: rgb(250, 250, 250);
        border-radius: 4px;
    }

    .modal-content {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .modal-item {
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 15px;
        height: 45px;
        border-bottom: 1px solid #e0e0e0;
    }
    .modal-item:hover {
        background-color: #f8f6f6;
    }
    .danger-btn {
        color: red;
    }
</style>
