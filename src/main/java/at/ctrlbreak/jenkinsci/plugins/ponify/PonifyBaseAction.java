package at.ctrlbreak.jenkinsci.plugins.ponify;

import java.net.URI;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;

import org.kohsuke.stapler.Stapler;

import jenkins.model.Jenkins;
import hudson.Functions;
import hudson.model.Action;
import hudson.model.BallColor;

public abstract class PonifyBaseAction implements Action {

	@Override
	public String getIconFileName() {
		return null;
	}

	@Override
	public String getDisplayName() {
		return "";
	}

	@Override
	public String getUrlName() {
		return "";
	}

	protected abstract BallColor getColor();

	public String getBgImage() {
		URI uri = URI.create(Jenkins.getInstance().getRootUrl());
		uri = uri.resolve("plugin/ponify/svg/");
		switch (getColor()) {
		case GREY:
		case GREY_ANIME:
		case ABORTED:
		case ABORTED_ANIME:
		case DISABLED:
		case DISABLED_ANIME:
		case NOTBUILT:
		case NOTBUILT_ANIME:
			uri = uri.resolve("grey.svg");
			break;
		case BLUE:
		case BLUE_ANIME:
			uri = uri.resolve("green.svg");
			break;
		case RED:
		case RED_ANIME:
			uri = uri.resolve("red.svg");
			break;
		case YELLOW:
		case YELLOW_ANIME:
			uri = uri.resolve("yellow.svg");
			break;
		default:
			break;
		
		}
		return uri.toString();
	}
	
	protected boolean isPonified() {
		return PonifyPlugin.isPonified();
	}
}
