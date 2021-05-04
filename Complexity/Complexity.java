

import java.lang.Math;
public class Complexity {
	
	//a method that has time complexity O(n).
	public static void  method0(int n) 
	{    int counter=0; 
	    for (int i=0; i<n; i++)
	   {
		
		System.out.println("Operation "+counter);
		counter++; 
		}
	    
		}
	
	//a method that has time complexity O(n2).
	public static void method1(int n) {
		int count = 0;
		for (int i=0;i<n;i++) {
			
			for(int j = 0;j<n;j++) {
				System.out.println("Operation "+count);
				count++; 
				
			}
		}
	}
	
	//a method that has time complexity O(n3).	
	public static void method2(int n) {
		int count = 0;
		for(int i=0;i<n;i++) {
			for(int j = 0; j <n; j++) {
				for(int k = 0; k<n; k++) {
					
					System.out.println("Operation "+count);
					count++;					
				}
			}
		}		
	}
	
	// a method that has time complexity O(logn).
	public static void method3(int n) {
		int count = 0;
		for(int i= 1; i<n; i *=2) {

			System.out.println("Operation "+count);
			count++;
			
		}		
	}
	
	//a method that has time complexity O(nlogn).
	public static void method4(int n) {
		int count = 0;
		for (int i= 0; i<n ; i++) {
			
			for(int j = 1; j<n; j *=2) {
				
				System.out.println("Operation "+count);
				count++;
				
			}
		}				
	}
	
	//a method that has time complexity O(log(logn)).
	public static void method5(int n) {
		int count = 0;
		for(double i = n; i > 2; i = Math.sqrt(i)) {
			
			System.out.println("Operation "+count);
			count++;
		}
	}
	
	//a method that has time complexity O(2n). 
	public static int count;
	public static int method6(int n) {
		int res = 0;
		if(n<=1) {
			System.out.println("Operation "+count);
			count++;			
			return 0;
		}
		else {
			
			System.out.println("Operation "+count);
			count++;
			res = method6(n-1)+method6(n-2);
			return res;
			
		}		
	}
	
	//Test 
/*	public static void main(String[] args) {
		int x =0;
		//Complexity.method0(10);
		//Complexity.method1(10);
		//Complexity.method2(10);
		//Complexity.method3(10);
		//Complexity.method4(10);
		//Complexity.method5(10);
		//x = Complexity.method7(10);
		//System.out.println(x);		
	}	*/
}
