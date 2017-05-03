package ut.algos.rb;

import java.awt.peer.SystemTrayPeer;

public class BinarySearch {

    private int search(int haystack[], int needle, int start, int end) {
        int mid = start + ((end - start) / 2);

        if (start >= end)
            return -1;

        if (haystack[mid] == needle) {
            return mid;
        } else if (haystack[mid] > needle) {
            return search(haystack, needle, start, mid);
        } else {
            return search(haystack,needle, mid, end);
        }

    }

    public int search(int haystack[], int needle) {
        return search(haystack, needle, 0, haystack.length);
    }

    public static void main(String[] args) {
        BinarySearch b = new BinarySearch();
        int arr[] = {1, 2, 3};
        int search = b.search(arr, 1);

        System.out.println(search);
    }
}
