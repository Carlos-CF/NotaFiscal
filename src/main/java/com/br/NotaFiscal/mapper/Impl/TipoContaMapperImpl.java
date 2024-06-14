/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.TipoConta;
import com.br.NotaFiscal.model.dto.TipoContaDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */

@Component
public class TipoContaMapperImpl implements CustomObjectMapper<TipoConta, TipoContaDTO>{

    @Override
    public TipoContaDTO converterParaDto(TipoConta entity) {
        
        TipoContaDTO dto = new TipoContaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        
        return dto;
    }

    @Override
    public TipoConta converterParaEntidade(TipoContaDTO dto) {
        
        TipoConta tipoConta = new TipoConta();
        tipoConta.setId(dto.getId());
        tipoConta.setNome(dto.getNome());
        tipoConta.setStatus(dto.isStatus());
        tipoConta.setDataCriacao(dto.getDataCriacao());
        tipoConta.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return tipoConta;
    }

    @Override
    public List<TipoContaDTO> converterParaListaDeDtos(List<TipoConta> entityList) {
        
        List<TipoContaDTO> lista = new ArrayList<>();
        for(TipoConta entity : entityList){
            lista.add(converterParaDto(entity));
        }
        
        return lista;
    }
    
  
}
