package lucene.ranker;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;


public class App 
{
	String indexDir = "ind/";
	static String dataDir;
	Indexer indexer;
	Searcher searcher;

	public static void main(String[] args) {
		
		App ranker;

		if(args[0].equals("index")){

			try {
				dataDir = args[1];
				ranker = new App();
				ranker.index();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

		if(args[0].equals("search")){
			try {
				ranker = new App();
				ranker.search(args[1]);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void index() throws IOException {
		indexer = new Indexer(indexDir);
		indexer.createIndex(dataDir, new TextFileFilter());	
		indexer.close();
	}

	private void search(String searchQuery) throws IOException, ParseException {
		searcher = new Searcher(indexDir);
		TopDocs hits = searcher.search(searchQuery);
		
		for(ScoreDoc scoreDoc : hits.scoreDocs) {
			Document doc = searcher.getDocument(scoreDoc);
			System.out.println(doc.get(LuceneConstants.FILE_NAME));      
		}  
	}
}
