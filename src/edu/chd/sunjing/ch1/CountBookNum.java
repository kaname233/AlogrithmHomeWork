package edu.chd.sunjing.ch1;

import java.util.Scanner;
/*
算法问题一：
题目描述：书从1页到n页，统计0到9出现的次数
 */
public class CountBookNum {
    private static int[] res = new int[10];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        countNum(num);
        for(int i = 0; i < 10; i++) {
            System.out.println(i+"出现次数是："+String.valueOf(res[i])+"次");
        }
    }
    public static void countNum(int num) {
        count(num);
        //去零
        int len = (int)Math.log10(num) + 1;
        for(int i = 0; i < len; i++) {
            res[0] -= (int)Math.pow(10, i);
        }
    }
    public static void count(int n) {
        //数n的位数
        int len = (int)Math.log10(n) + 1;
        //最高位的值p
        int p = n / (int)Math.pow(10, len - 1);
        //除最高位，各区间的0-9数字出现次数
        for(int i = 0; i < 10; i++) {
            res[i] += p * (len -1) * (int)Math.pow(10, len -2 );
        }
        //最高位[0,p-1]各数字出现次数
        for(int i = 0; i < p; i++) {
            res[i] += (int)Math.pow(10, len - 1);
        }
        //计算余数remain，如2019的余数是19
        int remain = n % (int)Math.pow(10, len - 1);
        //余数的位数
        int remain_len = (int)Math.log10(remain) + 1;
        //如果余数为0，结束递归
        if(remain == 0) {
            res[p]++; //最高位加1
            res[0] += len - 1; //0位加len-1
            return;
        }
        //如果余数不为0，最高位加（余数+1）次
        res[p] += (remain + 1);
        //如果余数不为0，并且长度不是len-1,比如2019的余数为19，长度为2而不是3，表示余数和最高位之间还有几位0,还要加上
        if(remain_len != len -1) {
            res[0] += (len - remain_len -1) * (remain + 1);
        }
        //递归，统计余数
        count(remain);
    }
}
