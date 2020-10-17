public class Question_12 {
    private static final String character_1 = "IXCM"; // 1 10 100 1000
    private static final String character_4 = "IVXLCD"; //4 40 400
    private static final String character_5 = "VLD"; // 5 50 500
    private static final String character_9 = "IXXCCM"; // 9 90 900

    public static String intToRoman(int num) {
        var integer = String.valueOf(num);
        var sb = new StringBuilder();
        int numLength = integer.length();
        for (var i = 0; i < numLength; i++) {
            var nowChar = integer.charAt(i);
            var beginIndex = numLength - 1 - i;

            if (nowChar == '9') {
                sb.append(character_9, 2 * beginIndex, 2 * beginIndex + 2);
                continue;
            } else if ((int) nowChar >= (int) '5') {
                sb.append(character_5.charAt(beginIndex));
                nowChar = (char) ((int) nowChar - 5);
            }

            if (nowChar == '4') sb.append(character_4, 2 * beginIndex, 2 * beginIndex + 2);
            else sb.append(String.valueOf(character_1.charAt(beginIndex)).repeat((int) nowChar - (int) '0'));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(8));
    }
}
