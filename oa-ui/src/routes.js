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
/*新增角色*/
const AddRole = resolve => require(['./pages/role/AddRole.vue'], resolve)
/*用户角色管理*/
const UserRole = resolve => require(['./pages/role/UserRole.vue'], resolve)
//工单管理         >>>>>>
/*我的工单*/
const MyOrder = resolve => require(['./pages/order/My.vue'], resolve)
/*发布工单*/
const PublishOrder = resolve => require(['./pages/order/Publish.vue'], resolve)
/*分发工单*/
const DispatchOrder = resolve => require(['./pages/order/Dispatch.vue'], resolve)
/*历史工单*/
const HistoryOrder = resolve => require(['./pages/order/History.vue'], resolve)
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
/*新增*/
const DeviceAdd = resolve => require(['./pages/device/Add.vue'], resolve)
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
      {path: 'addRole', meta: {title: '新增角色'}, icon: 'ios-home', component: AddRole},
      {path: 'userRole', meta: {title: '用户角色管理'}, icon: 'ios-home', component: UserRole},
      {path: 'myOrder', meta: {title: '我的工单'}, icon: 'ios-home', component: MyOrder},
      {path: 'publishOrder', meta: {title: '发布工单'}, icon: 'ios-home', component: PublishOrder},
      {path: 'dispatchOrder', meta: {title: '分发工单'}, icon: 'ios-home', component: DispatchOrder},
      {path: 'historyOrder', meta: {title: '历史工单'}, icon: 'ios-home', component: HistoryOrder},
      {path: 'allOrder', meta: {title: '所有工单'}, icon: 'ios-home', component: AllOrder},
      {path: 'employeeInfo', meta: {title: '员工信息'}, icon: 'ios-home', component: EmployeeInfo},
      {path: 'employeeAdd', meta: {title: '员工新增'}, icon: 'ios-home', component: EmployeeAdd},
      {path: 'deviceInfo', meta: {title: '设备信息'}, icon: 'ios-home', component: DeviceInfo},
      {path: 'deviceAdd', meta: {title: '设备新增'}, icon: 'ios-home', component: DeviceAdd},

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
  if (to.path == '/login') {
    sessionStorage.removeItem('user');
  }
  let user = JSON.parse(sessionStorage.getItem('user'));
  if (!user && to.path != '/login') {
    next({ path: '/login' })
  } else {
    next()
  }
})

router.afterEach(() => {
  iview.LoadingBar.finish();
  window.scrollTo(0, 0);
});

export default router;
