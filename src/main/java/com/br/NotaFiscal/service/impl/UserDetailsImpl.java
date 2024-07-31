/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.model.Usuario;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author carlos.fernandes
 */
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String nomeCompleto;
    private String senha;
    private Collection<? extends GrantedAuthority> permissoes;

    public UserDetailsImpl(Long id, String nomeCompleto, String senha, Collection<? extends GrantedAuthority> permissoes) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.senha = senha;
        this.permissoes = permissoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nomeCompleto;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissoes;
    }
   
}
