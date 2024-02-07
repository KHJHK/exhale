<template>
<div class="imageBackground">
        <section class="sub-nav1">
            <div id="breadcrum">
                <RouterLink :to="{name: 'MainPage'}">메인 홈</RouterLink>
                >
                <RouterLink :to="{name: 'PostWholeListView'}">커뮤니티</RouterLink>
                >
                <RouterLink :to="{name: 'PostCreateView'}">글쓰기</RouterLink>
            </div>
        </section>

        <!-- <form @submit.prevent="save"> -->
            <div>
                <input class="titleInput" placeholder="제목을 입력하세요" v-model="title">
            </div>
            <div class="author">
                (작성자명) 
            </div>
</div>

<div class="category">
    <div class="dropdown">
    <a class="btn dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
        카테고리 선택
    </a>

    <select class="dropdown-menu" v-model="selectedCategory" @change="onCategoryChange">
        <option disabled value="">카테고리를 선택해주세요</option>
        <option value= 1 class="dropdown-item" >정보 글</option>
        <option value= 2 class="dropdown-item">치료 후기</option>
        <option value= 3 class="dropdown-item">환자 이야기</option>
    </select>
    </div>
</div>

<div class="contentBackground">
    <div>
        <form>
        <v-container class='toggles'>
        <v-row>

        <v-col
            cols="12"
            sm="6"
            class="py-2 px-10"
        >
            <v-btn-toggle
            v-model="toggle_multiple"
            background-color="primary"
            dark
            multiple
            >
            <v-btn>
                <v-icon>mdi-format-bold</v-icon>
            </v-btn>

            <v-btn>
                <v-icon>mdi-format-italic</v-icon>
            </v-btn>

            <v-btn>
                <v-icon>mdi-format-underline</v-icon>
            </v-btn>

            <v-btn>
                <v-icon>mdi-format-color-fill</v-icon>
            </v-btn>
            </v-btn-toggle>
        </v-col>

        <v-col
            cols="12"
            sm="6"
            class="py-2 px-10"
        >
            <v-btn-toggle
            v-model="toggle_one"
            shaped
            mandatory
            >
            <v-btn>
                <v-icon>mdi-format-align-left</v-icon>
            </v-btn>

            <v-btn>
                <v-icon>mdi-format-align-center</v-icon>
            </v-btn>

            <v-btn>
                <v-icon>mdi-format-align-right</v-icon>
            </v-btn>

            <v-btn>
                <v-icon>mdi-format-align-justify</v-icon>
            </v-btn>
            </v-btn-toggle>
        </v-col>
        </v-row>
    </v-container>
                
            <div class="mb-3">
                <label for="content" class="form-label">어떻게 쓰나요?</label>
                <textarea v-model="content" class="form-control" id="content" rows="3" placeholder="여기를 클릭해서 글을 작성하거나, 오른쪽 이미지나 이모티콘 아이콘을 클릭해서 첨부한 후 작성하면 됩니다."></textarea>
            </div>
            
            <div class="twoButton">
                <button class="buttons" type="button" @click="goListPage">목록</button>
                <button class="buttons" type="button" @click="article_create">저장</button>
            </div>
        </form>

        </div>
    </div>
        
    <div class="add-icons">
        <div @click.prevent="imoji_pop" class="emoji">
        <font-awesome-icon icon="face-smile" />
        <div class="emoji-picker" v-if="imoji">
                <EmojiPicker :native="true" @select="onSelectEmoji"/>
        </div>
        </div>

        <v-file-input
                    class="custom-file-input-icon"
                    :rules="rules"
                    accept="image/png, image/jpeg, image/bmp"
                    placeholder=""
                    prepend-icon="mdi-folder-image"
                    label="이미지 선택"
                    show-input="false"
                    >
        </v-file-input>
    </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import { createPost } from '@/api/posts'
import EmojiPicker from 'vue3-emoji-picker'
import 'vue3-emoji-picker/css'
import { watch } from 'vue'
import { mdiConsolidate, mdiGateArrowRight } from '@mdi/js'
import { articleCreate } from '@/api/boards'

const selectedCategory = ref('');
const router = useRouter()
const title = ref('');
const content = ref('')
const thumbnail = ref(null);
const board_id = ref('')
const text = ref('center')
const icon = ref('justify')
const toggle_none = ref(null)
const toggle_one = ref(0)
const toggle_exclusive = ref(2)
const toggle_multiple = ref([0, 1, 2])

const rules = ref([
  value => {
    return !value || !value.length || value[0].size < 2000000 || 'Avatar size should be less than 2 MB!'
  },
])

// 목록
const goListPage = () => router.push({ name:'PostWholeListView' })


// 이모티콘 로직 모음 --------------------------------------------
const imoji = ref(false)

// 이모티콘 버튼 누를 시 이모지창 팝업
const imoji_pop = () => {
  imoji.value = true
}

// 이모티콘을 입력하거나 이모지창 내부를 클릭하면 반응안하고 창 외부 클릭시 창 사라짐 
const onDocumentClick = (event) => {
    const emojiPickerEl = event.target.closest('.emoji-picker');
    const emojiIconEl = event.target.closest('.emoji');
  if (!emojiPickerEl && !emojiIconEl && imoji.value) {
        imoji.value = false
  }};

// 클릭 이벤트 생성
onMounted(() => {
  document.addEventListener('click', onDocumentClick);
});

// 클릭 이벤트 해제
onUnmounted(() => {
  document.removeEventListener('click', onDocumentClick);
});

// 이모티콘 인풋창에 입력
const onSelectEmoji = (emoji) => {
    content.value += emoji.i
} 
// --------------------------------------------------------------    


// 게시글 생성
const article_create = async () => {
    try {
       const response = articleCreate(
            title.value,
            content.value,
            thumbnail.value,
            board_id.value
       )
       if (response) {
        alert('게시글 등록 완료!')
        router.push('/post-whole-list') 
    }
    } catch (error) {
        console.error('게시글 등록에 실패하였습니다.', error)
    }
}

// 게시물 종류 선택시 게시물 아이디 정해짐
const onCategoryChange = () => {
  board_id.value =  parseInt(selectedCategory.value, 10);
};




</script>

<style lang="scss" scoped>

@import "@/assets/scss/pages/_postcreate.scss"
</style>