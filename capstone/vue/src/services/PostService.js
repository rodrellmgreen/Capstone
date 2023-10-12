import axios from 'axios';

export default {

    getSpecificPost(postId) {
        return axios.get(`/posts/${postId}`)
    },
    getAllPostsForTeam(teamId) {
        return axios.get(`/posts/allTeam/${teamId}`)
    },
    getAllPostsForUser() {
        return axios.get(`/posts/allUser`)
    },
    createPost(post) {
        return axios.post('/posts', post)
    },
    updatePost(post) {
        return axios.put('/posts', post)
    },
    deletePost(postId) {
        return axios.delete(`/posts/${postId}`)
    }


}