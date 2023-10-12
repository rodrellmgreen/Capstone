<template>
  <div>
    <v-btn v-if="this.ifUserPhoneNumber" v-on:click="this.showTimeForm"
      >Set A Custom Text Alert</v-btn
    >

    <v-form class="txt-alert-date" v-if="formShow" v-on:submit="sendAlert">
      <input type="datetime-local" v-model="timeStamp" />
      <v-btn type="submit" v-if="submitTime">Confirm</v-btn>
    </v-form>
  </div>
</template>

<script>
import ProfileService from "../services/ProfileService.js";
export default {
  data() {
    return {
      userProfile: [],
      formShow: false,
      timeStamp: new Date(),
    };
  },

  created() {
    ProfileService.getProfile().then((response) => {
      this.newProfile = response.data;
    });
  },

  methods: {
    showTimeForm() {
      if (this.formShow == false) {
        this.formShow = true;
      } else {
        this.formShow = false;
      }
    },

    sendAlert() {
      ProfileService.sendSMS(this.timeStamp).then((response) => {
        console.log(response.status);
        console.log(response.data);
      });
      this.timeStamp = null;
    },
  },

  computed: {
    ifUserPhoneNumber() {
      if (this.userProfile.phoneNumber != "") {
        return true;
      } else {
        return false;
      }
    },
    submitTime() {
      if (this.timeStamp.length != null) {
        return true;
      } else {
        return false;
      }
    },

    triggerAlert() {
      let today = new Date();
      let date =
        today.getFullYear() +
        "-" +
        (today.getMonth() + 1) +
        "-" +
        today.getDate();
      let time = today.getHours() + ":" + today.getMinutes();
      let dateTime = date + " " + time;
      console.log("times compared: ", this.timeStamp == dateTime);
      if (this.timeStamp == dateTime) {
        return alert("phone alert sent");
      }
      return this.sendAlert();
    },
  },
};
</script>

<style>
</style>