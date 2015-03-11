package at.ctrlbreak.jenkinsci.plugins.ponify;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PonifyFilter implements Filter {

	private final String patternStr = "/(\\d{2}x\\d{2})/%s(_anime|)\\.(gif|png)";
	private final Pattern patternGrey = Pattern.compile(String.format(patternStr, "(?:grey|disabled|aborted|nobuilt)"));
	private final Pattern patternBlue = Pattern.compile(String.format(patternStr, "blue"));
	private final Pattern patternRed = Pattern.compile(String.format(patternStr, "red"));
	private final Pattern patternYellow = Pattern.compile(String.format(patternStr, "yellow"));

	@Override
	public void init(FilterConfig conf) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
			final HttpServletRequest httpReq = (HttpServletRequest) req;
			final HttpServletResponse httpResp = (HttpServletResponse) resp;
			if ((httpReq.getContextPath() + "/plugin/ponify/animation/ponified-text.png").equals(httpReq.getRequestURI())) {
				PonifyPlugin.setPonified(httpResp);
			}
			if (PonifyPlugin.isPonified(httpReq)) {
				String ponyUrl = tryMapPony(httpReq.getRequestURI());
				if (ponyUrl != null) {
					RequestDispatcher dispatcher = httpReq.getRequestDispatcher(ponyUrl);
					dispatcher.forward(httpReq, httpResp);
					return;
				}
			}
		}
		chain.doFilter(req, resp);
	}
	
	private String tryMapPony(String uri) {
		Matcher m;
		if ((m = patternGrey.matcher(uri)).find()) {
			return "/plugin/ponify/svg/grey" + m.group(2) + ".svg";
		} else if ((m = patternBlue.matcher(uri)).find()) {
			return "/plugin/ponify/svg/green" + m.group(2) + ".svg";
		} else if ((m = patternRed.matcher(uri)).find()) {
			return "/plugin/ponify/svg/red" + m.group(2) + ".svg";
		} else if ((m = patternYellow.matcher(uri)).find()) {
			return "/plugin/ponify/svg/yellow" + m.group(2) + ".svg";
		}
		return null;
	}
}
