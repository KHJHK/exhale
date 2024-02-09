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
        
        :to="{name: 'PostWholeListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostWholeListView'}"
        active-class="active"
      >전체</v-tab>
      <v-tab 
     
        :to="{name: 'PostInfoListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostInfoListView' }"
        active-class="active"
      >정보 글</v-tab>
      <v-tab 
        
        :to="{name: 'PostReviewListView'}" 
        class="nav-link"
        :class="{ active: route.name === 'PostReviewListView' }"
        active-class="active"
      >치료 후기</v-tab>
      <v-tab 
   
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
            <div v-for="(post, index) in posts" :key="post.id" >  
              <PostItem
               
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
<scrollTop/>
</template>

<script setup>
import { computed, onUpdated, ref, watch, onMounted, onUnmounted } from 'vue'
import { useRoute, useRouter } from 'vue-router' 

import PostItem from '@/components/posts/PostItem.vue'
import Pagination from '@/components/functions/Pagination.vue'
import CommunityMenu from '@/components/modals/CommunityMenu.vue'
import scrollTop from '@/components/functions/scrollTop.vue'
import PostMenu from '@/components/posts/PostMenu.vue'
import PostSlider from '@/components/posts/PostSlider.vue'
import PostSearch from '@/components/posts/PostSearch.vue'
import PostCreateBtn from '@/components/posts/PostCreateBtn.vue'
import Footers from '@/components/common/Footers.vue'
import {boardList} from '@/api/boards' 
import { useCrudStore } from '@/stores/crud'
const searchOption = ref(null);
const searchKeyword = ref('');
const crud = useCrudStore()
const handleSearch = ({ option, keyword }) => {
  searchOption.value = option;
  searchKeyword.value = keyword;
  // 필터링된 포스터 가져오는 로직
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

const board_list = async () => {
  try {
    const response = await boardList(
      crud.tab,
      crud.curPage
      )
      posts.value = response.data.response.article_list
      crud.totalPage = response.data.response.article_total_count
      crud.ITEM_PER_PAGE = response.data.response.page_size
      crud.PAGE_PER_SECTION = response.data.response.page_total_count
      
    }
   catch (error) {
      console.log(error)
   }}
  

watch(crud.curPage, () => {
     
    board_list()
   })



  onMounted(() => {
    crud.tab = 3
    board_list()
  })

onUnmounted(() => {
    board_list()
  })

// const updateTab = (newTab) => {
//   tab.value = newTab
// }



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
</script>

<style lang="scss" scoped>
  @import "@/assets/scss/layout/_article.scss";
  @import "@/assets/scss/layout/_grid.scss";
</style>