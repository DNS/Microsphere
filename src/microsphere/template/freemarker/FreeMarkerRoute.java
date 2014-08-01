
package microsphere.template.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import microsphere.ModelAndView;
import microsphere.TemplateViewRoute;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Renders HTML from Route output using FreeMarker.
 *
 * FreeMarker configuration can be set with the {@link FreeMarkerRoute#setConfiguration(Configuration)}
 * method. If no configuration is set the default configuration will be used where
 * ftl files need to be put in directory microsphere/template/freemarker under the resources directory.
 *
 */
public abstract class FreeMarkerRoute extends TemplateViewRoute {

    /** The FreeMarker configuration */
    private Configuration configuration;

    /**
     * Creates a FreeMarkerRoute for a path
     *
     * @param path The route path which is used for matching. (e.g. /hello, users/:name)
     */
    protected FreeMarkerRoute(String path) {
        super(path);
        this.configuration = createDefaultConfiguration();
    }

    /**
     * Creates a FreeMarkerRoute for a path and accept type
     *
     * @param path The route path which is used for matching. (e.g. /hello, users/:name)
     * @param acceptType The accept type which is used for matching.
     */
    protected FreeMarkerRoute(String path, String acceptType) {
        super(path, acceptType);
        this.configuration = createDefaultConfiguration();
    }

    /**
     * Creates a FreeMarkerRoute for a path with a configuration
     *
     * @param path The route path which is used for matching. (e.g. /hello, users/:name)
     * @param configuration The Freemarker configuration
     */
    protected FreeMarkerRoute(String path, Configuration configuration) {
        super(path);
        this.configuration = configuration;
    }

    /**
     * Creates a FreeMarkerRoute for a path and accept type with a configuration
     *
     * @param path The route path which is used for matching. (e.g. /hello, users/:name)
     * @param acceptType The accept type which is used for matching.
     * @param configuration The Freemarker configuration
     */
    protected FreeMarkerRoute(String path, String acceptType, Configuration configuration) {
        super(path, acceptType);
	     this.configuration = configuration;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(ModelAndView modelAndView) {
        try {
            StringWriter stringWriter = new StringWriter();

            Template template = configuration.getTemplate(modelAndView.getViewName());
            template.process(modelAndView.getModel(), stringWriter);

            return stringWriter.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Sets FreeMarker configuration.
     * Note: If configuration is not set the default configuration
     * will be used.
     *
     * @param configuration the configuration to set
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    private Configuration createDefaultConfiguration() {
        Configuration configuration = new Configuration();
        //configuration.setClassForTemplateLoading(FreeMarkerRoute.class, "");
        File f = new File("");
        String template_dir = f.getAbsolutePath();

        try {
            File fd = new File(template_dir + "/templates/");
            configuration.setDirectoryForTemplateLoading(fd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configuration;
    }

}
