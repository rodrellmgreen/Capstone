<template>
<v-container id="foodDiary">
  <v-container id="foodLogTable" style="overflow-x: auto">
    <table id="food-log">
      <caption class="tableHeader">
        Recent Foods
      </caption>
      <tbody>
        <tr>
          <th>Date</th>
          <th>Food</th>
          <th>Calories</th>
          <th>Carbs</th>
          <th>Protein</th>
          <th>fats</th>
          <th>fiber</th>
          <th>Servings</th>
        </tr>
        <tr v-for="food in sortedFood.slice(0, 7)" :key="food.date">
          <td>{{ food.date }}</td>
          <td>{{ food.type }}</td>
          <td>{{ food.calories }}</td>
          <td>{{ food.carbs }}</td>
          <td>{{ food.protein }}</td>
          <td>{{ food.fats }}</td>
          <td>{{ food.fiber }}</td>
          <td>{{ food.servingSize }}</td>
        </tr>
      </tbody>
    </table>
    
  </v-container>
  </v-container>
</template>

<script>
import FoodService from '../services/FoodService.js'
export default {
    data() {
        return {
            foodLog: [],
            
        }
    },
     created() {
        FoodService.getAllFoods().then(
            (response) => {
                this.foodLog = response.data;
            }
        )
    },

    computed: {
  sortedFood() {
    return [...this.foodLog].sort((a, b) => {
      if (new Date(a.date) > new Date(b.date)) {
        return -1;
      }
      if (new Date(a.date) < new Date(b.date)) {
        return 1;
      }
      // sort by the original index if the dates are equal
      return this.foodLog.indexOf(b) - this.foodLog.indexOf(a);
    });
    },    
        
}
}

   
</script>

