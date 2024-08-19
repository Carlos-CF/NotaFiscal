/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Conta;
import com.br.NotaFiscal.model.dto.ContaDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */
@Component
public class ContaMapperImpl implements CustomObjectMapper<Conta, ContaDTO> {

    @Override
    public ContaDTO converterParaDto(Conta entity) {

        ContaDTO dto = new ContaDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setNome(entity.getNome());
        dto.setValor(entity.getValor());
        dto.setValorImposto(entity.getValorImposto());
        dto.setStatus(entity.isStatus());
        dto.setEmpresa(entity.getEmpresa());
        dto.setFornecedor(entity.getFornecedor());
        dto.setTipoConta(entity.getTipoConta());
        dto.setFrequenciaConta(entity.getFrequenciaConta());
        dto.setGrupo(entity.getGrupo());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Conta converterParaEntidade(ContaDTO dto) {

        Conta conta = new Conta();
        conta.setId(dto.getId());
        conta.setNumero(dto.getNumero());
        conta.setNome(dto.getNome());
        conta.setValor(dto.getValor());
        conta.setValorImposto(dto.getValorImposto());
        conta.setStatus(dto.isStatus());
        conta.setEmpresa(dto.getEmpresa());
        conta.setFornecedor(dto.getFornecedor());
        conta.setTipoConta(dto.getTipoConta());
        conta.setFrequenciaConta(dto.getFrequenciaConta());
        conta.setGrupo(dto.getGrupo());
        conta.setDataCriacao(dto.getDataCriacao());
        conta.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return conta;
    }

    @Override
    public List<ContaDTO> converterParaListaDeDtos(List<Conta> entityList) {

        List<ContaDTO> lista = new ArrayList<>();
        for (Conta entity : entityList) {
            lista.add(converterParaDto(entity));
        }

        return lista;
    }

}
