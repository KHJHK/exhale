<template>
  <div :style="{ fontSize: fontSize + 'px' }">
<div id="content">
  <header>
    <section class="main-nav">
      <div class="navbar-logo-link">
        <RouterLink :to="{ name: 'MainPage' }">
          <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >
        </RouterLink>
      </div>
      <div class="menu" @click="toggleMenu">
        <PostMenu v-if="!show" />
        <CommunityMenu v-else />
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
        <button class="enlarge" @click="enlarge" style="position: fixed; right: 0px; z-index: 10;">
        <img src="@/assets/plus.svg" class="plus">
        {{ msg }}
        </button> 
      </section>
    
      
<!-- vuetify -->
<div class="vutifyTabs">
    <v-tabs
      v-model="tab"
      color="#6C9F9C"
      align-tabs="center"
    >
      <v-tab 
        :value="1"
        :to="{name: 'PostWholeListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostWholeListView'}"
        active-class="active"
      >전체</v-tab>
      <v-tab 
        :value="2"
        :to="{name: 'PostInfoListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostInfoListView' }"
        active-class="active"
      >정보 글</v-tab>
      <v-tab 
        :value="3"
        :to="{name: 'PostReviewListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostReviewListView' }"
        active-class="active"
      >치료 후기</v-tab>
      <v-tab 
        :value="4"
        :to="{name: 'PostStoryListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostStoryListView' }"
        active-class="active"
      >환자 이야기</v-tab>
    </v-tabs>
  </div>

    </header>
    
    <main>
      <svg-icon type="mdi" :path="path"></svg-icon>
      
      <div class="box-container">
        
        <section>
          <PostSlider />
        </section>
        
        <section class="box-item sub-nav3">
          <label><PostCreateBtn /></label> 
          <label><PostSearch @search="handleSearch" /></label>
          <label>최신순</label>
        </section>
        
        <section class="box-item">
          <article>
            <!-- <div v-for="(post, index) in posts.slice(pageStartIdx, pageStartIdx+ ITEM_PER_PAGE)" :key="post.id" > -->
            <div v-for="(post, index) in filteredPosts" :key="post.id" >  
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
    </main>
    
  </div>
  
  <footer class="footer">
    <Footers/>
  </footer>
</div>
</template>

<script setup>
import { computed, onUpdated, ref, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router' 
import { getPosts} from '@/api/posts'
import PostItem from '@/components/posts/PostItem.vue'
import Pagination from '@/components/functions/Pagination.vue'
import CommunityMenu from '@/components/modals/CommunityMenu.vue'

import PostMenu from '@/components/posts/PostMenu.vue'
import PostSlider from '@/components/posts/PostSlider.vue'
import PostSearch from '@/components/posts/PostSearch.vue'
import PostCreateBtn from '@/components/posts/PostCreateBtn.vue'
import Footers from '@/components/common/Footers.vue'

const searchOption = ref(null);
const searchKeyword = ref('');

const handleSearch = ({ option, keyword }) => {
  searchOption.value = option;
  searchKeyword.value = keyword;
  fetchPosts(); // 필터링된 포스트를 다시 가져옵니다.
};

const filteredPosts = computed(() => {
  if (!searchOption.value || !searchKeyword.value) {
    return posts.value;
  }
  return posts.value.filter(post => {
    const valueToSearch = post[searchOption.value];
    return valueToSearch && valueToSearch.includes(searchKeyword.value);
  });
});


const show = ref(false)

function toggleMenu() {
  show.value = !show.value
}

// vuetify Tabs components
const tab = ref(null);


// const open = () => {
//   show.value = !show.value
//   return show
// }

// console.log(show.value)




const posts = ref([])
const route = useRoute()
const router = useRouter()
const params = ref({
  _sort: 'create_date',
  _order: 'desc',
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
    // URL 쿼리 파라미터를 기반으로 필터링된 포스트를 가져옵니다.
    const searchBy = route.query.searchBy;
    const keyword = route.query.keyword;
    const { data } = await getPosts(searchBy, keyword);
    posts.value = data;
  } catch (error) {
    console.error(error);
  }
};
fetchPosts()

watch(() => route.query, fetchPosts);

const goPage = (id) => {
router.push(`/posts/${id}`)
}

</script>

<style lang="scss" scoped>
  @import "@/assets/scss/layout/_article.scss";
  @import "@/assets/scss/layout/_grid.scss";
</style>