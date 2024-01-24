<template>
    <div class="navbar">
    <nav>
      <RouterLink :to="{name: 'App'}">
        <img src="@/assets/logo_green.png" alt="logo" class="navbar-logo" >
      </RouterLink>
        <div class="navbar-links">
          <RouterLink :to="{name: 'AboutUs'}"  class="nav-link">메인 홈</RouterLink>
          <RouterLink :to="{name: 'ReadPost'}" class="nav-link">복습하기</RouterLink>
          <RouterLink :to="{name: 'Login'}" class="nav-link">커뮤니티</RouterLink>
          <RouterLink :to="{name: 'SignUp'}" class="nav-link">로그아웃</RouterLink>
        </div>
    </nav>
</div>
</template>

<script setup>

</script>

<style scoped>
.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem 1rem;
  
}
.navbar > nav {
  display: flex;
  align-items: center;
  /* justify-content: flex-end; */
  width: 100%;
}

.navbar-logo {
  width: 100px; /* Adjust as needed */
  margin-right: 800px; /* Space between logo and nav links */
}

.navbar-links {
  display: flex;
  align-items: center;
  gap: 30px; 
}

.nav-link {
  text-decoration: none;
  color: #333; /* Text color */
  padding-bottom: 0.5rem; /* Space for the underline */
  border-bottom: 2px solid transparent; /* Invisible underline */
  transition: border-color 0.3s; /* Smooth transition for hover effect */
  display: inline-block;
}


.nav-link:hover {
  border-bottom: 2px solid green; /* 호버 시 밑줄이 나타납니다 */
  text-decoration: none;
  color: black;
  transition: 0.4s;
  padding: 3px;
}
</style>