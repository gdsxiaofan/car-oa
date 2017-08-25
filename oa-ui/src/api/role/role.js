import {fetch,qs} from '../../lib/utils/fetch'

export function getRoleList(queryCondition) {
  return fetch({
    // url: 'role/getRoleList',
    url: 'login/aaa',
    method: 'get',
    params: queryCondition
  });
}
