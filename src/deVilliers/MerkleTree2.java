package deVilliers;

import java.util.ArrayList;
import java.util.List;

public class MerkleTree2 {
    // transaction List
    List<String> txList;
    // Merkle Root
    String root;

    public MerkleTree2(List<String> txList) {
        this.txList = txList;
        root = "";
    }

    public void merkle_tree() {

        List<String> tempTxList = new ArrayList<String>();

        for (int i = 0; i < this.txList.size(); i++) {
            tempTxList.add(this.txList.get(i));
        }

        List<String> newTxList = getNewTxList(tempTxList);
        while (newTxList.size() != 1) {
            newTxList = getNewTxList(newTxList);
        }

        this.root = newTxList.get(0);
    }

    private List<String> getNewTxList(List<String> tempTxList) {

        List<String> newTxList = new ArrayList<String>();
        int index = 0;
        while (index < tempTxList.size()) {
            // left
            String left = tempTxList.get(index);
            index++;

            // right
            String right = "";
            if (index != tempTxList.size()) {
                right = tempTxList.get(index);
            }

            // sha2 hex value
            String sha2HexValue = getSHA2HexValue(left + right);
            newTxList.add(sha2HexValue);
            index++;

        }

        return newTxList;
    }

    public String getSHA2HexValue(String str) {
        byte[] cipher_byte;
        try{
            Hasher gh = new Hasher();
            return gh.Hash(str, "SHA3-256");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String getRoot() {
        return this.root;
    }
}
