package org.LeetCode.PowXN;

class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            return myPowSub(1 / x, -N);
        }
        return myPowSub(x, N);
    }

    private double myPowSub(double x, long n) {
        if (n == 0) return 1.0;

        // Si es par: x^n = (x^2)^(n/2)
        if (n % 2 == 0) {
            return myPowSub(x * x, n / 2);
        }
        // Si es impar: x^n = x * x^(n-1)
        else {
            return x * myPowSub(x, n - 1);
        }
    }
}