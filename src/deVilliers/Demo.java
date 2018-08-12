package deVilliers;

import deVilliers.ConsoleMenu.Menu;
import deVilliers.Timestamp;
import deVilliers.XML.LeafStorage;
import deVilliers.XML.XMLdvc;
import deVilliers.deVillCargo;
import org.dom4j.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static deVilliers.XML.XMLdvc.getAllLeavesTree;
import static deVilliers.XML.XMLdvc.parse;
import static deVilliers.XML.XMLdvc.writeAccountsBack;

public class Demo {
    public static void main(String[] args) throws Exception {
        Menu mainMenu = new Menu();
        mainMenu.addChoice("Get Timestamps", () -> printTimestamps());
        mainMenu.addChoice("Source", () -> doSource());
        mainMenu.addChoice("Export", () -> doAppendSourceAndExport());
        mainMenu.run();





    }

    public static void printTimestamps()
    {
        ArrayList<Long> listTimes = new ArrayList<>();
        Timestamp ts = new Timestamp();
        System.out.println("Show Timestamps from 4 Servers and this System");
        listTimes = ts.getTimesNoThrowPrint();
        System.out.println("");
        if (ts.detectShaddy(listTimes) == false)
        {
            System.out.println("No substantial differences detected");
        }
        else
        {
            System.out.println("Substantial differences detected");
        }
        System.out.println("");
        if (ts.IntentionalShaddy(listTimes) == false)
        {
            System.out.println("No substantial differences detected");
        }
        else
        {
            System.out.println("Substantial differences detected");
        }
        System.out.println("");
        System.out.println("Average Time: " + ts.getAverageTime(listTimes));
        System.out.println("");
    }

    public static void doSource()
    {
        //https://en.wikipedia.org/wiki/Canadian_electoral_system#/media/File:St_Edward%27s_Crown_with_maple_leaves.svg
        System.out.println("Please enter a Source url ending in a file extension:");
        Scanner scanner = new Scanner(System.in);
        String sURL = scanner.nextLine();
        deVillCargo dvc = new deVillCargo(sURL, "000000000000000000000");
        System.out.println("Source 1 : " + dvc.toStringForTransactionData());

        deVillCargo dvc2 = new deVillCargo("https://en.wikipedia.org/wiki/Canadian_electoral_system#/media/File:St_Edward%27s_Crown_with_maple_leaves.svg", "000000000000000000000");
        System.out.println("Source 2 : " + dvc2.toStringForTransactionData());
        System.out.println("Same hash value for file: " + dvc.equalsTo(dvc2));
        System.out.println("");
    }

    public static void doAppendSourceAndExport()
    {
        try {
            System.out.println("Please enter a Source url ending in a file extension:");
            Scanner scanner = new Scanner(System.in);
            String sURL = scanner.nextLine();
            deVillCargo dvc = new deVillCargo(sURL, "000000000000000000000");

            String sFile = "Leaves.xml";
            XMLdvc xmld = new XMLdvc();
            File F = new File(sFile);
            Document cur = parse(F);


            LeafStorage LSS = new LeafStorage();
            LSS = getAllLeavesTree(cur);
            System.out.println(LSS.getSize());
            LSS.print();
            xmld.Append(cur, dvc);
            cur = writeAccountsBack(cur);



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
