/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Fornecedor;
import com.br.NotaFiscal.model.dto.FornecedorDTO;
import com.br.NotaFiscal.repository.FornecedorRepository;
import com.br.NotaFiscal.service.FornecedorService;
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
public class FornecedorServiceImpl implements FornecedorService{

    @Autowired
    private FornecedorRepository fornecedorRepository;
    
    @Autowired
    private CustomObjectMapper<Fornecedor, FornecedorDTO> fornecedorMapper;
    
    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {
        
        Fornecedor objeto = fornecedorRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Fornecedor com ID " + idObjeto + " Não foi encontrado!"));
        
        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Fornecedor objetoAtualizado = fornecedorRepository.saveAndFlush(objeto);
        
        FornecedorDTO objetoDTO = fornecedorMapper.converterParaDto(objetoAtualizado);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(FornecedorDTO objeto) throws Exception {
        
        if (fornecedorRepository.existsByNome(objeto.getNome())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Fornecedor. Já existe outro Fornecedor com o mesmo nome."));
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(objeto.getNome());
        fornecedor.setFantasia(objeto.getFantasia());
        fornecedor.setCNPJ(objeto.getCNPJ());
        fornecedor.setCodDatavale(objeto.getCodDatavale());
        fornecedor.setStatus(objeto.isStatus());

        Fornecedor objetoCriado = fornecedorRepository.saveAndFlush(fornecedor);
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
        
        List<FornecedorDTO> fornecedorDTOs = fornecedorMapper.converterParaListaDeDtos(fornecedorRepository.findAll());
        if(fornecedorDTOs.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Fornecedor cadastrados no Sistemas"));
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(fornecedorDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, FornecedorDTO objeto) throws Exception {
        
        Fornecedor dadosDto = fornecedorMapper.converterParaEntidade(objeto);
        Fornecedor paraEditar = fornecedorRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("O Fornecedor com ID " + idObjeto + " não foi encontrada!"));
        
        LocalDateTime dataHora = paraEditar.getDataCriacao();
        
        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Fornecedor objetoAtualizado = fornecedorRepository.saveAndFlush(dadosDto);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(fornecedorMapper.converterParaDto(objetoAtualizado)));
        
    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {
         
        Fornecedor fornecedor = fornecedorRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("Fornecedor com ID " + idObjeto + " não encontrado!"));
        
         return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(fornecedorMapper.converterParaDto(fornecedor)));
    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {
       
        fornecedorRepository.findById(idObjeto)
                .orElseThrow(()-> new NoSuchElementException("O Fornecedor com ID " + idObjeto + " não foi encontrado!"));
        
        fornecedorRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("O Fornecedor foi excluído com sucesso."));
    }
    
}
