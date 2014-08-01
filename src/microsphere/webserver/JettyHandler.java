
package microsphere.webserver;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;

/**
 * Simple Jetty Handler
 *
 */
class JettyHandler extends SessionHandler  {

	private static final Logger LOG = Log.getLogger(JettyHandler.class);

    private Filter filter;

    public JettyHandler(Filter filter) {
        this.filter = filter;
    }

    @Override
    public void doHandle(
            String target,
            Request baseRequest,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("jettyhandler, handle();");
        try {
            filter.doFilter(request, response, null);
            baseRequest.setHandled(true);
        } catch (NotConsumedException ignore) {
            // TODO : Not use an exception in order to be faster.
            baseRequest.setHandled(false);
        }
    }

}
