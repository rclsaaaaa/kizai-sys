<template>
  <header>
    <img @click="goToMainPage" src="./assets/タイトル.png" alt="Logo" class="logo">
  </header>
  <!-- <nav>
    <router-link to="/login">login</router-link> |
    <router-link to="/signup">signup</router-link> |
    <router-link to="/signup-auth-code">signup-auth-code</router-link> |
    <router-link to="/">main</router-link> |
    <router-link to="/password-reset">password-reset</router-link> |
    <router-link to="/password-reset-auth-code">password-reset-auth-code</router-link>
    
  </nav> -->
  <router-view/>
</template>

<script>
export default {
  data() {
    return {
    }
  },
  computed: {
    
  },
  mounted () {
    
  },
  methods: {
    goToMainPage(){
      this.$router.push('/')
    },
    
  }
}

</script>


<style>
body{
  background-image: url('./assets/背景画像.png');
  background-size: cover;
  background-attachment: fixed;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
