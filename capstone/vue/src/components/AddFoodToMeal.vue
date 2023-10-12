<template>
  <v-container></v-container>
</template>

<script>
import MealService from "../services/MealService.js";

export default {
  data() {
    return {};
  },

  methods: {
    addFoodToMeal() {
      MealService.addFoodToMeal(this.id, this.food).then((response) => {
        if (response.status === 201) {
          alert("Success! Food added");
          this.$router.go();
        }
      });
    },
  },
};
</script>

<style>
</style>