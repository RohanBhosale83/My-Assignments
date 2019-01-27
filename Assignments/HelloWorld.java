import java.util.HashSet;

public class HelloWorld {
	 public static char[] solve (String text) {
         HashSet vowelSet = new HashSet();
         char[] vowels = {'a','e','i','o','u','A','E','I','O','U'};

         for (char v : vowels) {
             vowelSet.add(v);
         }

         char[] asCharArray = text.toCharArray();
         int lastStop = asCharArray.length;

         for (int i = 0; i < lastStop; i++) {
             if(vowelSet.contains(asCharArray[i])) {
                 for (int j = lastStop - 1; j > i; j--) {
                     if(vowelSet.contains(asCharArray[j])) {
                         char temp = asCharArray[j];
                         asCharArray[j] = asCharArray[i];
                         asCharArray[i] = temp;

                         lastStop = j;
                         break;
                     }
                 }
             }
         }

         return asCharArray;
     }

     public static void main(String[] args) {
         String someText = "ROHAN";
         System.out.println(HelloWorld.solve(someText));
     }

 }