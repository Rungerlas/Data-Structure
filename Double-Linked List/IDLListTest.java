
public class IDLListTest {
	
	//test create the list and size method and tostring method
	public static void addtonode(int n, IDLList<Integer> test) {
		for (int i=n;i>0;i--) {
			test.add(i);
		}	
		System.out.print("IDLList:"+test.toString()+"\n");
		
		int size_test = test.size();
		
		if(size_test == n) {
			System.out.print("Size is right");
		}
		else {
			System.out.print("Size is wrong");
		}
	}
	
	//test add index method 
	public static void addindextest(IDLList<Integer> test, int index, int elem) {
		
		System.out.print("IDLList:"+test.toString()+"\n");
		
		test.add(index, elem);
		
		System.out.print(test.toString()+"\n");
		
		
	}
	
	//test add elem
	public static void addelem(IDLList<Integer> test,int elem) {
		
		System.out.print("IDLList:"+test.toString()+"\n");
		
		test.add(elem);
		
		System.out.print("After ADD element:"+test.toString());
	}
	
	//test append and get method
	public static void append_get_test(IDLList<Integer> test, int index, int elem) {
		
		System.out.print("IDLList:"+test.toString()+"\n");
		
		System.out.print("Target:"+test.get(index)+"\n");
		
		test.append(elem);
		
		System.out.print("Append after:"+test.toString()+"\n");
		
		
	}
	
	//test gethead & getlast
	public static void gethead_last(IDLList<Integer> test) {
		
		System.out.print("IDLList:"+test.toString()+"\n");
		
		System.out.print("Head:"+test.getHead()+"\n");
		
		System.out.print("Last tail:"+test.getLast()+"\n");
		
	}
	
	//test remove head & last
	public static void testremove(IDLList<Integer> test) {
		
		System.out.print("IDLList:"+test.toString()+"\n");
		
		System.out.print("The head removed:"+test.remove()+"\n");
		
		System.out.print("The last removed:"+test.removeLast()+"\n");
		
		System.out.print("New IDLList:"+test.toString()+"\n");	
		
	}
	
	//test remove at index and element
	public static void testremoveat(IDLList<Integer> test, int index, int elem) {
		System.out.print("IDLList:"+test.toString()+"\n");
		
		System.out.print("Removed element:"+test.removeAt(index)+"\n");
		
		System.out.print("New IDLList:"+test.toString()+"\n");
		
		test.remove(elem);
		
		System.out.print("After removed element:"+test.toString()+"\n");
				
	}
	
	
	
	
/*	public static void main(String[] args) {
		IDLList<Integer> test1 = new IDLList<Integer>();
		addtonode(20, test1);
		
		//append_get_test(test1,0,10);
		
		//gethead_last(test1);
		
		testremoveat(test1,1,22);
		
		
		//test tostring
	//	System.out.print(test1.toString()+"\n");
		
		//Test add index
		
	//	test1.add(21);
	//	test1.add(15,100);
		test1.get(14);
		System.out.print(test1.get(14));
		//System.out.print(test1.toString());
		
	} */

}
