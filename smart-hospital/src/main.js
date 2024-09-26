import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // Adjust the import path if necessary

const app = createApp(App);
app.use(router); // Register the router
app.mount('#app'); // Mount the Vue instance
