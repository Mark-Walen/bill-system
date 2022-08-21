package vlue.tech.billsystem.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Utils {

    public static String IMMUTABLE_STRING_SALT = "somekey";

    public String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String sha256(String str) {
        return DigestUtils.sha256Hex(str);
    }

    public String txtEncode(String key, String clear) {
        StringBuilder enc = new StringBuilder();
        int keyLen = key.length();
        int clearLen = clear.length();
        for (int i = 0; i < clearLen; i++) {
            char keyC = key.charAt(i % keyLen);
            char clearI = clear.charAt(i);
            char encC = (char) (((int) keyC + (int) clearI) % 256);
            enc.append(encC);
        }
        return Base64.encodeBase64URLSafeString(enc.toString().getBytes(StandardCharsets.UTF_8));
    }

    public String txtDecode(String key, String enc) {
        StringBuilder dec = new StringBuilder();

        enc = Base64.encodeBase64URLSafeString(enc.getBytes(StandardCharsets.UTF_8));
        int encLen = enc.length();
        int keyLen = key.length();
        for (int i = 0; i < encLen; i++) {
            char keyC = key.charAt(i % keyLen);
            char encI = enc.charAt(i);
            char decC = (char) ((256 + (int) encI - (int) keyC) % 256);
            dec.append(decC);
        }
        return dec.toString();
    }

    public String hashId(Integer userId) {
        String extraKey = "-";
        String x = sha256(userId + extraKey);
        return x.substring(0, 8);
    }

    public Boolean isNumeric(String data) {
        return StringUtils.isNumeric(data);
    }

    public List<Integer> parseIntList(String text) {
        List<Integer> res = new ArrayList<>();
        for (String tmp : text.split(",")) {
            int n = Integer.parseInt(tmp);
            res.add(n);
        }
        res.sort(Comparator.naturalOrder());
        return res;
    }

    public String jsonCompact(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static String randomString() {
        return sha256(String.valueOf(RandomUtils.nextInt()));
    }

    public static String immutableString() {
        String a = randomString().substring(0, 60);
        String b = sha256(a + IMMUTABLE_STRING_SALT).substring(0, 4);
        return a + b;
    }

    public Boolean isImmutableString(String txt) {
        int txtLen = txt.length();
        if (txtLen != 64) {
            return false;
        }
        String a = txt.substring(0, 60);
        String b = txt.substring(txtLen - 4, txtLen);
        return sha256(a + IMMUTABLE_STRING_SALT).substring(0, 4).equals(b);
    }
}
