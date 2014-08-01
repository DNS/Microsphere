
package microsphere.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Some utility methods
 *
 */
public final class MicrosphereUtils {

    public static final String ALL_PATHS = "+/*paths";
    
    private MicrosphereUtils() {}
    
    public static List<String> convertRouteToList(String route) {
        String[] pathArray = route.split("/");
        List<String> path = new ArrayList<String>();
        for (String p : pathArray) {
            if (p.length() > 0) {
                path.add(p);
            }
        }
        return path;
    }
    
    public static boolean isParam(String routePart) {
        return routePart.startsWith(":");
    }

    public static boolean isSplat(String routePart) {
        return routePart.equals("*");
    }
    
}
