<template>
  <v-container class="meal-display-ctr">
    <h1 class="quick-meals-header">Quick Meals</h1>
    <form class="meal-form" @submit.prevent="createMeal">
      <label class="create-meal-label" for="name">Meal Name:</label>
      <input
        type="text"
        id="meal-name"
        class="input-box"
        placeholder="Ex: Spaghetti"
        v-model="mealObj.mealType"
        required
        autofocus
      />
      <v-btn small class="create-meal-btn" type="submit">Add Meal</v-btn>
    </form>
  </v-container>
</template>

<script>
import MealService from "../services/MealService.js";

export default {
  data() {
    return {
      mealObj: {
        mealType: "",
      },
    };
  },

  methods: {
    createMeal() {
      MealService.createMeal(this.mealObj).then((response) => {
        if (response.status === 201) {
          alert(`Success! ${this.mealObj.mealType} created`);
          this.$router.go();
        }
      });
    },
  },
};
</script>

<style>
</style>