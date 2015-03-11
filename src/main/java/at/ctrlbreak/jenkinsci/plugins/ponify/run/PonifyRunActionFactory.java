package at.ctrlbreak.jenkinsci.plugins.ponify.run;

import java.util.Collection;
import java.util.Collections;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.Run;
import jenkins.model.TransientActionFactory;

@Extension
@SuppressWarnings("rawtypes")
public class PonifyRunActionFactory extends TransientActionFactory<Run> {

	@Override
	public Class<Run> type() {
		return Run.class;
	}

	@Override
	public Collection<? extends Action> createFor(Run target) {
		return Collections.singleton(new PonifyRunAction(target));
	}

}
