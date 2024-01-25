<template>
    <div id="breadcrum">
        <RouterLink :to="{name: 'MainPage'}">메인 홈</RouterLink>
        >
        <RouterLink :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
        >
        <RouterLink :to="{name: 'PostCreateView'}">글쓰기</RouterLink>
    </div>
    
    <div>
        <h2>게시글 작성</h2>
        <hr class="my-4" />
    </div>

    <form @submit.prevent>
        <div class="mb-3">
        <!-- <label for="maintitle" class="form-label">제목</label> -->
        <input type="text" class="form-control" id="maintitle" placeholder="제목을 입력하세요">
        </div>

        <div class="mb-3">
        <input type="text" class="form-control" id="maintitle" placeholder="소제목을 입력하세요">
        </div>

        <div>
            (작성자명)
        </div>

        <hr class="my-4" />

        <div>
            (카테고리 선택)
        </div>

        <hr class="my-4" />

        <div class="mb-3">
        <label for="content" class="form-label">어떻게 쓰나요?</label>
        <textarea class="form-control" id="content" rows="3" placeholder="여기를 클릭해서 글을 작성하거나, 오른쪽 이미지나 이모티콘 아이콘을 클릭해서 첨부한 후 작성하면 됩니다."></textarea>
        </div>
    </form>
    
    <div class="pt-4">
        <button type="button" class="btn btn-outline-dark me-2">목록</button>
        <button type="button" class="btn btn-primary">저장</button>
    </div>

    <div class="pt-4">
        <button type="button" class="btn btn-outline-dark me-2">사진첨부</button>
        <button type="button" class="btn btn-outline-dark">이모티콘첨부</button>
    </div>

</template>

<script setup>

</script>

<style lang="scss" scoped>

</style>