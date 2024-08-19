/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Grupo;
import com.br.NotaFiscal.model.dto.GrupoDTO;
import com.br.NotaFiscal.repository.GrupoRepository;
import com.br.NotaFiscal.service.GrupoService;
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
public class GrupoServiceImpl implements GrupoService{
    
    @Autowired
    private GrupoRepository grupoRepository;
    
    @Autowired
    private CustomObjectMapper<Grupo, GrupoDTO> grupoMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {
       
        Grupo objeto = grupoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("Grupo com ID " + idObjeto + " não encontrado!"));
        
        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Grupo objetoAtualizado = grupoRepository.saveAndFlush(objeto);
        
        GrupoDTO objetoDTO = grupoMapper.converterParaDto(objetoAtualizado);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(GrupoDTO objeto) throws Exception {
        
         if (grupoRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Grupo. Já existe outro Tipo Lançamento com o mesmo nome."));
         }
         
            Grupo grupo = new Grupo();
            grupo.setNome(objeto.getNome());
            grupo.setStatus(objeto.isStatus());
            BeanUtils.copyProperties(objeto,"id");
            Grupo objetoCriado = grupoRepository.saveAndFlush(grupo);
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
       
        List<Grupo> grupo = grupoRepository.findAll();
        if(grupo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Grupos cadastrados no Sistemas"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(grupoMapper.converterParaListaDeDtos(grupo)));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, GrupoDTO objeto) throws Exception {
        
        Grupo dadosDto = grupoMapper.converterParaEntidade(objeto);
        Grupo paraEditar = grupoRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Grupo com ID " + idObjeto + " não foi encontrada!"));
        
        LocalDateTime dataHora = paraEditar.getDataCriacao();
        
        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Grupo objetoAtualizado = grupoRepository.saveAndFlush(dadosDto);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(grupoMapper.converterParaDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
       
        Grupo grupo = grupoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("Grupo com ID " + idObjeto + " não encontrado!"));
        
         return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(grupoMapper.converterParaDto(grupo)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
       
        grupoRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("Grupo com ID " + idObjeto + " não encontrado!"));
        
        grupoRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Grupo foi excluído com sucesso."));        
    }
    
    
}
