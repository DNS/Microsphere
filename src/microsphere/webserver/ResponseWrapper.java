
package microsphere.webserver;

import javax.servlet.http.HttpServletResponse;

import microsphere.Response;

class ResponseWrapper extends Response {

    private Response delegate;

    public void setDelegate(Response delegate) {
        this.delegate = delegate;
    }

    @Override
    public void status(int statusCode) {
        delegate.status(statusCode);
    }

    @Override
    public void body(String body) {
        delegate.body(body);
    }

    @Override
    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public HttpServletResponse raw() {
        return delegate.raw();
    }

    @Override
    public void redirect(String location) {
        delegate.redirect(location);
    }

    @Override
    public void redirect(String location, int httpStatusCode) {
        delegate.redirect(location, httpStatusCode);
    }

    @Override
    public void header(String header, String value) {
        delegate.header(header, value);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public void type(String contentType) {
        delegate.type(contentType);
    }

    @Override
    public void cookie(String name, String value) {
        delegate.cookie(name, value);
    }

    @Override
    public void cookie(String name, String value, int maxAge) {
        delegate.cookie(name, value, maxAge);
    }

    @Override
    public void cookie(String name, String value, int maxAge, boolean secured) {
        delegate.cookie(name, value, maxAge, secured);
    }

    @Override
    public void cookie(String path, String name, String value, int maxAge, boolean secured) {
        delegate.cookie(path, name, value, maxAge, secured);
    }

    @Override
    public void removeCookie(String name) {
        delegate.removeCookie(name);
    }
}
