import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080/api', // or the URL where your Spring Boot app is running
  headers: {
    'Content-Type': 'application/json',
  },
});

export default instance;
