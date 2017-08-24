/**
 * 封装了 tree like JSON 的一些操作，
 *
 * 即，以[]为每层数据的最外层， 以 childkey 为孩子节点
 * 例如 : [{...},{...},{...,childkey:[{...}]}]
 *
 *
 */

import {
  fromJS
} from 'immutable'

/**
 *
 *  遍历一个树，基于 immutablejs，所以传入的 callback 必须用的 immutablejs 的语法。
 *  对挂载到 vue 后的 data 会导致丢失监听
 *
 * @export
 *
 * @param {Array} coll
 * @param {String} childkey
 * @param {Function} callback
 * @param {any} [initialValue={}]
 * @returns {Array} 修改后的 collection
 */

export function traversalTreeImmutable(coll, childkey, callback, initialValue = {}) {
  function _traverasl(coll, parent) {
    if (!coll || coll === undefined || coll.isEmpty()) {
      return coll
    }

    return coll.map(item => {
      let child = null
      if (item.has(childkey)) {
        child = _traverasl(item.get(childkey), item)
        // 如果 child 为空，则删除这个属性
        // console.log(child)
        if (!child || (child && child.isEmpty())) {
          item = item.delete(childkey)
          // console.log(item.toJS())
        } else {
          item = item.set(childkey, child)
        }
      }


      item = callback(item, parent)
      // console.log(item.toJS())

      return item
    })
  }


  return _traverasl(fromJS(coll), fromJS(initialValue)).toJS()
}


/**
 *
 *   先序遍历一个树， 会修改树内部的节点
 *
 *
 * @export
 * @param {any} coll
 * @param {any} childkey
 * @param {any} pre  先序访问的 callback
 * @param {any} post  后续访问的 callback
 * @param {any} [initialValue={}]
 * @returns
 */
export function traversalTree(coll, childkey, pre, post, initialValue = {}) {
  function _traverasl(coll, parent) {
    // console.log(coll)
    if (!coll || coll === undefined || coll.length <= 0) {
      return null
    }

    return coll.map(item => {
      item = pre(item, parent)

      if (childkey in item) {
        // console.log(item)
        item[childkey] = _traverasl(item[childkey], item)
        // 如果 child 为空，则删除这个属性
        // console.log(child)
        if (!item[childkey]) {
          delete item[childkey]
        }
      }

      item = post(item, parent)

      // console.log(item)
      return item
    })
  }


  return _traverasl(coll, initialValue)
}

/**
 *  返回第一个被找到的值
 *
 * @export
 * @param {any} coll
 * @param {any} childkey
 * @param {any} callback
 * @returns
 */

export function findTree(coll, childkey, callback) {
  if (!coll || coll === undefined || coll.length === 0) {
    return null
  }

  let ret = []
  coll.forEach(item => {
    // console.log(item.path)
    if (callback(item)) {
      // console.log('in')
      ret.push(item)
      // return item
    } else if (item[childkey]) {
      ret.push(findTree(item[childkey], childkey, callback))
    }
  })

  ret = ret.filter(x => x !== null)
  if (ret.length <= 0) {
    return null
  }

  return ret[0]
}



/**
 * immutable: and it return a new tree like object
 *
 * @export
 * @param {Array} coll
 * @param {String} childkey
 * @param {Function} callback
 * @returns
 */
export function filterTree(coll, childkey, callback) {
  if (!coll || coll === undefined || coll.length === 0) {
    return coll
  }
  // console.log(coll)
  coll = coll.filter(callback).map(item => {
    // console.log(item)
    const ret = Object.assign({}, item) // let it be immutable
    if (ret[childkey]) {
      ret[childkey] = filterTree(item[childkey], childkey, callback)
      // 如果当前节点不满足，但是孩子节点可以满足的，那么僵当前节点替换为孩子节点
      // 所以需要先遍历到叶子节点，再向上回溯
      // if (!callback(item)) {
      //   ret = ret[childkey]
      // }
      if (ret[childkey].length < 1) {
        delete ret[childkey]
      }
    }

    return ret
  })
  // console.log(beforeFilter)
  // coll = beforeFilter.
  // console.log(coll)
  // if (coll.length <= 0) {
  //   coll = beforeFilter.childkey
  // }


  // console.log(coll)
  return coll
}


export default {
  traversalTree,
  filterTree,
  findTree
}
