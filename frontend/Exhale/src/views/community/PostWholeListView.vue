<template>
<div id="content">
  <header>

    <section class="main-nav">
      <div>
        <RouterLink  :to="{ name: 'MainPage' }">
          <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >
        </RouterLink>
      </div>
      <div>
        <label class="menu">
          <PostMenu />
          <span>Menu</span>
        </label>
      </div>
    </section>


    <section class="sub-nav1">
      <div id="breadcrum">
          <RouterLink class="breadlink" :to="{name: 'MainPage'}">메인 홈</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
          >
          <RouterLink class="breadlink" :to="{name: 'PostStoryListView'}">환자이야기</RouterLink>
      </div>
      <button class="enlarge" @click="enlarge">{{ msg }}</button> 
    </section>

    <nav class="navbar-links">
        <RouterLink 
          :to="{name: 'PostWholeListView'}" 
          class="nav-link"
          :class="{ active: route.name === 'PostWholeListView'}"
          active-class="active"
        >전체</RouterLink>
        <RouterLink 
          :to="{name: 'PostInfoListView'}" 
          class="nav-link"
          :class="{ active: route.name === 'PostInfoListView' }"
          active-class="active"
          >정보 글</RouterLink>
        <RouterLink 
          :to="{name: 'PostReviewListView'}" 
          class="nav-link"
          :class="{ active: route.name === 'PostReviewListView' }"
          active-class="active"
          >치료 후기</RouterLink>
        <RouterLink 
          :to="{name: 'PostStoryListView'}" 
          class="nav-link"
          :class="{ active: route.name === 'PostStoryListView' }"
          active-class="active"
        >환자 이야기</RouterLink>
    </nav>
    
  </header>

  <main>
    <svg-icon type="mdi" :path="path"></svg-icon>
    <div class="p" :style="{ fontSize: fontSize + 'px' }">
      <div class="box-container">

        <section>
          <PostSlider />
        </section>

        <section class="box-item sub-nav3">
          <label><PostCreateBtn /></label> 
          <label><PostSearch /></label>
          <label>최신순</label>
        </section>

        <section class="box-item">
          <article>
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
          </article>
        </section>

        <section class="box-item">
          <div>
            <Pagination 
              :list="articles"
              v-bind="{ITEM_PER_PAGE, PAGE_PER_SECTION}"
              @change-page="onChangePage"
            />
          </div>
        </section>
        
      </div>
    </div>
  </main>
</div>

  <footer class="footer">
    <Footers/>
  </footer>

</template>

<script setup>

import { computed, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router' 
import { getPosts } from '@/api/posts'
import PostItem from '@/components/posts/PostItem.vue'
import Pagination from '@/components/functions/Pagination.vue'

import PostMenu from '@/components/posts/PostMenu.vue'
import PostSlider from '@/components/posts/PostSlider.vue'
import PostSearch from '@/components/posts/PostSearch.vue'
import PostCreateBtn from '@/components/posts/PostCreateBtn.vue'
import Footers from '@/components/common/Footers.vue'

const posts = ref([])
const route = useRoute()
const router = useRouter()
const params = ref({
  _sort: 'create_date',
  _order: 'asc',
})

const fontSize = ref(16);
const msg = computed(() => fontSize.value > 21 ? '원래대로' : '글자확대');
const enlarge = () => {
  fontSize.value ++;
  if (fontSize.value > 22) {
    fontSize.value = 16
  };
};

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
  @import "@/assets/scss/layout/_article.scss";
  @import "@/assets/scss/layout/_grid.scss";
</style>


