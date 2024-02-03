<template>
  <form @submit.prevent="submitComment">
    <div class="comments-container">
      <div class="input-group">
        <input v-model="newComment" type="text" class="form-control" placeholder="댓글을 작성하세요..." />
        <button class="btn btn-primary" type="submit" >등록</button>
      </div>
    </div>
  </form>
</template>
<script setup>
import { ref, inject } from 'vue';
import { createComments } from '@/api/posts';
const post = inject('post')

const emit = defineEmits(['commentCreated']);
const postId = defineProps({
  type: Number,
  required: true
});
const newComment = ref('');
console.log(post)
const currentDate = new Date().toISOString();
const submitComment = async () => {
  if (!newComment.value) {
    alert('댓글 내용을 입력해주세요.');
    return;
  }

  try {
    // post 객체의 id 값을 사용하여 댓글과 연관된 postId를 설정
    await createComments({ 
      nickname: post.value.nickname,
      postId: post.value.id, 
      content: newComment.value ,
      created_date:  currentDate
    });
    newComment.value = '';
    emit('commentCreated');
  } catch (error) {
    console.error('댓글 생성 중 오류 발생:', error);
    alert('댓글을 생성하는 데 실패했습니다.');
  }
};
</script>


<style scoped>
.form-control {
  width: 500px;
}

</style>