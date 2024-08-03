import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import LoginView from '../views/LoginView.vue'
import PasswordResetCodeView from '../views/PasswordResetCodeView.vue'
import PasswordResetView from '../views/PasswordResetView.vue'

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/password-reset-code',
    name: 'passwordResetCode',
    component: PasswordResetCodeView
  },
  {
    path: '/password-reset',
    name: 'passwordReset',
    component: PasswordResetView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
