package at.ctrlbreak.jenkinsci.plugins.ponify;

import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kohsuke.stapler.Stapler;

import hudson.Functions;
import hudson.Plugin;
import hudson.util.PluginServletFilter;

public class PonifyPlugin extends Plugin {
	private static final Logger logger = Logger.getLogger("at.ctrlbreak.jenkinsci.plugins.ponify");

	@Override
	public void start() throws Exception {
		super.start();
		PluginServletFilter.addFilter(new PonifyFilter());
	}

	static boolean isPonified() {
		return isPonified(Stapler.getCurrentRequest());
	}
	
	static boolean isPonified(HttpServletRequest request) {
		if (Functions.getCookie(request,"ponified") == null) {
			logger.info("New pony victim found.");
			return false;
		}
		return true;
	}
	
	static void setPonified() {
		setPonified(Stapler.getCurrentResponse()); 
	}
	
	static void setPonified(HttpServletResponse response) {
		Cookie cookie = new Cookie("ponified", "true");
		cookie.setPath("/jenkins");
		response.addCookie(cookie); 
	}
}
