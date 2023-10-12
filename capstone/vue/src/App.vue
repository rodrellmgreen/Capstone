<template>
  <v-app id="app">
    <v-container class="header"><img src="~@/assets/banner.png" /></v-container>
    <v-container id="navbar">
      <a id="logo-container"
        ><router-link v-bind:to="{ name: 'home' }">
          <img id="logo" src="~@/assets/tef.jpg" /> </router-link
      ></a>

      <a
        ><router-link
          v-bind:to="{ name: 'home' }"
          v-if="$store.state.token != ''"
          >Home</router-link
        ></a
      >

      <a
        ><router-link
          v-bind:to="{ name: 'trackFood' }"
          v-if="$store.state.token != ''"
          >Food</router-link
        ></a
      >

      <a
        ><router-link
          v-bind:to="{ name: 'profile' }"
          v-if="$store.state.token != ''"
          >Profile</router-link
        ></a
      >

      <a
        ><router-link
          v-bind:to="{ name: 'community' }"
          v-if="$store.state.token != ''"
          >Community
        </router-link></a
      >

      <a
        ><router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        ></a
      >
    </v-container>
    <router-view />
  </v-app>
</template>

<script>
import profileService from "./services/ProfileService.js";
export default {
  name: "my-teams",
  data() {
    return {
      profile: {},
    };
  },
  created() {
    profileService.getProfile().then((response) => {
      this.profile = response.data;
    });
  },
  methods: {},
};
</script>


<style>
.graphComponent {
  float: left;
  width: 50%;
  margin-top: 3%;
}

.progressComponent {
  float: right;
  width: 0%;
  padding: 0px;
  margin-top: 3.22%;
}

.calorie-chart-container {
  background: #174c4f;
  border: solid black 1px;
  border-radius: 10px;
}

.circle-graph-title {
  margin-top: 15px;
  color: whitesmoke;
}

.circle-chart {
  margin-top: 7%;
  /* width: 125%;
  height: 100%; */
}

.daily-cals {
  /* float: right; */
  width: 45%;
  padding: 0px;
  background: #174c4f;
  border: solid black 1px;
  border-radius: 10px;
  margin-right: auto;
}

/* .home-table {
 
} */

#app {
  font-size: 1 0 1rem;
  font-family: "Trebuchet MS", "Lucida Sans Unicode", "Lucida Grande",
    "Lucida Sans", Arial, sans-serif;
  text-decoration-line: none;
  font-weight: 900;
  color: #174c4f;
  text-align: center;
  justify-content: center;
  display: flex;

  /* can be treated like a fallback */
  background-color: rgb(97, 96, 96);

  /* will be "on top", if browser supports it */
  background-image: linear-gradient(rgb(97, 96, 96), whitesmoke);

  /* these will reset other properties, like background-position, but it does know what you mean */
  background: rgb(97, 96, 96);
  background: linear-gradient(rgb(97, 96, 96), whitesmoke);
}

.home-background {
  background-image: url("./assets/home-background.jpg");
  background-size: cover;
  height: 110vh;
}

.container.home {
  display: flex;
  flex-direction: column;
}

#home-title {
  border: solid black 2px;
  border-radius: 10px;
  background-color: #174c4f;
  color: whitesmoke;
  width: 50%;
  display: inline;
  margin-left: auto;
  margin-right: auto;
}

h1 {
  display: flex;
  margin: 0%;
  padding: 0%;
}

v-container {
  display: flexbox;
}

.profilePic {
  width: 25%;
  display: block;
  margin: 0 auto;
  border-radius: 25px;
}
#profile {
  display: flex;
  font-size: 2vw;
}

.profile-container {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
}

.edit-profile-header {
  justify-content: center;
  margin-bottom: 2%;
}

.edit-btn {
  margin-bottom: 5%;
}

.meal-display {
  margin-top: 3%;
}

.meal-display-ctr {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 2%;
}

.quick-meals-background {
  background-image: url("./assets/home-background.jpg");
  background-size: cover;
  height: 75vh;
}

.quick-meals-header {
  margin-bottom: 3%;
}

.add-food-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 3%;
}

.add-food-input {
  margin: 3px;
  background-color: whitesmoke;
}

.meal-display-btn {
  margin: 0.5%;
}

.create-meal-label {
  width: 110px;
  margin-right: 1%;
}

.meal-form {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.create-meal-btn {
  justify-content: center;
}

#meal-name {
  margin-right: 1%;
}

.meal-list {
  margin: 5px;
  font-size: 2.5 rem;
}

.log-meal-form {
  display: flex;
  flex-direction: column;
  margin-top: 2%;
  width: 25%;
  margin-left: auto;
  margin-right: auto;
}

.log-btn {
  margin-top: 2%;
}

.add-food-btn {
  margin-top: 1%;
}

.edit-food-header {
  justify-content: center;
  margin-top: 1%;
}

#date {
  background-color: whitesmoke;
}

#navbar {
  background-color: #174c4f;
  display: flex;
  align-items: flex-end;
  text-align: center;
  justify-content: space-between;
  height: 10vh;
}
#navbar a {
  float: left;
  display: flex;
  color: #f2f2f2;
  padding: 0px;
  text-decoration: none;
  justify-content: center;
  font-size: 1.5vw;
  margin-right: 1vw;
}

#logo-container {
  display: flex;
  justify-content: flex-start;
  max-width: 25%;
  height: 90%;
}
#logo {
  max-height: 110%;

  justify-content: flex-start;
}

.header {
  display: flex;
  background-color: #f1f1f1;
  text-align: center;
  padding: 20px;
  height: 25vh;
}
img {
  display: flex;

  width: 100%;
}

v-container.login {
  background-image: url("~@/assets/overnightOats.jpg");
  height: 200vh;
  background-size: cover;
  text-align: center;
}
v-container.home {
  width: 200vh;
}
/* v-container.home-background {
  background-image: url("~@/assets/omelete.jpg");
  background-size: cover;
  background-repeat: repeat;
  height: 200vh;
} */
v-container.food-tracker {
  background-image: url("~@/assets/steak.jpg");
  background-size: cover;
  height: 200vh;
}

#register-form-container {
  margin-top: 3%;
}

.form-form {
  margin-top: 1%;
  padding-bottom: 1%;
}

.quick-add-header {
  margin-bottom: 3%;
  margin-left: 38%;
}

.quick-add-form {
  display: flex;
  margin-bottom: 3%;
  justify-content: center;
}

.quick-add-input {
  margin-right: 1%;
}

.quickMeals {
  text-decoration: none;
  color: whitesmoke;
  margin: 1%;
}

.quick-add-btn {
  margin-bottom: 1%;
}

/** Login Styling Below */

.login {
  background-image: url("./assets/veg-l.jpg");
  background-size: cover;
  height: 100vh;
}

#login.text-center {
  margin-top: 3%;
  width: 60%;
}

#username {
  background: whitesmoke;
}

#password {
  background: whitesmoke;
}

.login-header {
  justify-content: center;
}

#register-background {
  background-image: url("./assets/veg-l.jpg");
  background-size: cover;
  height: 100vh;
}

.register-header {
  justify-content: center;
  margin-top: 1%;
  margin-bottom: 1.5%;
}

.flex-outer li,
.flex-inner {
  display: flex;
  justify-content: center;

  align-items: center;
}

.flex-outer > li > input {
  flex: 1 0 1rem;

  max-width: 220px;
}

.track-food-background {
  background-image: url("./assets/veg-l.jpg");
  background-size: cover;
  height: 115vh;
}

.food-tracker-form-header {
  justify-content: center;
  margin-bottom: 2%;
  margin-top: 1%;
}

/** Food Tables/Logs Styling Below */
#foodLogTable {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
  height: 100%;
  display: flex;
  padding: 0%;
  margin: 0%;
  border: 1px solid #ddd;
  border: solid black 2px;
  border-radius: 10px;
  flex-direction: column;
}

.update-food-btn {
  margin-right: 2%;
}

#streak {
  border: solid black 2px;
  border-radius: 10px;
  background-color: #174c4f;
  color: whitesmoke;
  width: 100%;
  font-size: 1.5em;
}

tr {
  background-color: #f2f2f2;
}

.tableHeader {
  vertical-align: middle;
  background-color: #174c4f;
  color: white;
  padding: 1%;
}
.tableCaption {
  font: 2rem;
}

th {
  background-color: #174c4f;
  color: white;
}

.card-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 3%;
  padding: 0px;
  border-radius: 25%;
}

#foodDiary {
  width: 100%;
  padding: 1%;
  display: flex;
  flex-direction: column;
  max-height: 500px;
}

/** Form Styling Below */

.mealType {
  font-size: 1rem;
}
.form-container {
  background: #174c4f;
  color: whitesmoke;
  border: solid black 2px;
  border-radius: 10px;
  padding: 0%;
  width: 50%;
  display: flex;
  flex-direction: column;
  margin-top: 1%;
  margin-bottom: 3%;
}
.flex-container {
  display: flex;
  padding: 2px;
  padding-right: 2%;
  text-align: center;
  align-content: center;
  justify-content: center;
}

.create-account-btn {
  margin-right:2%;
}

#quick-add {
  /* justify-content: right; */
  display: flex;
}

.input-label {
  display: flex;
  font-size: 2rem;
  padding-left: 2%;
}

label {
  margin-right: auto;
  display: flex;
  size: 2rem;
}
input {
  justify-content: center;
  display: flex;
  size: 1rem;
}

.input-box {
  background-color: whitesmoke;
  display: flex;
  padding-right: 2%;
}

.input-checkbox {
  padding-left: 2%;
}

#quick-add {
  margin-right: 25%;
}

#date.input-box {
  margin-right: 7%;
}

.sign-in {
  margin: 10px;
}

.cancel-btn {
  width: 95px;
  margin-bottom: 6%;
}

.txt-alert-date {
  background: whitesmoke;
  margin: 1%;
  margin-top: 2%;
  border: solid black 1px;
  border-radius: 5px;
}

.alert {
  color: red;
}

.sr-only {
  margin: 5px;
}

.form-control {
  margin: 1%;
  height: 5%;
}

.router-link {
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 5%;
}

.create-profile-header {
  justify-content: center;
  margin-bottom: 2%;
}

.create-profile-button {
  margin-bottom: 2%;
}

#publicPrivate {
  margin-right: auto;
  margin-left: auto;
}

/* community styling */

#community-background {
  background-image: url("./assets/community-background.jpg");
  background-size: cover;
  height: 75vh;
}

.team-background {
  background-image: url("./assets/community-background.jpg");
  background-size: cover;
  height: 75vh;
}

.myTeams {
  display: flex;
  flex-direction: column;
  list-style: none;
  width: 25vw;
}

.joinTeamsAll {
  display: flex;
  flex-direction: column;
  width: 25vw;
}
.text-center {
  border: solid black 2px;
  border-radius: 10px;
  background-color: #174c4f;
  color: whitesmoke;
  font-size: 1.5vw;
}

.allTeams {
  list-style: none;
  font-size: 2vw;
}

.allTeamsHeader {
  border: solid black 2px;
  border-radius: 10px;
  background-color: #174c4f;
  color: whitesmoke;
  justify-content: center;
}

.teamsList {
  list-style: none;
  font-size: 1.5vw;
}
#createTeam {
  display: flex;
  width: 25vw;
}
#membersTitle {
  border: solid black 2px;
  border-radius: 10px;
  background-color: #174c4f;
  color: whitesmoke;
  width: 25vw;
  font-size: 2vw;
}
#membersName {
  list-style: none;
  font-size: 1.5vw;
  width: 25vw;
}
#teamName {
  font-size: 2vw;
}
#teamPosts {
  width: 100%;
  flex-direction: column;
  margin-right: 20vw;
}
.teams {
  display: flex;
}

.community-alert {
  margin-top: 5%;
}
</style>