package com.coder.dream.lucene.operations;

import junit.framework.TestCase;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexOptions;
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

            FieldType filedType = new FieldType(TextField.TYPE_STORED);
            filedType.setIndexOptions(IndexOptions.NONE);
            Field unindexedField = new Field("unindexed", unindexed[i], filedType);
            doc.add(unindexedField);

            StringField unstoredField = new StringField("unstored",unstored[i], Field.Store.NO);
            doc.add(unstoredField);

            StringField textField = new StringField("text",text[i], Field.Store.YES);
            doc.add(textField);
            writer.addDocument(doc);
        }
        writer.close();
    }

    protected int getHitCount(String fieldName, String searchString) throws IOException {
        return 0;
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
