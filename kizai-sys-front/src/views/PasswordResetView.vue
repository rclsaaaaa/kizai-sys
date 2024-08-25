<template>
  <div class="password-reset-form">
    <div class="form-container">
      <h2>パスワード再設定</h2>
      <p>登録済みの社員番号を入力してください。</p>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <input type="text" v-model="employeeId" required placeholder="社員番号" />
        </div>
        <div class="button-group">
          <button type="button" @click="goBack">戻る</button>
          <button type="submit" @click="goToPasswordResetAuthCodePage">送信</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const URL = 'http://localhost:18080/user-info/password-reset'

export default {
  data() {
    return {
      employeeId: ''
    };
  },
  methods: {
    handleSubmit() {
      // 送信処理
      console.log('社員番号:', this.employeeId);
      // 認証コード送信のためのAPI呼び出しなどを実装
    },
    goBack() {
      // 戻る処理
      window.history.back();
    },
    goToPasswordResetAuthCodePage(){
      this.insertUserAuthCode()
      this.$store.commit('setAuthUserEmployeeId', this.employeeId);
      this.$router.push('/password-reset-auth-code')
    },
    insertUserAuthCode(){
      axios.put(`${URL}/${this.employeeId}`)
        .then(response => {
          
          console.log(response.data)
        })
        .catch(error => {
          console.error(error)
        })

    }
  }
};
</script>

<style scoped>
.password-reset-form {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 70vh;
}

.form-container {
  background: #c2d7ff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 300px;
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
}

.form-group input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

.button-group {
  display: flex;
  justify-content: space-between;
}

.button-group button {
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.button-group button[type="button"] {
  background-color: #ccc;
}

.button-group button[type="submit"] {
  background-color: #ff9800;
  color: white;
}
</style>
