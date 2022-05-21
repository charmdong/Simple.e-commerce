package com.commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@EnableJpaAuditing
@SpringBootApplication
public class CommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceApplication.class, args);
	}

	/**
	 * LastModifiedBy 어노테이션이 붙은 필드를 가지고 있는 엔티티가 수정되는 경우 호출됨
	 * @return 사용자 ID
	 */
	@Bean
	public AuditorAware<String> auditorProvider() {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String loginId = (String) session.getAttribute("loginId");

		return () -> Optional.of(loginId);
	}
}
