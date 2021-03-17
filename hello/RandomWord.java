/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        double count = 1;
        while (!StdIn.isEmpty()) {
            String next = StdIn.readString();
            if (StdRandom.bernoulli(1 / count))
                champion = next;
            count++;
        }
        System.out.println(champion);
    }
}
