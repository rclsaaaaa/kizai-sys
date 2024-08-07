<template>
  <div class="auth-code-input">
    <h2>認証コード入力</h2>
    <p>メールアドレスに送信された -桁の認証コードを入力してください。</p>
    <input type="text" v-model="authCode" placeholder="認証コードを入力">
    <button @click="goBack">戻る</button>
    <button @click="submit">送信</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      authCode: ''
    }
  },
  methods: {
    goBack() {
      // 戻るボタンクリック時の処理 (例: 前の画面に戻る)
      this.$router.back();
    },
    submit() {
      // 送信ボタンクリック時の処理 (例: サーバーに認証コードを送信)
      // axios.post('/api/auth/verify', { code: this.authCode })
      //   .then(response => {
      //     // 認証成功時の処理
      //   })
      //   .catch(error => {
      //     // 認証失敗時の処理
      //   })
    }
  }
}
</script>

<style scoped>
/* CSS styles for the component */
.auth-code-input {
  /* フォームのレイアウトを調整するCSS */
  text-align: center; /* 中央揃え */
}
</style>
