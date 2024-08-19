/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.NotaFiscal.service.impl;

import com.br.NotaFiscal.mapper.CustomObjectMapper;
import com.br.NotaFiscal.model.Conta;
import com.br.NotaFiscal.model.dto.ContaDTO;
import com.br.NotaFiscal.repository.ContaRepository;
import com.br.NotaFiscal.service.ContaService;
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
public class ContaServiceImpl implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private CustomObjectMapper<Conta, ContaDTO> contaMapper;

    @Override
    public ResponseEntity<Object> mudarStatus(Long idObjeto) throws Exception {

        Conta objeto = contaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A Conta com ID " + idObjeto + " não foi encontrada!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUltimaAtualizacao(LocalDateTime.now());
        Conta objetoAtualizado = contaRepository.saveAndFlush(objeto);

        //Converte o objeto atualizado para DTO
        ContaDTO objetoDTO = contaMapper.converterParaDto(objetoAtualizado);

        //Retorna a resposta com o objeto atualizado
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

    @Override
    public ResponseEntity<Object> cadastrar(ContaDTO objeto) throws Exception {

        if (contaRepository.existsByNumero(objeto.getNumero())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a Conta. Já existe outra Conta com o mesmo numero."));
        }

        Conta conta = new Conta();
        conta.setId(objeto.getId());
        conta.setNumero(objeto.getNumero());
        conta.setNome(objeto.getNome());
        conta.setValor(objeto.getValor());
        conta.setValorImposto(objeto.getValorImposto());
        conta.setStatus(objeto.isStatus());
        conta.setEmpresa(objeto.getEmpresa());
        conta.setFornecedor(objeto.getFornecedor());
        conta.setFrequenciaConta(objeto.getFrequenciaConta());
        conta.setTipoConta(objeto.getTipoConta());
        conta.setGrupo(objeto.getGrupo());

        Conta objetoCriado = contaRepository.saveAndFlush(conta);
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

        List<ContaDTO> contaDTOs = contaMapper.converterParaListaDeDtos(contaRepository.findAll());
        if (contaDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Conta cadastradas no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(contaDTOs));
    }

    @Override
    public ResponseEntity<Object> editar(Long idObjeto, ContaDTO objeto) throws Exception {

        Conta dadosDto = contaMapper.converterParaEntidade(objeto);
        Conta paraEditar = contaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A Conta com ID " + idObjeto + "não foi encontrada!"));

        if (contaRepository.existsByNumero(objeto.getNumero())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a Conta. Já existe outra Conta com o mesmo numero."));
        }

        LocalDateTime dataHora = paraEditar.getDataCriacao();

        dadosDto.setDataCriacao(dataHora);
        dadosDto.setUltimaAtualizacao(LocalDateTime.now());
        dadosDto.setId(idObjeto);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Conta objetoAtualizado = contaRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(contaMapper.converterParaDto(objetoAtualizado)));

    }

    @Override
    public ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception {

        Conta conta = contaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A Conta com ID " + idObjeto + " não foi encontrada!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(contaMapper.converterParaDto(conta)));

    }

    @Override
    public ResponseEntity<Object> excluir(Long idObjeto) throws Exception {

        contaRepository.findById(idObjeto)
                .orElseThrow(() -> new NoSuchElementException("A Conta com ID " + idObjeto + " não foi encontrada!"));

        contaRepository.deleteById(idObjeto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("A Conta foi excluída com sucesso."));
    }

}
