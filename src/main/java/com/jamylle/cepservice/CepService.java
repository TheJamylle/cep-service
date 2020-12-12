/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamylle.cepservice;

import com.jamylle.model.Endereco;

/**
 *
 * @author Jamylle
 */
public interface CepService {
    Endereco buscaEndereco(String cep);
}
