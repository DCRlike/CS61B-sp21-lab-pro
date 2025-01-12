package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> ANobug = new AListNoResizing<>();
        BuggyAList<Integer> Bhasbug = new BuggyAList<>();
        ANobug.addLast(4);
        Bhasbug.addLast(4);
        ANobug.addLast(5);
        Bhasbug.addLast(5);
        ANobug.addLast(6);
        Bhasbug.addLast(6);

        Assert.assertEquals(ANobug.size(), Bhasbug.size());
        Assert.assertEquals(ANobug.removeLast(), Bhasbug.removeLast());
        Assert.assertEquals(ANobug.removeLast(), Bhasbug.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 100000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                Assert.assertEquals(L.size(), B.size());
            } else if (operationNumber == 1) {
                // size
                if (L.size() == 0) {
                    continue;
                }
                int RemoveLastL = L.removeLast();
                int RemoveLastB = B.removeLast();
                Assert.assertEquals(RemoveLastB, RemoveLastL);
            } else if (operationNumber == 2) {
                if (L.size() == 0) {
                    continue;
                }
                int LastOfL = L.getLast();
                int LastOfB = B.getLast();
                Assert.assertEquals(LastOfL, LastOfB);
            }
        }
    }
}
