package main;

public class ArrayMult {
    public int[] mult(int[] array1, int[] array2) {
        int maxLen = Math.max(array1.length, array2.length); 
        int minLen = Math.min(array1.length, array2.length); 
        int[] outArray = new int[maxLen];

        // multiply overlapping elements only
        for (int i = 0; i < minLen; i++) {
            outArray[i] = array1[i] * array2[i];
        }

        // copy remaining elements from the longer array to outarray
        int[] longArray = (array1.length > array2.length) ? array1 : array2;
        for (int j = minLen; j < maxLen; j++) {
            outArray[j] = longArray[j];
        }
        return outArray;
    }
}