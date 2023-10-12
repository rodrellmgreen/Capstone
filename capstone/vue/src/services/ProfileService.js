import axios from 'axios';

export default {

    getProfile() {
        return axios.get('/profile');
    },

    addProfile(profile) {
        return axios.post('/profile', profile);
    },

    editProfile(profile) {
        return axios.put('/profile', profile);
    },

    deleteProfile() {
        return axios.delete('/profile');
    },
    updateStreak(user) {
        return axios.put('/profile/streak', user);
    },
    addNumberToTextAlerts() {
        return axios.put('/profile/sms');
    },
    sendSMS(timestamp){

        let putOBj = {
            timeStamp : timestamp
        }

        return axios.put('/profile/sms',putOBj)
    }

}