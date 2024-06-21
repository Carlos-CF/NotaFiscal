/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.SubGrupo;
import com.br.NotaFiscal.model.dto.SubGrupoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */

@Component
public class SubGrupoMapperImpl implements CustomObjectMapper<SubGrupo, SubGrupoDTO>{

    @Override
    public SubGrupoDTO converterParaDto(SubGrupo entity) {
        
        SubGrupoDTO dto = new SubGrupoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        
        return dto;
    }

    @Override
    public SubGrupo converterParaEntidade(SubGrupoDTO dto) {
        
        SubGrupo subGrupo = new SubGrupo();
        subGrupo.setId(dto.getId());
        subGrupo.setNome(dto.getNome());
        subGrupo.setStatus(dto.isStatus());
        subGrupo.setDataCriacao(dto.getDataCriacao());
        subGrupo.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return subGrupo;
    }

    @Override
    public List<SubGrupoDTO> converterParaListaDeDtos(List<SubGrupo> entityList) {
        
        List<SubGrupoDTO> lista = new ArrayList<>();
        for(SubGrupo entity : entityList){
            lista.add(converterParaDto(entity));
        }
        
        return lista;
    }
    
}
