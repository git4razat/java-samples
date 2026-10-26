package sample2026.neetcode;

public class MaxProductSubArray {

    public int maxProduct(int[] nums) {
        int prefixProduct = 1;
        int suffixProduct = 1;
        int maxProduct = nums[0];
        for (int i = 0; i < nums.length; i++) {
            prefixProduct = (prefixProduct == 0 ? 1 : prefixProduct) * nums[i];
            suffixProduct = (suffixProduct == 0 ? 1 : suffixProduct) * nums[nums.length - 1 - i];
            maxProduct = Math.max(maxProduct, Math.max(prefixProduct, suffixProduct));
        }
        return maxProduct;
    }
}
