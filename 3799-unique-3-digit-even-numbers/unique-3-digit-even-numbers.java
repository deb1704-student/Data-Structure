class Solution {
    public int totalNumbers(int[] digits) {
        int[] digitCounts = new int[10];
        for (int digit : digits) {
            digitCounts[digit]++;
        }

        int uniqueEvenNumbersCount = 0;

        for (int num = 100; num <= 998; num += 2){

            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int ones = num % 10;

            digitCounts[hundreds]--;
            digitCounts[tens]--;
            digitCounts[ones]--;

            if (digitCounts[hundreds] >= 0 && digitCounts[tens] >= 0 && digitCounts[ones]>= 0) {
                uniqueEvenNumbersCount++;
            }
            digitCounts[hundreds]++;
            digitCounts[tens]++;
            digitCounts[ones]++;
        }
        return uniqueEvenNumbersCount;
    }
}