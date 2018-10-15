/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Dud Felipe
 */
public class ProdutoDAO {
    public static void Inserir(Produto p){
        Connection conn = null;
        PreparedStatement pst = null;
        String url = "jdbc:mysql://localhost/PRODUTOBD";
        String sql = "INSERT INTO PRODUTO (Nome, Descricao, Preco_Compra, Preco_Venda, Quantidade, DT_Cadastro) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            //Propriedades para armazenamento de usuário e senha
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "");
            properties.put("serverTimezone", "UTC");
            
            conn = DriverManager.getConnection(url, properties);
            pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, p.getNome());
            pst.setString(2, p.getDescricao());
            pst.setBigDecimal(3, p.getPrecoCompra());
            pst.setBigDecimal(4, p.getPrecoVenda());
            pst.setInt(5, p.getQtd());
            
            Date data = new Date();
            Timestamp t = new Timestamp(data.getTime());
            
            pst.setTimestamp(6, t);
            
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next()){
                p.setId(rs.getInt(1));
            }
            
            if(p.getCats().length > 0){
                for(int i = 0; i < p.getCats().length; i++){
                    sql = "INSERT INTO produto_categoria (id_produto, id_categoria) VALUES(?, ?)";

                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, p.getId());
                    
                    if(p.getCats()[i].equals("A")){
                        pst.setInt(2, 1);
                    }
                    else if(p.getCats()[i].equals("B")){
                        pst.setInt(2, 2);
                    }
                    else if(p.getCats()[i].equals("C")){
                        pst.setInt(2, 3);
                    }
                    
                    pst.execute();
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void alterar(Produto p){
        Connection conn = null;
        PreparedStatement pst = null;
        String url = "jdbc:mysql://localhost/PRODUTOBD";
        String sql = "UPDATE PRODUTO SET Nome = ?, Descricao = ?, Preco_Compra = ?, Preco_Venda = ?, Quantidade = ?, DT_Cadastro = ? "
                + " WHERE id = ?;";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            //Propriedades para armazenamento de usuário e senha
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "");
            properties.put("serverTimezone", "UTC");
            
            conn = DriverManager.getConnection(url, properties);
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, p.getNome());
            pst.setString(2, p.getDescricao());
            pst.setBigDecimal(3, p.getPrecoCompra());
            pst.setBigDecimal(4, p.getPrecoVenda());
            pst.setInt(5, p.getQtd());
            
            Date data = new Date();
            Timestamp t = new Timestamp(data.getTime());
            
            pst.setTimestamp(6, t);
            
            pst.setInt(7, p.getId());
            
            pst.executeUpdate();
            
            if(p.getCats().length > 0){
                
                sql = "DELETE FROM PRODUTO_CATEGORIA WHERE id_produto = ?";
                
                pst = conn.prepareStatement(sql);
                pst.setInt(1, p.getId());
                
                pst.execute();
                
                for(int i = 0; i < p.getCats().length; i++){
                    sql = "INSERT INTO produto_categoria (id_produto, id_categoria) VALUES(?, ?)";

                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, p.getId());
                    
                    if(p.getCats()[i].equals("A")){
                        pst.setInt(2, 1);
                    }
                    else if(p.getCats()[i].equals("B")){
                        pst.setInt(2, 2);
                    }
                    else if(p.getCats()[i].equals("C")){
                        pst.setInt(2, 3);
                    }
                    
                    pst.execute();
                }
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void excluir(int id){
        Connection conn = null;
        PreparedStatement pst = null;
        String url = "jdbc:mysql://localhost/PRODUTOBD";
        String sql = "DELETE FROM produto_categoria WHERE id_produto = ?";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            //Propriedades para armazenamento de usuário e senha
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "");
            properties.put("serverTimezone", "UTC");
            
            conn = DriverManager.getConnection(url, properties);
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            pst.executeUpdate();
            
            sql = "DELETE FROM produto WHERE id = ?";
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, id);
            pst.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static List<Produto> listarProdutos(){
        Connection conn = null;
        PreparedStatement pst = null;
        String url = "jdbc:mysql://localhost/PRODUTOBD";
        String sql = "SELECT * FROM Produto";
        
        List<Produto> lista = new LinkedList<Produto>();
        List<String>categorias = new LinkedList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            //Propriedades para armazenamento de usuário e senha
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "");
            properties.put("serverTimezone", "UTC");
            
            conn = DriverManager.getConnection(url, properties);
            pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery(sql);
            
            while(rs.next()){
                
                Produto p = new Produto();
                
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("NOME"));
                p.setDescricao(rs.getString("DESCRICAO"));
                p.setPrecoCompra(rs.getBigDecimal("Preco_Compra"));
                p.setPrecoVenda(rs.getBigDecimal("Preco_Venda"));
                p.setQtd(rs.getInt("Quantidade"));
                
                lista.add(p);
            }
            
            for(int i = 0; i < lista.size(); i++){
                sql = "SELECT PRODUTO.ID, PRODUTO_CATEGORIA.ID_CATEGORIA, CATEGORIA.NOME FROM PRODUTO, PRODUTO_CATEGORIA, CATEGORIA " + 
                      " WHERE PRODUTO.ID = PRODUTO_CATEGORIA.ID_PRODUTO AND CATEGORIA.ID = PRODUTO_CATEGORIA.ID_CATEGORIA AND PRODUTO.ID = ?";
                
                pst = conn.prepareStatement(sql);
                pst.setInt(1, lista.get(i).getId());
                
                ResultSet rsCat = pst.executeQuery();
                while(rsCat.next()){
                    categorias.add(rsCat.getString("NOME"));
                }
                
                lista.get(i).setCats(categorias.toArray(new String[0]));
                categorias.clear();
            }
            
            return lista;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public static Produto obterProduto(int id){
        Connection conn = null;
        PreparedStatement pst = null;
        String url = "jdbc:mysql://localhost/PRODUTOBD";
        String sql = "SELECT * FROM Produto WHERE id = ?;";
        
        List<String>categorias = new LinkedList<String>();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            //Propriedades para armazenamento de usuário e senha
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "");
            properties.put("serverTimezone", "UTC");
            
            conn = DriverManager.getConnection(url, properties);
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                Produto p = new Produto();
                
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("NOME"));
                p.setDescricao(rs.getString("DESCRICAO"));
                p.setPrecoCompra(rs.getBigDecimal("Preco_Compra"));
                p.setPrecoVenda(rs.getBigDecimal("Preco_Venda"));
                p.setQtd(rs.getInt("Quantidade"));
                
                sql = "SELECT PRODUTO.ID, PRODUTO_CATEGORIA.ID_CATEGORIA, CATEGORIA.NOME FROM PRODUTO, PRODUTO_CATEGORIA, CATEGORIA " + 
                      " WHERE PRODUTO.ID = PRODUTO_CATEGORIA.ID_PRODUTO AND CATEGORIA.ID = PRODUTO_CATEGORIA.ID_CATEGORIA AND PRODUTO.ID = ?";
                
                pst = conn.prepareStatement(sql);
                pst.setInt(1, p.getId());
                
                ResultSet rsCat = pst.executeQuery();
                while(rsCat.next()){
                    categorias.add(rsCat.getString("NOME"));
                }
                
                p.setCats(categorias.toArray(new String[0]));
                categorias.clear();
                
                return p;
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
