import {fetch,qs} from '../../lib/utils/fetch'

export function getRoleList(queryCondition) {
  return fetch({
    url: 'v1/role/query',
    method: 'get',
    params: queryCondition
  });
}
export function delRole(roleId) {
  return fetch({
    url: 'v1/role/delete',
    method: 'delete',
    params: {roleId:roleId}
  });
}
