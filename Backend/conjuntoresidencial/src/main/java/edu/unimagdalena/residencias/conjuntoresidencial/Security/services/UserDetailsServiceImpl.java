package edu.unimagdalena.residencias.conjuntoresidencial.Security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unimagdalena.residencias.conjuntoresidencial.entities.Usuario;
import edu.unimagdalena.residencias.conjuntoresidencial.Repositories.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
	UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(usuario);
    }
}
