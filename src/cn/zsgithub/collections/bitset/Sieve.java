package cn.zsgithub.collections.bitset;

import java.util.BitSet;

public class Sieve {
	/** 求2-2000000之间的素数 */
	public static void main(String[] args) {
		int n = 2000000;
		long start=System.currentTimeMillis();
		BitSet bs = new BitSet(n + 1);// 位集
		int count = 0;
		int i=0;
		for (i = 2; i <= n; i++) {
			bs.set(i);// 将第i为置为"开"状态
		}
		
		//System.out.println("i="+i);
		i = 2;
		
		while (i * i <= n) {//1414*1414=1999396,1415*1415=2002225,i<=1414
			if (bs.get(i)) {// 第i位处于开状态，返回true,否则返回false
				count++;
				int k = 2 * i;
				while (k <= n) {
					bs.clear(k);// 将第i为置为"关"状态
					k += i;
				}
			}
			i++;
		}
		
		//System.out.println("i="+i);
	    while(i<=n){
			if(bs.get(i)){
				count++;
			}
			i++;
		}
		long end=System.currentTimeMillis();
		
		System.out.println("素数个数："+count);
		System.out.println("所用时间："+(end-start)+" millseconds");
		
	}

}
