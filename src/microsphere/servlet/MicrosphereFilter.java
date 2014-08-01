
package microsphere.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import microsphere.Access;
import microsphere.route.RouteMatcherFactory;
import microsphere.webserver.MatcherFilter;

/**
 * Filter that can be configured to be used in a web.xml file.
 * Needs the init parameter 'applicationClass' set to the application class where
 * the adding of routes should be made.
 *
 */
public class MicrosphereFilter implements Filter {

    public static final String APPLICATION_CLASS_PARAM = "applicationClass";

    private static final Logger LOG = LoggerFactory.getLogger(MicrosphereFilter.class);
    
    private String filterPath;

    private MatcherFilter matcherFilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Access.runFromServlet();

        final MicrosphereApplication application = getApplication(filterConfig);
        application.init();

        filterPath = FilterTools.getFilterPath(filterConfig);
        matcherFilter = new MatcherFilter(RouteMatcherFactory.get(), true, false);
    }

    /**
     * Returns an instance of {@link MicrosphereApplication} which on which {@link MicrosphereApplication#init() init()} will be called.
     * Default implementation looks up the class name in the filterConfig using the key {@link #APPLICATION_CLASS_PARAM}.
     * Subclasses can override this method to use different techniques to obtain an instance (i.e. dependency injection).
     *
     * @param filterConfig the filter configuration for retrieving parameters passed to this filter.
     * @return the microsphere application containing the configuration.
     * @throws ServletException if anything went wrong.
     */
    protected MicrosphereApplication getApplication(FilterConfig filterConfig) throws ServletException {
        try {
            String applicationClassName = filterConfig.getInitParameter(APPLICATION_CLASS_PARAM);
            Class<?> applicationClass = Class.forName(applicationClassName);
            return (MicrosphereApplication) applicationClass.newInstance();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request; // NOSONAR
        
        final String relativePath = FilterTools.getRelativePath(httpRequest, filterPath);
        
        LOG.debug(relativePath);
        
        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
            @Override
            public String getRequestURI() {
                return relativePath;
            }
        };
        matcherFilter.doFilter(requestWrapper, response, chain);
    }

    @Override
    public void destroy() {
        // ignore
    }

}
