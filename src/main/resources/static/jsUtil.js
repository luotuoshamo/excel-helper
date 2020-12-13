/**
 * 日期工具
 */
let DateUtil = new Object()

/**获取某个月的天数*/
DateUtil.daysOfMonth = function (year, month) {
    let date = new Date(year, month, 0)
    return date.getDate()
};

/**当前时间*/
DateUtil.currentDate = function () {
    return new Date()
}
/**当前年*/
DateUtil.currentYear = function () {
    return new Date().getFullYear()
}
/**当前月*/
DateUtil.currentMonth = function () {
    let month = new Date().getMonth() + 1
    return month < 10 ? "0" + month : month
}
/**当前日*/
DateUtil.currentDay = function () {
    let day = new Date().getDate()
    return day < 10 ? "0" + day : day
}
////////////////////////////////////////////////
/**
 * 加密
 */
let EncryptionUtil = new Object()
/**
 * 两次md5加密
 * @param str {String} 要加密的字符串
 */
EncryptionUtil.doubleMd5 = function (str) {
    return str
}


////////////////////////////////////////////////
/**
 * 字符串工具
 */
let StringUtil = new Object()
/**
 * 判断字符串是否为空
 */
StringUtil.isBlank = function (s) {
    if (!s) return true
    if (!s.trim()) return true
    return false
}

////////////////////////////////////////////////

/**通过标签id获取标签*/
function id(id) {
    return document.getElementById(id)
}