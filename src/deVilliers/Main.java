package deVilliers;

public class Main {

    public static void main(String[] args) throws Exception{

        Hasher h = new Hasher();
        String s = h.Hash("Hello");
        System.out.println(s);

        DownloadHasher dh = new DownloadHasher();
        System.out.println(dh.bufferDownFullHash("https://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html"));
       /* for (int i = 0; i <= 20; i++) {
            int a = (int)Math.pow(2.0, i);
            long p = System.currentTimeMillis();

            System.out.println(a + " : " + dh.bufferDownFullHash("https://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html", true, a));
            long e = System.currentTimeMillis();
            System.out.println("Time: " + (e-p));

        }*/

	// write your code here
    }
}
