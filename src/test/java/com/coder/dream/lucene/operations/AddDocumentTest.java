package com.coder.dream.lucene.operations;

import junit.framework.TestCase;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by konghang on 16/8/30.
 */
public class AddDocumentTest extends TestCase{

    protected String[] ids = {"1","2"};
    protected String[] unindexed = {"Netherlands","Italy"};
    protected String[] unstored = {"Amsterdam has lots of bridges","Venice has lots of canals"};
    protected String[] text = {"Amsterdam","Venice"};
    protected Directory directory;

    protected void setUp() throws IOException {
        directory = new RAMDirectory();

        IndexWriter writer = getWriter();
        for (int i = 0 ; i < ids.length; ++i){
            Document doc = new Document();
            doc.add(new TextField("id",ids[i], Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.close();
    }

    public IndexWriter getWriter() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(new WhitespaceAnalyzer());
        return new IndexWriter(directory,config);
    }

    public void testIndexWriter() throws IOException {
        IndexWriter writer = getWriter();
        assertEquals(ids.length,writer.numDocs());
        writer.close();
    }
}
