import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

// Haoqing Yan
public class ArrayFun {

	 public int numberOfPairs(String[] array){
		// TODO Auto-generated method stub
     int count=0;
	 for (int i=0;i<array.length-1;i++){
		 if (array.length==0){
			 return 0;
		 }else{
			 if (array[i].equals(array[i+1])){
				 count++;
			 }
		 }
	}
	return count;
	 }
	 
     public int numberOfVowels(char[] array){
    	 int count=0;
    	 for (int i=0;i<array.length;i++){
    		 if (array.length==0){
    			 return 0;
    		 }else{
    			 if (array[i]=='a'||array[i]=='A'||array[i]=='E'||array[i]=='e'||array[i]=='I'
    					 ||array[i]=='i'||array[i]=='o'||array[i]=='O'||array[i]=='u'||array[i]=='U'){
    				 count++;
    		 }
    	 }
    		 } 
    	 return count;
     }
     public boolean sumGreaterThan(double[] array, double sum){
    	 double SumofArray=0;
    	 if (array.length==0){
    		 return false;
    	 }else{
    	     for (int i=0;i<array.length;i++){
    		SumofArray=SumofArray+array[i];
    	}
    	     if (SumofArray>sum){
    	    	 return true;
    	     }else{
    	    	 return false;
    	     }
    	 }
    	 }
     
      public double[] stats(Scanner scanner){
    	double max=-99999;
    	double min=99999;
    	double sum=0;
    	double[] result = new double[3];
    	int i=0;
    	while (scanner.hasNextDouble()){
    		double current=scanner.nextDouble();
    		if (max<=current){
    			max=current;
    		}
    		if (min>=current){
    			min=current;
    		}
    		sum=sum+current;
    		i++;
    	}
    	result[0]=max;
    	result[1]=min;
    	result[2]=sum/i;
    	return result;
      }

     public int[] frequency(Scanner scanner){
    	 int[] result=new int[11];
		 while (scanner.hasNextInt()){
			 int i=scanner.nextInt();
			 result[i]++;
		 }
			 return result;
		 }
     
     public int howMany(String[] array, String valueToFind){
    	 int count=0;
    	 for (int i=0;i<array.length;i++){
    		 if (array[i]==valueToFind){
    			 count++;
    		 }
    	 }
		return count;
    	 
     }
     
     public void sortOfSort(int[] array){
    	 int max=0;
    	 int min=0;
    	 for (int i=0;i<array.length;i++){
    		 if (max<=array[i]){
    			 max=array[i];
    		 }
    		 if (min>=array[i]){
    			 min=array[i];
    		 }
    	 }
    	 int x=indexOf(max.array);
     }
     
     public void evensLeft(int[] array){
    	 int[] result=new int[array.length];
    	 int position=0;
    	 int length=array.length;
    	 for (int i=0;i<array.length;i++){
    		 if (array[i]%2==0){
    			 result[position]=array[i];
    			 position++;
    		 }else{
    			 result[length-1]=array[i];
    			 length--;
    		 }
    	 }
     }
     
     public void shiftNTimes(int[] array, int numShifts){
    	 
     }
     
     public char[] replaced(char[] array, char oldChar, char newChar){
    	 char[] result=array;
		for (int i=0;i<result.length;i++){
			if (result[i]==oldChar){
				result[i]=newChar;
			}
		}
		return result;	 
     }
}
