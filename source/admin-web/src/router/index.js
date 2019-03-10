import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页', permission: true, permissionAuthorities: ['AUTH_DASHBOARD'] }
                },
                {
                    path: '/client',
                    component: resolve => require(['../components/page/VisionClient.vue'], resolve),
                    meta: { title: '客户', permission: true, permissionAuthorities: ['AUTH_CLIENT'] }
                },
                {
                    path: '/activity',
                    component: resolve => require(['../components/page/VisionActivity.vue'], resolve),
                    meta: { title: '活动', permission: true, permissionAuthorities: ['AUTH_ACTIVITY'] }
                },
                {
                    path: '/activity-client',
                    component: resolve => require(['../components/page/VisionActivityClient.vue'], resolve),
                    meta: { title: '检测客户', permission: true, permissionAuthorities: ['AUTH_ACTIVITY'] }
                },
                {
                    path: '/activity-client-record',
                    component: resolve => require(['../components/page/VisionActivityClientRecord.vue'], resolve),
                    meta: { title: '检测报告', permission: true, permissionAuthorities: ['AUTH_ACTIVITY'] }
                },
                {
                    path: '/record',
                    component: resolve => require(['../components/page/VisionRecord.vue'], resolve),
                    meta: { title: '报告', permission: true, permissionAuthorities: ['AUTH_REPORT'] }
                },
                {
                    path: '/statistics',
                    component: resolve => require(['../components/page/VisionStatistics.vue'], resolve),
                    meta: { title: '统计', permission: true, permissionAuthorities: ['ROLE_STATISTICS'] }
                },
                {
                    path: '/icon',
                    component: resolve => require(['../components/page/Icon.vue'], resolve),
                    meta: { title: '自定义图标' }
                },
                {
                    path: '/table',
                    component: resolve => require(['../components/page/BaseTable.vue'], resolve),
                    meta: { title: '基础表格' }
                },
                {
                    path: '/tabs',
                    component: resolve => require(['../components/page/Tabs.vue'], resolve),
                    meta: { title: 'tab选项卡' }
                },
                {
                    path: '/form',
                    component: resolve => require(['../components/page/BaseForm.vue'], resolve),
                    meta: { title: '基本表单' }
                },
                {
                    // 富文本编辑器组件
                    path: '/editor',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve),
                    meta: { title: '富文本编辑器' }
                },
                {
                    // markdown组件
                    path: '/markdown',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve),
                    meta: { title: 'markdown编辑器' }    
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: resolve => require(['../components/page/Upload.vue'], resolve),
                    meta: { title: '文件上传' }   
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve),
                    meta: { title: 'schart图表' }
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve),
                    meta: { title: '拖拽列表' }
                },
                {
                    // 拖拽Dialog组件
                    path: '/dialog',
                    component: resolve => require(['../components/page/DragDialog.vue'], resolve),
                    meta: { title: '拖拽弹框' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: resolve => require(['../components/page/Permission.vue'], resolve),
                    meta: { title: '权限测试', permission: true }
                },
                {
                    path: '/404',
                    component: resolve => require(['../components/page/404.vue'], resolve),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: resolve => require(['../components/page/403.vue'], resolve),
                    meta: { title: '403' }
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/qrcode',
            name: 'qrcode',
            component: resolve => require(['../components/page/VisionActivityClientQRCode.vue'], resolve),
            meta: { title: '权限测试', permission: true, permissionAuthorities: ['AUTH_ACTIVITY'] }
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
