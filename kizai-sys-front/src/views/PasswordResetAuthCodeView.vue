<template>
  <div class="verification-code-form">
    <div class="form-container">
      <h2>認証コード入力</h2>
      <p>メールアドレスに送信された6桁の認証コードを入力してください。</p>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <input type="text" v-model="verificationCode" required />
        </div>
        <div class="button-group">
          <button type="button" @click="goBack">戻る</button>
          <button type="submit" @click="goToLoginPage">送信</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      verificationCode: ''
    };
  },
  methods: {
    handleSubmit() {
      // 送信処理
      console.log('認証コード:', this.verificationCode);
      // 必要に応じて画面遷移を実装
    },
    goBack() {
      // 戻る処理
      window.history.back();
    },
    goToLoginPage(){
      this.$router.push('/login')
    }
  }
};
</script>

<style scoped>
.verification-code-form {
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
