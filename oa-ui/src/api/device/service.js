import {fetch,qs} from '../../lib/utils/fetch'

//获取列表
export function getServiceList(param) {
  return fetch({
    url: `/v1/service/getlist`,
    method: 'get',
    params: param
  })
}
//新增
export function addService(device) {
  return fetch({
    url: `/v1/service/add`,
    method: 'post',
    data: device
  })
}
//修改
export function updateService(device) {
  return fetch({
    url: `/v1/service/update`,
    method: 'put',
    data: device
  })
}
//删除
export function delService(device) {
  return fetch({
    url: `/v1/service/del`,
    method: 'delete',
    data: device
  })
}
