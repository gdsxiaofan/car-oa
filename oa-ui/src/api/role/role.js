import {fetch,qs} from '../../lib/utils/fetch'

export function getRoleList(queryCondition) {
  return fetch({
    // url: 'role/getRoleList',
    url: 'v1/role/query',
    method: 'get',
    params: queryCondition
  });
}
