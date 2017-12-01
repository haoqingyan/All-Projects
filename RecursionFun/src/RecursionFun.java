//Haoqing Yan

public class RecursionFun {
	public int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}

	public int GCD(int m, int n) {
		m = Math.abs(m);
		n = Math.abs(n);
		if(n == 0){
	         return m;
	        }
	     return GCD(n, m%n);         
	}
	

	public double addReciprocals(int n) {
		if (n == 1) {
			return 1.0;
		} else {
			return 1.0 / n + addReciprocals(n - 1);
		}
	}

	public int fibonacci(int n) {
		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;
		else
			return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public String pairStar(String str) {
		if (str.length() == 1) {
			return str;
		}
		if (str.substring(0, 1).equals(str.substring(1, 2))) {
			return str.substring(0, 1) + "*" + pairStar(str.substring(1,str.length()));
		}
		return str.substring(0, 1) + pairStar(str.substring(1,str.length()));
	}

	public boolean nestParen(String str) {
		if (str.length() == 0) {
			return true;
		}
		if (str.length() > 1) {
			if (str.substring(0, 1).equals("(")) {
				return nestParen(str.substring(1, str.length() - 1));
			}
		}
		return false;
	}

	public int sumArray(int[] nums, int beginIndex, int endIndex) {
		if (beginIndex > endIndex) {
			return 0;
		} else {
			return nums[beginIndex] + sumArray(nums, beginIndex + 1, endIndex);
		}
	}

	public void reverseArray(int[] nums) {
		help(nums, 0, -1);
	}
	private void help(int[] nums, int beginIndex, int rightIndex) {
		if (beginIndex < (double) nums.length / 2) {
			int re = nums[beginIndex];
			nums[beginIndex] = nums[nums.length + rightIndex];
			nums[nums.length + rightIndex] = re;		
	     help(nums, beginIndex+1, rightIndex-1);
		}
	}
	

	public int arrayRange(int[] nums) {
		return help1(nums, nums[0], nums[0], 0);
	}

	private int help1(int[] nums, int max, int min, int index) {
		if (index == nums.length) {
			return max - min;
		}else{
			if (nums[index] > max) {
				max = nums[index];
			}
			if(nums[index] < min) {
				min = nums[index];
			}
			return help1(nums, max, min, index + 1);
		}

	}

	public int binarySearch(String searchValue, String[] strs) {
		return help2(searchValue, strs, 0, strs.length - 1,0);
	}

	private int help2(String searchValue, String[] strs, int leftindex, int rightindex, int num){
		if (num < strs.length ) {
	      int midindex = (leftindex + rightindex)/2;
	      if (strs[midindex].equals(searchValue) ) {
	    	  return midindex;
	    	  }else if(strs[midindex+1].equals(searchValue) ){
	    	  return midindex+1;
	    	  }else if (searchValue.compareTo(strs[midindex]) < 0){
	    	  return help2(searchValue, strs, leftindex, midindex,num+1);
	    	  }else {
	    	  return help2(searchValue, strs, midindex, rightindex,num+1);
		      }
		   }else{
			return -1;
		}
	}

	public String intWithCommas(int n) {
			 String result=n+"";
			 if(result.length()>3){
				 return intWithCommas(Integer.valueOf(result.substring(0,result.length()-3)))+","+result.substring(result.length()-3,result.length());
			 }else {
				return n+"";
			}
		}
}
