var crypto = require('crypto');
var _ = require('lodash');

/**
 * 获取签名
 * @param {*} appKey 
 * @param {*} secretKey 
 * @param {*} timestamp UTC时间戳（精度为毫秒）
 * @returns 
 */
function getSignature(appKey, secretKey, timestamp) {
    var tempdata = {
        "AppKey": appKey,
        "SecretKey": secretKey,
        "Timestamp": timestamp.toString()
    };
    var signstr = '';
    var data = {};
    _(tempdata).keys().sort().each(function (key) {
        data[key] = tempdata[key];
        signstr = signstr + '&' + key + '=' + tempdata[key];
    });
    signstr = signstr.substring(1);
    return base64(sha256(signstr));
}
function sha256(s) {
    var hash = crypto.createHash('sha256');
    hash.update(s, 'utf8');
    return hash.digest('hex').toLowerCase();
}
function base64(s) {
    var result = new Buffer.from(s, 'utf8');
    return result.toString('base64');
}