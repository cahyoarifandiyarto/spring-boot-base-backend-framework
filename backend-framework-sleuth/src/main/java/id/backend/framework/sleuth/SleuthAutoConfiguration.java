package id.backend.framework.sleuth;

import id.backend.framework.sleuth.tracer.TracerCustom;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class SleuthAutoConfiguration {

    @Bean
    public Filter traceIdInResponseHeader(Tracer tracer) {
        return new TracerCustom(tracer);
    }

}
