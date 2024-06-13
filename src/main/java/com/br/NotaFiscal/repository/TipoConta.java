/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.NotaFiscal.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos.fernandes
 */

@Repository
public interface TipoConta extends JpaRepository<TipoConta, Long>{
    
    public boolean existsByNome(String nome);
    
    public Optional<TipoConta> findByNome(String nome);
}
