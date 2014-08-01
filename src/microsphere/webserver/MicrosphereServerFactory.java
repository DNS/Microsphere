
package microsphere.webserver;

import microsphere.route.RouteMatcherFactory;

/**
 *
 *
 */
public final class MicrosphereServerFactory {

    private MicrosphereServerFactory() {}

    public static MicrosphereServer create(boolean hasMultipleHandler) {
        MatcherFilter matcherFilter = new MatcherFilter(RouteMatcherFactory.get(), false, hasMultipleHandler);
        matcherFilter.init(null);
        JettyHandler handler = new JettyHandler(matcherFilter);
        return new MicrosphereServerImpl(handler);
    }

}
