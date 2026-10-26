package sample2026.neetcode;

public class ProductExceptSelf {


    public int[] productExceptSelf(int[] nums) {
        int left = 1;
        int right = 1;
        int[] product = new int[nums.length];
        // prefix
        for (int i = 0; i < nums.length; i++) {
            product[i] = left;
            left = left * nums[i];
        }
        // postfix
        for (int j=nums.length - 1; j >= 0; j--) {
            product[j] = product[j] * right;
            right = right * nums[j];
        }
        return product;
    }
}
