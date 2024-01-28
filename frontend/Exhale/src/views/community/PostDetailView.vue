<template>
    <div>
        <h2>{{ post.title }}</h2>
        <p>{{ post.content }}</p>
        <p class="text-muted">{{ post.create_date}}</p>
        <hr class="my-4" />
        <div class="row g-2">
            <div class="col-auto">
                <button class="btn btn-outline-dark">이전글</button>
            </div>
            <div class="col-auto">
                <button class="btn btn-outline-dark">다음글</button>
            </div>
            <div class="col-auto me-auto"></div>
            <div class="col-auto">
                <button class="btn btn-outline-dark" @click="goListPage">목록</button>
            </div>
            <div class="col-auto">
                <button class="btn btn-outline-primary" @click="goEditPage">수정</button>
            </div>
            <div class="col-auto">
                <button class="btn btn-outline-danger" @click="remove" >삭제</button>
            </div>
        </div>
        
    </div>
</template>

<script setup>
import { getPostById, deletePost } from '@/api/posts';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { defineProps } from 'vue';



const post = ref({
    title: null,
    content: null,
    create_date: null,
})


const router = useRouter()
const route = useRoute()
const id = route.params.id

const props = defineProps({
    id: Number,
})


const fetchPost = async () => {
    const { data } = await getPostById(id)
    setPost(data)
}
const setPost = ({ title, content, create_date }) => {
    post.value.title = title
    post.value.content = content
    post.value.create_date = create_date
}
fetchPost()

const remove = async () => {
    try {
        if (confirm('삭제 하시겠습니까?') === false) {
            return
        }
        await deletePost(id)
        router.push({ name: 'PostWholeListView' })
    } catch (error) {
        console.error(error)
    }
}

const goListPage = () => router.push({name: 'PostWholeListView'})
const goEditPage = () => {
    const id = post.value.id || props.id
    router.push({ name: 'PostEditView', params: { id } })
}

</script>

<style lang="scss" scoped>

</style>