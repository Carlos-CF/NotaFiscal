/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Solicitacao;
import com.br.NotaFiscal.model.dto.SolicitacaoDTO;
import com.br.NotaFiscal.repository.SolicitacaoRepository;
import com.br.NotaFiscal.service.SolicitacaoService;
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
public class SolicitacaoServiceImpl implements SolicitacaoService{

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;
    
    @Autowired
    private CustomObjectMapper<Solicitacao, SolicitacaoDTO> solicitacaoMapper;
    
    @Override
    public ResponseEntity<Object> cadastrar(SolicitacaoDTO objeto) throws Exception {
        
        if(solicitacaoRepository.existsByNumeroAndEmpresa(objeto.getNumero(), objeto.getEmpresa())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar esse numero de Solicitação. Já existe esse numero de Solicitação na mesma empresa"));
        }
        
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setNumero(objeto.getNumero());
        solicitacao.setValor(objeto.getValor());
        solicitacao.setDataEmissao(objeto.getDataEmissao());
        solicitacao.setDataVencimento(objeto.getDataVencimento());
        solicitacao.setDataEntrega(objeto.getDataEntrega());
        solicitacao.setEmpresa(objeto.getEmpresa());
        solicitacao.setTipoSolicitacao(objeto.getTipoSolicitacao());
        BeanUtils.copyProperties(objeto, "id");
        Solicitacao objetoCriado = solicitacaoRepository.saveAndFlush(solicitacao);
        
     
        
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(objetoCriado.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<Object> listar() throws Exception {
      
        List<Solicitacao> solicitacao = solicitacaoRepository.findAll();
        if(solicitacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Solicitações cadastras no sistema"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(solicitacaoMapper.converterParaListaDeDtos(solicitacao)));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, SolicitacaoDTO objeto) throws Exception {
        
        Solicitacao dadosDto = solicitacaoMapper.converterParaEntidade(objeto);
        Solicitacao paraEditar = solicitacaoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("Solicitação com ID " + idObjeto + " não foi encontrada!"));
        
        Long idUsuarioEditado = objeto.getId();
        
        if(solicitacaoRepository.existsByNumeroAndEmpresa(objeto.getNumero(), objeto.getEmpresa())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel editar esse numero de Solicitação. Já existe esse numero de Solicitação na mesma empresa"));
        }
        
        LocalDateTime dataHora = paraEditar.getDataCriacao();
        
        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Solicitacao objetoAtualizado = solicitacaoRepository.saveAndFlush(dadosDto);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(solicitacaoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
        
        Solicitacao solicitacao = solicitacaoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("A Solicitação com ID " + idObjeto + " não encontrada!"));
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(solicitacaoMapper.converterParaDto(solicitacao)));
        
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
        
        solicitacaoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("Solicitação com ID " + idObjeto + " não encontrada!"));
        
        solicitacaoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Solicitação foi excluído com sucesso"));
    }
    
}
