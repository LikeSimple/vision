package org.vision.service.admin.configuration.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenProperties {

    @NotNull
    private String secret;

    @NotNull
    private Long expiration;

    @NotNull
    private String header;
}
