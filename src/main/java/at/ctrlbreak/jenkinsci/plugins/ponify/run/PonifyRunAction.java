package at.ctrlbreak.jenkinsci.plugins.ponify.run;

import at.ctrlbreak.jenkinsci.plugins.ponify.PonifyBaseAction;
import hudson.model.BallColor;
import hudson.model.Run;

public class PonifyRunAction extends PonifyBaseAction {


	private Run<?,?> run;
	
	public PonifyRunAction(Run<?,?> run) {
		this.run = run;
	}

	@Override
	protected BallColor getColor() {
		return run == null ? BallColor.GREY : run.getIconColor();
	}
}
