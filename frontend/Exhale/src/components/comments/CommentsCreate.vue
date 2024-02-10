<template>
  <form @submit.prevent="submitComment">
    <div>
      <div class="group">
        <textarea v-model="newComment" class="commentInput" placeholder="댓글을 작성하세요..."></textarea>
        <button class="submitBtn" >등록</button>
      </div>
    </div>
  </form>
</template>
<script setup>
import { ref, inject } from 'vue';

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

/* .titleInput {
  background: transparent;
  border: none;
  font-family: 'NotoSerifKR';
  margin-top: 6.5%;
  margin-left: 31.5%;
  font-size: 50px;
}

.titleInput:focus {
  width: 30%;
  border-color: none;
  background: transparent;
  color: rgb(50, 50, 50);
  outline: none;
  } */

.commentInput {
  width: 45vw;
  border: none;
  background: transparent;
}

.commentInput:focus {
  border-color: none;
  background: transparent;
  outline: none;
}

.submitBtn {
  cursor: pointer;
  /* background-color: lightgray; */
  border: 1px solid lightgray;
  border-radius: 30px;
  width: 50px;
}

.submitBtn:hover {
  background-color: rgb(108, 159, 156);
  color: white;
}


.group {
  display: flex;
  justify-content: space-between;
}

</style>