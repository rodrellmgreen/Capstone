<template>
  <v-container id="form-container" class="form-container">
    <v-form class="form-form" @submit.prevent="logFood" action="">
      <h1 class="edit-food-header">Edit Food</h1>

      <v-container class="flex-container">
        <label class="input-label" for="name">Food</label>
        <input
          class="input-box"
          id="name"
          type="text"
          v-model="$store.state.foodItemToUpdate.type"
          required
          placeholder="Ex: Potato"
        />
      </v-container>
      <v-container class="flex-container">
        <label class="input-label" for="calories">Calories</label>
        <input
          class="input-box"
          id="calories"
          type="number"
          v-model="$store.state.foodItemToUpdate.calories"
          required
          placeholder="Ex: 163"
        />
      </v-container>
      <v-container class="flex-container">
        <label class="input-label" for="carbs">Carbs</label>
        <input
          class="input-box"
          id="carbs"
          type="number"
          v-model="$store.state.foodItemToUpdate.carbs"
          required
          placeholder="Ex: 37"
        />
      </v-container>
      <v-container class="flex-container">
        <label class="input-label" for="protein">Protien</label>
        <input
          class="input-box"
          id="protein"
          type="number"
          v-model="$store.state.foodItemToUpdate.protein"
          required
          placeholder="Ex: 4"
        />
      </v-container>
      <v-container class="flex-container">
        <label class="input-label" for="fats">Fats</label>
        <input
          class="input-box"
          id="fats"
          type="number"
          v-model="$store.state.foodItemToUpdate.fats"
          required
          placeholder="Ex: 0"
        />
      </v-container>
      <v-container class="flex-container">
        <label class="input-label" for="fiber">Fiber</label>
        <input
          class="input-box"
          id="fiber"
          type="number"
          v-model="$store.state.foodItemToUpdate.fiber"
          required
          placeholder="Ex: 5"
        />
      </v-container>

      <v-container class="flex-container">
        <label class="input-label" for="servings">Serving</label>
        <input
          class="input-box"
          id="servings"
          type="number"
          v-model="$store.state.foodItemToUpdate.servingSize"
          required
          placeholder="Ex: 1"
        />
      </v-container>

      <v-container class="flex-container">
        <label class="input-checkbox" for="quick-add">Enable "Quick Add"</label>
        <input id="quick-add" type="checkbox" v-model="$store.state.foodItemToUpdate.quickAdd" />
      </v-container>

      <v-container class="flex-container">
        <label class="input-label" for="date">Date</label>
        <input
          class="input-box"
          id="date"
          type="date"
          v-model="$store.state.foodItemToUpdate.date"
          required
          placeholder="Ex: 1"
        />
      </v-container>

      <v-container id="mealType" class="flex-container">
        <v-container>
          <!-- <v-btn v-on:click="updateFood"> Snack </v-btn>
          <v-btn v-on:click="updateFood"> Breakfast </v-btn>
          <v-btn v-on:click="updateFood"> Lunch </v-btn> -->
          <v-btn class="update-food-btn" v-on:click="updateFood"> Update </v-btn>
          <v-btn v-on:click="deleteFood"> Delete Food </v-btn>

          

          
        </v-container>
      </v-container>
    </v-form>
  </v-container>
</template>

<script>
import FoodService from '../services/FoodService';
//import FoodService from "../services/FoodService"
export default {
 

    data(){
        return {
    

      foodItem: {
        type: "",
        calories: null,
        carbs: null,
        protein: null,
        fats: null,
        fiber: null,
        servingSize: null,
        quickAdd: null,
        date: "",
      },
    };
    },
    created() {
    
  },

    computed: {
    showOptions() {
      if (
        this.foodItem.type &&
        this.foodItem.calories &&
        this.foodItem.carbs &&
        this.foodItem.protein &&
        this.foodItem.fats &&
        this.foodItem.fiber &&
        this.foodItem.servingSize
      ) {
        return true;
      } else {
        return false;
      }
    },
  },
    methods: {
    updateFood(){
      this.foodItem = this.$store.state.foodItemToUpdate
       FoodService.updateFoodItem(this.foodItem.foodId, this.foodItem).then((response) => {
      if (response.status == 200) {
        this.$router.go();
      }
    });
    },
     deleteFood(){
      this.foodItem =this.$store.state.foodItemToUpdate
      FoodService.deleteFoodItem(this.foodItem.foodId).then((response)=> {
        if (response.status == 200){
          this.$router.go();
        }
      })
    },
       
    
}
    
}
</script>

<style>

</style>