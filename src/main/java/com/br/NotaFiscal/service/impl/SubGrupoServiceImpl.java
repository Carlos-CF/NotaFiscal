/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.SubGrupo;
import com.br.NotaFiscal.model.dto.SubGrupoDTO;
import com.br.NotaFiscal.repository.SubGrupoRepository;
import com.br.NotaFiscal.service.SubGrupoService;
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
public class SubGrupoServiceImpl implements SubGrupoService{

     @Autowired
     private SubGrupoRepository subGrupoRepository;
     
     @Autowired
     private CustomObjectMapper<SubGrupo, SubGrupoDTO> subGrupoMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {
        
        SubGrupo objeto = subGrupoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O subGrupo com ID " + idObjeto + " não foi encontrado!"));
        
        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        SubGrupo objetoAtualizado = subGrupoRepository.saveAndFlush(objeto);
        
        SubGrupoDTO objetoDTO = subGrupoMapper.converterParaDto(objetoAtualizado);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(SubGrupoDTO objeto) throws Exception {
        
        if(subGrupoRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o SubGrupo. Já existe outro SubGrupo com o mesmo nome."));
        }
        
        SubGrupo subGrupo = new SubGrupo();
        subGrupo.setNome(objeto.getNome());
        subGrupo.setStatus(objeto.isStatus());
        subGrupo.setGrupo(objeto.getGrupo());
        
        SubGrupo objetoCriado = subGrupoRepository.saveAndFlush(subGrupo);
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
        
        List<SubGrupoDTO> subGrupoDTOs = subGrupoMapper.converterParaListaDeDtos(subGrupoRepository.findAll());
        if(subGrupoDTOs.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe SubGrupo cadastrados no Sistemas"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(subGrupoDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, SubGrupoDTO objeto) throws Exception {
       
        SubGrupo dadosDto = subGrupoMapper.converterParaEntidade(objeto);
        SubGrupo paraEditar = subGrupoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O SubGrupo com ID " + idObjeto + "não foi encontrada!"));
        
        if(subGrupoRepository.existsByNome(objeto.getNome())){
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o SubGrupo. Já existe outro SubGrupo com o mesmo nome."));
        }
        
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        SubGrupo objetoAtualizado = subGrupoRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(subGrupoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
        
        SubGrupo subGrupo = subGrupoRepository.findById(idObjeto)
                 .orElseThrow(()-> new NoSuchElementException("O SubGrupo com ID " + idObjeto + " não foi encontrada!"));
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(subGrupoMapper.converterParaDto(subGrupo)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
       
        subGrupoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O SubGrupo com o ID " + idObjeto + " não foi encontrada!"));
        
        subGrupoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O SubGrupo foi excluído com sucesso."));
    }
    
   
}
