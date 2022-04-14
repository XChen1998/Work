public class TraverseMethod {
    /**
     * @param source:
     * @param target:
     * @return: return the index
     */
    public int strStr(String source, String target) {

        if (source == null) {
            return -1;
        }

        if (target == null || target.equals("")) {
            return 0;
        }
        int targetLength = target.length();

        for (int curPos = 0; curPos < source.length() - targetLength + 1; curPos++) {
            boolean notEqual = false;
            for (int index = 0; index < targetLength; index++) {
                if (!(source.charAt(curPos + index) == target.charAt(index))) {
                    notEqual = true;
                    break;
                }
            }
            if (!notEqual) {
                return curPos;
            }
        }
        return -1;
    }
}