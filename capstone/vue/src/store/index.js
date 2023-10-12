import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    updateFoodItem: false,
    foodItemToUpdate: {},
    
    allFoods: [

    ],

    snacks: [

    ],

    breakfeast: [

    ],

    lunch: [

    ],

    dinner: [

    ],
    newTeam: {},

  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    NEW_SNACK(state, foodItem) {
      state.snacks.push(foodItem);
    },
    NEW_BREAKFEAST(state, foodItem) {
      state.breakfeast.push(foodItem);
    },
    NEW_LUNCH(state, foodItem) {
      state.lunch.push(foodItem);
    },
    NEW_DINNER(state, foodItem) {
      state.dinner.push(foodItem);
    },
    NEW_ALLFOODS(state, foodItem) {
      state.allFoods.unshift(foodItem)
    },
    NEW_TEAM(state, newTeam) {
      state.newTeam = newTeam;
    }

  }
})
