public class Palindrome {
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }


    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        for (int i = 0; i < wordDeque.size() / 2; i++) {
            if (wordDeque.removeFirst() != wordDeque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        int size = wordDeque.size();
        for (int i = 0; i < size / 2; i++) {
            if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
