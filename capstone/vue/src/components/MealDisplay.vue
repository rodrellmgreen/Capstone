<template>
  <v-container class="meal-display">
      <!-- <h1>Meal List</h1> -->
      <v-container >
      <ul v-for="meal in meals" :key="meal.mealId">
          <li class="meal-list">{{meal.mealType}}</li>
          <v-btn class="meal-display-btn" small v-on:click="showAddFood = !showAddFood, mealType=meal.mealType, mealId=meal.mealId, addFoodBtn = meal.mealId" v-if="addFoodBtn != meal.mealId">Add Food</v-btn>
          <v-btn class="meal-display-btn" small v-on:click="showAddFood = !showAddFood, addFoodBtn=0" v-if="addFoodBtn === meal.mealId">Cancel</v-btn>
          <v-btn class="meal-display-btn" small v-if="viewFoodsBtn != meal.mealId" v-on:click="viewFoodsBtn = meal.mealId, viewFoods(meal.mealId), showFoods=true">View Foods</v-btn>
          <v-btn class="meal-display-btn" small v-if="viewFoodsBtn === meal.mealId" v-on:click="viewFoodsBtn = 0, showFoods=false">Cancel</v-btn>
          <v-btn class="meal-display-btn" small v-on:click="removeMeal(meal.mealId)">Remove Meal</v-btn>
          <v-btn class="meal-display-btn" small v-if="logMealBtn != meal.mealId" v-on:click="getFoods(meal)">Log</v-btn>
          <v-btn class="meal-display-btn" small v-if="logMealBtn === meal.mealId" v-on:click="addDate=!addDate, logMealBtn = 0">Cancel</v-btn>

          <v-container class="view-foods-list-container" v-if="showFoods && viewFoodsBtn === meal.mealId">
          <ul class="view-foods-list">
            <li v-for="food in foods" :key="food" >
              {{food.type}}
            </li>
          </ul>
          </v-container>

          <v-form class="log-meal-form" v-if="addDate && logMealBtn === meal.mealId" @submit.prevent="logMeal">
            <label for="date">Please enter date:</label>
            <input id="date" type="date" v-model="foodItem.date"/>
            <v-btn small class="log-btn" type="submit">Log</v-btn>
          </v-form>

          <v-form class="add-food-form" v-if="showAddFood && addFoodBtn === meal.mealId">
            <h3>{{mealType}}</h3>
            <!-- <label for="food">Food:</label> -->
            <input class="add-food-input" placeholder="food" v-model="foodItem.type" id="food" type="text" required/>
            <!-- <label for="calories">Calories:</label> -->
            <input class="add-food-input" placeholder="calories" v-model="foodItem.calories" id="calories" type="number" required/>
            <!-- <label for="protein">Protein:</label> -->
            <input class="add-food-input" placeholder="protein" v-model="foodItem.protein" id="protein" type="number" />
            <!-- <label for="carbs">Carbs:</label> -->
            <input class="add-food-input" placeholder="carbs" v-model="foodItem.carbs" id="carbs" type="number" />
            <!-- <label for="fiber">Fiber:</label> -->
            <input class="add-food-input" placeholder="fiber" v-model="foodItem.fiber" id="fiber" type="number" />
            <!-- <label for="fat">Fat:</label> -->
            <input class="add-food-input" placeholder="fat" v-model="foodItem.fat" id="fat" type="number" />
            <!-- <label for="serving-size">Serving Size:</label> -->
            <input class="add-food-input" placeholder="serving size" v-model="foodItem.servingSize" id="serving-size" type="number" />
            <v-btn class="add-food-btn" small v-on:click="addFoodToMeal">Add</v-btn>
          </v-form>

      </ul>

      </v-container>
  </v-container>
</template>

<script>
import MealService from '../services/MealService.js';
import FoodService from '../services/FoodService.js';

export default {

    data() {
        return{
            viewFoodsBtn: 0,
            logMealBtn: 0,
            addFoodBtn: 0,
            meals: [],
            showFoods: false,
            showAddFood: false,
            addDate: false,
            mealType: "",
            mealId: null,
            foodItem: {
                type: "",
                calories: null,
                carbs: null,
                protein: null,
                fats: null,
                fiber: null,
                servingSize: null, 
                quickAdd: false,
                date: "",
            },
            foods: []
        }
    },

    created() {
       MealService.getMeals().then(
           (response) => {
               this.meals = response.data;
           }
       );
    //    MealService.getFoods(3).then(
    //             (response) => {
    //                 this.foods = response.data;
    //             }
    //         );
    },

    methods: {
        removeMeal(id) {
            MealService.deleteMeal(id).then(
                (response) => {
                    if(response.status === 200) {
                        alert("Meal removed from list.");
                        this.$router.go();
                    }
                }
            );
        },
        addFoodToMeal() {
            MealService.addFoodToMeal(this.mealId, this.foodItem).then(
                (response) => {
                    if(response.status === 201) {
                        alert(`Success! ${this.foodItem.type} added to meal`);
                        this.$router.go();
                    }
                }
            );
        },
        logMeal() {
            this.addDate=!this.addDate;
            this.logMealBtn = 0;
            var strInputValue = this.foodItem.date; // <-- get my date string
            let strInputValue2 = strInputValue
                .replace(/-/, "/") // replace 1st "-" with "/"
                .replace(/-/, "/"); // replace 2nd "-" with "/"
            this.foodItem.date = new Date(strInputValue2);
            this.foods.push()
            
            for(let i=0; i<this.foods.length; i++) {
                this.foods[i].date = this.foodItem.date;
                FoodService.createFood(this.foods[i]);
            }
        },
        getFoods(meal) {
            this.mealId=meal.mealId;
            this.showAddFood=false;
            this.logMealBtn = meal.mealId;
            this.addDate=!this.addDate;
            MealService.getFoods(this.mealId).then(
                (response) => {
                    this.foods = response.data;
                }
            );
        },
        viewFoods(mealId) {
            MealService.getFoods(mealId).then(
                (response) => {
                    this.foods = response.data;
                }
            );
        }
    }

}
</script>

<style scoped>
    

    .input-box {
        border: solid black 1px;
    }

   

    ul {
        list-style: none;
    }

</style>