public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ans = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            ans.addLast(word.charAt(i));
        }
        return ans;
    }

    public boolean isPalindrome(String word) {
        int length = word.length();
        for (int i = 0; i < length / 2; i += 1) {
            if (word.charAt(i) != word.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        for (int i = 0; i < length / 2; i += 1) {
            if (!cc.equalChars(word.charAt(i), word.charAt(length - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
