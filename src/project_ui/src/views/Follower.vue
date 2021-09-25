<template>
    <div class="wrap">
        <div class="sidebar-title">
            <span>나를 팔로우한 친구</span>
        </div>
        <div class="user-list">
            <div class="user-info" v-for="(item, index) in followerList" v-bind:key="index">
                <div class="user-info-left">
                    <div class="user-icon">
                        <div class="user-img" v-if="item.storedImgPath">
                            <img :src="'http://localhost:9000/'+item.storedImgPath" alt="유저이미지">
                        </div>
                        <div class="user-img" v-else>
                            <img src="http://localhost:9000/images/default_img.jpeg" alt="유저이미지">
                        </div>
                    </div>
                    <div class="user_id">
                        {{item.userNick}}
                    </div>
                </div>
                <div class="follow-btn" v-on:click="addFollow({seq: item.userSeq, idx: index})">
                    팔로우
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {mapGetters, mapActions, mapState} from 'vuex'
export default {
    data() {
        return {
        }
    },
    async mounted() {
        await this.getUserInfo()
        await this.getFollowerList()
    },
    computed: {
        ...mapState(['followerList']),
        ...mapGetters(['myUserInfo', 'follow'])
    },
    methods: {
        ...mapActions(['getUserInfo', 'getFollowerList', 'addFollow', 'disFollower']),
        disFollower(idx) {
            this.$store.commit('disFollower', idx)
        }
    }
}
</script>

<style scoped>
    .wrap {
        width: 100%;
        height: 400px;
    }
    
    .sidebar-title {
        font-size: 13px;
        color: rgb(124, 124, 124);
        margin: 10px 0 5px 0;
    }

    .user-list {
        height: 250px;
        overflow: scroll;
    }

    .user-info {
        display: flex;
        align-items: center;
        margin-bottom: 6px;
        justify-content: space-between;
    }

    .user-info-left {
        display: flex;
        align-items: center;
    }

    .user-icon {
        width: 35px;
        height: 35px;
        margin-right: 6px;
    }
    .user-img {
        width: 100%;
        height: 100%;
        border: 1px solid rgb(194, 194, 194);
        border-radius: 180px;
        overflow: hidden;
    }
    .user-img img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .user_id {
        font-size: 13px;
        min-width: fit-content;
    }
    
    .follow-btn {
        font-size: 13px;
        font-weight: bold;
        margin-left: 15px;
        color: rgb(255, 144, 100);
        cursor: pointer;
        justify-self:flex-end;
    }
</style>