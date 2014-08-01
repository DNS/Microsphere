
package microsphere;


/**
 * A TemplateViewRoute is built up by a path (for url-matching) and the implementation of the 'render' method.
 * TemplateViewRoute instead of returning the result of calling toString() as body, it returns the result of calling render method.
 * 
 * The primary purpose of this kind of Route is provide a way to create generic and reusable components for rendering output using a Template Engine. For example to render objects to html by using Freemarker template engine..
 *
 *
 */
public abstract class TemplateViewRoute extends Route {

	protected TemplateViewRoute(String path) {
		super(path);
	}

	protected TemplateViewRoute(String path, String acceptType) {
		super(path, acceptType);
	}

	
	@Override
	public String render(Object object) {
		ModelAndView modelAndView = (ModelAndView)object;
		return render(modelAndView);
	}
	
	/**
	 * Creates a new ModelAndView object with given arguments.
	 * @param model object.
	 * @param viewName t be rendered.
	 * @return object with model and view set.
	 */
	public ModelAndView modelAndView(Object model, String viewName) {
		return new ModelAndView(model, viewName);
	}
	
	/**
	 * Method called to render the output that is sent to client.
	 * @param modelAndView object where object (mostly a POJO) and the name of the view to render are set.
	 * @return message that it is sent to client.
	 */
	public abstract String render(ModelAndView modelAndView);
	
}
