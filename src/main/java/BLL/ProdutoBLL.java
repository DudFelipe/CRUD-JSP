/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAO.ProdutoDAO;
import Modelos.Produto;
import java.math.BigDecimal;

/**
 *
 * @author Dud Felipe
 */
public class ProdutoBLL {
    public static void Inserir(Produto p) throws Exception{
        if(p.getNome().equals("") || p.getNome() == null){
            throw new Exception("O campo nome é obrigatório");
        }
        
        ProdutoDAO.Inserir(p);
    }
}
