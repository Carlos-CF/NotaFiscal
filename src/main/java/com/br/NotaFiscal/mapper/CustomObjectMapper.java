/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.NotaFiscal.mapper;

import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author carlos.fernandes
 */

@Mapper(componentModel = "spring")
public interface CustomObjectMapper<T, R> {
    
    R converterParaDto(T entity);
    
    T converterParaEntidade(R dto);
    
    List<R> converterParaListaDeDtos(List<T> entityList);
}
