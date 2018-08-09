package deVilliers;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;

import java.net.InetAddress;

public class Timestamp {
    //public static final String[] serverURL = new String[]{"time.google.com", "0.africa.pool.ntp.org","1.africa.pool.ntp.org","2.africa.pool.ntp.org","3.africa.pool.ntp.org","0.pool.ntp.org","1.pool.ntp.org","2.pool.ntp.org","3.pool.ntp.org","0.asia.pool.ntp.org","1.asia.pool.ntp.org","2.asia.pool.ntp.org","3.asia.pool.ntp.org","0.europe.pool.ntp.org","1.europe.pool.ntp.org","2.europe.pool.ntp.org","3.europe.pool.ntp.org","0.north-america.pool.ntp.org","1.north-america.pool.ntp.org","2.north-america.pool.ntp.org","3.north-america.pool.ntp.org","0.oceania.pool.ntp.org","1.oceania.pool.ntp.org","2.oceania.pool.ntp.org","3.oceania.pool.ntp.org","0.south-america.pool.ntp.org","1.south-america.pool.ntp.org","2.south-america.pool.ntp.org","3.south-america.pool.ntp.org", "ao.pool.ntp.org","bw.pool.ntp.org","gm.pool.ntp.org","ke.pool.ntp.org","ma.pool.ntp.org","mg.pool.ntp.org","re.pool.ntp.org","tn.pool.ntp.org","za.pool.ntp.org","ae.pool.ntp.org","am.pool.ntp.org","bd.pool.ntp.org","cn.pool.ntp.org","cy.pool.ntp.org","ge.pool.ntp.org","hk.pool.ntp.org","id.pool.ntp.org","in.pool.ntp.org","ir.pool.ntp.org","jp.pool.ntp.org","kr.pool.ntp.org","mv.pool.ntp.org","sg.pool.ntp.org","th.pool.ntp.org","tw.pool.ntp.org","at.pool.ntp.org","be.pool.ntp.org","bg.pool.ntp.org","ch.pool.ntp.org","cz.pool.ntp.org","de.pool.ntp.org","dk.pool.ntp.org","fi.pool.ntp.org","fr.pool.ntp.org","hu.pool.ntp.org","nl.pool.ntp.org","ru.pool.ntp.org","uk.pool.ntp.org","br.pool.ntp.org","au.pool.ntp.org","nz.pool.ntp.org","ca.pool.ntp.org","us.pool.ntp.org"};
    public static final String[] serverURL = new String[]{"time.google.com","0.pool.ntp.org","1.pool.ntp.org","2.pool.ntp.org","3.pool.ntp.org"};
    public static void printallTimes()
    {
        for (int i = 0; i <= serverURL.length - 1; i++)
        {
            System.out.println(i + " : " + serverURL[i] + " : " + getTime(i));
        }
    }

    public static long getTime(int i)
    {
        long ltime = 0;
        try {
            NTPUDPClient timeClient = new NTPUDPClient();
            InetAddress inetAddress = InetAddress.getByName(serverURL[i]);
            TimeInfo timeInfo = timeClient.getTime(inetAddress);
            NtpV3Packet message = timeInfo.getMessage();
            long serverTime = message.getTransmitTimeStamp().getTime();
            return serverTime;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return 0;
    }

    public static String toStringGetTime(int i)
    {
        long time = getTime(i);
        return String.valueOf(time);
    }

}
