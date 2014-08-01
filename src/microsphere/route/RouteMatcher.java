
package microsphere.route;

import java.util.List;



/**
 * Route matcher
 *
 */
public interface RouteMatcher {
    
    String ROOT = "/";
    char SINGLE_QUOTE = '\'';
    
    /**
     * Parses, validates and adds a route
     * 
     * @param route
     * @param acceptType
     * @param target
     */
    void parseValidateAddRoute(String route, String acceptType, Object target);

    /**
     * Finds the a target route for the requested route path and accept type
     * @param httpMethod
     * @param path
     * @param acceptType
     * @return
     */
    RouteMatch findTargetForRequestedRoute(HttpMethod httpMethod, String path, String acceptType);
    
    /**
     * Clear all routes
     */
    void clearRoutes();

	List<RouteMatch> findTargetsForRequestedRoute(HttpMethod httpMethod, String path, String acceptType);

}
