package hash_data;

import java.security.MessageDigest; 
import java.util.Base64;

public class hash {
    private static String add = "asdfasdfasd;@asdfasdfasd.?";

    public static String encryptToSHA1(String str) {
        String result = null;
        String s = str + add;
        try {
            byte[] data_to_byte = s.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(data_to_byte);
            byte[] digest = md.digest();
            result = Base64.getEncoder().encodeToString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

