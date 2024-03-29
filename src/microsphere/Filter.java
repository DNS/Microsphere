
package microsphere;

import microsphere.utils.MicrosphereUtils;


/**
 * A Filter is built up by a path (for url-matching) and the implementation of the 'handle' method.
 * When a request is made, if present, the matching routes 'handle' method is invoked. 
 *
 */
public abstract class Filter extends AbstractRoute {

	private static final String DEFAUT_CONTENT_TYPE = "text/html";
	
    private String path;
    private String acceptType;
    
    /**
     * Constructs a filter that matches on everything
     */
    protected Filter() {
        this(MicrosphereUtils.ALL_PATHS);
    }
    
    /**
     * Constructor
     * 
     * @param path The filter path which is used for matching. (e.g. /hello, users/:name) 
     */
    protected Filter(String path) {
        this(path, DEFAUT_CONTENT_TYPE);
    }
    
    protected Filter(String path, String acceptType) {
    	this.path = path;
    	this.acceptType = acceptType;
    }
    
    /**
     * Invoked when a request is made on this filter's corresponding path e.g. '/hello'
     * 
     * @param request The request object providing information about the HTTP request
     * @param response The response object providing functionality for modifying the response
     */
    public abstract void handle(Request request, Response response);

    public String getAcceptType() {
		return acceptType;
	}
    
    /**
     * Returns this route's path
     */
    String getPath() {
        return this.path;
    }
    
}
