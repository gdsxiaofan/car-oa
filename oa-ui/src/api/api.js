import {fetch,qs} from '../lib/utils/fetch'

let base = '';

//修改密码 TODO
export function updatePwd(oldPwd, newPwd) {
  return fetch({
    url: `/v1/user/updatePwd`,
    method: 'put',
    data: qs.stringify({oldPwd, newPwd}),
    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
  })
}

//登录
export function login(username, password) {
  return fetch({
    url: `login`,
    method: 'post',
    data: qs.stringify({username, password}),
    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
  })
}
//获取用户相关信息（菜单，个人信息等）
export function getUserInfo() {
  return fetch({
    url: `v1/user/query`,
    method: 'get'
  })
}
