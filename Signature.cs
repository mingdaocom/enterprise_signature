using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Security.Cryptography;

namespace MD.Utilituy
{
    public class Signature
    {
        /// <summary>
        /// 获得签名
        /// </summary>
        /// <param name="appKey">AppKey</param>
        /// <param name="secretKey">SecretKey</param>
        /// <param name="timestamp">UTC时间戳（精度为毫秒）</param>
        /// <returns></returns>
        public static string GetSignature(string appKey, string secretKey, long timestamp)
        {
            var dic = new Dictionary<string, string>();
            dic.Add("AppKey", appKey);
            dic.Add("SecretKey", secretKey);
            dic.Add("Timestamp", timestamp.ToString());

            var sortParamters = GetSortParamters(dic);

            var hash = SHA256Encryption(sortParamters);

            var bytes = Encoding.UTF8.GetBytes(hash);

            return Convert.ToBase64String(bytes);

        }

        /// <summary>
        /// 获得SHA256加密的字符
        /// </summary>
        /// <param name="strText"></param>
        /// <returns></returns>
        public static string SHA256Encryption(string strText)
        {
            var sha256 = SHA256.Create();

            var hashedBytes = sha256.ComputeHash(Encoding.UTF8.GetBytes(strText));

            // Get the hashed string.
            return BitConverter.ToString(hashedBytes).Replace("-", "").ToLower();

        }

        /// <summary>
        /// 返回升序排列的参数用&连接起来
        /// </summary>
        /// <param name="dic"></param>
        /// <returns></returns>
        public static string GetSortParamters(Dictionary<string, string> dic)
        {
            var sb = new StringBuilder();

            foreach (var item in dic.OrderBy(a => a.Key))
            {
                sb.Append("&").Append(item.Key).Append("=").Append(item.Value);
            }

            return sb.ToString().Substring(1);
        }
    }
}