package org.ploxie;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        int[] A = new int[]{1,0,1,1,1};

        int n = A.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] == A[i + 1])
                result = result + 1;
        }
        int r = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            if (i > 0) {
                if (A[i - 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            if (i < n - 1) {
                if (A[i + 1] != A[i])
                    count = count + 1;
                else
                    count = count - 1;
            }
            System.out.println(r);
            r = Math.max(r, count);
        }
        //System.out.println(result +", "+r + ", "+(result + r));

        System.out.println(result + r);

    }

    /*public static void main(String[] args) {

        int[] A = new int[]{5, 2, 4, 0, 0, 0};

        int smallestCost = Integer.MAX_VALUE;

        for(int p = 1 ; p < A.length;p++){
            for(int q = p+2;q < A.length-1;q++){

                int aP = A[p];
                int aQ = A[q];

                smallestCost = Math.min(smallestCost, (aP + aQ));
            }
        }

        System.out.println(smallestCost);

    }*/
}
