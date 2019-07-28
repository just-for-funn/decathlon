package com.dwt.training;

import com.dwt.training.exports.imp.XmlExporter;
import com.dwt.training.imports.imp.CsvFileImporter;

public class Application {


    public static void main(String ... args){
        if(args.length == 0)
        {
            printHelp();
            return;
        }
        doConversion(args[0]);
    }

    private static void doConversion(String fileName) {
       new DecathlonConverter(new CsvFileImporter() , new XmlExporter() , fileName , "decathlon.xml" ).convert();
       System.out.println("DONE");
    }

    private static void printHelp() {
        System.out.println("Invalid usage");
        System.out.println("Use with a file argument ===> java -jar file");
    }
}
