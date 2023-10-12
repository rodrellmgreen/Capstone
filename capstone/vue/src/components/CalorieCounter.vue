<template>
  <v-container>
    <v-container>
      <h1>Calorie Total: {{ calorieTotal }}</h1>
    <h2>Daily Calorie Percentage: {{ updateCalorieBar }}%</h2>
    <v-container class="progress">
    
      <v-container
        class="goal_percentage"
        :style="{ width: updateCalorieBar + '%' }"
      >
        <span class="progress__text">great things loading...</span>
      </v-container>
    </v-container>
    </v-container>
  </v-container>
</template>

<script>
import FoodService from "../services/FoodService.js";
import ProfileService from "../services/ProfileService.js";
export default {
  data() {
    return {
      foodLog: [],
      profile: [],
      goal_percentage: 0,
    };
  },

  created() {
    FoodService.getTodaysFood().then((response) => {
      this.foodLog = response.data;
    });

    ProfileService.getProfile().then((response) => {
      this.profile = response.data;
    });
  },

  computed: {
    updateCalorieBar() {
      let goal_percentage = (this.calorieTotal / this.calorieGoal) * 100;

      goal_percentage = Math.round(goal_percentage);

      return goal_percentage;
    },

    calorieGoal() {
      if (this.profile.currentWeight > this.profile.desiredWeight) {
        return this.profile.currentWeight * 14.5 - 250;
      } else {
        return this.profile.currentWeight * 14.5 + 250;
      }
    },

    calorieTotal() {
      let total = 0;
      this.foodLog.forEach((food) => {
        total += food.calories;
      });
      return total;
    },
  },
};
</script>

<style>
.progress {
  position: relative;
  justify-content: center;
  width: 50%;
  height: 30px;
  border: 1px solid black;
  background: #ccc;
  border-radius: 10px;
  overflow: hidden;
}

.goal_percentage {
  height: 100%;
  background-color: green;

  transition: all 1s;
}

.progress__text {
  position: absolute;
  top: 50%;
  right: 5px;
  transform: translateY(-50%);
}
/* .bar[style] {
  background-color: yellow;
  width: 50%;
}

.bar[style][style] {
  background-color: red;
   width: 75%;
} */

/* .bar[style] {
  width: 50%;
}

.bar[style][style] {
  width: 75%;
} */
</style>