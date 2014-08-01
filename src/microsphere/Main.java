
package microsphere;


import java.util.*;

        import static microsphere.Microsphere.*;

        import microsphere.template.freemarker.FreeMarkerRoute;


public class Main {
    static final String DEBUG_IP_ADDR = "0.0.0.0";  // listen to all local interfaces
    static final int DEBUG_PORT = 8080;

    public static void main(String[] args) {
        if (System.getenv("OPENSHIFT_INTERNAL_IP") != null) {
            setIpAddress(System.getenv("OPENSHIFT_INTERNAL_IP"));
        } else {
            setIpAddress(DEBUG_IP_ADDR);
        }
        if (System.getenv("OPENSHIFT_INTERNAL_PORT") != null) {
            int port = Integer.parseInt( System.getenv("OPENSHIFT_INTERNAL_PORT") );
            setPort(port);
        } else {
            setPort(DEBUG_PORT);
        }

        externalStaticFileLocation("public");   // not in classpath
        //staticFileLocation("static");         // in the classpath


        //Configuration cfg = new Configuration();
        //cfg.setSharedVariable("wrap", new WrapDirective());
        //cfg.setSharedVariable("company", "Foo Inc.");


        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "from /";
            }
        });

        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
                return request.raw().getRequestURI();
            }
        });


        get(new Route("/404") {
            @Override
            public Object handle(Request request, Response response) {
                response.status(404);
                response.type("text/plain");
                return "Error 404\n";
            }
        });

        get(new Route("/hello/:name") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World! "  + request.params(":name");
            }
        });





        get(new Route("/blog") {
            @Override
            public Object handle(Request request, Response response) {
                //response.type("text/plain");
                String x = "blog !";

                return x;
            }
        });

        get(new Route("/blog/:title_id") {
            @Override
            public Object handle(Request request, Response response) {
                String title_id = request.params(":title_id");  // BUG: need quote escape !
                String query = String.format("select * from blog where title_id = '%s'", title_id);
                return "blog title_id";
            }
        });

        get(new FreeMarkerRoute("/test") {
            @Override
            public ModelAndView handle(Request request, Response response) {

                List list = new ArrayList();
                list.add("red");
                list.add("green");
                list.add("blue");

                HashMap hashmap = new HashMap();
                hashmap.put("a", 123);
                hashmap.put("b", "hashmap ok");


                //SimpleHash map = new SimpleHash();  // will use the default wrapper
                Map map = new HashMap();           //  use HashMap
                map.put("thelist", list);
                map.put("anotherString", "blah");
                map.put("anotherNumber", new Double(3.14));
                map.put("thehash", hashmap);

                return modelAndView(map, "test.html");
            }
        });

    }
}



