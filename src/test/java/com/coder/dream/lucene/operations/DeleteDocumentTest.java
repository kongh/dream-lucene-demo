package com.coder.dream.lucene.operations;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import java.io.IOException;

/**
 * Created by konghang on 16/8/30.
 */
public class DeleteDocumentTest extends AddDocumentTest{

    public void testDeleteBeforeOptimize() throws IOException {
        IndexWriter writer = getWriter();
        assertEquals(2,writer.numDocs());

        writer.deleteDocuments(new Term("id","1"));
        writer.commit();

        assertTrue(writer.hasDeletions());
        assertEquals(2,writer.maxDoc());

    }
}
