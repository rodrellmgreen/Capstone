<template>
  <v-container id="form-container" class="form-container">
    <v-form class="form-form" @submit.prevent="editProfile">
      <h1 class="edit-profile-header">Edit Profile</h1>
      <v-container class="flex-container">
        <label for="email" class="input-label">Email Address:</label>
        <input
          type="text"
          id="email"
          class="input-box"
          v-model="newProfile.email"
          required
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="age" class="input-label">Age:</label>
        <input
          type="number"
          id="age"
          class="input-box"
          placeholder="age"
          v-model="newProfile.age"
          required
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="height-feet" class="input-label">Height (ft):</label>
        <input
          type="number"
          id="height-feet"
          class="input-box"
          placeholder="height (ft)"
          v-model="newProfile.feet"
          required
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="height-inches" class="input-label">Height (in):</label>
        <input
          type="number"
          id="height-inches"
          class="input-box"
          placeholder="height (in)"
          v-model="newProfile.inches"
          required
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="current-weight" class="input-label">Current Weight:</label>
        <input
          type="number"
          id="current-weight"
          class="input-box"
          placeholder="current weight"
          v-model="newProfile.currentWeight"
          required
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="desired-weight" class="input-label">Desired Weight:</label>
        <input
          type="number"
          id="desired-weight"
          class="input-box"
          placeholder="desired weight"
          v-model="newProfile.desiredWeight"
          required
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="displayName" class="input-label">DisplayName:</label>
        <input
          type="text"
          id="displayName"
          class="input-box"
          placeholder="display name"
          v-model="newProfile.displayName"
          required
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="profilePic" class="input-label">Add Profile Picture:</label>
        <input
          type="text"
          id="profilePic"
          class="input-box"
          placeholder="profile picture url"
          v-model="newProfile.profilePicture"
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="publicPrivate" class="input-label">Phone Number:</label>
        <input
          type="text"
          id="phoneNumber"
          class="input-box"
          v-model="newProfile.phoneNumber"
          autofocus
        />
      </v-container>
      <v-container class="flex-container">
        <label for="publicPrivate" class="input-label">Make Public:</label>
        <input
          type="checkbox"
          id="publicPrivate"
          class="input-box"
          v-model="newProfile.publicPrivate"
          autofocus
        />
      </v-container>
      <v-btn class="edit-profile-button" type="submit">Submit</v-btn>
    </v-form>
  </v-container>
</template>

<script>
import ProfileService from "../services/ProfileService.js";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "profile",

  data() {
    return {
      newProfile: {
        email: "",
        age: null,
        feet: null,
        inches: null,
        currentWeight: null,
        desiredWeight: null,
        displayName: "",
        profilePicture: "",
        phoneNumber: "",
        publicPrivate: null,
      },
    };
  },

  created() {
    ProfileService.getProfile().then((response) => {
      this.newProfile = response.data;
    });
  },

  methods: {
    editProfile() {
      ProfileService.editProfile(this.newProfile).then((response) => {
        if (response.status === 200) {
          alert("Success! Profile changed.");
          this.$router.go();
        }
      });
    },
  },
};
</script>

<style>
/** Check App.vue for styles */
</style>