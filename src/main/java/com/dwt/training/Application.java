package com.dwt.training;

public class Application {


    public static void main(String ... args){
        if(args.length == 0)
        {
            printHelp();
            return;
        }
        doConversion();
    }

    private static void doConversion() {
       System.out.println("Not implemented yet.");
    }

    private static void printHelp() {
        System.out.println("Invalid usage");
        System.out.println("Use with a file argument ===> java -jar file");
    }
}
