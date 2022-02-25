public class BSTTree {
    class TreeNode {
        int val;
        private TreeNode left;
        private TreeNode right;
    }

    private TreeNode root;

    public static boolean isBSTGood(TreeNode T) {
        return isBSTGood(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBSTGood(TreeNode T, int min, int max) {
        if (T == null) {
            return true;
        } else if (T.val > max || T.val < min) {
            return false;
        }
        return isBSTGood(T.left, min, T.val) && isBSTGood(T.right, T.val, max);
    }
}
