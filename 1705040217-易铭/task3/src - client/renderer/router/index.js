import Vue from 'vue'
import Router from 'vue-router'
import Homepage from '../layout/Homepage'
import Table from "../components/Table";
import Calculator from "../components/Calculator";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Homepage',
      component: Homepage
    },
    {
      path: '/table',
      name: 'Table',
      component: Table
    },
    {
      path: '/calculator',
      name: 'Calculator',
      component: Calculator
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})
