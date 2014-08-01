
package microsphere.webserver;

/**
 *
 *
 */
public interface MicrosphereServer {

    /**
     * Ignites the microsphere server, listening on the specified port, running SSL secured with the specified keystore
     * and truststore.  If truststore is null, keystore is reused.
     *
     * @param host The address to listen on
     * @param port - the port
     * @param keystoreFile       - The keystore file location as string
     * @param keystorePassword   - the password for the keystore
     * @param truststoreFile     - the truststore file location as string, leave null to reuse keystore
     * @param truststorePassword - the trust store password
     * @param staticFilesRoute    - the route to static files in classPath
     * @param externalFilesLocation    - the route to static files external to classPath.
     */
    void ignite(
            String host,
            int port,
            String keystoreFile,
            String keystorePassword,
            String truststoreFile,
            String truststorePassword,
            String staticFilesRoute,
            String externalFilesLocation);

    /**
     * Stops the microsphere server
     */
	void stop();
}
