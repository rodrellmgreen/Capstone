<template>
  <v-container id="form-container" class="form-container">
    <v-form class="form-form" @submit="logFood" action="">
      <h1 class="food-tracker-form-header">Log Food</h1>

      <v-container class="flex-container">
        <label class="input-label" for="name">Food</label>
        <input
          class="input-box"
          id="name"
          type="text"
          v-model="foodItem.type"
          required
          placeholder="Ex: Potato"
        />
      </v-container>
      <!-- <div class="flex-container">
        <label class="input-label" for="calories">Calories</label>
        <input
          class="input-box"
          id="calories"
          type="number"
          v-model="foodItem.calories"
          required
          placeholder="Ex: 163"
        />
      </div> -->
      <v-container class="flex-container">
        <label class="input-label" for="carbs">Carbs</label>
        <input
          class="input-box"
          id="carbs"
          type="number"
          v-model="foodItem.carbs"
          required
          placeholder="Ex: 37"
        />
      </v-container>
      <v-container class="flex-container">
        <label class="input-label" for="protein">Protein</label>
        <input
          class="input-box"
          id="protein"
          type="number"
          v-model="foodItem.protein"
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
          v-model="foodItem.fats"
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
          v-model="foodItem.fiber"
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
          v-model="foodItem.servingSize"
          required
          placeholder="Ex: 1"
        />
      </v-container>

      <v-container class="flex-container">
        <label class="input-checkbox" for="quick-add">Enable "Quick Add"</label>
        <input  id="quick-add" type="checkbox" v-model="foodItem.quickAdd" />
      </v-container>

      <v-container class="flex-container">
        <label class="input-label" for="date">Date</label>
        <input
          class="input-box"
          id="date"
          type="date"
          v-model="foodItem.date"
          required
          placeholder="Ex: 1"
        />
      </v-container>

      <v-container
        id="mealType"
        v-if="showOptions"
        @close="showOptions = false"
      >
        <v-container>
          <v-btn v-on:click="logFood"> Log Food </v-btn>
          <!-- <v-btn v-on:click="logFood"> Breakfast </v-btn>
          <v-btn v-on:click="logFood"> Lunch </v-btn>
          <v-btn v-on:click="logFood"> Dinner </v-btn> -->
        </v-container>
      </v-container>
    </v-form>
  </v-container>
</template>

<script>
import FoodService from "../services/FoodService";
export default {
  data() {
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

  //    watch: {
  //   foodItemDate(newDate) {

  //     this.foodItem.date = new Date(newDate).toJSON();
  //     // console.log(this.foodItemDate)
  //   }
  // },
  created() {
    FoodService.createFood(this.foodItem).then((response) => {
      if (response.status == 200) {
        this.$router.go();
      }
    });
  },

  computed: {
    showOptions() {
      if (
        this.foodItem.type &&
        // this.foodItem.calories &&
        this.foodItem.carbs &&
        this.foodItem.protein &&
        this.foodItem.fats &&
        this.foodItem.fiber &&
        this.foodItem.servingSize &&
        this.foodItem.date
      ) {
        return true;
      } else {
        return false;
      }
    },
  },

  methods: {
    logFood() {
      var strInputValue = this.foodItem.date; // <-- get my date string

      let strInputValue2 = strInputValue
        .replace(/-/, "/") // replace 1st "-" with "/"
        .replace(/-/, "/"); // replace 2nd "-" with "/"

      this.foodItem.date = new Date(strInputValue2);

      this.foodItem.calories =
        (this.foodItem.carbs - this.foodItem.fiber) *
          4 *
          this.foodItem.servingSize +
        this.foodItem.protein * 4 * this.foodItem.servingSize +
        this.foodItem.fats * 9 * this.foodItem.servingSize;

      this.foodItem.carbs = this.foodItem.carbs * this.foodItem.servingSize;
      this.foodItem.protein = this.foodItem.protein * this.foodItem.servingSize;
      this.foodItem.fats = this.foodItem.fats * this.foodItem.servingSize;
      this.foodItem.fiber = this.foodItem.fiber * this.foodItem.servingSize;

      FoodService.createFood(this.foodItem).then((response) => {
        if (response.status === 201) {
          alert("Success! Food Added.");
          this.$router.go();
        }
      });
    },
  },
};
</script>

