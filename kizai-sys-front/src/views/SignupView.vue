<template>
  <div class="registration-form">
    <div class="form-container">
      <h2>新規登録</h2>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <input type="email" id="email" v-model="email"  required placeholder="メールアドレス"/>
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
          <button type="submit">送信</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
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
  background: rgba(255, 255, 255, 0.9);
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
