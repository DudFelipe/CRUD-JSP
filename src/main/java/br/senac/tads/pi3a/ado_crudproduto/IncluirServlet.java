/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3a.ado_crudproduto;

import BLL.ProdutoBLL;
import Modelos.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dud Felipe
 */
@WebServlet(name = "IncluirServlet", urlPatterns = {"/produto/incluir"})
public class IncluirServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Produto p = ProdutoBLL.obterProduto(id);
        
        request.setAttribute("nome", p.getNome());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/produto/cadastro.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        Produto p = new Produto();
        
        p.setNome(request.getParameter("nome"));
        p.setDescricao(request.getParameter("descricao"));
        p.setPrecoCompra(BigDecimal.valueOf(Double.parseDouble(request.getParameter("prcompra"))));
        p.setPrecoVenda(BigDecimal.valueOf(Double.parseDouble(request.getParameter("prvenda"))));
        p.setQtd(Integer.parseInt(request.getParameter("qtd")));
        p.setCats(request.getParameterValues("cat"));
        
        try{
            ProdutoBLL.Inserir(p);
            
        }
        catch(Exception ex){
            out.println(ex.getMessage());
        }
    }
}
