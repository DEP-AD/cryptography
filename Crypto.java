package lk.ijse.crypto;

public class Crypto {

    /**
     * original text --> cipher text
     * =============================
     * concatenate the original text with key (plainText + key)
     * then get reversed String that concatenate text (get reverse string of  the scrambled text)
     * then add each character of reversedText to sum of unicode
     * then that new integer value multiply by 49
     * and after subtract 7 by last integer value.
     * */
    public static String encrypt(String plainText, String key){
        String cipherText="";
        String scrambledText = plainText + key;
        String reversedText = reverseString(scrambledText);
        int secret = getUnicodeSum(key);

        for (int i = 0; i < reversedText.length(); i++) {
            int code  = reversedText.charAt(i);
            code += secret;
            code = operateCode(code);
            char newChar=(char) code;
            cipherText += newChar;
        }
        return cipherText;
    }

    public static String decrypt(String cipherText, String key){
        String reversedText="";
        int secret = getUnicodeSum(key);

        for (int i = 0; i < cipherText.length(); i++) {
            int code  = cipherText.charAt(i);
            code = reversedOperateCode(code);
            code -= secret;
            char orgChar=(char) code;
            reversedText += orgChar;
        }

        String originalText= reverseString(reversedText);

        if(!originalText.endsWith(key)){
            throw new RuntimeException("Invalid key");
        }

        return originalText.substring(0,originalText.length() - key.length());
    }

    private static String reverseString(String text){
        String reversedText = "";
        for (int i = text.length()-1; i >=0; i--) {
            reversedText += text.charAt(i);
        }
        return reversedText;
    }

    private static int getUnicodeSum(String text){
        int sum = 0;
        for (int i = 0; i < text.length(); i++) {
            sum += text.codePointAt(i);
        }
        return sum;
    }

   private static int operateCode(int code){
        code *= 49;
        code -= 7;

        return code;
    }

    private static  int reversedOperateCode(int code){
        code += 7;
        code /= 49;

        return code;
    }

}
