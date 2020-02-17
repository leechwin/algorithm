class Solution {
    public boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            // check key
            if (map.keySet().contains(ch)) {
                stack.push(ch);
            } else if (map.values().contains(ch)) {
                // check value
                if (!stack.isEmpty() && map.get(stack.peek()) == ch) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
