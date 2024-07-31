/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.model.Usuario;
import com.br.NotaFiscal.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author carlos.fernandes
 */
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário foi encontrado com esse email!"));
       // Obtenha as permissões do usuário
        List<GrantedAuthority> authorities = getAuthorities(usuario);
        
        return new UserDetailsImpl(usuario.getId(), usuario.getNomeCompleto(), usuario.getSenha(), authorities);
    }
    
    private List<GrantedAuthority> getAuthorities(Usuario usuario) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        // Adicione as permissões do usuário aqui
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        
        // Adicione outras permissões conforme necessário
        
        return authorities;
    }
    
}
