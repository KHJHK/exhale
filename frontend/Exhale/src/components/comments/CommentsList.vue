<template>
    <div v-for="comment in comments" :key="postId" class="comment">
        <hr>
        <p>{{ comment.nickname }}</p> 
        <h4>{{ comment.content }}</h4>
        <p>{{ comment.create_date }}</p>
        
        <hr>
        
         
         
    </div>
  </template>
  
  <script>
  import { getComments } from '@/api/posts';
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
    margin-bottom: 10px;
  }
  </style>
  