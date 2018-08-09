package deVilliers;

import java.security.SecureRandom;
import java.util.Random;

public class Proof_of_Work {
    String sContent;
    String sHashtoUse;
    String sPattern;
    long nonce;

    public Proof_of_Work(String sContent, String sHashtoUse, String sPattern, long nonce) {
        this.sContent = sContent;
        this.sHashtoUse = sHashtoUse;
        this.sPattern = sPattern;
        this.nonce = nonce;
    }

    public Proof_of_Work(String sContent, String sPattern, long nonce) {
        this.sContent = sContent;
        this.sPattern = sPattern;
        this.nonce = nonce;
        this.sHashtoUse = "SHA3-256";
    }

    public Boolean ProofWork() throws Exception
    {
        long iAns = -1;
        Boolean bfound = false;
        String sAttempt = "";
        Hasher gHash = new Hasher();
        while (bfound == false) {
            iAns = iAns + 1;
            sAttempt = gHash.Hash(sContent + iAns, sHashtoUse);
            if (sAttempt.contains(sPattern)) {
                this.nonce = iAns;
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param ipos identifies where the pattern occurs. 0 -> Start; 1 -> Anywhere; 2 -> End
     * @return
     */
    public long pow(int ipos)
    {
        long iAns = -1;
        Boolean bfound = false;

        try {
            String sAttempt = "";
            Hasher gHash = new Hasher();

            while (bfound == false)
            {
                iAns = iAns + 1;
                sAttempt = gHash.Hash(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }
    /**
     *
     * @param ipos identifies where the pattern occurs. 0 -> Start; 1 -> Anywhere; 2 -> End
     * @return
     */
    public long pow(long lstart, int ipos)
    {
        long iAns = lstart-1;
        Boolean bfound = false;

        try {
            String sAttempt = "";
            Hasher gHash = new Hasher();

            while (bfound == false)
            {
                iAns = iAns + 1;
                sAttempt = gHash.Hash(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

    public String powString(long lstart, int ipos)
    {
        long iAns = lstart;
        Boolean bfound = false;
        String sAttempt = "";
        try {

            Hasher gHash = new Hasher();
            sAttempt = gHash.Hash(sContent+iAns, sHashtoUse);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return sAttempt;
    }

    public long powReverse(int ipos)
    {
        long iAns = Long.MAX_VALUE;
        Boolean bfound = false;

        try {
            String sAttempt = "";
            Hasher gHash = new Hasher();
            while (bfound == false)
            {
                sAttempt = gHash.Hash(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                }
                iAns = iAns - 1;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

    public long recPOW()
    {
        try {
            Hasher gh = new Hasher();
            String scurhash = gh.Hash(sContent+nonce, sHashtoUse);
            if (scurhash.startsWith(sPattern) == false)
            {
                //System.out.println("Failure: " + scurhash);
                nonce = nonce + 1;
                recPOW();
            }
            else
            {
                System.out.println("RecurPOW (" + nonce + ") :  " + scurhash);
                return nonce;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return nonce;
    }

    public long randPOW(int ipos)
    {
        Random random = new Random();
        Boolean bfound = false;
        long iAns = -1;
        try
        {
            Hasher gHash = new Hasher();
            String sAttempt = "";
            while (bfound == false)
            {
                iAns = SecureRandom.getInstanceStrong().nextLong();
                if (iAns < 0)
                {
                    iAns = iAns*-1;
                }
                sAttempt = gHash.Hash(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                }
            }
            return iAns;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

    public long randPosNegPOW(int ipos)
    {
        Random random = new Random();
        Boolean bfound = false;
        long iAns = -1;
        try
        {
            Hasher gHash = new Hasher();
            String sAttempt = "";
            while (bfound == false)
            {
                iAns = SecureRandom.getInstanceStrong().nextLong();
                sAttempt = gHash.Hash(sContent+iAns, sHashtoUse);
                switch(ipos)
                {
                    case 0:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    case 1:
                    {
                        if (sAttempt.contains(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    case 2:
                    {
                        if (sAttempt.endsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                    break;
                    default:
                    {
                        if (sAttempt.startsWith(sPattern) == true) {
                            bfound = true;
                        }
                    }
                }
            }
            return iAns;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return iAns;
    }

}
