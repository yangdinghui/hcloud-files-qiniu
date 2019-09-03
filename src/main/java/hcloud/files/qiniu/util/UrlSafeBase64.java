package hcloud.files.qiniu.util;

import com.qiniu.common.Constants;
import com.qiniu.util.Base64;

/**
 * URL安全的Base64编码和解码
 */

public final class UrlSafeBase64 {

    private UrlSafeBase64() {
    }   // don't instantiate

    /**
     * 编码字符串
     *
     * @param data 待编码字符串
     * @return 结果字符串
     */
    public static String encodeToString(String data) {
        return encodeToString(data.getBytes(Constants.UTF_8));
    }

    /**
     * 编码数据
     *
     * @param data 字节数组
     * @return 结果字符串
     */
    public static String encodeToString(byte[] data) {
        return com.qiniu.util.Base64.encodeToString(data, com.qiniu.util.Base64.URL_SAFE | com.qiniu.util.Base64.NO_WRAP);
    }

    /**
     * 解码数据
     *
     * @param data 编码过的字符串
     * @return 原始数据
     */
    public static byte[] decode(String data) {
        return com.qiniu.util.Base64.decode(data, com.qiniu.util.Base64.URL_SAFE | Base64.NO_WRAP);
    }
}
