/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.TipoSolicitacao;
import com.br.NotaFiscal.model.dto.TipoSolicitacaoDTO;
import com.br.NotaFiscal.repository.TipoSolicitacaoRepository;
import com.br.NotaFiscal.service.TipoSolicitacaoService;
import com.br.NotaFiscal.service.util.ApiResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author carlos.fernandes
 */
@Service
public class TipoSolicitacaoServiceImpl implements TipoSolicitacaoService {

    @Autowired
    private TipoSolicitacaoRepository tipoSolicitacaoRepository;

    @Autowired
    private CustomObjectMapper<TipoSolicitacao, TipoSolicitacaoDTO> tipoSolicitacaoMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        TipoSolicitacao objeto = tipoSolicitacaoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("Tipo Solicitacao com ID " + idObjeto + " não encontrada!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        TipoSolicitacao objetoAtualizado = tipoSolicitacaoRepository.saveAndFlush(objeto);

        TipoSolicitacaoDTO objetoDTO = tipoSolicitacaoMapper.converterParaDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(TipoSolicitacaoDTO objeto) throws Exception {

        if (tipoSolicitacaoRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Solicitacao. Já existe outro Tipo Solicitacao com o mesmo nome."));
        }

        TipoSolicitacao tipoSolicitacao = new TipoSolicitacao();
        tipoSolicitacao.setNome(objeto.getNome());
        tipoSolicitacao.setStatus(objeto.isStatus());

        TipoSolicitacao objetoCriado = tipoSolicitacaoRepository.saveAndFlush(tipoSolicitacao);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/{id}")
                        .buildAndExpand(objetoCriado.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<Object> listar() throws Exception {
       
        List<TipoSolicitacaoDTO> tipoSolicitacaoDTOs = tipoSolicitacaoMapper.converterParaListaDeDtos(tipoSolicitacaoRepository.findAll());
        if(tipoSolicitacaoDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Tipo Solicitação cadastrados no Sistemas"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoSolicitacaoDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, TipoSolicitacaoDTO objeto) throws Exception {
        
        TipoSolicitacao dadosDto = tipoSolicitacaoMapper.converterParaEntidade(objeto);
        TipoSolicitacao paraEditar = tipoSolicitacaoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O TipoSolicitacao com ID " + idObjeto + " não foi encontrada!"));
        
        if(tipoSolicitacaoRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Tipo Solicitação, Já existe outro Tipo Solicitação com o mesmo nome."));
        }
        
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        TipoSolicitacao objetoAtualizado = tipoSolicitacaoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoSolicitacaoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
        
        TipoSolicitacao tipoSolicitacao = tipoSolicitacaoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Tipo Solicitacao com ID " + idObjeto + " não foi encontrada!"));
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(tipoSolicitacaoMapper.converterParaDto(tipoSolicitacao)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
        
        tipoSolicitacaoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Tipo Solicitacao com ID " + idObjeto + " não foi encontrada!"));
        
        tipoSolicitacaoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Tipo Solicitacao foi excluído com sucesso."));
    }

}
