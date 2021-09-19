<template>
    <div class="modal-wrap" v-on:click.self="clickModal(false)">
        <div class="modal-container">
            <div class="modal-content">
                <div class="modal-item" v-if="modalSeq != userInfo.userSeq && followCheck == 0">팔로우</div>
                <div class="modal-item danger-btn" v-if="modalSeq != userInfo.userSeq  && followCheck == 1">팔로우 취소</div>
                <div class="modal-item" v-if="modalSeq != userInfo.userSeq">가리기</div>
                <div class="modal-item" v-if="modalSeq == userInfo.userSeq">수정하기</div>
                <div class="modal-item danger-btn" v-if="modalSeq == userInfo.userSeq">삭제하기</div>
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
    computed: {
        ...mapState(['userInfo', 'followCheck']),
        ...mapGetters(['modalSeq'])
    },
    methods: {
        ...mapActions(['clickModal']),
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
