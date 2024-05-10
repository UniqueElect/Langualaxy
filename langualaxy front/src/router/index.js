import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/HomePage.vue'; 
import Tests from '../views/TestsPage.vue'; 
import Lessons from '../views/LessonsPage.vue'; 
import Helloworld from '../components/HelloWorld.vue'; 
import About from '@/views/AboutUs.vue';
import Terms from '@/views/TermsPolicies.vue';
import Webs from '@/views/UsefullWebsites.vue';
import TestPage from '@/components/TestPage.vue';
import Games from '@/views/GamesPage.vue'
import NotFound from '@/components/NotFound.vue';
import Flashcard from '@/components/FlashcardItem.vue'
import Login from '@/views/LoginPage.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/tests',
    name: 'Tests',
    component: Tests
  },
  {
    path: '/lessons',
    name: 'Lessons',
    component: Lessons
  },
  {
    path: '/helloworld',
    name: 'Helloworld',
    component: Helloworld
  },
  {
    path: '/games',
    name: 'Games',
    component: Games
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/terms',
    name: 'Terms',
    component: Terms
  },
  {
    path: '/webs',
    name: 'Webs',
    component: Webs
  },
  {
    path: '/test/:testId', 
    name: 'TestPage',
    component: TestPage,
    props: true 
  },
  {
    path: '/:catchAll(.*)', 
    name: 'NotFound',
    component: NotFound
  },
  {
    path: '/flashcard/:themeId', 
    name: 'Flashcard',
    component: Flashcard
  },
  {
    path: '/login', 
    name: 'Login',
    component: Login
  },
  
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
