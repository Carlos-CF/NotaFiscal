/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Empresa;
import com.br.NotaFiscal.model.dto.EmpresaDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */

@Component
public class EmpresaMapperImpl implements CustomObjectMapper<Empresa, EmpresaDTO>{

    @Override
    public EmpresaDTO converterParaDto(Empresa entity) {
       
        EmpresaDTO dto = new EmpresaDTO(); 
        dto.setId(entity.getId());
        dto.setCodigo(entity.getCodigo());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        
        return dto;
        
    }

    @Override
    public Empresa converterParaEntidade(EmpresaDTO dto) {
        
        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setCodigo(dto.getCodigo());
        empresa.setNome(dto.getNome());
        empresa.setStatus(dto.isStatus());
        empresa.setDataCriacao(dto.getDataCriacao());
        empresa.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        
        return empresa;
    }

    @Override
    public List<EmpresaDTO> converterParaListaDeDtos(List<Empresa> entityList) {
       
        List<EmpresaDTO> lista = new ArrayList<>();
        for(Empresa entity : entityList){
            lista.add(converterParaDto(entity));
        }
        
        return lista;
    }
    
    
}
