package com.coder.dream.lucene.searcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.StandardDirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by konghang on 16/8/29.
 */
public class Searcher {

   public void search(String indexDir, String q) throws IOException, ParseException {
       Directory dir = FSDirectory.open(Paths.get(indexDir));
       IndexReader indexReader = StandardDirectoryReader.open(dir);
       IndexSearcher searcher = new IndexSearcher(indexReader);

       QueryParser parser = new QueryParser("contents",new StandardAnalyzer());
       Query query = parser.parse(q);

       long start = System.currentTimeMillis();
       TopDocs hits = searcher.search(query, 10);
       long end = System.currentTimeMillis();

       for(int i = 0; i < hits.scoreDocs.length; ++i){
           Document document = searcher.doc(hits.scoreDocs[i].doc);
           System.out.println(searcher.explain(query,hits.scoreDocs[i].doc).toString());
       }

       System.out.println("Found " + hits.totalHits + " document(s) " + (end - start) + " milliseconds");

       indexReader.close();
   }
}
