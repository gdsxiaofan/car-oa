import {fetch,qs} from '../../lib/utils/fetch'

export function getRoleList(queryCondition) {
  return fetch({
    url: 'v1/role/query',
    method: 'get',
    params: queryCondition
  });
}
export function getRoleById(id) {
  return fetch({
    url: 'v1/role/getRoleMenu',
    method: 'get',
    params: id
  });
}
export function delRole(roleId) {
  return fetch({
    url: 'v1/role/delete',
    method: 'delete',
    params: {roleId:roleId}
  });
}
export function addRole(role) {
  return fetch({
    url: 'v1/role/add',
    method: 'post',
    data: role
  });
}
export function updateRole(role) {
  return fetch({
    url: 'v1/role/update',
    method: 'put',
    data: role
  });
}
