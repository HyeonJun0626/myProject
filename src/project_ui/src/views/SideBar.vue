<template>
    <div class="sidebar-wrap">
        <div class="sidebar-container">
            <div class="myinfo-box" v-on:click="moveMyPage">
                <div class="myinfo-img" v-if="myUserInfo">
                    <img class="profile" v-bind:src="myUserInfo.profileImg" alt="프로필">
                </div>
                <div class="user-id-box">
                    <div class="user-id" v-if="myUserInfo">
                        {{myUserInfo.userNick}}
                    </div>
                    <div class="user-email" v-if="myUserInfo">
                        {{myUserInfo.userId}}
                    </div>
                </div>
            </div>

            <div class="sidebar-title">
                <div class="sidebar-btn" v-bind:class="{'active-color': isFollow}" v-on:click="moveFollow">팔로우</div>
                <div class="sidebar-btn" v-bind:class="{'active-color': isFollower}" v-on:click="moveFollower">팔로워</div>
                <!-- <router-link class="sidebar-btn" v-on:click="moveFollow">팔로우</router-link>
                <router-link class="sidebar-btn" v-on:click="moveFollower">팔로워</router-link> -->
            </div>

            <div class="router-section">
                <router-view></router-view>
            </div>
        </div>
            <p class="m-0 my-tag">made by HyeonJun</p>
    </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
    data() {
        return {
            isFollow: true,
            isFollower: false
        }
    },
    mounted() {
        // await this.getUserInfo()
        // await this.getFollowList()
    },
    computed: {
        // ...mapState(['followList']),
        ...mapGetters(['myUserInfo'])
    },
    methods: {
        // ...mapActions(['getUserInfo', 'getFollowList']),
        moveFollow() {
            this.$router.replace({name:"Follow"}).catch(()=>{})
            this.isFollow = true
            this.isFollower = false
        },
        moveFollower() {
            this.$router.replace({name:"Follower"}).catch(()=>{})
            this.isFollow = false
            this.isFollower = true
        },
        moveMyPage() {
            this.$router.push({name:"MyPage"}).catch(()=>{})
            
        }
    }

}
</script>

<style scoped>
    .sidebar-wrap {
        position: relative;
        padding: 6px 0;
        width: 100%;
        height: 420px;
        border-bottom: 1px solid #e0e0e0;
    }
    .sidebar-container {
        width: 100%;
        /* height: 450px; */
        display: flex;
        flex-direction: column;
    }

    .myinfo-box {
        display: flex;
        width: 100%;
        align-items: center;
        padding: 13px 4px;
        
    }

    .myinfo-box:hover{
        cursor: pointer;
        text-decoration: underline;
        text-underline-position: under;
    }
    .myinfo-box:hover .myinfo-img {
        box-shadow: 1px 1px 5px 1px lightgray;
    }

    .myinfo-img {
        width: 55px;
        height: 55px;
        border-radius: 180px;
        overflow: hidden;
        margin-right: 10px;
        border: 1px solid rgb(221, 221, 221);
    }

    .myinfo-img img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .user-id {
        font-size: 14px;
        font-weight: bold;
    }
    .user-email {
        font-size: 12px;
        color: rgb(124, 124, 124);
    }

    .sidebar-title {
        display: flex;
        width: 125px;
        justify-content: space-between;
    }

    .sidebar-btn {
        padding: 6px 12px;
        font-size: 14px;
        
    }

    .sidebar-btn:hover {
        cursor: pointer;
        font-weight: bold;
        /* border-right: 1px solid #e0e0e0; */
        /* border-top: 1px solid #e0e0e0; */
        /* border: 1px solid #e0e0e0;
        border-bottom: none; */
    }

    .router-section {
        width: 100%;
        height: 280px;
        padding: 0 12px;
    }

    .my-tag {
        text-align: center;
        font-size: 12px;
        color: rgb(185, 185, 185);
    }

    .active-color {
        color: rgb(255, 144, 100);
        font-weight: bold;
    }
</style>