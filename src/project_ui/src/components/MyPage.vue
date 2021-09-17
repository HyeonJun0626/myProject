<template>
    <div>
        <header-ui></header-ui>
        <main>
        <insert-modal v-if="$store.state.modalOpen"></insert-modal>
            <div class="content-container">
                <div class="profile-header">
                    <div class="profile-box" v-on:click="clickModal(true)">
                        <div class="profile-imgbox">
                            <img v-bind:src="userInfo.profileImg" alt="프사">
                        </div>
                    </div>
                    <div class="profile-info-box">
                        <div class="nick-name">
                            <p class="m-0">{{userInfo.userNick}}</p>
                        <span class="profile-rewrite" v-on:click="clickModal(true)">
                            <i class="fa fa-cog" aria-hidden="true"></i>
                        </span>
                        </div>
                        <div class="user-id">
                            <p>{{userInfo.userId}}</p>
                        </div>
                        <div class="content-count">
                            <div>
                                게시물 <span>12</span>
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
                                <div type="button" class="delete-icon">
                                    <i class="fa fa-times" aria-hidden="true"></i>
                                </div>
                                <div class="center-icon">
                                    <div class="like">
                                        <i class="fa fa-heart-o hidden-icon" aria-hidden="true"><span
                                                class="count">2</span></i>
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
import {mapState, mapActions, mapGetters} from 'vuex'
export default {
    name: 'Mypage',
    components: {
        'header-ui' : Header,
        'footer-ui' : Footer,
        'insert-modal' : ProfileInsert
    },
    data() {
        return {
            myBoardList: '',
            // userSeq: this.$store.state.userInfo.userSeq
        }
    },
    mounted() {
        let obj = this
        obj.$axios.get("http://localhost:9000/board/getBoardList", {
            params: {
                userSeq: obj.$store.state.userInfo.userSeq,
            }
        })
        .then(function (res) {
            console.log("통신 성공")
            obj.myBoardList = res.data
            console.log(obj.myBoardList);
        })
        .catch(function (err) {
            console.log(err)
            console.log("통신 실패")
        })
    },
    computed: {
        ...mapState(['userInfo'], 'modalOpen'),
    },
    methods: {
        ...mapGetters(['modal']),
        ...mapActions(['clickModal']),
        moveInsert() {
            this.$router.push({name: 'Insert'})
        }
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
    main {
        padding-bottom: 70px;
        margin-top: 70px;
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
        width: 250px;
        height: 250px;
        margin-top: 30px;
        border: 1px solid lightgray;
        border-radius: 4px;
        overflow: hidden;
        box-shadow: 4px 12px 30px 5px rgb(0 0 0 / 15%);
        position: relative;
    }

    .content-card:hover {
        box-shadow: 4px 12px 30px 6px rgb(0 0 0 / 30%);
        transition: .3s;
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