<template>
  <v-container class="home-background">
    <v-container class="home">
      <h1 id="home-title">TEF Stats</h1>
      <p v-if="$store.state.token == ''">
        You must be authenticated to see this
      </p>
      <router-link v-if="$store.state.token == ''" v-bind:to="{ name: 'login' }"
        >Login</router-link
      ><br />

      <v-container id="progress-charts">
      <weekly-graph class="graphComponent" />
      <progress-circle class="progressComponent" />
      </v-container>

      <v-container class="home-table">
      <recent-food />
      </v-container>
    </v-container>
  </v-container>
</template>

<script>
import RecentFood from '../components/RecentFood.vue'
import WeeklyGraph from '../components/WeeklyGraph.vue'
import ProgressCircle from '../components/ProgressCircle.vue'

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "home",
  components: {
    RecentFood,
    WeeklyGraph,
    ProgressCircle,
  },
};
</script>
