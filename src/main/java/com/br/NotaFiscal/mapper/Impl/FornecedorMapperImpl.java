/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Fornecedor;
import com.br.NotaFiscal.model.dto.FornecedorDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */
@Component
public class FornecedorMapperImpl implements CustomObjectMapper<Fornecedor, FornecedorDTO> {

    @Override
    public FornecedorDTO converterParaDto(Fornecedor entity) {

        FornecedorDTO dto = new FornecedorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setFantasia(entity.getFantasia());
        dto.setCNPJ(entity.getCNPJ());
        dto.setCodDatavale(entity.getCodDatavale());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;

    }

    @Override
    public Fornecedor converterParaEntidade(FornecedorDTO dto) {

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(dto.getId());
        fornecedor.setNome(dto.getNome());
        fornecedor.setFantasia(dto.getFantasia());
        fornecedor.setCNPJ(dto.getCNPJ());
        fornecedor.setCodDatavale(dto.getCodDatavale());
        fornecedor.setStatus(dto.isStatus());
        fornecedor.setDataCriacao(dto.getDataCriacao());
        fornecedor.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return fornecedor;
    }

    @Override
    public List<FornecedorDTO> converterParaListaDeDtos(List<Fornecedor> entityList) {

        List<FornecedorDTO> lista = new ArrayList<>();
        for (Fornecedor entity : entityList) {
            lista.add(converterParaDto(entity));
        }
        return lista;
    }

}
