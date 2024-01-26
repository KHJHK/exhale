<template>

  <PostSlider />

  <br>

  <PostCreateBtn /> <PostSearch />

  <hr>

  <h3>치료 후기</h3>

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
import { useRoute, useRouter } from 'vue-router' 
import { getPosts } from '@/api/posts'
import PostMenu from '@/components/posts/PostMenu.vue'
import PostSlider from '@/components/posts/PostSlider.vue'
import PostSearch from '@/components/posts/PostSearch.vue'
import PostCreateBtn from '@/components/posts/PostCreateBtn.vue'
import PostItem from '@/components/posts/PostItem.vue'
import Pagination from '@/components/functions/Pagination.vue'

const posts = ref([])
const route = useRoute()
const router = useRouter()
const articles = new Array(111)

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
.article {
  display: block;
  width: 600px;
  gap: 20px;
}

</style>


