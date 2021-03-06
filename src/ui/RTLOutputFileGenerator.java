package ui;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.File;

/**
 * @author Anton Chepurov
 */
public class RTLOutputFileGenerator implements DocumentListener {

	private final ApplicationForm applicationForm;

	public RTLOutputFileGenerator(ApplicationForm applicationForm) {
		this.applicationForm = applicationForm;
	}

	public File generate() {
		File sourceFile = applicationForm.getSourceFile();
		if (sourceFile == null) {
			return null;
		}

		StringBuilder name = new StringBuilder(sourceFile.getName().replaceAll(".agm$", ""));
		name.append("_RTL.agm");

		return new File(sourceFile.getParent(), name.toString());
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		react();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		react();
	}

	void react() {
		BusinessLogic.ParserID parserId = applicationForm.getSelectedParserId();
		if (parserId == BusinessLogic.ParserID.HlddBeh2HlddRtl) {

			File file = generate();

			applicationForm.setRtlRtlFile(file);

		}
	}
}
