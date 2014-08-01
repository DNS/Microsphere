
package microsphere;

public final class Access {

    private Access() {}
    
    public static String getBody(Response response) {
        return response.body();
    }

    public static void runFromServlet() {
        Microsphere.runFromServlet();
    }

}
