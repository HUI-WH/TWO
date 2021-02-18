import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/search',
    component: Layout,
    redirect: '/search/article',
    children: [{
      path: 'article',
      name: 'ArticleSerach',
      component: () => import('@/views/search/index')
    }]
  },
  {
    path: '/article',
    component: Layout,
    redirect: '/article/show',
    name: 'Art',
    meta: { title: 'Article', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'show',
        name: 'Show',
        component: () => import('@/views/article/index'),
        meta: { title: 'Article', icon: 'table' }
      }
    ]
  },
  {
    path: '/person',
    component: Layout,
    redirect: '/person/info',
    name: 'Art',
    meta: { title: 'Article', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'info',
        name: 'Person',
        redirect: '/person/info/profile',
        component: () => import('@/views/person/person'),
        meta: { title: '个人中心', icon: 'table' },
        children:[
          {
            path: 'profile',
            name: 'Profile',
            component: () => import('@/views/person/profile')
          }
        ]
      }
    ]
  },
  {
    path: '/blog',
    component: Layout,
    redirect: '/blog/own/list',
    name: 'Art',
    meta: { title: 'Article', icon: 'el-icon-s-help' },
    children: [
      {
        path: 'own',
        name: 'ownBlog',
        redirect: 'own/list',
        component: () => import('@/views/ownBlog/index'),
        children:[
          {
            path: 'list',
            name: 'BlogList',
            component: () => import('@/views/ownBlog/blogList'),
          }
        ]
      }
    ]
  },


  {
    path: '/article/create',
    component: () => import('@/views/article/createArticle'),
    name: 'CreateArticle',
    meta: { title: '写博客', icon: 'el-icon-s-help' }
  },
  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
