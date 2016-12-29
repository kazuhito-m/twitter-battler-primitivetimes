package com.github.kazuhito_m.twitterbattler.primitive.infrastructure.security

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.savedrequest.NullRequestCache

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @throws[Exception]
  override protected def configure(http: HttpSecurity) {

    http.formLogin().loginPage("/login.html").permitAll
      .and.logout.clearAuthentication(true)

    def h2 = http
      .authorizeRequests
      .antMatchers("/api/**").authenticated
      .antMatchers("/").authenticated
      .antMatchers("/**.html").authenticated
      .antMatchers("/api/session").permitAll
      .antMatchers("/login.html").permitAll
      .and.asInstanceOf[HttpSecurity]

    h2.headers().frameOptions().disable() // for h2
      .and
      .requestCache
      .requestCache(new NullRequestCache)
      .and
      .sessionManagement
      .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
      .and.csrf.disable

  }
}
