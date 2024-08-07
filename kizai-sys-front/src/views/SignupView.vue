<template>
  <div class="register-form">
    <h2>新規登録</h2>
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="email">メールアドレス</label>
        <input type="email" id="email" v-model="form.email" required>
      </div>
      <div>
        <label for="employeeNumber">社員番号</label>
        <input type="text" id="employeeNumber" v-model="form.employeeNumber" required>
      </div>
      <div>
        <label for="password">パスワード</label>
        <input type="password" id="password" v-model="form.password" required>
      </div>
      <div>
        <label for="confirmPassword">パスワード確認</label>
        <input type="password" id="confirmPassword" v-model="form.confirmPassword" required>
      </div>
      <button type="submit">送信</button>
      <button type="button" @click="$emit('cancel')">戻る</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        email: '',
        employeeNumber: '',
        password: '',
        confirmPassword: ''
      }
    }
  },
  methods: {
    handleSubmit() {
      // フォームのデータをサーバーに送信する処理
      console.log(this.form)
      // 例：axios.post('/api/register', this.form)
    }
  }
}
</script>

<style scoped>
/* CSS styles for the component */
.register-form {
  /* フォームのレイアウトを調整するCSS */
}
</style>
