import {fetch,qs} from '../../lib/utils/fetch'
const baseUrl='/v1/tpm/'
//获取列表
export function getOrderList(param) {
  return fetch({
    url: `${baseUrl}getlist`,
    method: 'get',
    params: param
  })
}
//获取列表
export function getAuditlist(param) {
  return fetch({
    url: `${baseUrl}getAuditlist`,
    method: 'get',
    params: param
  })
}
//获取工单详情
export function getOrderInfo(tpmId) {
  return fetch({
    url: `${baseUrl}detail`,
    method: 'get',
    params: {tpmId}
  })
}
//处理工单
export function checkUser(orderParam) {
  return fetch({
    url: `${baseUrl}checkUser`,
    method: 'get',
    params:  orderParam,
  })
}
//处理工单
export function doOrder(orderParam) {
  return fetch({
    url: `${baseUrl}update`,
    method: 'post',
    data: orderParam,
    headers: {'Content-Type': 'multipart/form-data'}
  })
}

