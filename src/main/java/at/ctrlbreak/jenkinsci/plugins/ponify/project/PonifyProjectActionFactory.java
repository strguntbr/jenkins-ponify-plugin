package at.ctrlbreak.jenkinsci.plugins.ponify.project;

import java.util.Collection;
import java.util.Collections;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.TransientProjectActionFactory;
import hudson.model.AbstractProject;

@Extension
public class PonifyProjectActionFactory extends TransientProjectActionFactory {

	@Override
	@SuppressWarnings("rawtypes")
	public Collection<? extends Action> createFor(AbstractProject target) {
		return Collections.singleton(new PonifyProjectAction(target));
	}

}
