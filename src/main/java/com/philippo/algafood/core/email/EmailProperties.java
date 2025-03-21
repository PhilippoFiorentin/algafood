package com.philippo.algafood.core.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Validated
@Getter
@Setter
@Component
@ConfigurationProperties("algafood.mail")
public class EmailProperties {

    @NotNull
    private String sender;

    private Sandbox sandbox = new Sandbox();

    private Impl impl = Impl.FAKE;

    public enum Impl {
        SMTP, FAKE, SANDBOX
    }

    @Getter
    @Setter
    public class Sandbox {
        @NotNull
        private String recipient;
    }

}
