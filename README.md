### 明道企业授权签名算法

- 进入明道云系统 **组织管理 - 其他 - 组织密钥**，获取企业授权开放接口密钥对信息
- 获取当前 Unix 时间戳（精度为毫秒），此时间戳在调用数据接口过程中需要传递
- 将密钥对当中的 AppKey、SecretKey 和此 Unix 时间戳进行按照键值对参数的方式拼接为字符串，如：`AppKey=value1&SecretKey=value2&Timestamp=value3`，各个参数以&拼接
- 将拼接的字符串用SHA256加密，并进行BASE64编码即可得到签名

### 签名算法参考

- [Java](Signature.java)
- [C#](Signature.cs)
- [Go](Signature.go)


关于企业授权开放接口，详见《[企业授权开放接口说明](https://www.showdoc.cc/mingdao?page_id=15519621)》
