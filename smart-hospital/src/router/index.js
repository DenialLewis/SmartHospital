import { createRouter, createWebHistory } from 'vue-router';
import thaiMedicine from '../components/thaiMedicine.vue'; // Adjust the path as necessary
import ChatBubble from '../components/ChatBubble.vue';
const routes = [
  {
    path: '/',
    name: 'App', // or whatever component you have
    component: () => import('../App.vue'), // Example for home component
  },
  {
    path: '/thai-medicine',
    name: 'thaiMedicine',
    component: thaiMedicine,
  },
  {
    path: '/chat-bot',
    name: 'chatBubble',
    component: ChatBubble,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
