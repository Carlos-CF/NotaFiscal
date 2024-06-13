/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.NotaFiscal.service.util;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author carlos.fernandes
 */
public interface CrudService<T> {
    
    ResponseEntity<Object> cadastrar(T objeto) throws Exception;
    
    ResponseEntity<Object> listar() throws Exception;
    
    ResponseEntity<Object> editar(Long idObjeto, T objeto) throws Exception;
    
    ResponseEntity<Object> listarPorId(Long idObjeto) throws Exception;
    
    ResponseEntity<Object> excluir(Long idObjeto) throws Exception;
}
