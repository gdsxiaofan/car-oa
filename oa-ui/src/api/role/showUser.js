import {fetch,qs} from '../../lib/utils/fetch'

export function getUserList(queryCondition) {
  return fetch({
    url: 'v1/user/query/list',
    method: 'get',
    params: queryCondition
  });
}
export function updateUser(user) {
  return fetch({
    url: 'v1/user/updateUser',
    method: 'put',
    data: user
  });
}
