<template>
<div>
    <div class="main-container">
        <modal-ui v-if="modalOpen"></modal-ui>
        <header-ui></header-ui>
        <div class="content-container">
            <div class="follower-section">
                <div class="follower-info" v-for="(item, index) in topUserList" v-bind:key="index">
                    <div class="follower-img" v-if="item.storedImgPath">
                        <img v-bind:src="'http://localhost:9000/'+item.storedImgPath" alt="팔로워 이미지">
                    </div>
                    <div class="follower-img" v-if="!item.storedImgPath">
                        <img src="http://localhost:9000/images/default_img.jpeg" alt="유저이미지">
                    </div>
                    <div class="follower-name">
                        <p class="m-0">{{item.userNick}}</p>
                    </div>
                </div>
            </div>

                <div class="content-section">
                    <div class="content-body" v-for="(item, index) in allBoardList" v-bind:key="index">
                        <div class="content-title">
                            <div class="user-info">
                                <div class="user-icon">
                                    <div class="user-img" v-if="item.userImg">
                                        <img v-bind:src="'http://localhost:9000/' + item.userImg" alt="유저이미지">
                                    </div>
                                    <div class="user-img" v-if="!item.userImg">
                                        <img src="http://localhost:9000/images/default_img.jpeg" alt="유저이미지">
                                    </div>
                                </div>
                                <div class="user_id">
                                    {{item.userNick}}
                                </div>
                            </div>
                            <div type="button" class="more-btn" v-on:click="clickModal({isModal:true, modalSeq: item.userSeq, followCheck: item.followCheck, boardSeq: item.boardSeq, idx: index})">
                                ...
                            </div>
                        </div>

                        <!-- <div class="content-img" v-for="items in item.imgList" v-bind:key="items.imgSeq">
                            <img v-bind:src="'http://localhost:9000/'+items.storedImgPath" alt="">
                        </div> -->
                        <v-carousel hide-delimiter-background show-arrows-on-hover >
                            <v-carousel-item v-for="(items, index) in item.imgList" :key="index" 
                            :src="'http://localhost:9000/'+items.storedImgPath">
                            </v-carousel-item>
                        </v-carousel>
                        <div class="btn-section">
                            <div class="like-btn like" v-if="item.likeNy == 0" v-on:click="likeOn(item.boardSeq, index)">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-heart mr-3" viewBox="0 0 16 16">
                                    <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                                </svg>
                            </div>
                            <div class="disLike-btn like" v-else v-on:click="likeOn(item.boardSeq, index)">
                                
                                
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-heart-fill mr-3 heart-on" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
                                </svg>
                            </div>
                            <div class="comment-btn">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-chat mr-3" viewBox="0 0 16 16">
                                    <path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
                                </svg>
                            </div>
                        </div>
                        <div class="like-count">
                            <p class="m-0 mr-2">좋아요</p>
                            <span class="">{{item.likeCnt}}</span>
                            <span>개</span>
                        </div>
                        <div class="content">
                            <div class="user_id mr-2">
                                {{item.userNick}}
                            </div>
                            <div class="content-text">
                                <p class="m-0">{{item.content}}</p>
                            </div>
                        </div>
                        <div class="comment-count">
                            <p class="m-0 mr-1">댓글</p>
                            <span>{{item.replyList.length}}</span>
                            <span>개 모두 보기</span>
                        </div>
                        <div class="comment-section" v-if="item.replyList.length >= 3">
                            <div class="comment-box" v-for="(item, index) in item.replyList.slice(item.replyList.length-3, item.replyList.length)" v-bind:key="index">
                                <div class="user_id mr-2" v-on:click="clickModal({isModal:true, modalSeq:item.userSeq, replyModal: true, replySeq: item.replySeq, boardSeq: item.boardSeq})">
                                    {{item.userNick}}
                                </div>
                                <div class="comment">
                                    <p class="m-0">{{item.content}}</p>
                                </div>
                            </div>
                        </div>
                        <div class="comment-section" v-if="item.replyList.length < 3">
                            <div class="comment-box" v-for="(item, index) in item.replyList" v-bind:key="index">
                                <div class="user_id mr-2" v-on:click="clickModal({isModal:true, modalSeq:item.userSeq, replyModal: true, replySeq: item.replySeq, boardSeq: item.boardSeq})">
                                    {{item.userNick}}
                                </div>
                                <div class="comment">
                                    <p class="m-0">{{item.content}}</p>
                                </div>
                            </div>
                        </div>
                        <div class="create-date">
                            <p class="m-0">{{item.createDt}}</p>
                        </div>
                        <div class="comment-input-box">
                            <input class="input-text m-0" type="text" name="comment" id="comment" placeholder="댓글 달기 ..." autocomplete="off" @input="reply=$event.target.value">
                            <div class="submit-btn" type="button"  v-on:click="inputReply({content: reply, boardSeq: item.boardSeq, userSeq: userInfo.userSeq, userNick: userInfo.userNick, idx: index})">
                                작성
                            </div>
                        </div>
                    </div>
                    <infinite-loading @infinite="infiniteHandler" spinner="circles">
                        <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">더 이상 목록이 없습니다.</div>
                    </infinite-loading>
                </div>

                <div class="myinfo-container">
                    <div class="myinfo-section">
                        <router-view></router-view>
                    </div>
                </div>
        </div>
        <footer-ui></footer-ui>
    </div>

    </div>
</template>

<script>
import Header from '../components/Header'
import Footer from '../components/Footer'
import moreModal from '../views/moreModal'
import InfiniteLoading from 'vue-infinite-loading';
// import SideBar from '../views/SideBar.vue'
import {mapState, mapActions, mapMutations} from 'vuex'
export default {
    name: 'Main',
    components: {
        'header-ui' : Header,
        'footer-ui' : Footer,
        'modal-ui' : moreModal,
        InfiniteLoading
        // 'sidebar-ui' : SideBar
    },
    data() {
        return {
            topUserList: '',
            reply: '',
            startNum: 0,
        }
    },
    async created() {
        let obj = this
        await obj.getUserInfo()
        // await obj.getAllBoardList()
        await obj.getTopUserList()
        await obj.infiniteHandler
    },
    computed: {
        ...mapState(['userInfo', 'allBoardList', 'modalOpen'])
    },
    methods: {
        ...mapActions(['clickModal', 'getUserInfo', 'getAllBoardList']),
        ...mapMutations(['isAddReply']),
        moveInsert() {
            this.$router.push({
                name: 'Insert'
            });
        },
        likeOn(boardSeq, index) {
            let obj = this
            let idx = index;
            obj.$axios.post("http://localhost:9000/board/likeOnOf", {}, {
                params: {
                    userSeq: obj.userInfo.userSeq,
                    boardSeq
                }
            })
            .then(function (res) {
                console.log('좋아요 전송 성공')
                console.log(res.data)
                obj.allBoardList[idx].likeNy = res.data
                if (res.data == 1) {
                    obj.allBoardList[idx].likeCnt++
                }
                else {
                    obj.allBoardList[idx].likeCnt--
                }
            })
            .catch(function (err) {
                console.log(err)
                console.log('좋아요 전송 실패')
            })
        },
        async getTopUserList() {
            let obj = this
            obj.$axios.get("http://localhost:9000/user/getTopUserList")
            .then(function (res) {
                console.log('인기유저 리스트 요청 성공')
                obj.topUserList = res.data
            })
            .catch(function (err) {
                console.log('인기유저 리스트 요청 실패')
                console.log(err)
            })
        },
        inputReply(dataList) {
            if (dataList.content != null && dataList.content != '') {
                let obj = this
                obj.$axios.post("http://localhost:9000/board/inputReply",
                    dataList
                )
                .then(function (res) {
                    console.log('댓글 작성 요청 성공')
                    let payload = {
                        idx: dataList.idx,
                        reply: res.data
                    }
                    obj.isAddReply(payload)
                    obj.clearInput()
                })
                .catch(function (err) {
                    console.log(err)
                    console.log('댓글 작성 요청 실패')
                })
            }
        },
        clearInput() {
            var el = document.getElementsByClassName('input-text');
            for(var i=0; i<el.length; i++){
                el[i].value = '';
            }
        },
        async infiniteHandler($state) {
            if (this.allBoardList.length%5 == 0) {
                let obj = this;
                obj.$axios.post("http://localhost:9000/board/getAllBoardList", {}, {
                    params: {
                        userSeq: obj.userInfo.userSeq,
                        startNum: obj.startNum
                    },
                }).
                then(function (res) {
                    if (res.data.length > 0) {
                        setTimeout(() => {
                        obj.startNum += 5;
                        // obj.$store.commit('isAllBoardList', res.data)
                        // obj.allBoardList.push(res.data);
                        for (let i = 0; i < res.data.length; i++) {
                            // obj.allBoardList.push(res.data[i]);
                            obj.$store.commit('isAllBoardList', res.data[i])
                        }
                        // obj.list.push(res.data);
                        $state.loaded()
                    }, 1000)} else {
                        $state.complete();
                    }
                });
            }
            else {
                $state.complete();
            }
        }
        },
    }

</script>

<style scoped>
    @media (max-width: 450px) {

    }
    @media (max-width: 735px) {
        .myinfo-container {
            display: none !important;
        }
        .content-container {
            justify-content: center;
        }
    }

    .main-container {
        width: 100%;
        /* height: 100vh; */
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

    .follower-section {
        display: flex;
        width: 65%;
        height: 110px;
        min-width: 350px;
        border: 1px solid #e0e0e0;
        margin: 25px 0;
        padding: 6px 16px;
        overflow: scroll;
        background-color: #ffff;
    }

    .follower-info {
        display: flex;
        flex-direction: column;
        width: 60px;
        height: 88px;
        align-items: center;
        justify-content: space-between;
        margin: 3px 12px;
        /* height: 60px; */
    }
    .follower-info:hover {
        cursor: pointer;
    }

    .follower-img {
        width: 60px;
        height: 60px;
        border-radius: 180px;
        border: 3px solid rgb(255, 144, 100);
        padding: 1px;
        overflow: hidden;
    }

    .follower-img img {
        width: 100%;
        height: 100%;
        border-radius: 180px;
        object-fit: cover;
    }

    .follower-name {
        width: 66px;
        font-size: 12px;
        margin-top: 10px;
        text-align: center;
    }

    .follower-name p {
        white-space:nowrap; 
        overflow: hidden; 
        text-overflow: ellipsis;
    }

    .content-section {
        /* display: flex;
        flex-direction: column; */
        width: 65%;
        min-width: 350px;
        margin-bottom: 100px;
    }

    .myinfo-container {
        position: absolute;
        right: 0;
        /* width: 30%; */
        min-width: 250px;
        height: 100vh;
        /* height: 100%; */
        /* border: 1px solid red; */
    }

    .myinfo-section {
        display: flex;
        position: fixed;
        width: 250px;
        /* height: 400px; */
        margin: 25px 0;
        /* border: 1px solid red; */
    }

    .content-body {
        display: flex;
        flex-direction: column;
        width: 100%;
        margin-bottom: 20px;
        background-color: #ffff;
        border: 1px solid #e0e0e0;
    }

    .user-info {
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
        font-size: 14px;
        font-weight: bolder;
        min-width: fit-content;
    }

    .user_id:hover {
        text-decoration: underline;
        text-underline-position: under;
        cursor: pointer;
    }

    .content-title {
        display: flex;
        width: 100%;
        padding: 12px 16px;
        justify-content: space-between;
        border-bottom: 1px solid #e0e0e0;
        background-color: #ffff;
    }

    .content-img {
        width: 100%;
        overflow: hidden;
    }

    .content-img img{
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .btn-section {
        display: flex;
        width: 100%;
        padding: 12px 16px 4px;
    }

    .like {
        cursor: pointer;
    }

    .heart-on {
        color: rgb(255, 0, 0);
    }

    .like-count {
        display: flex;
        padding: 0 16px;
        font-size: 15px;
    }

    .content {
        display: flex;
        width: 100%;
        padding: 0 16px 12px;
        align-items: center;
    }

    .content-text {
        font-size: 12px;
        min-width: 200px;
        max-width: 600px;

    }
    .content-text p {
        /* width: 300px; */
        white-space:nowrap; 
        overflow: hidden; 
        text-overflow: ellipsis;
    }

    .comment-count {
        display: flex;
        width: 100%;
        padding: 0 16px 2px;
        font-size: 14px;
        color: rgb(124, 124, 124);
    }

    .comment-section {
        /* height: 70px; */
        overflow: hidden;
    }

    .comment-box {
        display: flex;
        align-items: center;
        padding: 0 16px 2px;
        width: 100%;
        overflow: hidden;
    }

    .comment {
        font-size: 12px;
        min-width: 200px;
        max-width: 600px;
    }

    .comment p {
        width: 100%;
        white-space:nowrap; 
        overflow: hidden; 
        text-overflow: ellipsis;
    }

    .create-date {
        font-size: 12px;
        color: rgb(124, 124, 124);
        padding: 5px 16px 4px;
    }

    .comment-input-box {
        padding: 4px 16px;
        border-top: 1px solid #e0e0e0;
        margin-top: 5px;
        font-size: 14px;
        display: flex;
        align-items: center;
    }

    #comment {
        border-style: none;
    }

    .submit-btn {
        width: 30px;
        text-align: center;
        align-items: center;
        color: rgb(255, 72, 0);
    }

    .submit-btn:hover {
        cursor: pointer;
    }

</style>
