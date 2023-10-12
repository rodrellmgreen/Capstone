<template>
  <v-container class="quick-meals-background">
      <v-container class="form-container">
      <CreateMeal/>
      <MealDisplay/>
      </v-container>
  </v-container>
</template>

<script>
import CreateMeal from '../components/CreateMeal.vue';
import MealDisplay from '../components/MealDisplay.vue';

export default {

    components: {
        CreateMeal,
        MealDisplay
    }

}
</script>

<style>

</style>