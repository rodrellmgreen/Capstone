<!-- eslint-disable vue/multi-word-component-names -->
<!-- eslint-disable vue/multi-word-component-names -->
<template>

  <v-container class="track-food-background">

        <v-btn class="quickMeals"><router-link class="quickMeals" v-bind:to="{name: 'meals'}">Quick Meals</router-link></v-btn>
        <v-btn v-if="showQuickAdd" v-on:click="showQuickAdd = !showQuickAdd">Cancel</v-btn>
        <quick-add v-if="showQuickAdd"/>
        
        
        <v-btn v-if="!showQuickAdd" v-on:click="showQuickAdd = !showQuickAdd">Quick Add</v-btn>
        
       <v-container class="flex-container">
        <update-food v-if="$store.state.updateFoodItem" />
        <food-tracker-form v-if="!showQuickAdd && !$store.state.updateFoodItem"/>  
        <food-diary/>
       </v-container>
        
       
  </v-container>

    
</template>


<script>
import FoodDiary from '../components/FoodDiary.vue'
import FoodTrackerForm from '../components/FoodTrackerForm.vue'
import QuickAdd from '../components/QuickAdd.vue';
import UpdateFood from '../components/UpdateFood.vue';


export default {

    data() {
        return {
            showQuickAdd: false,
            updateFoodItem: false
        }
    },

    // eslint-disable-next-line vue/multi-word-component-names
    name: 'food',
    components:{
        FoodTrackerForm,
        FoodDiary,
        QuickAdd,
        UpdateFood,
    }
}
</script>
