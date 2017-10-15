import {fetch,qs} from '../../lib/utils/fetch'
const baseurl='/v1/check/'
//获取列表
export function getServiceList(deviceId) {
  return fetch({
    url: `${baseurl}getlist`,
    method: 'get',
    params: {deviceId}
  })
}
//新增
export function addService(device) {
  return fetch({
    url: `${baseurl}add`,
    method: 'post',
    data: device
  })
}
//修改
export function updateService(device) {
  return fetch({
    url: `${baseurl}update`,
    method: 'put',
    data: device
  })
}
//删除
export function delService(device) {
  return fetch({
    url: `${baseurl}delete`,
    method: 'delete',
    data: device
  })
}
