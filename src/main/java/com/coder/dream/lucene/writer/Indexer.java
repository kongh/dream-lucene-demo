package com.coder.dream.lucene.writer;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by konghang on 16/8/29.
 */
public class Indexer {

    private IndexWriter writer;

    public Indexer(String indexDir) throws Exception{
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        writer = new IndexWriter(dir,config);
    }

    public void close() throws IOException {
        writer.close();
    }

    public int index(String dataDir, FileFilter filter) throws IOException {
        File[] files = new File(dataDir).listFiles();

        for(File f : files){
            if(!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead() && (filter != null && filter.accept(f))){
                indexFile(f);
            }
        }

        return writer.numDocs();
    }

    private Document indexFile(File f) throws IOException {
        System.out.println("Indexing " + f.getCanonicalPath());
        Document doc = new Document();
        doc.add(new TextField("contents", new FileReader(f)));
        doc.add(new StringField("fileName",f.getName(), Field.Store.YES));
        doc.add(new Field("fullPath", f.getCanonicalPath(),StringField.TYPE_STORED));
        writer.addDocument(doc);
        return doc;
    }

    public static class TextFilesFilter implements FileFilter{

        public boolean accept(File path) {
            return true;
//            return path.getName().toLowerCase().endsWith(".txt");
        }

    }
}
