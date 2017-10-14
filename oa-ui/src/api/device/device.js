import {fetch,qs} from '../../lib/utils/fetch'

//获取列表
export function getDeviceList(param) {
  return fetch({
    url: `/v1/device/getlist`,
    method: 'get',
    params: param
  })
}
//新增
export function addDevcie(device) {
  return fetch({
    url: `/v1/device/add`,
    method: 'post',
    data: device
  })
}
//修改
export function updateDevcie(device) {
  return fetch({
    url: `/v1/device/update`,
    method: 'put',
    data: device
  })
}
//详情
export function devcieInfo(id) {
  return fetch({
    url: `/v1/device/detail`,
    method: 'get',
    params: {id}
  })
}
//删除
export function delDevcie(device) {
  return fetch({
    url: `/v1/device/delete`,
    method: 'delete',
    data: device
  })
}
