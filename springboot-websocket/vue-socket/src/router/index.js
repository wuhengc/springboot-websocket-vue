import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import wbesocket from '@/components/wbesocket'
import wbesocketpoint from '@/components/wbesocketpoint'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/ws',
      name: 'wbesocket',
      component: wbesocket
    },
    {
      path: '/wsp',
      name: 'wbesocketpoint',
      component: wbesocketpoint
    }
  ]
})
