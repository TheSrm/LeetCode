package org.LeetCode.ContainerWithMostWater;

class Solution {
    public int maxArea(int[] height) {
        int max_area=0;
        int left = 0;
        int right = height.length-1;
        boolean Continuar=true;

        while (Continuar){
            if(left>right){
                Continuar=false;
            }else{
                max_area= Math.max(Math.min(height[left], height[right])*(right-left),max_area);
                if(height[left]<height[right]){
                    left++;
                } else {
                    right--;
                }
            }
        }

    return max_area;

    }


}