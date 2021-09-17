<template>
    <div class="wrap">
        <div class="sidebar-title">
            <span>내 팔로우 목록</span>
        </div>

        <div class="user-list">
            <div class="user-info">
                <div class="user-icon">
                    <div class="user-img">
                        <img src="@/assets/profile1.jpg" alt="유저이미지">
                    </div>
                </div>
                <div class="user_id">
                    슝슝슈슝슝슝슈슝
                </div>
                <span class="follow-btn">팔로잉</span>
            </div>
        </div>
    </div>
</template>

<script>
export default {

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
    }
</style>