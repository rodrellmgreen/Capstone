<template>
  <v-container class="card-container">
    <CreateProfile v-if="!this.currentProfile.displayName"/>
    <EditProfile v-if="edit"/>
    <text-alert v-if="!edit && !this.currentProfile.displayName==''"/>
    <v-btn v-if="!this.currentProfile.displayName == '' && edit" class="cancel-btn" v-on:click="edit = !edit">Cancel</v-btn>
    <ProfileDisplay v-if="!this.currentProfile.displayName == '' && !edit"/>
    <v-btn v-if="!this.currentProfile.displayName == '' && !edit" class="edit-btn" v-on:click="edit = !edit">Edit Profile</v-btn>
    
  </v-container>
</template>

<script>
import CreateProfile from '../components/CreateProfile.vue';
import EditProfile from '../components/EditProfile.vue';
import ProfileDisplay from '../components/ProfileDisplay.vue';
import ProfileService from '../services/ProfileService.js';
import TextAlert from '../components/TextAlert.vue';
export default {

  data() {
    return {

      edit: false,

      currentProfile: {}

    }
  },

  components: {
      CreateProfile,
      EditProfile,
      ProfileDisplay,
      TextAlert,
  },

  created() {
    ProfileService.getProfile().then(
        (response) => {
            this.currentProfile = response.data;
        }
      );
  },

  methods: {
    displayEditProfile() {

    }
  }

}
</script>

