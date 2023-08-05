package hiFes.hiFes.filter;



import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.repository.user.HostUserRepository;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.user.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {
    private static final String NO_CHECK_URL = "/login";

    private final JwtService jwtService;
    private final HostUserRepository hostUserRepository;
    private final NormalUserRepository normalUserRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService::isTokenValid)
                .orElse(null);

        if (refreshToken != null) {
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
            checkNormalRefreshTokenAndReIssueAccessToken(response, refreshToken);
            return;
        }
        if (refreshToken == null) {
            checkAccessTokenAndAuthentication(request, response, filterChain);
            checkNormalAccessTokenAndAuthentication(request, response, filterChain);
        }


    }
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        hostUserRepository.findByRefreshToken(refreshToken)
                .ifPresent(user -> {
                    String reIssuedRefreshToken = reIssueRefreshToken(user);
                    jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(user.getEmail()),
                            reIssuedRefreshToken);
                });
    }

    private String reIssueRefreshToken(HostUser hostUser) {
        String reIssuedRefreshToken = jwtService.createRefreshToken();
        hostUser.updateRefreshToken(reIssuedRefreshToken);
        hostUserRepository.saveAndFlush(hostUser);
        return reIssuedRefreshToken;
    }

    public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                  FilterChain filterChain) throws ServletException, IOException {
        log.info("checkAccessTokenAndAuthentication call ");
        jwtService.extractAccessToken(request)
                .filter(jwtService::isTokenValid)
                .ifPresent(accessToken -> jwtService.extractEmail(accessToken)
                        .ifPresent(email -> hostUserRepository.findByEmail(email)
                                .ifPresent(this::saveAuthentication)));

        filterChain.doFilter(request, response);
    }

    public void saveAuthentication(HostUser myHostuser) {
        UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder()
                .username(myHostuser.getEmail())
                .build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetailsUser, null,
                        authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void checkNormalRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        normalUserRepository.findByRefreshToken(refreshToken)
                .ifPresent(user -> {
                    String reIssueNormalRefreshToken = reIssueNormalRefreshToken(user);
                    jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(user.getEmail()),
                            reIssueNormalRefreshToken);
                });
    }

    private String reIssueNormalRefreshToken(NormalUser normalUser) {
        String reIssueNormalRefreshToken = jwtService.createRefreshToken();
        normalUser.updateRefreshToken(reIssueNormalRefreshToken);
        normalUserRepository.saveAndFlush(normalUser);
        return reIssueNormalRefreshToken;
    }

    public void checkNormalAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                  FilterChain filterChain) throws ServletException, IOException {
        log.info("checkNormalAccessTokenAndAuthentication call ");
        jwtService.extractAccessToken(request)
                .filter(jwtService::isTokenValid)
                .ifPresent(accessToken -> jwtService.extractEmail(accessToken)
                        .ifPresent(email -> normalUserRepository.findByEmail(email)
                                .ifPresent(this::saveNormalAuthentication)));

        filterChain.doFilter(request, response);
    }

    public void saveNormalAuthentication(NormalUser myNormaluser) {
        UserDetails userDetailsUser = org.springframework.security.core.userdetails.User.builder()
                .username(myNormaluser.getEmail())
                .build();

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetailsUser, null,
                        authoritiesMapper.mapAuthorities(userDetailsUser.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }



}
