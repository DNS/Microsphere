
package microsphere.servlet;

/**
 * The application entry point when Microsphere is run in a servlet context.
 *
 */
public interface MicrosphereApplication {
    
    /**
     * Invoked from the MicrosphereFilter. Add routes here.
     */
    void init();
}
