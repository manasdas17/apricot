package io.scan;

import java.io.File;
import java.util.Scanner;

/**
 * Scanner for reading from HLDD files (*.agm files)
 *
 * @author Anton Chepurov
 */
public class HLDDScanner {
	private final static String COMMENT = ";";

	private final Scanner scanner;

	/**
	 * Constructor for Scanner.
	 *
	 * @param hlddSource String or File representing the HLDD structure
	 * 					 (for other classes an Exception will be thrown)
	 * @throws Exception if specified <code>hlddSource</code> parameter
	 * 					 is not of neither String nor File type
	 */
	public HLDDScanner(Object hlddSource) throws Exception {
		if (hlddSource instanceof File) {
			scanner = new Scanner((File) hlddSource);
		} else if (hlddSource instanceof String) {
			scanner = new Scanner((String) hlddSource);
		} else
			throw new Exception("Unsupported hlddSource used in " + HLDDScanner.class.getSimpleName() + ": " + hlddSource.toString());
	}


	/**
	 * @return last read line(token) or <code>null</code> if EOF is reached
	 */
	public String next() {

		while (true) {
			/* EOF is reached */
			if (!scanner.hasNextLine()) return null;

			String lastReadToken = scanner.nextLine().trim().toUpperCase();

			/* Skip comments */
			if (lastReadToken.startsWith(COMMENT)) continue;

			/* Cut off comments from the end of line */
			if (lastReadToken.contains(";"))
				lastReadToken = lastReadToken.substring(0, lastReadToken.indexOf(";")).trim();

			/* Skip empty lines */
			if (lastReadToken.length() == 0) continue;

			return lastReadToken;
		}

	}

	public void close() {
		scanner.close();
	}
}
