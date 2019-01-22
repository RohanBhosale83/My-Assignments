public class RemoveDuplicates {

	public static void main(String[] args) {
		RemoveDuplicates.test();
	}

	public static void test() {
		String input = "Mississipi";
		char[] characters = input.toCharArray();
		
		/*
		 * for(int i=0;i<characters.length;i++) { System.out.println(characters[i]); }
		 * System.out.println("****************************"); Map map = new HashMap();
		 * map.put("M",1 ); map.put("i",2 ); map.put("s",3 ); map.put("s",4 );
		 * map.put("i",5 ); map.put("s",6 ); map.put("s",7 ); map.put("i",8 );
		 * map.put("p",9 ); map.put("i",10 );
		 * 
		 * Set< Map.Entry > st = map.entrySet();
		 * 
		 * for (Map.Entry< String,Integer> me:st) { System.out.print(me.getKey()+":");
		 * System.out.println(me.getValue()); }
		 * System.out.println("****************************");
		 * 
		 * TreeSet<String> ts = new TreeSet<String>(); ts.add("M"); ts.add("i");
		 * ts.add("s"); ts.add("s"); ts.add("i"); ts.add("s"); ts.add("s"); ts.add("i");
		 * ts.add("p"); ts.add("i"); System.out.println(ts);
		 * System.out.println("****************************");
		 */
	   int index = 0;  
	   for (int i = 0; i < characters.length; i++) {
		for (int j = 0; j <i; j++) {
			if(characters[i]==characters[j]) {
				break;
			}
			if (j == i) 
				characters[index++] = characters[i];
		   } 
		     System.out.println(characters[index++]);
			
		}
	}
	   
	
}
