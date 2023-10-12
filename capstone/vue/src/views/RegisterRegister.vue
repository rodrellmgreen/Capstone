<template>
  <v-container id="register-background">
    <v-container id="register-form-container" class="form-container">
    <v-form class="form-form" @submit.prevent="register">
      <h1 class="register-header">Create Account</h1>
      <v-container class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </v-container>
      <v-container class="flex-container">
      <label for="username" class="input-label">Username:</label>
      <input
        type="text"
        class="input-box"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      </v-container>
      <v-container class="flex-container">
      <label for="password" class="input-label">Password:</label>
      <input
        type="password"
        class="input-box"
        placeholder="Password"
        v-model="user.password"
        required
      />
      </v-container>
      <v-container class="flex-container">
      <label for="confirmPassword" class="input-label">Confirm Password:</label>
      <input
        type="password"
        class="input-box"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      </v-container>
      
      <v-btn class="create-account-btn" type="submit">
        Create Account
      </v-btn>
      <!-- <v-btn class="cancel-btn" v-on:click.prevent="sendHome">Cancel</v-btn> -->
      
      <router-link class="router-link" :to="{ name: 'login' }">Have an account?</router-link>
    </v-form>
    </v-container>
  </v-container>
</template>

<script>
import authService from '../services/AuthService';

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
    sendHome() {
      this.user.username = '';
      this.user.password = '';
      this.user.confirmPassword = '';
      this.$router.push('/');
    }
  },
};
</script>

<style>
/** Check App.vue for styles */
</style>
