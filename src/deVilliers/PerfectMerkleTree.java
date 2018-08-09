package deVilliers;

import java.util.ArrayList;

public class PerfectMerkleTree {
    private ArrayList<String> listHashes = new ArrayList<String>();//list of leaf nodes
    private merkleNode merkleRoot;//root of merkle tree
    private Boolean treeBuilt;
    private static class merkleNode
    {
        merkleNode left;
        merkleNode right;
        public String sHashData;

        merkleNode (String hash)
        {
            left = null;
            right = null;
            sHashData = hash;
        }
        merkleNode ()
        {
            left = null;
            right = null;
            sHashData = null;
        }
        merkleNode (merkleNode mnleft, merkleNode mnright)
        {
            left = mnleft;
            right = mnright;
        }

        Boolean isEqual(merkleNode mnOther)
        {
            Boolean b = false;
            if (this.sHashData.equals(mnOther.sHashData))
            {
                b = true;
            }
            return b;
        }

    }

    public void setMerkleRoot(String sin)
    {
        merkleRoot = new merkleNode(sin);
    }

    public String getMerkleRoot()
    {
        return merkleRoot.sHashData;

    }

    public int lengthList()
    {
        return this.listHashes.size();
    }

    public void populateListHashes(ArrayList<String> listIN)
    {
        for (String s : listIN)
        {
            listHashes.add(s);
        }
    }


    /**
     * Initializes empty tree
     */
    public void MerkleTree()
    {
        treeBuilt = false;
        merkleRoot = null;
        listHashes.clear();
    }

    /**
     * Returns true if hash is in the tree
     * recursion
     * @param hashdata
     * @return
     */
    public boolean lookup(String hashdata)
    {
        return(lookup(merkleRoot, hashdata));
    }
    /**
     Recursive lookup  -- given a node, recur
     down searching for the given hash value.
     */
    private boolean lookup(merkleNode node, String hashdata) {
        if (node==null) {
            return(false);
        }

        if (hashdata==node.sHashData) {
            return(true);
        }
        else if (hashdata.compareTo(node.sHashData) < 0) {
            return(lookup(node.left, hashdata));
        }
        else {
            return(lookup(node.right, hashdata));
        }
    }

   /* public merkleNode makeParents(merkleNode mnLeft, merkleNode mnRight)
    {
        merkleNode Parent = new merkleNode();
    }*/

    public String getLeaf(int i)
    {
        if ((i >= 0) && (i < lengthList()))
        {
            return listHashes.get(i);
        }
        else
            return "";
    }

    public void buildTree(String HashToUse)
    {
        int inumLeaves = listHashes.size();
        int iadd = AmountToAdd(LogOfBase(2, listHashes.size()), 2);
        //if there are not enough leaves to make a perfect binary merkle tree then pad it with a specific element
        if (iadd != 0) {
            String sPad = listHashes.get(0);
            for (int j = 0; j <= iadd - 1; j++)
            {
                listHashes.add(sPad);
            }
        }
        try
        {
            int iheight = (int)LogOfBase(2, inumLeaves);
            int ihalf = inumLeaves;
            ArrayList<merkleNode> alParents = new ArrayList<merkleNode>();
            ArrayList<merkleNode> alGrandParents = new ArrayList<merkleNode>();
            Hasher gh = new Hasher();
            int m = 0;
            for (int i = 0; i <= listHashes.size() - 1; i++)
            {
                String s = listHashes.get(i);
                merkleNode mnCur = new merkleNode(s);
                alParents.add(mnCur);
            }
            boolean bpar = true;
            while (m < iheight)
            {
                if ((bpar == true) && (alParents.size() > 0))
                {
                    alGrandParents.clear();
                    for (int i = 0; i <= alParents.size() - 2; i+=2)
                    {
                        String scurHash = gh.Hash(alParents.get(i).sHashData + alParents.get(i+1).sHashData, HashToUse);
                        merkleNode curNode = new merkleNode(scurHash);
                        alGrandParents.add(curNode);
                    }
                    bpar = false;
                }
                if ((bpar == false) && (alGrandParents.size() > 0))
                {
                    alParents.clear();
                    for (int j = 0; j <= alGrandParents.size() - 2; j+=2)
                    {
                        String scurHash = gh.Hash(alGrandParents.get(j).sHashData + alGrandParents.get(j+1).sHashData, HashToUse);
                        merkleNode curNode = new merkleNode(scurHash);
                        alParents.add(curNode);
                    }
                    bpar = true;
                }
                m = m + 1;
            }
            if (alParents.size() == 1)
            {
                setMerkleRoot(alParents.get(0).sHashData);
            }
            if (alGrandParents.size() == 1)
            {
                setMerkleRoot(alGrandParents.get(0).sHashData);
            }
            treeBuilt = true;
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
    private merkleNode insert(merkleNode node, String data) {
        if (node==null) {
            node = new merkleNode(data);
        }
        else {
            if (data.compareTo(node.sHashData) <= 0)  {
                node.left = insert(node.left, data);
            }
            else {
                node.right = insert(node.right, data);
            }
        }
        return(node); // in any case, return the new pointer to the caller
    }

    public void addHashes(String sHash)
    {
        listHashes.add(sHash);
    }
    public static double LogOfBase(int base, int num)
    {
        return Math.log(num) / Math.log(base);
    }
    public static int ceilingLogOfBase(int base, int num)
    {
        int i = (int)Math.ceil(Math.log(num) / Math.log(base));
        return i;
    }

    public static int AmountToAdd(double din, int base)
    {
        int inum = 0;
        int iadd;
        double ceiling = Math.ceil(din);
        if (ceiling == din) {
            return inum;
        }
        else
        {
            double dinPlay = Math.pow(base * 1.00, din);
            double difference = Math.pow(base * 1.00, ceiling) - dinPlay;
            return (int)difference;
        }
    }

    /**
     * Compare Whether 2 trees are identical, if roots of trees are the same then compare leaves
     * @param OtherTree
     * @return true if leaves and roots are the same else false
     */
    public Boolean compareTrees(PerfectMerkleTree OtherTree)
    {
        if (this.treeBuilt == OtherTree.treeBuilt == true)
        {
            if (!(this.merkleRoot.isEqual(OtherTree.merkleRoot)))
            {
                return false;
            }
            else
            {
                int icountHashes = lengthList();
                int icountOtherHashes = OtherTree.lengthList();
                if (icountHashes == icountOtherHashes)//automatically false if the size of the lists differs
                {
                    for (int i = 0; i <= icountHashes - 1; i++)
                    {
                        String s = this.getLeaf(i);
                        String sOther = OtherTree.getLeaf(i);
                        if (!(s.equals(sOther)))
                        {
                            return false;
                        }
                    }
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            System.out.println("Either Tree has not been built");
            return false;
        }
    }
}
