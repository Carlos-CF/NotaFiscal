/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.NotaFiscal.repository;

import com.br.NotaFiscal.model.Conta;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlos.fernandes
 */
@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
    
    public boolean existsByNumero(Long numero);
    
    public Optional<Conta> findByNome(String nome);
}
