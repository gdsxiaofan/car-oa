import _ from 'lodash'
import Tree from './tree'
import {
  APP_ROUTE_PREFIX
} from './consts'


/**
 *
 * @param {any} root
 * @returns
 *
 * @example
 *
 * [{
  "id": "/applications/#/microservice",
  "name": "微服务",
  "icon": "ios-cloud",
  "menuList":[]}]
  =====>
 "/applications/#/microservice" :{
   "name": "微服务",
  "icon": "ios-cloud"
}
 *
 */
function flatPermission(root) {
  if (root == null) {
    return
  }
  const ret = {}

  root.forEach(x => {
    ret[x.id] = _.omit(x, ['menuList'])
    ret.title = ret.name
    // console.log(ret)
    // console.log(level)
    if (x.menuList) {
      // 如果没有 id 则认为它是按钮控制
      // 但是 menuList 既包括按钮的，也包括页面的，所以先要 partition
      // part[0] 是包括页面控制，part[1] 是按钮控制
      const part = _.partition(x.menuList, o => o.type !== 'BUTTON')
      if (part[1].length > 0) {
        ret[x.id].btnList = part[1]
      }
      Object.assign(ret, flatPermission(part[0]))
    }
  })
  return ret
}


function fullPath(path) {
  return APP_ROUTE_PREFIX + path
}



/**
 *把 permission 这一层的数据和asyncRoute 这一层匹配的数据加到 ret 中
 * 不是 immutable，且会改变 path 成完成路径
 *
 * @export
 * @param {any} asyncRoute
 * @param {any} permission
 * @returns
 */
export function filterPermission2Route(asyncRoute, permission, prefix = '') {
  // 先把permission铺平
  const permisssionMap = flatPermission(permission)

  // console.log(permisssionMap)


  function traversal(coll, path) {
    if (!coll || coll === undefined || coll.length === 0) {
      return coll
    }

    // console.log(path)
    coll.forEach(item => {
      // 防止出现两个 // 的情况
      if (path === '/') {
        path = ''
      }
      if (item.path[0] !== '/') {
        item.path = path + '/' + item.path
      } else {
        item.path = path + item.path
      }
      if (item.redirect) {
        item.redirect = prefix + item.redirect
      }
      // console.log(item.path)
      if (item.meta && item.meta.id in permisssionMap) {
        Object.assign(item.meta, permisssionMap[item.meta.id])
      }
      if (item.children) {
        traversal(item.children, item.path)
      }
    })
  }

  traversal(asyncRoute, prefix)


  return Tree.filterTree(asyncRoute, 'children', x => !x.meta || x.meta.id in permisssionMap)
}
