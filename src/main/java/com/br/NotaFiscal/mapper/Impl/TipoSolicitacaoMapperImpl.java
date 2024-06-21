/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.mapper.Impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.TipoSolicitacao;
import com.br.NotaFiscal.model.dto.TipoSolicitacaoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author carlos.fernandes
 */
@Component
public class TipoSolicitacaoMapperImpl implements CustomObjectMapper<TipoSolicitacao, TipoSolicitacaoDTO> {

    @Override
    public TipoSolicitacaoDTO converterParaDto(TipoSolicitacao entity) {

        TipoSolicitacaoDTO dto = new TipoSolicitacaoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setStatus(entity.isStatus());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());

        return dto;
    }

    @Override
    public TipoSolicitacao converterParaEntidade(TipoSolicitacaoDTO dto) {

        TipoSolicitacao tipoSolicitacao = new TipoSolicitacao();
        tipoSolicitacao.setId(dto.getId());
        tipoSolicitacao.setNome(dto.getNome());
        tipoSolicitacao.setStatus(dto.isStatus());
        tipoSolicitacao.setDataCriacao(dto.getDataCriacao());
        tipoSolicitacao.setUltimaAtualizacao(dto.getUltimaAtualizacao());

        return tipoSolicitacao;
    }

    @Override
    public List<TipoSolicitacaoDTO> converterParaListaDeDtos(List<TipoSolicitacao> entityList) {

        List<TipoSolicitacaoDTO> lista = new ArrayList<>();
        for (TipoSolicitacao entity : entityList) {
            lista.add(converterParaDto(entity));
        }
        return lista;
    }

}
