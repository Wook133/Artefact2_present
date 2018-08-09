package deVilliers;

import java.util.ArrayList;

public class HashChain_Double {
    private ArrayList<String> listHashes = new ArrayList<String>();//list of leaf nodes
    private HashChain tail;//tail of hashchain

    public HashChain_Double(ArrayList<String> listHashes) {
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
                sHashCur = gh.Hash(s, HashToUse);
                sHashout = gh.Hash(sHashout+sHashCur, HashToUse);
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
