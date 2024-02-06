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
                <input class="titleInput" placeholder="제목을 입력하세요">
            </div>
            <div class="author">
                (작성자명)
            </div>
</div>

<!-- ----------------------------------------------------------------------------------------- -->

<div class="category">
    <div class="dropdown">
    <a class="btn dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
        카테고리 선택
    </a>

    <ul class="dropdown-menu">
        <li><a class="dropdown-item" href="#">정보 글</a></li>
        <li><a class="dropdown-item" href="#">치료 후기</a></li>
        <li><a class="dropdown-item" href="#">환자 이야기</a></li>
    </ul>
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
                <textarea v-model="form.content" class="form-control" id="content" rows="3" placeholder="여기를 클릭해서 글을 작성하거나, 오른쪽 이미지나 이모티콘 아이콘을 클릭해서 첨부한 후 작성하면 됩니다."></textarea>
            </div>
            
            <div class="twoButton">
                <button class="buttons" type="button" @click="goListPage">목록</button>
                <button class="buttons" type="button" @click="save">저장</button>
            </div>
        </form>

        </div>
    </div>
        
    <div class="add-icons">
        <label @click.prevent="imoji_pop" class="emoji">
        <font-awesome-icon icon="face-smile" />
        <div class="emoji-picker" v-show="imoji">
                <EmojiPicker :native="true" @select="onSelectEmoji"/>
        </div>
        </label>

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
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { createPost } from '@/api/posts'
import EmojiPicker from 'vue3-emoji-picker'
import 'vue3-emoji-picker/css'
import { watch } from 'vue'
import { mdiConsolidate, mdiGateArrowRight } from '@mdi/js'
const router = useRouter()
const form = ref({
    title: null,
    content: null,
})

const rules = ref([
  value => {
    return !value || !value.length || value[0].size < 2000000 || 'Avatar size should be less than 2 MB!'
  },
])

const text = ref('center')
const icon = ref('justify')
const toggle_none = ref(null)
const toggle_one = ref(0)
const toggle_exclusive = ref(2)
const toggle_multiple = ref([0, 1, 2])


const goListPage = () => router.push({ name:'PostWholeListView' })


const imoji = ref(false)
const imoji_pop = () => {
    imoji.value = !imoji.value
   
}


const save = () => {
    try {
        createPost({
            ...form.value,
            create_date: Date.now(),
        })
        router.push({name: 'PostWholeListView'})
    } catch (error) {
        console.error(error)
    }
}
 
function onSelectEmoji(emoji) {
  form.value.content+= emoji.i
}


</script>

<style lang="scss" scoped>
@import "@/assets/scss/pages/_postcreate.scss"
</style>