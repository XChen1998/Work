public class RKAlgs {
    /**
     * @param source: A source string
     * @param target: A target string
     * @return: An integer as index
     */
    public int strStr(String source, String target) {

        // write your code here
        int base = 100000;
        if (source == null || target == null) {
            return -1;
        }

        int targetLength = target.length();
        if (targetLength == 0) {
            return 0;
        }

        int power = 1;

        for (int i = 0; i < targetLength; i++) {
            power = power * 31 % base;
        }

        int targetCode = 0;

        for (int i = 0; i < targetLength; i++) {
            targetCode = (targetCode * 31 + target.charAt(i)) % base;
        }

        int sourceCode = 0;

        for (int i = 0; i < source.length(); i++) {

            sourceCode = (sourceCode * 31 + source.charAt(i)) % base;

            if (i < targetLength - 1) {
                continue;
            }

            if (i >= targetLength) {
                sourceCode = sourceCode - (source.charAt(i - targetLength) * power) % base;
                if (sourceCode < 0) {
                    sourceCode += base;
                }
            }

            if (sourceCode == targetCode) {
                if (source.substring(i - targetLength + 1, i + 1).equals(target)) {
                    return i - targetLength + 1;
                }
            }
        }
        return -1;
    }
}