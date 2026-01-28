package DSA.BitManipulation;

public class OperationToSameDigit {
    public static void main(String[] args) {
        int a = 15;
        int b = 8;
        String str1 = Integer.toBinaryString(a);
        String str2 = Integer.toBinaryString(b);
        int count = 0;
        int i = str1.length()-1;
        int j = str2.length()-1;
        while(i >= 0 && j >= 0){
            if(str1.charAt(i) != str2.charAt(j)){
                count++;
            }
            i--;
            j--;
        }
        while(i >= 0){
            if(str1.charAt(i) == '1'){
                count++;
            }
            i--;
        }
        while(j >= 0){
            if(str2.charAt(j) == '1'){
                count++;
            }
            j--;
        }
        System.out.println(makeEqual(15, 8));
        System.out.println(count);
    }
    public static int makeEqual(int a, int b){
        // first of all find the xor so that you will get the 1 where no match.
        int val = a ^ b;

        // no you have to find the set bit or no of 1's
        int count = 0;
        while(val > 0){
            val = val & (val -1);
            count++;
        }

        return count;
    }
}
