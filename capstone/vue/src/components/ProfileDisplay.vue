<template>
  <v-container class="profile-container"> 
      <!-- Put profile picture in src="" below (currentProfile.profilePicture) -->
      
      <img class="profilePic" v-if="currentProfile.profilePicture == ''" src="../assets/defaultUserImg.png" alt="User Profile Picture">
      <img class="profilePic" v-if="currentProfile.profilePicture !== ''" :src="currentProfile.profilePicture" alt="User Profile Picture">
    <table id="profile">
    <td>
      <tr class="display-name">{{currentProfile.displayName}}</tr>
      <tr>Age: {{currentProfile.age}}</tr>
      <tr>Email: {{currentProfile.email}}</tr>
      <tr>Height: {{currentProfile.feet}}' {{currentProfile.inches}}''</tr>
      <tr>Current Weight: {{currentProfile.currentWeight}}</tr> 
      <tr>Goal Weight: {{currentProfile.desiredWeight}}</tr>
      <tr>{{publicPrivate}}</tr>
    </td>
    </table>
  </v-container>
</template>

<script>
import ProfileService from '../services/ProfileService.js';

export default {

    data() {
        return {
            currentProfile: {}
        }
    },

    created() {
        ProfileService.getProfile().then(
            (response) => {
                this.currentProfile = response.data;
            }
        )
    },

    computed: {
        publicPrivate() {
            if(this.currentProfile.publicPrivate) {
                return "Public";
            }
            else {
                return "Private";
            }
        }
    }


}
</script>

