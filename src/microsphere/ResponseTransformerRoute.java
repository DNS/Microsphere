
package microsphere;

/**
 * A ResponseTransformerRoute is built up by a path (for url-matching) and the
 * implementation of the 'render' method. ResponseTransformerRoute instead of
 * returning the result of calling toString() as body, it returns the result of
 * calling render method.
 * 
 * The primary purpose of this kind of Route is provide a way to create generic
 * and reusable transformers. For example to convert an Object to JSON format.
 *
 */
public abstract class ResponseTransformerRoute extends Route {

    protected ResponseTransformerRoute(String path) {
        super(path);
    }

    protected ResponseTransformerRoute(String path, String acceptType) {
        super(path, acceptType);
    }

    /**
     * Method called for rendering the output.
     * 
     * @param model
     *            object used to render output.
     * 
     * @return message that it is sent to client.
     */
    public abstract String render(Object model);

}
