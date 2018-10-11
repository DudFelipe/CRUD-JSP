<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Manutenção de Produtos - Alterar/Excluir</h1>
        <table border="1px">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Preço de Venda</th>
                <th>Preço de Compra</th>
                <th>Categorias</th>
            </tr>
            <c:forEach items="${produtos}" var="p">
                <tr>
                    <td><c:out value="${p.getId()}" /></td>
                    <td><c:out value="${p.getNome()}" /></td>
                    <td><c:out value="${p.getPrecoVenda()}" /></td>
                    <td><c:out value="${p.getPrecoCompra()}" /></td>
                    <td>
                        <ul>
                            <c:forEach items="${p.getCats()}" var="cat">
                                <li><c:out value="${cat}" /></li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/AlteraServlet?id=${p.getId()}">Alterar</a></td>
                    <td><a href="${pageContext.request.contextPath}/ExcluiServlet?id=${p.getId()}">Excluir</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
