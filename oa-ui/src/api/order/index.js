import {fetch,qs} from '../../lib/utils/fetch'

//获取列表
export function getOrderList(param) {
  return fetch({
    url: `/v1/order/list`,
    method: 'get',
    params: param
  })
}
//获取工单详情
export function getOrderInfo(id) {
  return fetch({
    url: `/v1/order/info`,
    method: 'get',
    params: {id}
  })
}
//处理工单
export function checkUser(orderParam) {
  return fetch({
    url: `/v1/order/checkUser`,
    method: 'get',
    params:  orderParam,
  })
}
//处理工单
export function doOrder(orderParam) {
  return fetch({
    url: `/v1/order/do`,
    method: 'put',
    data:  orderParam,
  })
}

