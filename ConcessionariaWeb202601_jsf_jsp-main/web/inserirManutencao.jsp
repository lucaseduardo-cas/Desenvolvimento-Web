<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Nova Manutenção</title>
</head>
<body>
    <h1>Cadastrar Manutenção</h1>
    <form action="salvarManutencao.jsp" method="POST">
        Descrição: <input type="text" name="descricao" required><br><br>
        Valor: <input type="number" step="0.01" name="valor" required><br><br>
        Quilometragem: <input type="number" name="km" required><br><br>
        ID do Automóvel: <input type="number" name="auto_id" value="1" required><br><br>
        <button type="submit">Salvar Manutenção</button>
    </form>
    <br>
    <a href="listarManutencao.jsp">Voltar para a lista</a>
</body>
</html>