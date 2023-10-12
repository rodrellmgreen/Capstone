<template>
  <v-container class="myTeams">
    <h2 class="text-center">My Teams</h2>

    <ul class="teamsList">
      <li v-for="team in myTeams" :key="team.teamId">
        <router-link :to="{ name: 'team', params: { teamIndex: team.teamId } }">
          {{ team.teamName }}</router-link
        >
      </li>
    </ul>
  </v-container>
</template>

<script>
import teamService from "../services/TeamService.js";
export default {
  name: "my-teams",
  data() {
    return {
      myTeams: [],
    };
  },
  created() {
    teamService.getUsersTeams().then((response) => {
      this.myTeams = response.data;
    });
  },
  methods: {},
};
</script>

<style>
</style>





