<template>
    <div>
        <h1>날;숨(커뮤니티)</h1>
    </div>
    <div id="breadcrum">
        <RouterLink :to="{name: 'MainPage'}">메인 홈</RouterLink>
        >
        <RouterLink :to="{name: 'ReadPost'}">커뮤니티</RouterLink>
        >
        <!-- <RouterLink :to="{name: ''}">환자이야기</RouterLink> -->
    </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
    #breadcrum a {
        color: black;
    }
</style>