<template>
    <div class="main-container">
        <header-ui></header-ui>
        <modal-ui v-if="$store.state.modalOpen"></modal-ui>
        <main>
        <insert-modal v-if="$store.state.profileModalOpen"></insert-modal>
            <div class="content-container">
                <div class="profile-header">
                    <div class="profile-box" v-on:click="clickProfileModal({isModal: true})">
                        <div class="profile-imgbox" v-if="myUserInfo">
                            <img v-bind:src="myUserInfo.profileImg" alt="프사">
                        </div>
                    </div>
                    <div class="profile-info-box">
                        <div class="nick-name" v-if="myUserInfo">
                            <p class="m-0">{{myUserInfo.userNick}}</p>
                        <span class="profile-rewrite" v-on:click="clickProfileModal({isModal: true})">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </span>
                        </div>
                        <div class="user-id" v-if="myUserInfo">
                            <p>{{myUserInfo.userId}}</p>
                        </div>
                        <div class="content-count"  v-if="myUserInfo">
                            <div>
                                게시물 <span>{{myUserInfo.writeCnt}}</span>
                            </div>
                            <div>
                                팔로우 <span>80</span>
                            </div>
                            <div>
                                팔로워 <span>120</span>
                            </div>
                        </div>
                    </div>
                    <div class="write-btn" type="button" v-on:click="moveInsert">
                        <i class="fa fa-pencil" aria-hidden="true"></i>
                    </div>
                </div>



                <div class="content-box">
                    <div class="content-card" v-for="item in myBoardList" v-bind:key="item.boardSeq">
                        <div class="content-img">
                            <div class="hidden-box">
                                <div type="button" class="delete-icon"  v-on:click="clickModal({isModal:true, modalSeq: item.userSeq, followCheck: item.followCheck, boardSeq: item.boardSeq})">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
                                        <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
                                    </svg>
                                </div>
                                <div class="center-icon">
                                    <div class="like">
                                        <i class="fa fa-heart-o hidden-icon" aria-hidden="true"><span
                                                class="count">{{item.likeCnt}}</span></i>
                                    </div>
                                    <div class="comment">
                                        <i class="fa fa-comment-o" aria-hidden="true"><span class="count">1</span></i>
                                    </div>
                                </div>
                            </div>
                            <img v-bind:src="'http://localhost:9000/'+item.imgList[0].storedImgPath" alt="썸네일">
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <footer-ui></footer-ui>
    </div>
</template>

<script>
import Header from '../components/Header'
import Footer from '../components/Footer'
import ProfileInsert from '../views/ProfileInsert.vue'
import moreModal from '../views/moreModal'
import {mapState, mapActions, mapGetters} from 'vuex'
export default {
    name: 'MyPage',
    components: {
        'header-ui' : Header,
        'footer-ui' : Footer,
        'insert-modal' : ProfileInsert,
        'modal-ui' : moreModal
    },
    data() {
        return {
            // myBoardList: ''
            // userSeq: this.$store.state.userInfo.userSeq
            // myBoardList: this.$store.state.myBoardList
        }
    },
    async mounted() {
        console.log("mypage mounted 111");
        await this.getUserInfo()
        this.getMyBoardList()
        console.log("mypage mounted 222");
    },
    computed: {
        ...mapState(['myBoardList', 'modalOpen', 'profileModalOpen']),
        ...mapGetters(['myUserInfo'])
    },
    methods: {
        ...mapActions(['clickModal', 'getMyBoardList', 'getUserInfo', 'clickProfileModal']),
        moveInsert() {
            this.$router.push({name: 'Insert'})
        },
    },
}
</script>

<style scoped>
    @media (max-width: 450px) {
        .content-count {
            flex-direction: column !important;
        }
    }
    @media (max-width: 750px) {
        .profile-box {
            margin-right: 15px !important;
        }
        .profile-imgbox {
            width: 77px;
            height: 77px;
            margin-left: 0;
            transition: .2s;
        }

        .content-box {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .content-card {
            width: 300px !important;
            height: 300px !important;
            transition: .2s;
        }
    }
    .main-container {
        padding-bottom: 70px;
        margin-top: 50px;
        background-color: rgb(250, 250, 250);
        height: 100%;
    }
    .content-container {
        max-width: 935px;
        /* display: flex;
        flex-wrap: wrap; */
        margin: 20px auto;
        padding-top: 25px;
    }

    .profile-header {
        display: flex;
        width: 100%;
        padding-bottom: 44px;
        border-bottom: 1px solid lightgray;
    }

    .profile-box {
        display: flex;
        margin-right: 50px;
        cursor: pointer;
    }

    .profile-imgbox:hover {
        box-shadow: 1px 1px 5px 1px lightgray;
    }

    .profile-imgbox {
        width: 150px;
        height: 150px;
        border-radius: 180px;
        overflow: hidden;
        border: 1px solid rgb(221, 221, 221);
        /* margin-left: 50px; */
    }

    .profile-imgbox img {
        object-fit: cover;
        width: 100%;
        height: 100%;
    }

    .profile-info-box {
        display: flex;
        flex-direction: column;
        justify-content: center;
        width: 70%;
    }

    .nick-name {
        display: flex;
        align-items: center;
        font-size: 24px;
        font-weight: bold;
    }
    
    .profile-rewrite {
        margin-left: 10px;
        font-size: 15px;
        padding: 6px 12px;
        color: #5e5e5e;
        cursor: pointer;
    }

    .profile-rewrite:hover {
        transform: scale(1.1);
    }

    .user-id {
        font-size: 14px;
        color: #5e5e5e;
    }

    .content-count {
        display: flex;
        flex-wrap: wrap;
    }

    .content-count span {
        font-weight: bold;
        margin-right: 12px;
    }

    .write-btn {
        /* display: flex; */
        font-size: 30px;
        width: 60px;
        height: 60px;
        padding: 10px 5px;
        color: #ff9064;
        background-color: #ffff;
        border-radius: 180px;
        text-align: center;
        align-self: flex-end;
    }

    .fa-pencil:hover {
        text-shadow: 1px 1px 15px #ffceba;
        transform: scale(.9);
    }


    .content-box {
        display: flex;
        flex-wrap: wrap;
        width: 100%;
        justify-content: space-between;
    }

    .content-card {
        width: 280px;
        height: 280px;
        margin-top: 30px;
        border: 1px solid lightgray;
        border-radius: 4px;
        overflow: hidden;
        box-shadow: 4px 12px 30px 5px rgb(0 0 0 / 15%);
        position: relative;
    }

    .content-card:hover {
        box-shadow: 4px 12px 30px 6px rgb(0 0 0 / 30%);
        transition: .1s;
        transform: scale(1.01);
    }

    .hidden-box {
        display: flex;
        width: 100%;
        height: 100%;
        position: absolute;
        background-color: rgba(0, 0, 0, 35%);
        top: 100%;
    }

    .center-icon {
        display: flex;
        width: 100%;
        color: #ffff;
        font-size: 26px;
        align-self: center;
        justify-content: center;
    }

    .count {
        font-size: 20px;
    }

    .like {
        margin-right: 15px;
    }

    .count {
        margin-left: 10px;
    }

    .delete-icon {
        position: absolute;
        height: 32px;
        width: 32px;
        right: 20px;
        top: 5px;
        color: #ffff;
        font-size: 32px;
        text-align: center;
        justify-items: center;
    }

    .content-card:hover .hidden-box {
        width: 100%;
        height: 100%;
        position: absolute;
        transition: .35s;
        top: 0;
        cursor: pointer;
    }

    .content-img {
        width: 100%;
        height: 100%;
        overflow: hidden;
    }

    .content-card img {
        width:100%;
        height:100%;
        object-fit:cover;
    }
</style>