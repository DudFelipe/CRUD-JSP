<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastro de Produto</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:choose>
            <c:when test="${produto == null}">
                <form action="${pageContext.request.contextPath}/produto/incluir" method="post">
                    <p>Nome: <input id="nome" name="nome" type="text"></p>
                    <p>Descrição <textarea id="descricao" name="descricao"></textarea></p>
                    <p>Preço de Compra <input id="precoCompra" name="prcompra" type="text"></p>
                    <p>Preço de Venda <input id="precoVenda" name="prvenda" type="text"></p>
                    <p>Quantidade <input id="quantidade" name="qtd" type="text"></p>
                    <p>
                        <input id="cat1" name="cat" type="checkbox" value="A">Categoria 1
                        <input id="cat2" name="cat" type="checkbox" value="B">Categoria 2
                        <input id="cat3" name="cat" type="checkbox" value="C">Categoria 3
                    </p>
            </c:when>
            <c:otherwise>
                <form action="${pageContext.request.contextPath}/AlteraServlet?id=${produto.getId()}" method="post">
                    <p>Nome: <input id="nome" name="nome" type="text" value="<c:out value="${produto.getNome()}" />"></p>
                    <p>Descrição <textarea id="descricao" name="descricao"><c:out value="${produto.getDescricao()}" /></textarea></p>
                    <p>Preço de Compra <input id="precoCompra" name="prcompra" type="text" value="<c:out value="${produto.getPrecoCompra()}" />"></p>
                    <p>Preço de Venda <input id="precoVenda" name="prvenda" type="text" value="<c:out value="${produto.getPrecoVenda()}" />"></p>
                    <p>Quantidade <input id="quantidade" name="qtd" type="text" value="<c:out value="${produto.getQtd()}" />"></p>
                    <p>
                        <input id="cat1" name="cat" type="checkbox" value="A">Categoria 1
                        <input id="cat2" name="cat" type="checkbox" value="B">Categoria 2
                        <input id="cat3" name="cat" type="checkbox" value="C">Categoria 3
                    </p>
            </c:otherwise>
            </c:choose>
            <input id="salvar" name="salvar" value="Salvar produto" type="submit">
        </form>
    </body>
</html>
