import java.util.Scanner;
import java.util.HashSet;
import java.util.Arrays;
import java.util.*;

public class Difference {
	
	public static int diff2 (int arr1[], int arr2[], int m, int n)
	{
		//int a[] = new int[m];
		HashSet<Integer> set = new HashSet<>();
		for (int i=0; i < m; i++)
		{
			set.add(arr1[i]);
		}
		
		
		//for checking
		int count = 0;
		for (int j=0; j < n; j++)
		{
			if(!set.contains(arr2[j]))
			{
				System.out.print(arr2[j] + " ");
				count ++;
				/*a[k] = arr1[i];
				k++;*/
			}
		}
	    return count;
		/*System.out.println(set);
		System.out.println("Elements which are there in set 1 but not in set 2: ");
		for(int y : a)
		{
			System.out.println(a[y]);
		}
		
		return a.length;*/
		
		
	}
	
	public static int diff1 (int arr1[], int arr2[], int m, int n)
	{
		//int a[] = new int[m];
		HashSet<Integer> set = new HashSet<>();
		for (int j=0; j < n; j++)
		{
			set.add(arr2[j]);
		}
		
		
		//for checking
		int count = 0;
		for (int i=0; i < m; i++)
		{
			if(!set.contains(arr1[i]))
			{
				System.out.print(arr1[i] + " ");
				count ++;
				/*a[k] = arr1[i];
				k++;*/
			}
		}
	    return count;
		/*System.out.println(set);
		System.out.println("Elements which are there in set 1 but not in set 2: ");
		for(int y : a)
		{
			System.out.println(a[y]);
		}
		
		return a.length;*/
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Enter size of 1st array");
		int m = sc.nextInt();
		int[] arr1 = new int[m];
		for (int i=0; i < m; i++)
		{
			arr1[i] = sc.nextInt();
		}
		
		
		System.out.println("Enter size of 2nd array");
		int n = sc.nextInt();
		int[] arr2 = new int[n];
		for (int j=0; j < n; j++)
		{
			arr2[j] = sc.nextInt();
		}
		
		int count1 = diff1(arr1, arr2, m ,n);
		int count2 = diff2(arr1, arr2, m ,n);
		int count3 = count1 + count2;
		System.out.println("Total uncommon count: " + count3);
	}

}
