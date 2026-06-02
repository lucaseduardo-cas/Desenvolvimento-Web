<%-- 
    Document   : manterMarca.jsp
    Created on : 05/05/2026, 19:35:51
    Author     : heube
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manter Marca</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <br />
        <br />
        <h4>Manter Marca</h4>

        <form method="POST" action="CMarca" name="manterMarca">

            <table>

                <tr>
                    <td>Código</td>
                    <td><input type="text"
                               readonly="readonly"
                               name="codigo"
                               size="10"
                               maxlength="10"
                               value="<c:out value="${marca.codigo}" />"
                               /></td>
                </tr>

                <tr>
                    <td>Nome</td>
                    <td><input type="text" 
                               name="nome"
                               size="30"
                               maxlength="30"
                               value="<c:out value="${marca.nome}" />"
                               /></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                
                <tr>
                    <td colspan="2" align="center"> 
                        <input type="submit" value="Salvar" />
                        <input type="button" value="Voltar" onclick="history.go(-1)" />
                    </td>
                </tr>

            </table>


        </form>
    </body>
</html>
