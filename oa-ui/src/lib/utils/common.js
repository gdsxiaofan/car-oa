/**
 * 搜索过滤的通用函数
 *
 * @export
 * @param {Array} target  过滤的数组，其中数组的每一项是对象
 * @param {String} filterVal  搜索的字符串，可以以空格分割
 * @param {Boolean} deep 是否搜索对象的子对象
 * @returns {Array} 搜索过滤后的新数组
 */
export function filterSearch(target, filterVal, deep = false) {
  if (!target || target.length <= 0) {
    return []
  }
  if (!filterVal || filterVal === '' || filterVal.length === 0) {
    return target.slice()
  }

  const searchReg = []
  filterVal.split(' ').filter(x => x !== undefined && x !== '').forEach(x => searchReg.push(new RegExp(x)))

  return target.filter(x => {
    const joinedValues = Object.values(x).filter(x => x !== undefined).map(x => {
      if (deep) {
        return JSON.stringify(x)
      } else {
        // return x.toString()
        return String(x)
      }
    }).join()
    // console.log(joinedValues)
    // console.log(searchReg.some(x => x.test(joinedValues)))
    return searchReg.every(x => x.test(joinedValues))
  })
}


/**
 * 排序函数
 *
 * @export
 * @param {boolean} [ingoreCase=false] 是否忽视大小写
 * @param {boolean} [reverse=false]    排序的顺序
 * @param {String} [key=null]          需要排序的那个对象 key
 * @returns {Function} 返回新的排序函数
 */
export function sortStringFn(ingoreCase = false, reverse = false, key = null) {
  // console.log(key)
  const r = reverse ? -1 : 1

  return function(a, b) {
    let ret = 0
    if (key) {
      a = a[key]
      b = b[key]
    }
    // console.log(a)
    if (ingoreCase) {
      a = a.toLowerCase()
      b = b.toLowerCase()
    }

    if (a > b) {
      ret = 1
    } else if (a < b) {
      ret = -1
    }

    return ret * r
  }
}


export function addTitle(title) {
  title = title ? title + ' - 拍拍信' : '微服务管理 - 拍拍信';
  window.document.title = title;
}


/**
 *  格式化日期函数
 *  用法: FormatData.call(data,'yyyy-MM-dd hh:mm:ss')
 *
 * @param {String} fmt  日期格式
 * @returns 符合日期格式的字符串
 */
export function formatData(fmt) { // author: meizz
  const o = {
    'M+': this.getMonth() + 1, // 月份
    'd+': this.getDate(), // 日
    'h+': this.getHours(), // 小时
    'm+': this.getMinutes(), // 分
    's+': this.getSeconds(), // 秒
    'q+': Math.floor((this.getMonth() + 3) / 3), // 季度
    S: this.getMilliseconds() // 毫秒
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
  for (const k in o) { if (new RegExp('(' + k + ')').test(fmt)) fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length)); }
  return fmt;
}
//获取cookie、
export function getCookie(name) {
  let arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
  if (arr = document.cookie.match(reg))
    return (arr[2]);
  else
    return null;
}

//设置cookie,增加到vue实例方便全局调用
export function setCookie (c_name, value, expiredays) {
  let exdate = new Date();
  exdate.setDate(exdate.getDate() + expiredays);
  document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
}

//删除cookie
export function delCookie (name) {
  let exp = new Date();
  exp.setTime(exp.getTime() - 1);
  let cval = getCookie(name);
  if (cval != null)
    document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}
