import axios from 'axios';
// import store from 'src/store';
import { Message } from 'iview';

// 创建axios实例
const service = axios.create({
  // baseURL: 'http://localhost/', // api的base_url
  // baseURL: baseUrl.dev,
  timeout: 5 * 1000                  // 请求超时时间
});


// request拦截器
service.interceptors.request.use(config => {
  // Do something before request is sent

  // config.baseURL = 'http://sdf/'
  // config.url = config.baseURL + config.url
  // console.log(store.getters.serverUrl)
  // console.log(baseUrl[store.getters.serverUrl])

  // config.url = 'dsf/d'
  // console.log(config.url)
  // if (store.getters.token) {
  //   config.headers['X-Token'] = store.getters.token; // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
  // }
  return config;
}, error => {
  // Do something with request error
  console.error(error); // for debug
  Promise.reject(error);
})

// respone拦截器
service.interceptors.response.use(
  response => response
  /**
  * 下面的注释为通过response自定义code来标示请求状态，当code返回如下情况为权限有问题，登出并返回到登录页
  * 如通过xmlhttprequest 状态码标识 逻辑可写在下面error中
  */
  // const code = response.data.code;
  // // 50014:Token 过期了 50012:其他客户端登录了 50008:非法的token
  // if (code === 50008 || code === 50014 || code === 50012) {
  //   Message({
  //     message: res.message,
  //     type: 'error',
  //     duration: 5 * 1000
  //   });
  //   // 登出
  //   store.dispatch('FedLogOut').then(() => {
  //     router.push({ path: '/login' })
  //   });
  // } else {
  //   return response
  // }
  ,
  error => {
    console.error('err' + error);// for debug
    Message.error(response.msg,
      3
    );
    return Promise.reject(error);
  }
)

export default service;
