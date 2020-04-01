import axios from 'axios';

let base = '';
let instance = axios.create({
   headers: {'content-type': 'application/x-www-form-urlencoded'},
   withCredentials: true

})
export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };
