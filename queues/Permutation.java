/* *****************************************************************************
 *  Name: Andrew Chan
 *  Date: 20 Apr 2020
 *  Description: Princeton Algorithms, Part I
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> q;
        int k = Integer.parseInt(args[0]);
        q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }

    }
}
