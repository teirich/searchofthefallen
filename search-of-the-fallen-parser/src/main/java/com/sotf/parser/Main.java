package com.sotf.parser;

import com.sotf.parser.language.Novel;
import com.sotf.parser.parsing.Parser;
import com.sotf.parser.solr.Solr;

import java.io.*;

public class Main {


    public static BufferedReader getFileReader(String fileName) {
        File file = new File(fileName);
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        }
        catch (FileNotFoundException fnfe) {
            throw new RuntimeException("File not found");
        }

        return new BufferedReader(fileReader);
    }

    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Missing input param!");
            return;
        }

        String fileName = args[0];
        String novelName = args[1];

        String solrAddress = args.length > 2? args[2] : "http://localhost:8983/solr/sotf3";

        BufferedReader reader = getFileReader(fileName);

        Parser parser = new Parser();
        Novel novel = parser.parseNovel(novelName, reader);
        Solr solr = new Solr();
        solr.seed(solrAddress,novel);
    }
}
