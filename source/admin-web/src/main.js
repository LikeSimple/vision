import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios';
import ElementUI from 'element-ui';
import store from './store'
import { USER_GET_PROFILE } from './store/mutation-types'
import { getToken } from './util/token'
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import './assets/css/icon.css';
import './components/common/directives';
import "babel-polyfill";

Vue.config.productionTip = false
Vue.use(ElementUI, {
    size: 'small'
});

Vue.prototype.$axios = axios;

function hasPermission(authorities, permissionAuthorities) {
    if (authorities.indexOf('AUTH_ADMIN') >= 0) return true
    if (!permissionAuthorities) return true
    return authorities.some( authority => permissionAuthorities.indexOf(authority) >= 0)
}

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    const token = getToken();
    if (!token && to.path !== '/login') {
        next('/login');
    } else if (token && store.state.user.authorities.length === 0) {
        //GET USERINFO
        store.dispatch(USER_GET_PROFILE).then( // 获取用户数据
            // 动态增加路由
            // next({ ...to, replace: true }) // hack方法 确保addRoutes已完成
            // ,set the replace: true so the navigation will not leave a history record
            to.path == '/login' ? next('/dashboard') : next()
        ).catch(error => {
            // Logout
            console.log(error)
        })
    } else if (to.meta.permission) {
        hasPermission(store.state.user.authorities, to.meta.permissionAuthorities) ? next(): next('/403')
    } else {
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        } else {    
            next();
        }
    }
})


new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')