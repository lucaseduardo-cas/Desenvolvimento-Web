<%-- 
    Document   : listarMarca.jsp
    Created on : 05/05/2026, 19:35:25
    Author     : heube
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Marcas</title>
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <br/>
        <br/>
        <h4>Lista de Marcas</h4>
        <br/>

        <form name="listarMarca" action="CMarca" method="GET">
            <div>
                <table style="width:70%" border="1">
                    <thead>
                        <th>Código</th>
                        <th>Nome</th>
                        <th colspan="2">Ação</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${listamarcas}" var="marca">
                            <tr>
                                <td><c:out value="${marca.codigo}"/></td>
                                <td><c:out value="${marca.nome}"/></td>
                                <td><a href="CMarca?acao=alterar&codigo=<c:out value="${marca.codigo}" />">Alterar</a></td>
                                <td><a href="CMarca?acao=excluir&codigo=<c:out value="${marca.codigo}" />" onclick="return confirm('Confirma a exclusão?')" >Excluir</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <td align="center" colspan="4"><a href="CMarca?acao=incluir">Nova Marca</a></td>
                    </tfoot>
                </table>
            </div>
        </form>

    </body>
</html>
