import {fetch,qs} from '../lib/utils/fetch'

let base = '';

//修改密码
export function updatePwd(oldPwd, newPwd) {
  return fetch({
    url: `updatePwd`,
    method: 'post',
    data: {oldPwd, newPwd}
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

export const requestLogin = params => {
  return fetch.post(`${base}/login`, params).then(res => res.data);
};

export const getUserList = params => {
  return fetch.get(`${base}/user/list`, {params: params});
};

export const getUserListPage = params => {
  return fetch.get(`${base}/user/listpage`, {params: params});
};

export const removeUser = params => {
  return fetch.get(`${base}/user/remove`, {params: params});
};

export const editUser = params => {
  return fetch.get(`${base}/user/edit`, {params: params});
};

export const addUser = params => {
  return fetch.get(`${base}/user/add`, {params: params});
};
