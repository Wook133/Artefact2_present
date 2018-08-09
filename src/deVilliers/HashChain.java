package deVilliers;

import java.util.ArrayList;

public class HashChain {

    private ArrayList<String> listHashes = new ArrayList<String>();//list of leaf nodes
    private HashChain tail;//tail of hashchain

    public HashChain(ArrayList<String> listHashes) {
        populateListHashes(listHashes);
        this.tail = null;
    }

    public void populateListHashes(ArrayList<String> listIN)
    {
        for (String s : listIN)
        {
            this.listHashes.add(s);
        }
    }

    /**
     * 1 fewer hash per iteration
     * @param HashToUse
     * @return
     */
    public String HashChain(String HashToUse)
    {
        try
        {
            String sHashout = "";
            String sHashCur = null;
            Hasher gh = new Hasher();
            for (String s : listHashes)
            {
                sHashout = gh.Hash(sHashout+s, HashToUse);
            }
            return sHashout;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
