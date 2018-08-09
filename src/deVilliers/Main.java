package deVilliers;

public class Main {

    public static void main(String[] args) throws Exception{

        Hasher h = new Hasher();
        String s = h.Hash("Hello");
        System.out.println(s);

        DownloadHasher dh = new DownloadHasher();
        System.out.println(dh.bufferDownFullHash("https://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html"));

        Timestamp ts = new Timestamp();
        ts.printallTimes();



    }
}
