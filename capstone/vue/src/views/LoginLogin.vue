<template>
  <v-container class="login">
    <!-- <h1 class="login-header">Welcome to TechFitness</h1> -->

    <v-container id="login" class="text-center">
      <v-form class="form-signin" @submit.prevent="login">
        <ul class="flex-outer">
          <h1 class="login-header">Welcome to TechFitness!</h1>
          <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
          <v-container
            class="alert alert-danger"
            role="alert"
            v-if="invalidCredentials"
            >Invalid username and password!</v-container
          >
          <v-container
            class="alert-success"
            role="alert"
            v-if="this.$route.query.registration"
            >Thank you for registering, please sign in.</v-container
          >
          <li>
            <label for="username" class="sr-only">Username:</label>
            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            />
          </li>
          <li>
            <label for="password" class="sr-only">Password:</label>
            <input
              type="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="user.password"
              required
            />
          </li>
          <router-link :to="{ name: 'register' }">Need an account?</router-link>
          <v-btn class="sign-in" type="submit">Sign in</v-btn>
        </ul>
      </v-form>
    </v-container>
  </v-container>
</template>

<script>
import authService from "../services/AuthService";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/home");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

