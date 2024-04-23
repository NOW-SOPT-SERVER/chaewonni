package org.sopt.practice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //JPA가 엔티티를 감시할 수 있게 해줌
public class JpaAuditingConfig {

}
