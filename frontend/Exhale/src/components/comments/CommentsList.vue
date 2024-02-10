<template>

  <div class="commentTitle">
    <h3>댓글 {{  }}</h3>
    <button class="commentWriteBtn">댓글 작성</button>
  </div>

  <hr>

    <div v-for="comment in comments" :key="postId">
      
      <div class="comment">

        <div class="commentContent">
          <v-avatar size="65" class="avatar">
            <v-img
              src="https://cdn.vuetifyjs.com/images/john.jpg"
              alt="John"
            ></v-img>
          </v-avatar>
          <label for="">
            <p class="commentNickname">{{ comment.nickname }}</p> 
            <p class="commentCreatedate">{{ comment.create_date }}</p>
            <p class="commentContent">{{ comment.content }}</p>
            <button class="commentReview">답글달기</button>
          </label>
        </div>
          
          <div class="commentBtn">
            <button class="editBtn">수정</button>
            <button class="deleteBtn">삭제</button>
          </div>
        </div>
        
        <hr class="underHr">

    </div>

  </template>
  
  <script>

  import { ref, onMounted, defineProps, defineEmits } from 'vue'; 
  export default {
    props: {
      postId: {
        type: Number,
        required: true
      }
    },
    data() {
      return {
        comments: []
      };
    },
    async created() {
      this.fetchComments();
    },

    methods: {
      async fetchComments() {
        const response = await getComments(this.postId);
        this.comments = response.data;
       
      }
    }
  };
  </script>
  
  <style scoped>
  .comment {
    margin-top: 50px;
    margin-bottom: 10px;
    display: flex;
    justify-content: space-around;
  }

  .commentTitle {
    display: flex;
    justify-content: space-around;
    margin-bottom: 5vh;
  }
  .commentTitle h3 {
    color: lightgray;
    font-family: 'NotoSansKR';
    letter-spacing: 5px;
  }

  hr {
    width: 58%;
    margin-left: 21%;
  }

  .underHr {
    margin-top: 6vh;
    color: rgb(177, 177, 177);
  }
  .avatar {
    margin-top: -35vh;
    margin-right: 3vw;
    margin-left: 4vw;
    
  }

  .commentWriteBtn {
    border: 1px solid lightgray;
    border-radius: 30px;
    padding: 10px 25px;
  }

  .commentCreatedate {
    margin-top: -2vh;
    color: lightgray;
  }

  .commentContent {
    /* margin-top: vh; */
    margin-bottom: 5vh;
  }

  .commentReview {
    margin-top: 2vh;
    color: gray;
  }

  .commentBtn {
    padding-right: 70px;
    color: gray;
  }

  .editBtn {
    padding-right: 2vw;
  }
  </style>
  