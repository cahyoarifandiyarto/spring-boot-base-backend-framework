package id.backend.framework.sleuth;

import id.backend.framework.sleuth.tracer.TracerCustom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.sleuth.Tracer;

import javax.servlet.Filter;

@SpringBootTest
public class SleuthAutoConfigurationTest {

    private Tracer tracer;

    private Filter filter;

    @Autowired
    public void setTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    @BeforeEach
    void init() {
        filter = new TracerCustom(tracer);
    }

    @Test
    void initTest() {
        Assertions.assertNotNull(tracer);
        Assertions.assertNotNull(filter);
    }

    @SpringBootApplication
    public static class Application {

    }

}
