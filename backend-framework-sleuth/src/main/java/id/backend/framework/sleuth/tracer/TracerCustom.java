package id.backend.framework.sleuth.tracer;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TracerCustom implements Filter {

    private final Tracer tracer;

    public TracerCustom(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Span currentSpan = tracer.currentSpan();
        if (currentSpan != null) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.addHeader("traceId", currentSpan.context().traceId());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
