import hashlib
import base64

# 获取签名 timestamp UTC时间戳（精度为毫秒）
def getSignature(appkey, appsecret, timestamp):
    dict = {"AppKey": appkey, "SecretKey": appsecret,
            "Timestamp": str(timestamp)}
    dict_sort = sorted(dict.items(), key=lambda k: k[0], reverse=False)
    signstr = ''
    for key, value in dict_sort:
        signstr = signstr + '&' + key + '=' + value
    signstr = signstr[1:]
    sha = hashlib.sha256()
    sha.update(signstr.encode("utf-8"))
    sign = str(base64.b64encode(sha.hexdigest().encode("utf-8")), "utf-8")
    return sign
