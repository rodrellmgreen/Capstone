import axios from 'axios';

export default {

    createMeal(mealObj) {
        return axios.post(`/meals/new-meal`, mealObj);
    },

    addFoodToMeal(mealId, foodItem) {
        return axios.post(`/meals/${mealId}`, foodItem)
    },

    getMeals() {
        return axios.get('/meals');
    },

    getFoods(mealId) {
        return axios.get(`/meals/${mealId}`)
    },

    updateMealType(meal) {
        return axios.put('/meals', meal);
    },

    updateMealFood(id, meal) {
        return axios.put(`/meals/${id}`, meal);
    },

    deleteMeal(id) {
        return axios.delete(`/meals/${id}`);
    },

}