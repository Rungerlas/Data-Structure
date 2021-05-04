
public class BinaryNumber {
	private int data[];
	private boolean overflow;
	
	public BinaryNumber(int length) {
		
		int [] binarynumber = new int[length];
		data = binarynumber;
				
	}  //creating a binary number of length and consisting only of zeros
	
	public BinaryNumber(String str) {
		int [] binarynumber = new int[str.length()];
		for(int i = 0; i < str.length(); i++) {
			
			binarynumber[i] = java.lang.Character.getNumericValue(str.charAt(i));						
		}	
		data = binarynumber;
	}  //creating a binary number given a string
	
	public int getLength() {
		
		return data.length;
		
	} // for determining the length of a binary number
	
	public int getDigit(int index) {
		
		if (index > data.length || index < 0) {
			
			System.out.println("The index is out of bounds!");
			return 0;
		}
		else {
			
			return data[index];
		}
		
	}  //for obtaining a digit of a binary number given an index
	
	
	public void shiftR(int amount) {
		
		int shiftedlength = data.length + amount;
		BinaryNumber shifted_binarynumber = new BinaryNumber(shiftedlength);
		
		for (int i = 0; i < data.length; i++) {
			
			shifted_binarynumber.data[i+amount] = data[i];
			
		}
		data = shifted_binarynumber.data;
		
	}  // for shifting all digits in a binary number any number of places to right
	
	public void add(BinaryNumber aBinaryNumber) {
		
		BinaryNumber result = new BinaryNumber(data.length+1);
		if (data.length != aBinaryNumber.getLength()) {
			System.out.println("The lengths of the binary numbers do not coincide!");
		}
		else {
			
			for(int i =0; i< data.length;i++) {
				
				result.data[i] = data[i]+aBinaryNumber.data[i];
				
			}
			for(int i =0; i< data.length;i++) {
				if (result.data[i] == 2 & i < data.length-1) {
					result.data[i] =0;
					result.data[i+1] +=1;
				} else {
					if (result.data[i] == 1) {
						result.data[i] = 1;
						
					} else {
						if (result.data[i] ==3 & i < data.length-1) {
							result.data[i] =1;
							result.data[i+1] +=1;
							
						}else {
							if (result.data[i] >1 & i == data.length-1 & overflow == false) {
								if (result.data[i] == 2) {
									result.data[i] = 0;
									result.data[i+1] = 1;
									overflow = true;
								}else {
									result.data[i] = 1;
									result.data[i+1] = 1;
									overflow = true;
								}
								
							}
						}
					}
				}					
			}
		}
		data = result.data;	
	}  // for adding two binary numbers
	
	public String toString() {
		String result = "";
		if (overflow == true) {
			return "overflow";
		}
		else {
			for (int i = 0; i <data.length;i++) {
				result += data[i];
			}
		}
		return result;		
	}  // for transforming a binary number to a string
	
	public int toDecimal() {
		
		int res = 0;
		
		for (int i = 0; i < data.length ; i++) {
			
			res += Math.pow(2*data[i], i);		
		}
		
		return res;
	}  // for transforming a binary number to its decimal notation
	
	public void clearOverflow() {
		
		overflow = false;
		
	}   // clear the overflow flag
	
//	public static void main(String[] args) {
		//int data =8;
	//	String data = "1110";
	//	String data2 = "1110";
	//	BinaryNumber binarynumber1 = new BinaryNumber(data);
		
		//binarynumber1.shiftR(3);
		//for (int i=0;i<binarynumber1.getLength();i++) {

		//	System.out.println(binarynumber1.data[i]);
		
		//}
	//	BinaryNumber aBinaryNumber = new BinaryNumber(data2);
	//	binarynumber1.add(aBinaryNumber);
	//	System.out.println(binarynumber1.toString());
	//	for (int i=0;i<binarynumber1.getLength();i++) {
	//		System.out.println(binarynumber1.data[i]);			
	//		}		
	//	} 
}
