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
import java.sql.Timestamp;
import java.util.Date;
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
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, p.getNome());
            pst.setString(2, p.getDescricao());
            pst.setBigDecimal(3, p.getPrecoCompra());
            pst.setBigDecimal(4, p.getPrecoVenda());
            pst.setInt(5, p.getQtd());
            
            Date data = new Date();
            Timestamp t = new Timestamp(data.getTime());
            
            pst.setTimestamp(6, t);
            
            pst.execute();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
