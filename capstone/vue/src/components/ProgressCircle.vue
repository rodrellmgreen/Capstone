<template>
  <div class="daily-cals">
    <div class="circle-chart">
      <JSCharting
        v-show="loaded"
        :options="getChartOptions"
        style="max-width: 400px; height: 400px; margin: 0 auto"
      />
    </div>
    <h2 class="circle-graph-title">Daily Calorie Goal: {{ this.calorieGoal }}</h2>
  </div>
</template>

<script>
import JSCharting from "jscharting-vue";
import FoodService from "../services/FoodService";
import ProfileService from "../services/ProfileService.js";
export default {
  name: "circularGauge",
  components: { JSCharting },

  data() {
    return {
      totalCalories: this.calorieTotal,
      calorieGoal: this.userCalorieGoal,
      fiftyPercentGoal: 0,
      seventyFivePercentGoal: 0,
      ninetyPercentGoal: 0,
      goalPercentage: 0,

      loaded: false,
      foodLog: [],
      profile: [],
    };
  },
  created() {
    FoodService.getTodaysFood().then((response) => {
      this.foodLog = response.data;
      this.totalCalories = 0;

      this.foodLog.forEach((food) => {
        this.totalCalories += food.calories;
      });
    });

    ProfileService.getProfile().then((response) => {
      this.profile = response.data;
      this.calorieGoal = 0;
      if (this.profile.currentWeight > this.profile.desiredWeight) {
        this.calorieGoal = this.profile.currentWeight * 14.5 - 250;
      } else {
        this.calorieGoal = this.profile.currentWeight * 14.5 + 250;
      }
      let percentages = this.calorieGoal / 2;
      percentages = Math.round(percentages);
      this.fiftyPercentGoal = percentages;

      percentages = this.calorieGoal * 0.75;
      percentages = Math.round(percentages);
      this.seventyFivePercentGoal = percentages;

      percentages = this.calorieGoal * 0.9;
      percentages = Math.round(percentages);
      this.ninetyPercentGoal = percentages;

      this.goalPercentage = (this.totalCalories / this.calorieGoal) * 100;
      this.goalPercentage = Math.round(this.goalPercentage);
      this.loaded = true;
    });
  },
  methods: {},
  computed: {
    getChartOptions() {
      return {
        type: "gauge ",
        legend_visible: false,
        chartArea_boxVisible: false,
        debug: true,
        text: "some text",
        xAxis: {
          /* Used to position marker on top of axis line. */
          scale: { range: [0, 1], invert: true },
        },
        palette: {
          pointValue: "%yValue",
          ranges: [
            { value: [0, this.fiftyPercentGoal], color: "#FF5353" },
            {
              value: [this.fiftyPercentGoal, this.seventyFivePercentGoal],
              color: "#FFD221",
            },
            {
              value: [this.seventyFivePercentGoal, this.ninetyPercentGoal],
              color: "#77E6B4",
            },
            {
              value: [this.ninetyPercentGoal, this.calorieGoal],
              color: "Green",
            },
          ],
        },
        yAxis: {
          defaultTick: { padding: 13, enabled: false },
          customTicks: [
            this.fiftyPercentGoal,
            this.seventyFivePercentGoal,
            this.ninetyPercentGoal,
          ],
          line: {
            width: 15,
            breaks_gap: 0.03,
            color: "smartPalette",
          },
          scale: { range: [0, this.calorieGoal] },
        },
        defaultSeries: {
          opacity: 1,
          shape: {
            label: {
              align: "center",
              verticalAlign: "middle",
            },
          },
        },
        series: [
          {
            type: "marker",
            name: "Score",
            shape_label: {
              text: "Calories: %value Progress: " + this.goalPercentage + "%",

              style: { fontSize: 28 },
            },
            defaultPoint: {
              tooltip: "%yValue",
              marker: {
                outline: {
                  width: 5,
                  color: "white",
                },
                fill: "currentColor",
                type: "circle",
                visible: true,
                size: 35,
              },
            },
            points: [[1, this.totalCalories]],
          },
        ],
      };
    },
  },
};
</script>

