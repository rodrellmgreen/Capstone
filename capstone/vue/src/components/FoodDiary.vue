<template>
  <v-container id="foodDiary">
    <v-container id="foodLogTable" style="overflow-x: auto">
      <table id="food-log">
        <caption class="tableHeader">
          Food Log
        </caption>
        <tbody>
          <tr>
            <th>Date</th>
            <th>Food</th>
            <th>Calories</th>
            <th>Carbs</th>
            <th>Protein</th>
            <th>Fats</th>
            <th>Fiber</th>
            <th>Servings</th>
            <th>Edit Food</th>
          </tr>
          <tr v-for="food in sortedFood" :key="food">
            <td>{{ food.date }}</td>
            <td>{{ food.type }}</td>
            <td>{{ food.calories }}</td>
            <td>{{ food.carbs }}</td>
            <td>{{ food.protein }}</td>
            <td>{{ food.fats }}</td>
            <td>{{ food.fiber }}</td>
            <td>{{ food.servingSize }}</td>

            <td>
              <v-btn
                class="editFoodEntry"
                v-if="editBtn != food.foodId"
                v-on:click="startUpdate(food)"
                >Edit</v-btn
              >
              <v-btn
                class="editFoodEntry"
                v-if="editBtn === food.foodId"
                v-on:click="endUpdate"
                >Cancel</v-btn
              >
            </td>
          </tr>
        </tbody>
      </table>
    </v-container>

    <v-container id="streak">Food Log Streak: {{ this.streak }}</v-container>
  </v-container>
</template>

<script>
import FoodService from "../services/FoodService.js";

export default {
  methods: {
    startUpdate(food) {
      this.editBtn = food.foodId;
      this.$store.state.updateFoodItem = !this.$store.state.updateFoodItem;
      this.$store.state.foodItemToUpdate = food;
    },
    endUpdate() {
      this.editBtn = 0;
      this.$store.state.updateFoodItem = !this.$store.state.updateFoodItem;
    },
  },

  data() {
    return {
      foodLog: [],
      updateFoodItem: false,
      editBtn: 0,
    };
  },
  created() {
    FoodService.getAllFoods().then((response) => {
      this.foodLog = response.data;
    });
  },

  computed: {
    sortedFood() {
      return [...this.foodLog].sort((a, b) => {
        const dateA = new Date(a.date);
        const dateB = new Date(b.date);
        if (dateA > dateB) {
          return -1;
        }
        if (dateA < dateB) {
          return 1;
        }
        return this.foodLog.indexOf(b) - this.foodLog.indexOf(a);
      });
    },
    streak() {
      let streak = 0;

      for (let i = 0; i < this.sortedFood.length - 1; i++) {
        let newDate = new Date(this.sortedFood[i].date);
        let oldDate = new Date(this.sortedFood[i + 1].date);
        let oldDatePlusOne = new Date(oldDate.getTime() + 24 * 60 * 60 * 1000);
        if (newDate > oldDatePlusOne) {
          return streak;
        }
        if (
          newDate.toISOString().slice(0, 10) ==
          oldDatePlusOne.toISOString().slice(0, 10)
        ) {
          streak++;
        }
      }

      return streak;
    },
  },
};
</script>

<style>
</style>