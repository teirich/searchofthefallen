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

    /**
     * Notes:
     *  port forward to prod - ssh  -L 8983:localhost:8983 -i "search-of-the-fallen.pem" user@host
     *  create solr core - http://localhost:8983/solr/admin/cores?action=CREATE&name=sotf
     *  manually add fields in UI
     *      - book (int)
     *      - chapter (int)
     *      - nextText (text_en)
     *      - novel (int)
     *      - prevText (text_en)
     *      - sequence (int)
     *      - text (text_en)
     * @param args  Arg0 = source text file, Arg1 = Novel # (GOTM = 1), Optional Arg3 = solrAddress
     */
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Missing input param!");
            return;
        }

        String fileName = args[0];
        Integer novelNumber = Integer.valueOf(args[1]);

        String solrAddress = args.length > 2? args[2] : "http://localhost:8983/solr/sotf3";

        BufferedReader reader = getFileReader(fileName);

        Parser parser = new Parser();
        Novel novel = parser.parseNovel(novelNumber, reader);
        Solr solr = new Solr();
        solr.seed(solrAddress,novel);
    }
}
