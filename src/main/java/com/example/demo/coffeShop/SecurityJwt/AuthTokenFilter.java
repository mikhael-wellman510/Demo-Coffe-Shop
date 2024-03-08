package com.example.demo.coffeShop.SecurityJwt;

import com.example.demo.coffeShop.Service.JwtService.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private static final Logger logger = Logger.getLogger(AuthTokenFilter.class.getName());

    // Todo -> ini untuk cek bearer token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String headerAuth = request.getHeader("Authorization");
            String token = null;

            if (headerAuth != null && headerAuth.startsWith("Bearer ")){
                token = headerAuth.substring(7);
            }
        // Todo -> verify token , apakah true di jwt Util ?


            if (token != null && jwtUtil.verifyJwtToken(token)) {


                Map<String, String> userInfo = jwtUtil.getUsherInfoByToken(token);


                //Todo -> ini untuk cek Error
                logger.info("User info: " + userInfo);

                //Todo -> jgn sampai salah ini get nya
                UserDetails userDetails = userService.loadUserByUserId(userInfo.get("userId"));
                // ===== optional kalau error


                //Todo -> ini untuk cek Error
                String userId = userInfo.get("userId");
                logger.info("User IDs: " + userId);
                // ===== optional kalau error

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }catch (Exception e){

            logger.severe("Error processing request: " + e.getMessage());
        }

        filterChain.doFilter(request,response);
    }
}
