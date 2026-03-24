package swp4.testing;

import swp4.basics.collections.OwnStack;
import java.util.EmptyStackException;

public class OwnStackTest {
    public static void main(String[] args) {
        System.out.println("OwnStack basic tests");

        OwnStack<Integer> intStack = new OwnStack<>();
        System.out.println("isEmpty (expect true): " + intStack.isEmpty());
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        System.out.println("size (expect 3): " + intStack.size());
        System.out.println("peek (expect 3): " + intStack.peek());
        System.out.println("pop (expect 3): " + intStack.pop());
        System.out.println("pop (expect 2): " + intStack.pop());
        System.out.println("pop (expect 1): " + intStack.pop());
        System.out.println("isEmpty (expect true): " + intStack.isEmpty());

        OwnStack<String> strStack = new OwnStack<>(1);
        strStack.push("a");
        strStack.push("b"); // force growth
        System.out.println("strStack: " + strStack);

        try {
            OwnStack<Double> empty = new OwnStack<>(0);
            empty.pop();
            System.out.println("ERROR: Empty pop did not throw");
        } catch (EmptyStackException e) {
            System.out.println("EmptyStackException correctly thrown on pop()");
        }

        System.out.println("All tests finished");
    }
}

