import VueRouter from 'vue-router'
import iview from 'iview'
//登录页
const Login = resolve => require(['./pages/Login.vue'], resolve)
//404
const NotFound = resolve => require(['./pages/404.vue'], resolve)
//页面主体
const Home = resolve => require(['./pages/Home.vue'], resolve)
//权限管理            >>>>>>
/*角色管理*/
const Role = resolve => require(['./pages/role/Role.vue'], resolve)
/*排班管理*/
const Plan = resolve => require(['./pages/role/Plan.vue'], resolve)
/*用户角色管理*/
const ShowUser = resolve => require(['./pages/role/ShowUser.vue'], resolve)

//工单管理         >>>>>>
const LookOrder = resolve => require(['./pages/order/look.vue'], resolve)
const fixOrder = resolve => require(['./pages/order/fix.vue'], resolve)
const verifyOrder = resolve => require(['./pages/order/verify.vue'], resolve)
const configOrder = resolve => require(['./pages/order/config.vue'], resolve)
/*所有工单*/
const AllOrder = resolve => require(['./pages/order/All.vue'], resolve)
//员工管理          >>>>>>
/*信息*/
const EmployeeInfo = resolve => require(['./pages/employee/Info.vue'], resolve)
/*新增*/
const EmployeeAdd = resolve => require(['./pages/employee/Add.vue'], resolve)
//设备管理          >>>>>>
/*信息*/
const DeviceInfo = resolve => require(['./pages/device/Info.vue'], resolve)
/*服务*/
const Service = resolve => require(['./pages/device/Service.vue'], resolve)
let routes = [
  {
    path: '/',
    component: Home,
    meta: {
      title: '首页'
    },
    iconCls: 'ios-home', //图标样式class
    children: [
      {path: 'role', meta: {title: '角色管理'}, icon: 'ios-home', component: Role},
      {path: 'plan', meta: {title: '排班管理'}, icon: 'ios-home', component: Plan},
      {path: 'userRole', meta: {title: '用户角色管理'}, icon: 'ios-home', component: ShowUser},
      {path: 'lookOrder', meta: {title: '巡检工单'}, icon: 'ios-home', component: LookOrder},
      {path: 'fixOrder', meta: {title: '维修工单'}, icon: 'ios-home', component: fixOrder},
      {path: 'verifyOrder', meta: {title: '审核工单'}, icon: 'ios-home', component: verifyOrder},
      {path: 'configOrder', meta: {title: '配置工单'}, icon: 'ios-home', component: configOrder},
      {path: 'allOrder', meta: {title: '所有工单'}, icon: 'ios-home', component: AllOrder},
      {path: 'employeeInfo', meta: {title: '员工信息'}, icon: 'ios-home', component: EmployeeInfo},
      {path: 'employeeAdd', meta: {title: '员工新增'}, icon: 'ios-home', component: EmployeeAdd},
      {path: 'deviceInfo', meta: {title: '设备信息'}, icon: 'ios-home', component: DeviceInfo},
      {path: 'service', meta: {title: '服务信息'}, icon: 'ios-home', component: Service},

    ]
  },
  {
    path: '/login',
    component: Login,
    name: '',
    hidden: true
  },
  {
    path: '/404',
    component: NotFound,
    name: '',
    hidden: true
  },
  {
    path: '*',
    hidden: true,
    redirect: {path: '/print'}
  }
];

const router = new VueRouter({
  routes
})
router.beforeEach((to, from, next) => {
  iview.LoadingBar.start();
  if (to.path === '/login') {
    sessionStorage.removeItem('Authorization');
  }
  let jwt = sessionStorage.getItem("Authorization");
  if (!jwt && to.path !== '/login') {
    next({ path: '/login',query:{ redirect: to.fullPath} })
    iview.LoadingBar.finish();
    window.scrollTo(0, 0);
  } else {
    next()
  }
})

router.afterEach(() => {
  iview.LoadingBar.finish();
  window.scrollTo(0, 0);
});

export default router;
