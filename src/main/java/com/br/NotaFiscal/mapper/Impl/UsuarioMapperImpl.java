/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Usuario;
import com.br.NotaFiscal.model.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */
@Component
public class UsuarioMapperImpl implements CustomObjectMapper<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO converterParaDto(Usuario entity) {

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNomeCompleto(entity.getNomeCompleto());
        dto.setEmail(entity.getEmail());
        dto.setSenha(entity.getSenha());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Usuario converterParaEntidade(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setEmail(dto.getNomeCompleto());
        usuario.setSenha(dto.getSenha());
        usuario.setDataCriacao(dto.getDataCriacao());
        usuario.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return usuario;
    }

    @Override
    public List<UsuarioDTO> converterParaListaDeDtos(List<Usuario> entityList) {

        List<UsuarioDTO> lista = new ArrayList<>();
        for (Usuario entity : entityList) {
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
}
