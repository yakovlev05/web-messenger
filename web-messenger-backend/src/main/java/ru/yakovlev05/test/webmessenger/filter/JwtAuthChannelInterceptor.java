package ru.yakovlev05.test.webmessenger.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.yakovlev05.test.webmessenger.service.auth.JwtService;

// Именно авторизацию в вебсокетах изучал у него
@Component
@RequiredArgsConstructor
public class JwtAuthChannelInterceptor implements ChannelInterceptor {
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String HEADER_NAME = "Authorization";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String authHeader = accessor.getFirstNativeHeader(HEADER_NAME);
        if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            String jwtToken = authHeader.substring(BEARER_PREFIX.length());

            if (jwtService.validateToken(jwtToken)) {
                String username = jwtService.extractUsername(jwtToken);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);

                    accessor.setUser(authToken);
                }
            }
        }

        return message;
    }
}
