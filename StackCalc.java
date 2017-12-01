package code;
import java.util.Stack;

/**
 * This class is used for doing the basic calculation.
 *
 * This handles addition and multiplication of token1 and token2.
 * Read values, token1 and token2 in this case, from LLInt.
 *
 * Each StackCalc object maintains a stack of LLInt values.
 * The void push(String n) method converts a string to an LLInt and pushes it on the top of the stack.
 * The String multiply() method pops the top two values off the stack, multiplies them, 
 * pushes the result onto the stack, and returns a string representation of the product.
 * The String add() method is similar, but for addition.
 * You can add other methods for debugging, printing, clearing, etc., as desired.
 */

public class StackCalc {
    private static final int RADIX = 10; // constant 10
    private Stack<LLInt> stack = new Stack<LLInt>();
    
    /**
     * This method is used for pushing number to the statck
     *
     * @param token
     *            the token to be pushed
     */
    public void push(String token) {
        // push token to the stack
        LLInt newToken = new LLInt();
        for (int i = 0; i < token.length(); i++) {
            try {
                int number = Integer.parseInt(token.charAt(token.length() - i - 1) + "");
                newToken.append(number);
            } catch (Exception e) {
                System.err.println("Error:  Illegale Input. The token must be integer.");
                System.exit(1);
            }
        }
        stack.push(newToken);
        
    }
    
    /**
     * This method is used for adding token1 and token2
     *
     */
    public String add() {
        LLInt result = new LLInt();
        LLInt token1 = stack.pop();
        LLInt token2 = stack.pop();
        int size1 = token1.size(); // size of token1
        int size2 = token2.size(); // size of token2
        // the integer used for increasing the weight as power of 10
        int carry = 0;
        // adding token1 and token2 digit by digit
        for (int i = 0; i < size1 && i < size2; i++) {
            int temp = token1.get(i) + token2.get(i) + carry;
            if (temp >= RADIX) {
                temp = temp - RADIX;
                carry = 1;
            } else {
                carry = 0;
            }
            result.append(temp);
        }
        if (size1 > size2) {
            for (int i = size2; i < size1; i++) {
                int temp = token1.get(i) + carry;
                if (temp >= RADIX) {
                    temp = temp - RADIX;
                    carry = 1;
                } else {
                    carry = 0;
                }
                result.append(temp);
            }
        } else {
            for (int i = size1; i < size2; i++) {
                int temp = token2.get(i) + carry;
                if (temp >= RADIX) {
                    temp = temp - RADIX;
                    carry = 1;
                } else {
                    carry = 0;
                }
                result.append(temp);
            }
        }
        // adding one more digit if necessary
        if (carry > 0) {
            result.append(carry);
        }
        // remove unnecessary '0'
        while (result.size() > 1 && result.get(result.size() - 1) == 0) {
            result.removeLast();
        }
        stack.push(result);
        return result.toString();
    }
    
    /**
     * This method is a helper method for multiply, which is used for adding
     * token1 and token2. It has the same algorithm with public void add()
     *
     * @param token1
     *            the first token
     * @param token1
     *            the second token
     * @return the addition of token1 and token2
     */
    public LLInt add(LLInt token1, LLInt token2) {
        LLInt result = new LLInt();
        int size1 = token1.size();
        int size2 = token2.size();
        int carry = 0; // the integer used for increasing the weight as power of
        // 10
        for (int i = 0; i < size1 && i < size2; i++) {
            int temp = token1.get(i) + token2.get(i) + carry;
            if (temp >= RADIX) {
                temp = temp - RADIX;
                carry = 1;
            } else {
                carry = 0;
            }
            result.append(temp);
        }
        if (size1 > size2) {
            for (int i = size2; i < size1; i++) {
                int temp = token1.get(i) + carry;
                if (temp >= RADIX) {
                    temp = temp - RADIX;
                    carry = 1;
                } else {
                    carry = 0;
                }
                result.append(temp);
            }
        } else {
            for (int i = size1; i < size2; i++) {
                int temp = token2.get(i) + carry;
                if (temp >= RADIX) {
                    temp = temp - RADIX;
                    carry = 1;
                } else {
                    carry = 0;
                }
                result.append(temp);
            }
        }
        if (carry > 0) {
            result.append(carry);
        }
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) != 0) {
                return result;
            }
        }
        // remove unnecessary '0'
        while (result.size() > 1 && result.get(result.size() - 1) == 0) {
            result.removeLast();
        }
        return result;
    }
    
    /**
     * This method is used for adding token1 and token2
     *
     */
    public String multiply() {
        LLInt result = new LLInt();
        LLInt token1 = stack.pop();
        LLInt token2 = stack.pop();
        result.append(0);// the initial value is 0
        int size1 = token1.size();
        int size2 = token2.size();
        int power = 0;
        int carry = 0;
        for (int i = 0; i < size1; i++) {
            LLInt tempResult = new LLInt();
            for (int j = 0; j < power; j++) {
                tempResult.append(0);
            }
            // multiply the number digit by digit
            for (int j = 0; j < size2; j++) {
                int temp = token1.get(i) * token2.get(j) + carry;
                if (temp >= RADIX) {
                    carry = temp / RADIX;
                    temp = temp - RADIX * carry;
                } else {
                    carry = 0;
                }
                tempResult.append(temp);
            }
            // adding one more digit if necessary
            if (carry > 0) {
                tempResult.append(carry);
            }
            result = add(result, tempResult);
            carry = 0;
            power++;
        }
        // remove unnecessary '0'
        while (result.size() > 1 && result.get(result.size() - 1) == 0) {
            result.removeLast();
        }
        stack.push(result);
        return result.toString();
    }
}
