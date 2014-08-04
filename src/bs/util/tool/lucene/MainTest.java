package bs.util.tool.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;

import bs.util.tool.lucene.common.ConvertUtils;
import bs.util.tool.lucene.common.DocumentType;
import bs.util.tool.lucene.common.FileBean;
import bs.util.tool.lucene.common.Log;
import bs.util.tool.lucene.common.SearchResult;
import bs.util.tool.lucene.index.IndexFiles;
import bs.util.tool.lucene.search.SearchFiles;

public class MainTest {

	public static void main(String[] args) {
		operateIndex();
		search();
	}

	public static void operateIndex() {
		IndexWriter writer = null;
		try {
			writer = IndexFiles.newIndexWriter();

			List<Document> documents = new ArrayList<Document>();
			for (int i = 0; i < 100; i++) {
				FileBean fileBean = new FileBean();
				fileBean.setId(Integer.toString(i));
				fileBean.setTitle("测试标题" + i);
				fileBean.setType(DocumentType.TEXT);
				fileBean.setContent("你好啊，测试测试哦" + i);
				fileBean.setUri("http://www.thebestofyouth.com/" + i);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					//
				}
				fileBean.setTime(new Date());
				documents.add(ConvertUtils.fileToDocument(fileBean));
			}

			//IndexFiles.addIndex(documents, writer);
			IndexFiles.updateIndex(documents, writer);
			//IndexFiles.deleteIndex(documents, writer);

		} catch (IOException e) {
			Log.log.error("New IndexWriter error.", e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					Log.log.error("Close IndexWriter error.", e);
				}
			}
		}
	}

	public static void search() {
		SearchResult searchResult = SearchFiles.search("content", "你好", 2);
		System.out.println("结果总数：" + searchResult.getTopDocs().totalHits);
		List<Document> documents = searchResult.getDocuments();
		for (int i = 0; i < documents.size(); i++) {
			Document document = documents.get(i);
			System.out.print("Id: " + document.get("id") + ", Title: " + document.get("title") + ", Type: "
					+ document.get("type") + ", Content: " + document.get("content") + ", Uri: " + document.get("uri")
					+ ", Time: " + new Date(Long.parseLong(document.get("time"))) + "\n");
		}
	}

}
