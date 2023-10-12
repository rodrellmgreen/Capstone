<template >
 <v-container class="calorie-chart-container">
  <v-container class="graph">
    <JSCharting :options="options" style="height: 400px"></JSCharting>
    <!-- <button v-on:click="updateData">Update Chart Data</button> -->
  </v-container>
  <v-container id="graph-toggle">
      <v-btn small v-on:click="Weekly"> Last Week </v-btn> | 
      <v-btn small v-on:click="Monthly"> Last Month </v-btn> |
      <v-btn small v-on:click="ThreeMonths"> Last 3 Months </v-btn> |
      <v-btn small v-on:click="SixMonths"> Last 6 Months </v-btn>
  </v-container>
</v-container>
</template>

<script>
import JSCharting from "jscharting-vue";
import FoodService from "../services/FoodService.js";

export default {
  name: "methodUpdate",
  data() {
    let options = {
      type: "line spline",
      title: { label: { text: "" } },
      legend: {
        position: "inside top right",
      },
      xAxis: { scale: { type: "time" } },
      yAxis: { scale: { type: "auto" } },
      defaultPoint: {
        marker: {
          outline: {
            width: 2,
            color: "currentColor",
          },
          fill: "white",
        },
      },
    
    };
    
    return {
      options,
      foodLog: [],
    };
  },

  created() {
    
    FoodService.getAllFoods().then((response) => {
      this.foodLog = response.data;
      console.log('dates: ', response.data)
      this.options.series = [
      {
        
        name: "Total Weekly Calories",
        points: this.getWeeklyFoodPoints(),
      },
    ];
      this.Weekly()
    });

    
  },

  methods: {
    Weekly() {
      this.options = {
        series: [
          {
            name: "Total Weekly Calories",
            points: this.getWeeklyFoodPoints(),
          },
        ],
      };
    },

      Monthly() {
      this.options = {
        series: [
          {
            name: "Last Month Total Calories",
            points: this.getMonthlyFoodPoints(),
          },
        ],
      };
    },

    ThreeMonths() {
      this.options = {
        series: [
          {
            name: "Last 3 Months Total Calories",
            points: this.get3MonthsFoodPoints(),
          },
        ],
      };
    },

    SixMonths() {
      this.options = {
        series: [
          {
            name: "Last 6 Months Total Calories",
            points: this.get6MonthsFoodPoints(),
          },
        ],
      };
    },

    getWeeklyFoodPoints() {
      let points = [];
      let now = new Date();
      for (let i = 0; i < 7; i++) {
        let eachDay = new Date(now.getTime() - i * 24 * 60 * 60 * 1000);
        // console.log(now.getTime())
        // console.log(i)
        // console.log()
        // console.log(eachDay);

        

        let totalCalories = this.foodLog
          .filter((food) => {
            var strInputValue = food.date;
            let strInput2 = strInputValue.replace(/-/, "/").replace(/-/, "/")
            let foodDate = new Date(strInput2);
            console.log('whats this date: ',foodDate)
            let sameDay = foodDate.getDate() == eachDay.getDate();
            let sameMonth = foodDate.getMonth() == eachDay.getMonth();
            let sameYear = foodDate.getFullYear() == eachDay.getFullYear();

            return sameMonth && sameDay && sameYear;
          })
          .reduce((calories, food) => {
            return calories + food.calories;
          }, 0);

        let foodPoint = {
          id: "p" + (i + 1),
          x: eachDay,
          y: totalCalories,
        };
        points.push(foodPoint);
      }

      console.log('Andy DEBUT')
      console.log(points);

      return points;
    },

     getMonthlyFoodPoints() {
      let points = [];
      let now = new Date();
      for (let i = 0; i < 30; i++) {
        let eachDay = new Date(now.getTime() - i * 24 * 60 * 60 * 1000);
        

        let totalCalories = this.foodLog
          .filter((food) => {
            let foodDate = new Date(food.date);
            let sameDay = foodDate.getDate() == eachDay.getDate();
            let sameMonth = foodDate.getMonth() == eachDay.getMonth();
            let sameYear = foodDate.getFullYear() == eachDay.getFullYear();

            return sameMonth && sameDay && sameYear;
          })
          .reduce((calories, food) => {
            return calories + food.calories;
          }, 0);

        let foodPoint = {
          id: "p" + (i + 1),
          x: eachDay,
          y: totalCalories,
        };
        points.push(foodPoint);
      }

      return points;
    },

     get3MonthsFoodPoints() {
      let points = [];
      let now = new Date();
      for (let i = 0; i < 90; i++) {
        let eachDay = new Date(now.getTime() - i * 24 * 60 * 60 * 1000);
        

        let totalCalories = this.foodLog
          .filter((food) => {
            let foodDate = new Date(food.date);
            let sameDay = foodDate.getDate() == eachDay.getDate();
            let sameMonth = foodDate.getMonth() == eachDay.getMonth();
            let sameYear = foodDate.getFullYear() == eachDay.getFullYear();

            return sameMonth && sameDay && sameYear;
          })
          .reduce((calories, food) => {
            return calories + food.calories;
          }, 0);

        let foodPoint = {
          id: "p" + (i + 1),
          x: eachDay,
          y: totalCalories,
        };
        points.push(foodPoint);
      }

      return points;
    },

     get6MonthsFoodPoints() {
      let points = [];
      let now = new Date();
      for (let i = 0; i < 180; i++) {
        let eachDay = new Date(now.getTime() - i * 24 * 60 * 60 * 1000);
        

        let totalCalories = this.foodLog
          .filter((food) => {
            let foodDate = new Date(food.date);
            let sameDay = foodDate.getDate() == eachDay.getDate();
            let sameMonth = foodDate.getMonth() == eachDay.getMonth();
            let sameYear = foodDate.getFullYear() == eachDay.getFullYear();

            return sameMonth && sameDay && sameYear;
          })
          .reduce((calories, food) => {
            return calories + food.calories;
          }, 0);

        let foodPoint = {
          id: "p" + (i + 1),
          x: eachDay,
          y: totalCalories,
        };
        points.push(foodPoint);
      }

      return points;
    },
  },
  computed: {},

  components: {
    JSCharting,
  },
};
</script>
<style >
.graph {
width: 96.5%;
}
</style>