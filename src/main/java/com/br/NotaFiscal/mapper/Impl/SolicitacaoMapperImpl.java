/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Solicitacao;
import com.br.NotaFiscal.model.dto.SolicitacaoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */
@Component
public class SolicitacaoMapperImpl implements CustomObjectMapper<Solicitacao, SolicitacaoDTO> {

    @Override
    public SolicitacaoDTO converterParaDto(Solicitacao entity) {

        SolicitacaoDTO dto = new SolicitacaoDTO();
        dto.setId(entity.getId());
        dto.setNumero(entity.getNumero());
        dto.setValor(entity.getValor());
        dto.setDataEmissao(entity.getDataEmissao());
        dto.setDataVencimento(entity.getDataVencimento());
        dto.setDataEntrega(entity.getDataEntrega());
        dto.setEmpresa(entity.getEmpresa());
        dto.setTipoSolicitacao(entity.getTipoSolicitacao());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public Solicitacao converterParaEntidade(SolicitacaoDTO dto) {

        Solicitacao solicitacao = new Solicitacao();

        solicitacao.setId(dto.getId());
        solicitacao.setNumero(dto.getNumero());
        solicitacao.setValor(dto.getValor());
        solicitacao.setDataEmissao(dto.getDataEmissao());
        solicitacao.setDataVencimento(dto.getDataVencimento());
        solicitacao.setDataEntrega(dto.getDataEntrega());
        solicitacao.setEmpresa(dto.getEmpresa());
        solicitacao.setTipoSolicitacao(dto.getTipoSolicitacao());
        solicitacao.setDataCriacao(dto.getDataCriacao());
        solicitacao.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return solicitacao;

    }

    @Override
    public List<SolicitacaoDTO> converterParaListaDeDtos(List<Solicitacao> entityList) {

        List<SolicitacaoDTO> lista = new ArrayList<>();
        for (Solicitacao entity : entityList) {
            lista.add(converterParaDto(entity));
        }

        return lista;
    }
}
