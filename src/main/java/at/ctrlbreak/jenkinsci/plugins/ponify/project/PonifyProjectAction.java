package at.ctrlbreak.jenkinsci.plugins.ponify.project;

import at.ctrlbreak.jenkinsci.plugins.ponify.PonifyBaseAction;
import hudson.model.AbstractProject;
import hudson.model.BallColor;

public class PonifyProjectAction extends PonifyBaseAction {

	private AbstractProject<?,?> project;
	
	public PonifyProjectAction(AbstractProject<?,?> project) {
		this.project = project;
	}

	@Override
	protected BallColor getColor() {
		return project == null ? BallColor.GREY : project.getIconColor();
	}
}
