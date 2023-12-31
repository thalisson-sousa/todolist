package br.com.thalissonsousa.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.thalissonsousa.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {
            // Peegar a autenticação (usuario e senha)
            var authorization = request.getHeader("Authorization");

            // Removendo a palavra basic do inicio da requisição Base64
            var authEncoded = authorization.substring("Basic".length()).trim();

            // Decodando a requisição
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            // Convertendo em String
            var authString = new String(authDecode);

            // Transformando em um Array de String e separando em variaveis diferentes
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // validar usuario
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                // validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    // Segue viagem
                    request.setAttribute("idUser", user.getId()); //Adicionando o idUser na request e passando para a controler
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }

}
