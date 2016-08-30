package com.coder.dream.lucene.operations;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import java.io.IOException;

/**
 * Created by konghang on 16/8/30.
 */
public class UpDocumentTest extends DeleteDocumentTest{

    public void testUpdate() throws IOException {

        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new TextField("id","1", Field.Store.YES));
        doc.add(new TextField("conten","123456", Field.Store.YES));

        writer.updateDocument(new Term("id","1"),doc);

        writer.close();
    }
}
