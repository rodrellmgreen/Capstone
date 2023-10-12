<template>
  <v-container class="quick-add">

      <h1 class="quick-add-header"> Quick Add Foods </h1>
      <v-form class="quick-add-form" v-for="food in quickAddFoodsFiltered" v-bind:key="food">
       
        <input class="quick-add-input" type="radio" v-bind:value="food.type" v-on:change="addToFoodItem($event)">
         
          {{food.type}} -
          Calories: {{food.calories}},
          Protein: {{food.protein}},
          Carbs: {{food.carbs}},
          Fat: {{food.fats}},
          Fiber: {{food.fiber}},
          Serving Size: {{food.servingSize}}
         
      </v-form>
      <v-btn class="quick-add-btn" small v-on:click="logFood">Log Food Item</v-btn>
  </v-container>
</template>

<script>
import FoodService from '../services/FoodService.js';

export default {

    data() {
      return {

        quickAddFoodsUnfiltered: [],

        foodItem: {
          type: "",
          calories: null,
          carbs: null,
          protein: null,
          fats: null,
          fiber: null,
          servingSize: null, 
          quickAdd: null,
          date: null,
        }

      }
    },

    methods: {
      logFood() {
        FoodService.createFood(this.foodItem).then(
            (response) => {
                if(response.status === 201) {
                  alert("Success! Food Added.");
                  this.$router.go();
                  
                }
            } 
        );
      },

      addToFoodItem(event) {
        if(this.foodItem.type !== event.target.value) {
          for(let i=0; i<this.quickAddFoodsUnfiltered.length; i++) {
            if(this.quickAddFoodsUnfiltered[i].type == event.target.value) {
              this.foodItem.type = this.quickAddFoodsUnfiltered[i].type;
              this.foodItem.calories = this.quickAddFoodsUnfiltered[i].calories;
              this.foodItem.carbs = this.quickAddFoodsUnfiltered[i].carbs;
              this.foodItem.protein = this.quickAddFoodsUnfiltered[i].protein;
              this.foodItem.fats = this.quickAddFoodsUnfiltered[i].fats;
              this.foodItem.fiber = this.quickAddFoodsUnfiltered[i].fiber;
              this.foodItem.servingSize = this.quickAddFoodsUnfiltered[i].servingSize;
              this.foodItem.quickAdd = this.quickAddFoodsUnfiltered[i].quickAdd;
              this.foodItem.date = this.quickAddFoodsUnfiltered[i].date;
            }
          }
        }
      }
    },

    created() {
        FoodService.getQuickAddFoods().then(
          (response) => {
            this.quickAddFoodsUnfiltered = response.data;
          }
        );
    },

    computed: {
      quickAddFoodsFiltered() {
        let quickAddFoodsFiltered = this.quickAddFoodsUnfiltered;
        //let duplicateIndexArr = [];
        for(let i=0; i<this.quickAddFoodsUnfiltered.length; i++) {
          for(let j=0; j<this.quickAddFoodsUnfiltered.length; j++) {
            if(i !== j && this.quickAddFoodsUnfiltered[i].type == this.quickAddFoodsUnfiltered[j].type && this.quickAddFoodsUnfiltered[i].calories == this.quickAddFoodsUnfiltered[j].calories) {
              quickAddFoodsFiltered.splice(j, 1);
            }
          }
        }
        
        return quickAddFoodsFiltered;
      }
    }

}
</script>

<style>

  template {
    text-align: center;
    display: flex;
  }

  .quick-add {
    background-color: #174C4F;
    color: white;
    border: solid grey 5px;
    border-radius: 5px;
    width: 60%;
    text-align: center;
    margin: 10px;
    margin-left: auto;
    margin-right: auto;
  }

  .quick-add-container {
    /* background-color: white; */
    border: solid white 1px;
    border-radius: 5px;
  }
</style>