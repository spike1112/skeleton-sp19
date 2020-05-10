public class Palindrome {

    /* Put the word into the deque */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);

        return isPalindrome(wordDeque);
    }

    /** Recursive method: first compare the first char and the last char, then recursive determine the removed deque */
    private boolean isPalindrome(Deque<Character> wordDeque) {
        if (wordDeque.size() == 0 || wordDeque.size() == 1) {
            return true;
        }
        return wordDeque.removeFirst() == wordDeque.removeLast() && isPalindrome(wordDeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);

        return isPalindrome(wordDeque, cc);
    }

    private boolean isPalindrome(Deque<Character> wordDeque, CharacterComparator cc) {
        if (wordDeque.size() == 0 || wordDeque.size() == 1) {
            return true;
        }
        char first = wordDeque.removeFirst();
        char last = wordDeque.removeLast();

        return cc.equalChars(first, last) && isPalindrome(wordDeque, cc);
    }


}
