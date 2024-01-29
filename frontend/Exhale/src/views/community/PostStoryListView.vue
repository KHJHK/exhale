<template>

  <h3>환자 이야기</h3>

  <div class="article">
    <div v-for="(post, index) in posts.slice(pageStartIdx, pageStartIdx+ ITEM_PER_PAGE)" :key="post.id" >
      <PostItem
        :number="pageStartIdx + index + 1"
        :title="post.title"
        :content="post.content"
        :create_date="post.create_date"
        :id="post.id"
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
import PostItem from '@/components/posts/PostItem.vue'
import Pagination from '@/components/functions/Pagination.vue'

const posts = ref([])
const route = useRoute()
const router = useRouter()
const params = ref({
  _sort: 'create_date',
  _order: 'asc',
})
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

const fetchPosts = async () => {
  try {
    const { data } = await getPosts()
    posts.value = data
  } catch (error) {
    console.error(error)
  }
  // console.dir(response)
  // getPosts().then(response => {
  //   console.log('response:', response)
  // }).catch(error => {
  //   console.log('error:', error)
  // })
};
fetchPosts()

const goPage = (id) => {
router.push(`/posts/${id}`)
}

</script>

<style lang="scss" scoped>
.article {
  display: block;
  width: 600px;
  gap: 20px;
}

</style>


