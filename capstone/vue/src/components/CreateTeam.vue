<template>
  <div>
    <v-container id="createTeam">
      <v-text-field
        v-model="newTeam.teamName"
        label="Create A Team"
        auto-grow
        row="2"
        class="createTeam"
      >
      </v-text-field>
    </v-container>
    <v-btn @click="createTeam()">Create</v-btn>
  </div>
</template>

<script>
import teamService from "../services/TeamService.js";
export default {
  name: "create-team",
  data() {
    return {
      newTeam: {},
    };
  },

  methods: {
    createTeam() {
      teamService.createATeam(this.newTeam).then((response) => {
        if (response.status == 201) {
          alert("New Team Created!");
          window.location.reload();
        }
      });
    },
  },
};
</script>

<style>
</style>