import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import LoginView from '../views/LoginView.vue'
import PasswordResetAuthCodeView from '../views/PasswordResetAuthCodeView.vue'
import PasswordResetView from '../views/PasswordResetView.vue'
import SignupView from '../views/SignupView.vue'
import SignupAuthCodeView from '../views/SignupAuthCodeView.vue'

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
    path: '/password-reset-auth-code',
    name: 'passwordResetAuthCode',
    component: PasswordResetAuthCodeView
  },
  {
    path: '/password-reset',
    name: 'passwordReset',
    component: PasswordResetView
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignupView
  },
  {
    path: '/signup-auth-code',
    name: 'signupAuthCode',
    component: SignupAuthCodeView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
