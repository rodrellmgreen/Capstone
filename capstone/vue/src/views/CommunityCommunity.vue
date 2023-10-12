<template>
  <v-container id="community-background">
    <join-teams v-if="profile.publicPrivate"/>
    <my-teams v-if="profile.publicPrivate"/>
    <create-team v-if="profile.publicPrivate"/>

    <v-container v-if="!profile.publicPrivate">
      <h2 class="community-alert">Make your profile public to access community</h2>
    </v-container>

  </v-container>
</template>

<script>
import CreateTeam from "../components/CreateTeam.vue";
import JoinTeams from "../components/JoinTeams.vue";
import MyTeams from "../components/MyTeams.vue";
import ProfileService from "../services/ProfileService.js";

export default {
  components: { JoinTeams, MyTeams, CreateTeam },

  data() {
    return {
      profile: {}
    }
  },

  created() {
    ProfileService.getProfile().then((response) => {
      this.profile = response.data;
    });
  }
};
</script>

<style>
</style>