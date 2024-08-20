<template>
  <div class="registration-form">
    <div class="form-container">
      <h2>新規登録</h2>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <input type="email" id="email" v-model="employeeAddress"  required placeholder="メールアドレス"/>
        </div>
        <div class="form-group">
          <input type="text" id="employeeId" v-model="employeeId" required placeholder="社員番号"/>
        </div>
        <div class="form-group">
          <input type="password" id="password" v-model="password" required placeholder="パスワード"/>
        </div>
        <div class="form-group">
          <input type="password" id="confirmPassword" v-model="confirmPassword" required placeholder="パスワード確認"/>
        </div>
        <div class="button-group">
          <button type="button" @click="goBack">戻る</button>
          <button type="submit" @click="goToSignupAuthCodePage">送信</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const URL = 'http://localhost:18080/user-info/registration'

export default {
  data() {
    return {
      employeeAddress: '',
      employeeId: '',
      password: '',
      confirmPassword: ''
    };
  },
  methods: {
    handleSubmit() {
      if (this.password !== this.confirmPassword) {
        alert('パスワードが一致しません。');
        return;
      }
      // 送信処理
      console.log('メールアドレス:', this.email);
      console.log('社員番号:', this.employeeId);
      console.log('パスワード:', this.password);
      // 必要に応じて画面遷移を実装
    },
    goBack() {
      // 戻る処理
      window.history.back();
    },
    insertUserInfo(){

      const formData = {
        employeeId:this.employeeId,
        password:this.password,
        employeeAddress:this.employeeAddress
      }

      axios.put(URL, formData)
        .then(response => {
          console.log(response.data)

        })
        .catch(error => {
          console.error(error)
        });

    },
    goToSignupAuthCodePage(){
      this.insertUserInfo()
      this.$router.push('/signup-auth-code')
    }
  }
};
</script>

<style scoped>
.registration-form {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.form-container {
  background: #c2d7ff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 300px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
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
