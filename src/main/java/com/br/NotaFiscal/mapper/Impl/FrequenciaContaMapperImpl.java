/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.FrequenciaConta;
import com.br.NotaFiscal.model.dto.FrequenciaContaDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */
@Component 
public class FrequenciaContaMapperImpl implements CustomObjectMapper<FrequenciaConta, FrequenciaContaDTO>{

    @Override
    public FrequenciaContaDTO converterParaDto(FrequenciaConta entity) {
        
        FrequenciaContaDTO dto = new FrequenciaContaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        
        return dto;
    }

    @Override
    public FrequenciaConta converterParaEntidade(FrequenciaContaDTO dto) {
       
        FrequenciaConta frequenciaConta = new FrequenciaConta();
        frequenciaConta.setId(dto.getId());
        frequenciaConta.setNome(dto.getNome());
        frequenciaConta.setStatus(dto.isStatus());
        frequenciaConta.setDataCriacao(dto.getDataCriacao());
        frequenciaConta.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return frequenciaConta;
    }

    @Override
    public List<FrequenciaContaDTO> converterParaListaDeDtos(List<FrequenciaConta> entityList) {
        
        List<FrequenciaContaDTO> lista = new ArrayList<>(); 
        for(FrequenciaConta entity : entityList){
            lista.add(converterParaDto(entity));
        }
        return lista;
    }
    
}
