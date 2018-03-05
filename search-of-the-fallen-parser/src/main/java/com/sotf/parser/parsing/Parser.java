package com.sotf.parser.parsing;

import com.sotf.parser.language.Book;
import com.sotf.parser.language.Chapter;
import com.sotf.parser.language.Paragraph;
import com.sotf.parser.language.Novel;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thad on 1/7/17.
 */
public class Parser {

    private static final Pattern bookPattern = Pattern.compile("^(Book \\w*|Prologue|Epilogue|Glossary)$");
    private static final Pattern chapterPattern = Pattern.compile("^Chapter (\\w|-)*$");
    private static final String[] reservedNames = {"Prologue", "Epilogue", "Epilogue I", "Epilogue II", "Glossary"};

    private String currentLine;
    private Paragraph lastParagraph;
    private Novel novel;
    private Book currentBook;
    private Chapter currentChapter;
    private int sequence;

    public Novel parseNovel(int number, BufferedReader reader) {
        //initialize
        lastParagraph = null;
        currentLine = null;
        sequence = 0;

        novel = new Novel(number);
        currentBook = new Book(-1);
        currentChapter = new Chapter(-1);

        currentBook.addChapter(currentChapter);
        novel.addBook(currentBook);

        try {
            while ((currentLine = reader.readLine()) != null){
                if(currentLine.isEmpty()){
                    continue;
                }
                Matcher bookMatcher = bookPattern.matcher(currentLine);
                if(bookMatcher.matches()){
                    String match = bookMatcher.group();
                    newBook(resolveBookNumber(match));
                    //these books don't have chapters, need to make one for them
                    if(Arrays.stream(reservedNames).anyMatch(reserveName->reserveName.equals(match))){
                        newChapter(resolveChapterNumber(match));
                    }
                    continue;
                }

                Matcher chapterMatcher = chapterPattern.matcher(currentLine);
                if(chapterMatcher.matches()){
                    String match = chapterMatcher.group();
                    newChapter(resolveChapterNumber(match));
                    continue;
                }
                newParagraph(currentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file");
        }
        return novel;
    }

    private void newChapter(int number) {
        Chapter chapter = new Chapter(number);
        currentChapter = chapter;
        currentBook.addChapter(currentChapter);
    }

    private void newBook(int number) {
        currentBook = new Book(number);
        novel.addBook(currentBook);
    }

    //look at this janky shit
    private int resolveChapterNumber(String string) {
        switch (string) {
            case "Prologue": return 0;
            case "Epilogue": return 100;
            case "Epilogue I": return 100;  //for TCG
            case "Epilogue II": return 101;
            case "Chapter One": return 1;
            case "Chapter Two": return 2;
            case "Chapter Three": return 3;
            case "Chapter Four": return 4;
            case "Chapter Five": return 5;
            case "Chapter Six": return 6;
            case "Chapter Seven": return 7;
            case "Chapter Eight": return 8;
            case "Chapter Nine": return 9;
            case "Chapter Ten": return 10;
            case "Chapter Eleven": return 11;
            case "Chapter Twelve": return 12;
            case "Chapter Thirteen": return 13;
            case "Chapter Fourteen": return 14;
            case "Chapter Fifteen": return 15;
            case "Chapter Sixteen": return 16;
            case "Chapter Seventeen": return 17;
            case "Chapter Eighteen": return 18;
            case "Chapter Nineteen": return 19;
            case "Chapter Twenty": return 20;
            case "Chapter Twenty-one":
            case "Chapter Twenty-One": return 21;
            case "Chapter Twenty-two":
            case "Chapter Twenty-Two": return 22;
            case "Chapter Twenty-three":
            case "Chapter Twenty-Three": return 23;
            case "Chapter Twenty-four":
            case "Chapter Twenty-Four": return 24;
            case "Chapter Twenty-five":
            case "Chapter Twenty-Five": return 25;
            case "Chapter Twenty-six":
            case "Chapter Twenty-Six": return 26;
            case "Chapter Twenty-seven":
            case "Chapter Twenty-Seven": return 27;
            case "Chapter Twenty-eight":
            case "Chapter Twenty-Eight": return 28;
            case "Chapter Twenty-nine":
            case "Chapter Twenty-Nine": return 29;
            case "Chapter Thirty": return 30;
            default: return -1;
        }
    }

    private int resolveBookNumber(String string) {
        switch (string) {
            case "Prologue": return 0;
            case "Epilogue": return 100;
            case "Epilogue I": return 100;  //for TCG
            case "Epilogue II": return 101;
            case "Book One": return 1;
            case "Book Two": return 2;
            case "Book Three": return 3;
            case "Book Four": return 4;
            case "Book Five": return 5;
            case "Book Six": return 6;
            case "Book Seven": return 7;
            case "Book Eight": return 8;
            case "Book Nine": return 9;
            case "Book Ten": return 10;
            default: return -1;
        }
    }

    private void newParagraph(String text) {
        Paragraph paragraph = new Paragraph(lastParagraph, text, null, currentChapter, currentBook, novel, sequence++);
        if(lastParagraph != null) lastParagraph.setNext(paragraph);
        currentChapter.addParagraph(paragraph);
        lastParagraph = paragraph;
    }
}
