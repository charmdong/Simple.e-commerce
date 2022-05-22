package com.commerce;

import com.commerce.dto.SessionVO;
import com.commerce.util.SessionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class CommerceApplication {

    public static void main (String[] args) {
        SpringApplication.run(CommerceApplication.class, args);
    }

    /**
     * LastModifiedBy 어노테이션이 붙은 필드를 가지고 있는 엔티티가 수정되는 경우 호출됨
     *
     * @return 사용자 ID
     */
    @Bean
    public AuditorAware<String> auditorProvider () {

        return () -> {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            SessionVO loginInfo = (SessionVO) attr.getRequest().getSession().getAttribute(SessionUtils.LOGIN_SESSION);
            String loginId = loginInfo.getId();

            if (StringUtils.hasText(loginId)) {
                return Optional.of(loginId);
            }
            return Optional.of("Anonymous");
        };
    }
}
