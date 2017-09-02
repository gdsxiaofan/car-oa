import {fetch,qs} from '../../lib/utils/fetch'

export function getUserList(queryCondition) {
  return fetch({
    url: 'v1/user/query/list',
    method: 'get',
    params: queryCondition
  });
}
export function addUser(user) {
  return fetch({
    url: 'v1/user/addUser',
    method: 'post',
    data: user
  });
}
export function updateUser(user) {
  return fetch({
    url: 'v1/user/updateUser',
    method: 'put',
    data: user
  });
}
export function delUser(id) {
  return fetch({
    url: 'v1/user/delUser',
    method: 'delete',
    params: {id}
  });
}
export function isActiveUser(id,isActive) {
  return fetch({
    url: 'v1/user/isActiveUser',
    method: 'put',
    params: {id,isActive}
  });
}
