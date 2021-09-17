<template>
    <div class="footer-container">
        <div class="footer-content">
            <div>made by HyeonJun</div>
        </div>
    </div>
</template>

<script>
export default {
}
</script>

<style scoped>
    .footer-container {
        position: fixed;
        display: flex;
        bottom: 0px;
        height: 60px;
        width: 100%;
        border-top: 1px solid rgb(238, 238, 238);
        box-shadow: 1px 1px 5px 1px rgb(238, 238, 238);
        background-color: #ffff;
    }
    .footer-content {
        justify-content: center;
        margin: auto;
        color: #8d8d8d;
    }
</style>