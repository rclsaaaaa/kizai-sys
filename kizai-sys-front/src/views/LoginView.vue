<template>
  <div class="login-container">
    <div class="login-box">
      <h2>ログイン</h2>
      <input type="text" id="employee-id" v-model="employeeId" required placeholder="社員番号"/>

      <input type="password" id="password" v-model="password" required placeholder="パスワード"/>

      <div class="button-group">
        <button @click="goBack">戻る</button>
        <button @click="login">送信</button>
      </div>

      <div class="links">
        <a href="#" @click="goToSignupPage">新規登録</a>
        <a href="#" @click="goToPasswordResetPage">パスワードを忘れた方はこちら</a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const URL = 'http://localhost:18080/user-login'

export default {
  data() {
    return {
      employeeId: '',
      password: ''
    };
  },
  methods: {
    goBack() {
      // 前の画面に戻る処理
      this.$router.go(-1);
    },
    login() {
      // ログイン情報を送信する処理
      const formData = {
        employeeId: this.employeeId,
        password: this.password
      };
      // ここにログインAPI呼び出しを追加
      axios.post(URL, formData)
        .then(response => {
          console.log(response.data.employeeId)
          this.goToMainPage()
        })
        .catch(error => {
          console.error(error)
        });
    },
    register() {
      // 新規登録画面に遷移する処理
      this.$router.push({ name: 'Register' });
    },
    forgotPassword() {
      // パスワード再設定画面に遷移する処理
      this.$router.push({ name: 'ForgotPassword' });
    },
    goToMainPage(){
      console.log('メインページ遷移メソッド実行')
      this.$router.push('/')
    },
    goToSignupPage(){
      this.$router.push('/signup')
    },
    goToPasswordResetPage(){
      this.$router.push('/password-reset')
    }

  }
};
</script>

<style scoped>
.login-container {
  text-align: center;
  height: 70vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

h1 {
  font-size: 2rem;
  color: #f7931e;
}

.login-box {
  background: #c2d7ff;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
  display: block;
  margin: 0.5rem 0;
}

input {
  width: 100%;
  padding: 0.5rem;
  margin-bottom: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.button-group {
  display: flex;
  justify-content: space-between;
}

button {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:first-of-type {
  background: #ccc;
}

button:last-of-type {
  background: #f7931e;
  color: white;
}

.links {
  margin-top: 1rem;
  font-size: 0.9rem;
}

.links a {
  color: #f00;
  text-decoration: none;
  display: block;
  margin: 0.5rem 0;
}

.links a:hover {
  text-decoration: underline;
}
</style>
