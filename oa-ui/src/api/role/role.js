import {fetch,qs} from '../../lib/utils/fetch'

export function getRoleList(queryCondition) {
  return fetch({
    url: 'role/getRoleList',
    method: 'get',
    params: queryCondition
  });
}
