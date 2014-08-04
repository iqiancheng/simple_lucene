package bs.util.tool.lucene.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 配置.
 * 
 * @author baishui2004
 */
public class Config {

	/**
	 * 索引存储目录.
	 */
	public static String IndexDirectory;

	static {
		String confFile = "config.properties";
		final Properties props = new Properties();
		InputStream inStream = null;
		try {
			inStream = Config.class.getClassLoader().getResourceAsStream(confFile);
			props.load(inStream);

			IndexDirectory = props.getProperty("IndexDirectory");
		} catch (FileNotFoundException e) {
			Log.log.error("*****************Properties file '" + confFile + "' not found!*****************", e);
		} catch (IOException e) {
			Log.log.error("*****************Properties file '" + confFile + "' found IOException!*****************", e);
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					//
				}
			}
		}
	}

	/**
	 * Lucene Version.
	 */
	public static final Version VERSION = Version.LUCENE_4_9;

	/**
	 * Lucene Stand analyzer.
	 */
	@SuppressWarnings("unused")
	private static final Analyzer STANDARDANALYZER = new StandardAnalyzer(VERSION);

	/**
	 * IK analyzer.
	 */
	private static final Analyzer IKANALYZER = new IKAnalyzer();

	/**
	 * Analyzer.
	 */
	public static final Analyzer ANALYZER = IKANALYZER;

	/**
	 * Lucene document id's name.
	 */
	public static final String IDNAME = "id";

}
