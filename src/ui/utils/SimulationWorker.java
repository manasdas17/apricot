package ui.utils;

import ui.utils.uiWithWorker.TaskSwingWorker;
import io.ConsoleWriter;

import java.util.List;
import java.io.OutputStream;

import ui.ApplicationForm;

/**
 * @author Anton Chepurov
 */
@Deprecated
public class SimulationWorker extends TaskSwingWorker {
	private final ApplicationForm applicationForm;

	public SimulationWorker(List<String> executableCommand, OutputStream infoOut, OutputStream errorOut,
							ApplicationForm applicationForm, ConsoleWriter consoleWriter) {
		super(executableCommand, infoOut, errorOut, consoleWriter);
		this.applicationForm = applicationForm;
	}

	protected Boolean doInBackground() {
		/* Disable HLDD SIMULATION BUTTON and SIMULATE BUTTON */
		applicationForm.setEnableHlddSimulButton(false);
		applicationForm.setEnableSimulateButton(false);
		/* Start worker */
		return super.doInBackground();
	}

	protected void done() {
		/* Enable HLDD SIMULATION BUTTON and SIMULATE BUTTON */
		applicationForm.setEnableHlddSimulButton(true);
		applicationForm.setEnableSimulateButton(true);
		super.done();
	}
}
