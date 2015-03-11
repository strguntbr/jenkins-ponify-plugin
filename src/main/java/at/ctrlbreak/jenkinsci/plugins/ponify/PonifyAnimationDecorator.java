package at.ctrlbreak.jenkinsci.plugins.ponify;

import hudson.Extension;
import hudson.model.PageDecorator;

@Extension
public class PonifyAnimationDecorator extends PageDecorator {
	public boolean isPonifyAnimationEnabled() {
		return !PonifyPlugin.isPonified();
	}
}
