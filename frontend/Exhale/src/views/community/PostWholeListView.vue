<template>
    <div>
        <h1>날;숨(커뮤니티)</h1>
    </div>
    <div id="breadcrum">
        <RouterLink :to="{name: 'MainPage'}">메인 홈</RouterLink>
        >
        <RouterLink :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
        >
        <RouterLink :to="{name: 'PostStoryListView'}">환자이야기</RouterLink>
    </div>

    <br>

    <PostMenu />

    <br>

    <nav>
        <RouterLink :to="{name: 'PostWholeListView'}">전체</RouterLink>
        |
        <RouterLink :to="{name: 'PostInfoListView'}">정보 글</RouterLink>
        |
        <RouterLink :to="{name: 'PostReviewListView'}">치료 후기</RouterLink>
        |
        <RouterLink :to="{name: 'PostStoryListView'}">환자 이야기</RouterLink>
    </nav>

    <br>

    <PostSlider />

    <br>

    <PostCreateBtn /> <PostSearch />

    <hr>
    <div class="article">
      <div v-for="(post, index) in posts.slice(pageStartIdx, pageStartIdx+ ITEM_PER_PAGE)" :key="post.articleId" >
        <PostItem
          :number="pageStartIdx + index + 1"
          :title="post.title"
          :content="post.content"
          :create_date="post.create_date"
          :articleId="post.articleId"
          @go-to-detail="goPage"
        ></PostItem>
      </div>
    </div>
    <div>
      <Pagination 
        :list="articles"
        v-bind="{ITEM_PER_PAGE, PAGE_PER_SECTION}"
        @change-page="onChangePage"
      />
    </div>
</template>

<script setup>
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router' 
import { getPosts } from '@/api/posts'
import PostMenu from '@/components/posts/PostMenu.vue'
import PostSlider from '@/components/posts/PostSlider.vue'
import PostSearch from '@/components/posts/PostSearch.vue'
import PostCreateBtn from '@/components/posts/PostCreateBtn.vue'
import PostItem from '@/components/posts/PostItem.vue'
import Pagination from '@/components/functions/Pagination.vue'

const posts = ref([])
const router = useRouter()
const articles = new Array(111);

		for (let i = 0; i < articles.length; i++) {
			articles[i] = `Article ${i + 1}`;
		}
   
		const ITEM_PER_PAGE = ref(10);
		const PAGE_PER_SECTION = ref(10);
		let curPage = ref(1);

		const pageStartIdx = computed(() => {
			return (curPage.value - 1) * ITEM_PER_PAGE.value;
		});

    const onChangePage = (data) => {
  curPage.value = data;
};

const fetchPosts = () => {
  posts.value = getPosts()

};

fetchPosts()

const goPage = (articleId) => {
  router.push(`/post/${articleId}`)
}

</script>

<style lang="scss" scoped>
    #breadcrum a {
        color: black;
      }
      .article {
        display: block;
        width: 600px;
        gap: 20px;
      }
    </style>


