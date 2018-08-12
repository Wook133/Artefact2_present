package deVilliers;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;

public final class deVillCargo implements java.io.Serializable, Comparable<deVillCargo>, Comparator<deVillCargo>
{
    private String shash = "";
    private String HashofFile;
    private String Timestamp;
    private String SizeofFile;
    private String publicAddressAdder;
    private String OriginalURL;
    private ArrayList<Pair<String,String>> listMeta = new ArrayList<>();//removeMeta?
    private ArrayList<String> moreLinks = new ArrayList<>();
    private Integer sizeofSource;//Remove?

    public deVillCargo(String hashofFile, String timestamp, String sizeofFile, String pkAdder, String originalURL) {
        // LSize = (long)hashofFile.length() + (long)timestamp.length() + (long)sizeofFile.length() + (long)pkAdder.length() + (long)originalURL.length();
        //if (LSize < maxNumberBytes) {
        HashofFile = hashofFile;
        Timestamp = timestamp;
        SizeofFile = sizeofFile;
        this.publicAddressAdder = pkAdder;
        OriginalURL = originalURL;

        //}
    }

    public deVillCargo(String originalURL, String paa) {
        OriginalURL = originalURL;
        DownloadHasher dh = new DownloadHasher();
        Pair<String, Pair<String, String>> tuple = dh.bufferDownFullHashTuple(OriginalURL);
        HashofFile = tuple.getKey();
        Pair<String, String> pair = tuple.getValue();
        SizeofFile =  pair.getValue();
        publicAddressAdder = paa;
        Timestamp ts = new Timestamp();
        ArrayList<Long> lout = new ArrayList<>();
        lout = ts.getTimesNoThrow();
        if (ts.detectShaddy(lout) == false) {
            Timestamp = Long.toString(ts.getAverageTime(lout));
        }
        else {
            this.Timestamp = Long.toString(System.currentTimeMillis());
            System.out.println("Problem with your connection to all time servers for Timestamp");
        }
        shash = hashCargo();

    }

    public deVillCargo(String hashofFile, String sizeofFile, String pkAdder, String originalURL) throws Exception {
        HashofFile = hashofFile;
        Timestamp ts = new Timestamp();
        ArrayList<Long> lout = new ArrayList<>();
        lout = ts.getTimes();
        if (ts.detectShaddy(lout) == false) {
            Timestamp = Long.toString(ts.getAverageTime(lout));
        }
        else {
            this.Timestamp = Long.toString(System.currentTimeMillis());
            System.out.println("Problem with your connection to all time servers for Timestamp");
        }
        SizeofFile = sizeofFile;
        this.publicAddressAdder = pkAdder;
        OriginalURL = originalURL;
    }
    public deVillCargo() {
    }


    public Pair<String, String> removeMeta(int i)
    {

        //Pair<String, String> pCur = listMeta.get(i);
        //LSize = LSize - ((pCur.getKey().length()) +(pCur.getValue().length()));
        //pCur = null;
        return listMeta.remove(i);
    }
    public String removeLink(int i)
    {
        // String s = moreLinks.get(i);
        // LSize = LSize - (s.length());
        // s = null;
        return moreLinks.remove(i);
    }

    public Boolean addMeta(Pair<String, String> i)
    {
        /**Long size = Long.valueOf(i.getKey().length() + i.getValue().length());
         Long ltemp = size + LSize;
         if (ltemp < maxNumberBytes)
         {
         LSize = ltemp;**/
        if (listMeta.size() < 1000)
        {
            listMeta.add(i);
            return true;
        }
        else
            return false;
    }
    public Boolean addLink(String i)
    {
        if (moreLinks.size() < 1000)
        {
            moreLinks.add(i);
            return true;
        }
        else
            return false;
    }
    public Boolean addLinks(ArrayList<String> listIn)
    {
        boolean badded = false;
        for (String s : listIn)
        {
            badded = addLink(s);
        }
        return badded;
    }


    public String getHashofFile() {
        return HashofFile;
    }

    public void setHashofFile(String hashofFile) {
        HashofFile = hashofFile;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getSizeofFile() {
        return SizeofFile;
    }

    public void setSizeofFile(String sizeofFile) {
        SizeofFile = sizeofFile;
    }

    public String getpublicAdressAdder() {
        return publicAddressAdder;
    }

    public void setPkAdder(String pkAdder) {
        this.publicAddressAdder = pkAdder;
    }

    public String getOriginalURL() {
        return OriginalURL;
    }

    public void setOriginalURL(String originalURL) {
        OriginalURL = originalURL;
    }

    public ArrayList<Pair<String, String>> getListMeta() {
        return listMeta;
    }

    public void setListMeta(ArrayList<Pair<String, String>> listMeta) {
        this.listMeta = listMeta;
    }

    public ArrayList<String> getMoreLinks() {
        ArrayList<String> sout = new ArrayList<>();
        for (String s : moreLinks)
        {
            sout.add(s);
        }
        return sout;
    }

    public void setMoreLinks(ArrayList<String> moreLinks) {
        this.moreLinks = moreLinks;
    }

    @Override
    public String toString()
    {
        String sout = "";
        sout = HashofFile + "_" + Timestamp + "_" + SizeofFile + "_" + publicAddressAdder + "_" + OriginalURL;
        String stemp = "";
        sout = sout + "_" + stemp;
        stemp = "";
        for (int j = 0; j <= moreLinks.size() - 1; j++) {
            stemp = stemp + "_" + moreLinks.get(j);
        }
        if (moreLinks.size() > 0) {
            sout = sout + "_" + stemp;
        }
        return sout;
    }

    public String toStringWithHash()
    {
        String sout = "";
        sout = hashCargo() +"_"+ HashofFile + "_" + Timestamp + "_" + SizeofFile + "_" + publicAddressAdder + "_" + OriginalURL;
        String stemp = "";
        stemp = "";
        if (moreLinks.size() < 100) {
            for (int j = 0; j <= moreLinks.size() - 1; j++) {
                stemp = stemp + "_" + moreLinks.get(j);
            }
            if (moreLinks.size() > 0) {
                sout = sout + "_" + stemp;
            }
        }
        return sout;
    }

    @Override
    public int compare(deVillCargo o1, deVillCargo o2) {
        String s = o1.toStringWithHash();
        String sObj = o2.toStringWithHash();
        return (s.compareTo(sObj));
    }

    @Override
    public int compareTo(deVillCargo o) {
        //System.out.println("Ey");
        String s = this.toStringWithHash();
        String sObj = o.toStringWithHash();
        return (s.compareTo(sObj));
    }

    public Boolean equalsTo(deVillCargo obj1) {
        String s = this.toString();
        String sObj = obj1.toString();
        return (s.equals(sObj));
    }

    public String getPublicAddressOfCreator() {
        return publicAddressAdder;
    }

    public String toStringForTransactionData()
    {
        String sout = "";
        sout = hashCargo() +"_"+  HashofFile + "_" + Timestamp + "_" + SizeofFile + "_" + publicAddressAdder + "_" + OriginalURL;
        String stemp = "";
        sout = sout + "_" + stemp;
        stemp = "";
        for (int j = 0; j <= moreLinks.size() - 1; j++) {
            stemp = stemp + "_" + moreLinks.get(j);
        }
        if (moreLinks.size() > 0) {
            sout = sout + "_" + stemp;
        }
        return sout;
    }


    public ArrayList<Pair<String, String>> toCargoArraylistPairForSending()
    {
        ArrayList<Pair<String, String>> listout = new ArrayList<>();
        Pair<String, String> pairCur = new Pair<>("HashofCargo", this.hashCargo());
        listout.add(pairCur);
        pairCur = new Pair<>("HashofFile", HashofFile);
        listout.add(pairCur);
        pairCur = new Pair<>("Timestamp", Timestamp);
        listout.add(pairCur);
        pairCur = new Pair<>("SizeofFile", SizeofFile);
        listout.add(pairCur);
        pairCur = new Pair<>("PublicAddressAdder", publicAddressAdder);
        listout.add(pairCur);
        pairCur = new Pair<>("OriginalURL", OriginalURL);
        listout.add(pairCur);
        for (int i = 0; i <= moreLinks.size() - 1; i++)
        {
            pairCur = new Pair<>("AdditionalURL" + i + ":", moreLinks.get(i));
            listout.add(pairCur);
        }
        return listout;
    }


    public String hashCargo() {
        try {
            Hasher gh = new Hasher();
            return gh.Hash(this.toString(), "SHA3-256");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("de Vill Source, No Such Class or Method: General Hash");
        }
        return getHashofFile();
    }

    public long getApproximateSize()
    {
        String s = toString();
        long lout = getByteLengthString(s);
        return lout;
    }

    public static int getByteLengthString(String s)
    {
        return s.getBytes().length;
    }
    public static int getByteLengthInteger()
    {
        return Integer.BYTES;
    }



    public boolean verifyHash()
    {//bufferDown
        try {
            DownloadHasher dBuf = new DownloadHasher();
            String sDownloadHash = dBuf.bufferDownFullHash(this.getOriginalURL());
            if (sDownloadHash.compareTo(this.getHashofFile()) == 0)
            {
                return true;
            }
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("de Vill Source, No Such Class or Method: General Hash");
        }
        return false;
    }

    public class deVillCargoComparator implements Comparator<deVillCargo> {
        @Override
        public int compare(deVillCargo o1, deVillCargo o2) {
            if(o1.hashCargo().compareTo(o2.hashCargo()) == 0)
                return 0;
            else if (o1.hashCargo().compareTo(o2.hashCargo()) == -1)
                return -1;
            else
                return 1;
        }
    }


}
