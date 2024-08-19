/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Grupo;
import com.br.NotaFiscal.model.dto.GrupoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */

@Component
public class GrupoMapperImpl implements CustomObjectMapper<Grupo, GrupoDTO>{

    @Override
    public GrupoDTO converterParaDto(Grupo entity) {
        
        GrupoDTO dto = new GrupoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        
        return dto;
    }

    @Override
    public Grupo converterParaEntidade(GrupoDTO dto) {
        
        Grupo grupo = new Grupo();
        grupo.setId(dto.getId());
        grupo.setNome(dto.getNome());
        grupo.setStatus(dto.isStatus());
        grupo.setDataCriacao(dto.getDataCriacao());
        grupo.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return grupo;
    }

    @Override
    public List<GrupoDTO> converterParaListaDeDtos(List<Grupo> entityList) {
       
        List<GrupoDTO> lista = new ArrayList<>();
        for(Grupo entity : entityList){
            lista.add(converterParaDto(entity));
        }
        
        return lista;
    }
    
}
