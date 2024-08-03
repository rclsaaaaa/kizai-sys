<template>
  <div>
    <button @click="showToast">Show Toast</button>
    <div v-if="show" class="toast">
      {{ message }}
      <button @click="hideToast" class="toast-close-btn">✖</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PassReset2View',
  data () {
    return {
      show: false,
      message: ''
    }
  },
  methods: {
    showToast () {
      this.message = 'This is a toast message.'
      this.show = true

      // 3秒後にトーストを非表示にする
      setTimeout(() => {
        this.show = false
      }, 3000)
    },
    hideToast () {
      this.show = false
    }
  }
}
</script>

<style>
.toast {
  position: fixed;
  bottom: 20px;
  left: 50%;
  transform: translateX(-50%);
  background-color: #333;
  color: #fff;
  padding: 50px;
  border-radius: 5px;
  z-index: 999;
}
.toast-close-btn {
  position: absolute;
  top: 5px;
  right: 5px;
  background: none;
  border: none;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
}
</style>

<!-- <template>
  <div>
    <button @click="showToast">Show Toast</button>
    <div v-if="show" class="toast">
      {{ message }}
      <button @click="hideToast" class="toast-close-btn">✖</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PassReset2View',
  data () {
    return {
      show: false,
      message: ''
    }
  },
  methods: {
    showToast () {
      this.message = 'This is a toast message.'
      this.show = true

      setTimeout(() => {
        this.show = false
      }, 3000)

      // 画面遷移
      this.$router.push('/Top')
    },
    hideToast () {
      this.show = false
    }
  },
  mounted () {
    // 画面遷移後にトーストを表示する
    if (this.$route.path === '/Top') {
      this.showToast()
    }
  }
}
</script>
 -->
