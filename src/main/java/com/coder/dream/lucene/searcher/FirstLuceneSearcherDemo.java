package com.coder.dream.lucene.searcher;

import java.io.IOException;

/**
 * Created by konghang on 16/8/29.
 */
public class FirstLuceneSearcherDemo {

    public static void main(String[] args) throws Exception {
        String indexDir = "./target/lucene";

        Searcher searcher = new Searcher();
        searcher.search(indexDir,"pay");
    }
}
