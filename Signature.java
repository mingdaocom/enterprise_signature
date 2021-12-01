import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Signature {

    /**
     * 获取签名
     *
     * @param appKey    AppKey
     * @param secretKey SecretKey
     * @param timestamp UTC时间戳（精度为毫秒）
     * @return
     */
    public static String getSignature(String appKey, String secretKey, Long timestamp) throws Exception {

        //添加参数
        Map<String, String> paras = new HashMap<>();
        paras.put("AppKey", appKey);
        paras.put("SecretKey", secretKey);
        paras.put("Timestamp", timestamp.toString());

        //按Key排序
        TreeMap<String, String> sortParas = new TreeMap<>();
        sortParas.putAll(paras);

        //拼接参数
        Iterator<String> it = sortParas.keySet().iterator();
        StringBuilder sortQueryStringTmp = new StringBuilder();
        while (it.hasNext()) {
            String key = it.next();
            sortQueryStringTmp.append("&").append(key).append("=").append(paras.get(key));
        }
        String sortedQueryString = sortQueryStringTmp.substring(1);

        String sign = Signature.SHA256(sortedQueryString);
        sign = (new sun.misc.BASE64Encoder().encode(sign.getBytes("UTF-8"))).replace("\n", "").replace("\r", "");//需替换BASE64的换行
        return sign;

    }

    /**
     * SHA256加密
     *
     * @param strText
     * @return
     */
    private static String SHA256(final String strText) throws Exception {

        String strResult = null;

        if (strText != null && strText.length() > 0) {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(strText.getBytes("UTF-8"));

            byte byteBuffer[] = messageDigest.digest();

            StringBuffer strHexString = new StringBuffer();
            for (int i = 0; i < byteBuffer.length; i++) {
                String hex = Integer.toHexString(0xff & byteBuffer[i]);
                if (hex.length() == 1) {
                    strHexString.append('0');
                }
                strHexString.append(hex);
            }
            strResult = strHexString.toString();
        }

        return strResult;
    }
}