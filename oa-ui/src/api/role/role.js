import {fetch,qs} from '../../lib/utils/fetch'

export function getAllMenu() {
  return fetch({
    url: 'v1/role/getAllMenu',
    method: 'get',
  });
}
export function getRoleList(queryCondition) {
  return fetch({
    url: 'v1/role/query',
    method: 'get',
    params: queryCondition
  });
}
export function getRoleById(id) {
  return fetch({
    url: 'v1/role/getAllMenuByRoleId',
    method: 'get',
    params: {id}
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

/**得到排班 */
export function getPlan() {
  return fetch({
    url: 'v1/arrange/getlist',
    method: 'get',
  });
}
/**修改排班 */
export function updatePlan(plan) {
  return fetch({
    url: 'v1/arrange/update',
    method: 'put',
    data:plan
  });
}
/**添加排班 */
export function addPlan(plan) {
  return fetch({
    url: 'v1/arrange/add',
    method: 'post',
    data:plan
  });
}
/**删除排班 */
export function deletePlan(id) {
  return fetch({
    url: 'v1/arrange/delete/'+id,
    method: 'delete',
  });
}
