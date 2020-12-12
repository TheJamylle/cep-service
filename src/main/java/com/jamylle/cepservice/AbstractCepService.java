/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamylle.cepservice;

import com.jamylle.model.Endereco;
import com.jamylle.cepservice.CepService;
import com.sun.istack.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


public abstract class AbstractCepService implements CepService {
    private static final Logger LOG = Logger.getLogger(AbstractCepService.class);
    private String dominio;
    private final WebTarget target;

    public AbstractCepService(String dominio) {
        this.dominio = insertTrailingSlash(dominio);
        Client client = ClientBuilder.newClient();
        this.target = client.target(dominio);
    }
    
    protected final String insertTrailingSlash(String path){
        return path.endsWith("/") ? path : path + "/";
    }
    
    protected abstract String buildPath(String cep);
    
    protected String getFullPath(String cep){
        return this.dominio + buildPath(cep);
    }

    @Override
    public Endereco buscaEndereco(String cep) {
        LOG.info(String.format("Buscando endereço para o CEP %s usando o serviço %s", cep, dominio));
        return target.path(buildPath(cep)).request().get(Endereco.class);
    }
    
}
