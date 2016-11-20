package io.vertx.example;


import com.google.common.base.Strings;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Optional;

@Data
@ConfigurationProperties("app")
public class AppProperties {
    private String webroot;

    public String getWebroot() {
        return Optional.ofNullable(webroot)
                .map(Strings::emptyToNull)
                .map(String::trim)
                .orElse("webroot");
    }
}
