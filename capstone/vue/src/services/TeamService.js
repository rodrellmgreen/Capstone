import axios from 'axios';

export default {

    getUsersTeams() {
        return axios.get('/teams')
    },
    getMembersOfTeam(id) {
        return axios.get(`/teams/${id}/members`)
    },
    createATeam(team) {
        return axios.post('/teams', team)
    },
    addUserToTeam(team) {
        return axios.post('/teams/addMember', team)
    },
    getAllAvailableTeams() {
        return axios.get('/teams/allTeams')
    },
    updateTeamName(team) {
        return axios.put('/teams', team)
    },
    removeUserFromTeam(teamId) {
        return axios.delete(`/teams/${teamId}`)
    },
    deleteTeam(teamId) {
        return axios.delete(`/teams/remove/${teamId}`)
    },
    getSpecificTeam(teamId) {
        return axios.get(`/teams/${teamId}`)
    }

}