package com.coder.dream.lucene.writer;

/**
 * 索引.txt文件
 *
 * Created by konghang on 16/8/29.
 */
public class FirstLuceneWriterDemo {

    public static void main(String[] args) throws Exception {

        //索引目录
        String indexDir = "./target/lucene";
        String dataDir = "./src/main/resources";

        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(indexDir);
        int numIndexed;
        try{
            numIndexed = indexer.index(dataDir, new Indexer.TextFilesFilter());
        }finally {
            indexer.close();
        }
        long end = System.currentTimeMillis();

        System.out.println("Indexing " + numIndexed + " files took " + (end - start) + " milliseconds");
    }
}
