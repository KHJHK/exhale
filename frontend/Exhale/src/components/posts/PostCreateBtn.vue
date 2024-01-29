<template>
  <div>
    <button
      class="btn btn-outline-light"
      type="button"
      @click="goPage"
    >
      글쓰기
    </button>
  </div>
</template>

<script setup>
  import { useRouter } from 'vue-router';

  const router = useRouter();
  const goPage = () => {
    router.push('/posts/create');
  };

</script>

<style lang="scss" scoped>
  .btn {
    color: black;
  }
</style>