
package microsphere;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import microsphere.route.RouteMatch;

public final class RequestResponseFactory {

    private RequestResponseFactory() {}
    
    public static Request create(RouteMatch match, HttpServletRequest request) {
        return new Request(match, request);
    }
    
    public static Response create(HttpServletResponse response) {
        return new Response(response);
    }
    
}
