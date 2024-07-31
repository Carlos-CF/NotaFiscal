/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.config;

import com.br.NotaFiscal.model.Usuario;
import com.br.NotaFiscal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author carlos.fernandes
 */

@Configuration
public class InitialConfig {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @EventListener
    public void appReady(ApplicationReadyEvent event) throws Exception {
    
        Usuario usuario = new Usuario();
        
        usuario.setNomeCompleto("Carlos Fernandes");
        usuario.setEmail("carlos.fernandes@frigoestrela.com.br");
        usuario.setSenha(passwordEncoder.encode("12345678"));
        
        usuarioRepository.save(usuario);
    }
    
}
