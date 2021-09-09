<template>
    <div class="protected">
        <div class="modal-page" v-if="modalShow == true">
            <div v-on:click="modalShow = false" class="close-icon" type="button"><i class="fa fa-times"
                    aria-hidden="true"></i></div>
            <div class="modal-body">
                <div class="modal-img">
                    <img src="../assets/profile1.jpg" alt="상세이미지">
                </div>
                <div class="modal-content">
                    <div class="modal-title">
                        <div type="button" class="header-profile-box">
                            <img src="../assets/profile1.jpg" alt="헤더프로필">
                        </div>
                        <div class="user-nick">
                            <p>roo113</p>
                        </div>
                    </div>
                    <div class="modal-text">
                        <span>여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는
                            본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올
                            위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는
                            본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올
                            위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는 본문이 올 위치여기는
                            본문이 올 위치</span>
                    </div>

                    <div class="modal-reply-menu">
                        <div class="like">
                            <i class="fa fa-heart-o hidden-icon" aria-hidden="true"><span class="count">2</span></i>
                        </div>
                        <div class="comment">
                            <i class="fa fa-comment-o" aria-hidden="true"><span class="count">1</span></i>
                        </div>
                    </div>

                    <div class="modal-reply">
                        <table style="border-collapse: separate; border-spacing: 0 .2rem;">
                            <tr class="reply">
                                <td>
                                    <div type="button" class="reply-profile-box">
                                        <img src="../assets/profile1.jpg" alt="헤더프로필">
                                    </div>
                                </td>
                                <td>
                                    <div class="reply-user-nick">
                                        <p>roo113</p>
                                    </div>
                                </td>
                                <td>
                                    사진이 너무이쁩니다 댓글을 남겨봤어요 이글은 줄바꿈이 어디까지 될까요 ?
                                </td>
                            </tr>
                            <tr class="reply">
                                <td>
                                    <div type="button" class="reply-profile-box">
                                        <img src="../assets/profile1.jpg" alt="헤더프로필">
                                    </div>
                                </td>
                                <td>
                                    <div class="reply-user-nick">
                                        <p>roo113</p>
                                    </div>
                                </td>
                                <td>
                                    사진이 너무이쁩니다 댓글을 남겨봤어요 이글은 줄바꿈이 어디까지 될까요 ?
                                </td>
                            </tr>
                            <tr class="reply">
                                <td>
                                    <div type="button" class="reply-profile-box">
                                        <img src="../assets/profile1.jpg" alt="헤더프로필">
                                    </div>
                                </td>
                                <td>
                                    <div class="reply-user-nick">
                                <td>roo113</td>
                    </div>
                    </td>
                    <td>사진이 너무이쁩니다 댓글을 남겨봤어요 이글은 줄바꿈이 어디까지 될까요 ?</td>
                    </tr>
                    </table>
                </div>
                <div class="modal-reply-input">
                    <input type="text" name="reple" id="reple">
                    <button type="button" name="reply-submit" id="reply-submit"><i class="fa fa-paper-plane"
                            aria-hidden="true"></i></button>
                </div>
            </div>
        </div>
    </div>
    <div class="header">
        <div class="navi">
            <div class="logo">
                <a href="#" class="brand-name">Gonyang</a>
            </div>
            <div class="user-info">
                <div type="button" class="header-profile-box">
                    <img src="../assets/profile1.jpg" alt="헤더프로필">
                </div>
                <div class="logout" type="button" v-on:click="logout">
                    <i class="fa fa-sign-out" aria-hidden="true"></i>
                </div>
            </div>
        </div>
    </div>
    <main>
        <div class="content-container">
            <div class="profile-header">
                <div class="profile-box">
                    <div class="profile-imgbox">
                        <img src="../assets/profile1.jpg" alt="프로필">
                    </div>
                </div>
                <div class="profile-info-box">
                    <div class="nick-name">
                        <p>{{$store.state.userInfo.userNick}}</p>
                    </div>
                    <div class="user-id">
                        <p>tester@tester.com</p>
                    </div>
                </div>
                <div class="write-btn" type="button" v-on:click="moveInsert">
                    <i class="fa fa-pencil" aria-hidden="true"></i>
                </div>
            </div>


            <div class="content-box">
                <div v-on:click="modalShow = true" class="content-card">
                    <div class="hidden-box">
                        <div type="button" class="delete-icon">
                            <i class="fa fa-times" aria-hidden="true"></i>
                        </div>
                        <div class="center-icon">
                            <div class="like">
                                <i class="fa fa-heart-o hidden-icon" aria-hidden="true"><span class="count">2</span></i>
                            </div>
                            <div class="comment">
                                <i class="fa fa-comment-o" aria-hidden="true"><span class="count">1</span></i>
                            </div>
                        </div>
                    </div>
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
                        <img src="../assets/profile1.jpg" alt="썸네일">
                    </div>
                </div>
                <div class="content-card">
                    <div class="hidden-box">
                        <div type="button" class="delete-icon">
                            <i class="fa fa-times" aria-hidden="true"></i>
                        </div>
                        <div class="center-icon">
                            <div class="like">
                                <i class="fa fa-heart-o hidden-icon" aria-hidden="true"><span class="count">2</span></i>
                            </div>
                            <div class="comment">
                                <i class="fa fa-comment-o" aria-hidden="true"><span class="count">1</span></i>
                            </div>
                        </div>
                    </div>
                    <div class="content-img">
                        <img src="../assets/content1.jpg" alt="썸네일">
                    </div>
                </div>
                <div class="content-card">
                    <div class="hidden-box">
                        <div type="button" class="delete-icon">
                            <i class="fa fa-times" aria-hidden="true"></i>
                        </div>
                        <div class="center-icon">
                            <div class="like">
                                <i class="fa fa-heart-o hidden-icon" aria-hidden="true"><span class="count">2</span></i>
                            </div>
                            <div class="comment">
                                <i class="fa fa-comment-o" aria-hidden="true"><span class="count">1</span></i>
                            </div>
                        </div>
                    </div>
                    <div class="content-img">
                        <img src="../assets/content2.jpg" alt="썸네일">
                    </div>
                </div>
            </div>
        </div>
    </main>
    </div>
</template>

<script>
    import {
        mapState,
        mapActions
    } from 'vuex'
    export default {
        name: 'Main',
        data() {
            return {
                modalShow: false,
            }
        },
        computed: {
            ...mapState(["isLogin", "isLoginError"])
        },
        methods: {
            ...mapActions(["logout"]),
            moveInsert() {
                this.$router.push({
                    name: 'Insert'
                });
            }
        },
    }
</script>

<style scoped>
    @media (max-width: 735px) {
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
            transition: .2s;
        }
    }

    @font-face {
        font-family: 'ONE-Mobile-POP';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }

    .modal-page {
        font-family: sans-serif;
        width: 100%;
        height: 100%;
        position: fixed;
        top: 0;
        left: 0;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 99998;
        display: flex;
        align-items: center;
        justify-content: center;
        /* display: none; */
    }

    .close-icon {
        position: absolute;
        top: 5%;
        right: 15%;
        color: #ffff;
        font-size: 30px;
    }

    .modal-body {
        min-width: 300px;
        max-width: 935px;
        /* width: 100%; */
        margin: auto;
        padding: 0;
        /* /* position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%); */
        /* background-color: #ffff; */
        display: flex;
        flex-wrap: wrap;
    }

    .modal-img {
        width: 60%;
        object-fit: cover;
        overflow: hidden;
        display: flex;
    }

    .modal-img img {
        width: 100%;
        /* height: 100%; */
    }

    .modal-content {
        width: 40%;
        display: flex;
        flex-direction: column;
        border-radius: unset;
    }

    .modal-title {
        display: flex;
        width: 100%;
        padding: 16px;
        border-bottom: 1px solid lightgray;
    }

    .user-nick {
        display: flex;
        align-items: center;
        margin: 0 5px;
        font-weight: bold;
        font-size: 13px;
    }

    .user-nick>p {
        margin: 0;
    }

    .modal-text {
        font-size: 13px;
        padding: 10px 24px;
        height: 280px;
        overflow: scroll;
        -ms-overflow-style: none;
        border-bottom: 1px solid lightgray;
    }

    .modal-text::-webkit-scrollbar {
        display: none;
        width: 0 !important;
    }

    .modal-reply-menu {
        display: flex;
        height: 45px;
        padding: 5px 16px;
        border-bottom: 1px solid lightgray;
        display: flex;
        font-size: 21px;
    }

    .modal-reply {
        font-size: 13px;
        padding: 10px 24px;
        height: 150px;
        overflow: scroll;
        -ms-overflow-style: none;
        border-bottom: 1px solid lightgray;
    }

    .modal-reply::-webkit-scrollbar {
        display: none;
        width: 0 !important;
    }

    .reply {
        vertical-align: top !important;
    }

    .reply-profile-box {
        width: 24px;
        height: 24px;
        border-radius: 180px;
        object-fit: cover;
        overflow: hidden;
        box-shadow: 0 0 1px 1px #f2f2f2;
    }

    .reply-profile-box img {
        width: 100%;
        height: 100%;
    }

    .reply-user-nick {
        align-items: center;
        margin: 0 5px;
        font-weight: bold;
        font-size: 12px;
    }

    .modal-reply-input {
        display: flex;
    }

    .modal-reply-input input {
        font-size: 13px;
        margin: 0;
        padding-left: 10px;
        border: none;
        height: 57px;
    }

    #reply-submit {
        width: 55px;
        height: 100%;
        color: #68c5f6;
        border-style: none;
        background-color: #fff;
    }

    #reply-submit:hover {
        font-size: 14px;
    }





    .header {
        /* padding: 8px 42px; */
        height: 50px;
        background-color: #ffff;
        border-bottom: 1px solid lightgray;
        box-shadow: 1px 2px 5px lightgray;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .navi {
        width: 935px;
        height: 100%;
        justify-content: space-between;
        display: flex;
    }

    .logo {
        display: flex;
        align-items: center;
    }

    .brand-name {
        font-family: 'ONE-Mobile-POP';
        font-size: 24px;
        text-decoration: none;
        color: rgb(255, 144, 100);
    }

    .brand-name:hover {
        color: #000000;

    }

    .user-info {
        display: flex;
        align-items: center;
    }


    .header-profile-box {
        width: 40px;
        height: 40px;
        margin-right: 10px;
        border-radius: 180px;
        object-fit: cover;
        overflow: hidden;
        box-shadow: 0 0 1px 1px #f2f2f2;
    }

    .logout {
        font-size: 30px;
    }

    .header-profile-box img {
        width: 100%;
        height: 100%;
    }

    .content-container {
        max-width: 935px;
        display: flex;
        flex-wrap: wrap;
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
    }

    .profile-imgbox {
        width: 150px;
        height: 150px;
        border-radius: 180px;
        overflow: hidden;
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
        width: 70%;
    }

    .nick-name {
        font-size: 24px;
        font-weight: bold;
    }

    .write-btn {
        display: flex;
        font-size: 30px;
        align-items: center;
        justify-content: center;
        width: 80px;
        height: 80px;
        color: #ff9064;
        background-color: #ffff;
        border-radius: 180px;
        align-self: flex-end;

    }

    .write-btn:hover {
        color: royalblue;
    }


    .content-box {
        display: flex;
        flex-wrap: wrap;
        width: 100%;
        justify-content: space-between;
    }

    .content-card {
        width: 30%;
        /* height: 293px;     */
        /* margin: 40px 20px; */
        border: 1px solid lightgray;
        margin: 30px 0;
        border-radius: 8px;
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
        /* cursor: pointer; */
    }

    .content-img {
        width: 100%;
        height: 100%;
        overflow: hidden;
        object-fit: cover;
    }

    .content-card img {
        width: 100%;
        height: 100%;
    }
</style>