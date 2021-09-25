<template>
    <div class="wrap">
        <div class="sidebar-title">
            <span>내 팔로우 목록</span>
        </div>
        <div class="user-list" v-if="followList">
            <div class="user-info" v-for="(item, index) in this.followList" v-bind:key="index">
                <div class="user-info-left">
                    <div class="user-icon">
                        <div class="user-img" v-if="item.storedImgPath != null">
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
                <div class="follow-btn" v-on:click="disFollow(item.userSeq, index)">
                    언팔로우
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {mapState, mapGetters, mapActions} from 'vuex'
export default {
    data() {
        return {
            // followList: ''
        }
    },
        computed: {
            ...mapGetters(['myUserInfo']),
            ...mapState(['followList'])
    },
    async mounted() {
        await this.getUserInfo()
        await this.getFollowList()
    },
    methods: {
        ...mapActions(['getUserInfo', 'getFollowList']),
        disFollow(userSeq, index) {
            let obj = this
            let idx = index
            obj.$axios.post("http://localhost:9000/user/disFollow", {}, {
                params: {
                    toUserSeq: userSeq,
                    fromUserSeq: obj.myUserInfo.userSeq
                }
            })
            .then(function() {
                console.log('팔로우 취소 성공')
                obj.followList.splice(idx, 1);
                // obj.$router.go()
            })
            .catch(function (err) {
                console.log(err)
                console.log('팔로우 취소 실패')
            })
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