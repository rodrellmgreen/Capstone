<template>
  <v-container class="team-background">
    <h2 id="teamName">{{ team.teamName }}</h2>
    <v-container class="teams">
      <v-container>
        <h3 id="membersTitle">Members</h3>
        <ul id="membersName">
          <li v-for="member in members" :key="member.id">
            {{ member }}
          </li>
        </ul>
      </v-container>
      <v-container id="teamPosts">
        <v-card v-for="post in teamPosts" :key="post.postId" class="posts">
          <v-card-title id="posterName">{{ post.displayName }}</v-card-title>
          <v-card-text id="posterMessage">{{ post.message }}</v-card-text>
        </v-card>
        <v-card-actions>
          <v-container>
            <v-textarea
              v-model="post.message"
              label="Post a Message"
              auto-grow
              row="2"
              class="postArea"
            ></v-textarea>
            <v-btn @click="addPost(post)">Post</v-btn>
          </v-container>
        </v-card-actions>
      </v-container>
    </v-container>
  </v-container>
</template>

<script>
import teamService from "../services/TeamService.js";
import postService from "../services/PostService.js";
export default {
  data() {
    return {
      team: {},
      members: [],
      teamId: this.$route.params.teamIndex,
      post: {
        teamId: Number.parseInt(this.$route.params.teamIndex),
        message: "",
      },
      teamPosts: [],
    };
  },
  created() {
    teamService.getSpecificTeam(this.teamId).then((response) => {
      this.team = response.data;
    });
    teamService.getMembersOfTeam(this.teamId).then((response) => {
      this.members = response.data;
    });
    postService.getAllPostsForTeam(this.teamId).then((response) => {
      this.teamPosts = response.data;
    });
  },
  methods: {
    addPost(post) {
      postService.createPost(post).then((response) => {
        if (response.status == 201) {
          postService.getAllPostsForTeam(this.teamId).then((response) => {
            this.teamPosts = response.data;
            this.post = {};
          });
        }
      });
    },
  },
};
</script>

<style>
</style>