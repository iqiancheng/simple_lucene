package bs.test.tool.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 测试IndexFiles.
 * 
 * @author baishui2004
 */
public class IndexFilesTest {

	public static String LUNCENE_WORKSPACE = "E:/WorkSpace/LuceneWorkSpace/";
	public static String LUNCENE_DATA_CH = LUNCENE_WORKSPACE + "TestData_ch/";

	public static void main(String[] args) throws Exception {
		System.out.println("英文分词Lucene\n");
		main_lucene();
		System.out.println("\n\n中文分词IKAnalyzer\n");
		main_ik();
	}

	/**
	 * 英文分词Lucene.
	 * 
	 * @throws Exception
	 */
	public static void main_lucene() throws Exception {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
		IndexFiles.index(LUNCENE_WORKSPACE + "TestData_en/", LUNCENE_WORKSPACE + "TestIndex_en2/", analyzer);
	}

	/**
	 * 中文分词IKAnalyzer.
	 * 
	 * @throws Exception
	 */
	public static void main_ik() throws Exception {
		Analyzer analyzer = new IKAnalyzer();
		IndexFiles.index(LUNCENE_DATA_CH, LUNCENE_WORKSPACE + "TestIndex_ch_ik2/", analyzer);
	}

}
