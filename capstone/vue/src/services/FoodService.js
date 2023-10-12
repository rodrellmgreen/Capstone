import axios from 'axios'

export default {

    getAllFoods() {
        return axios.get('/food')
    },
    getSpecificFoodById(id) {
        return axios.get(`/food/${id}`)
    },
    createFood(food) {
        return axios.post('/food', food)
    },
    updateFoodItem(id, food) {
        return axios.put(`/food/${id}`, food)
    },
    deleteFoodItem(id) {
        return axios.delete(`/food/${id}`)
    },
    getQuickAddFoods() {
        return axios.get('/food/quickAdd')
    },
    getTodaysFood(){
        return axios.get('/food/today')
    }

}