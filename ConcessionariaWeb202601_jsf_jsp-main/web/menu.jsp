<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="mb-4 p-3 bg-dark text-white rounded d-flex justify-content-between align-items-center shadow-sm">
    <div class="d-flex gap-3 align-items-center">
        <a href="index.jsp" class="text-white text-decoration-none fw-bold">Home</a> | 
        <a href="listarVeiculo.jsp" class="text-white text-decoration-none fw-bold">Oficina (Veículos)</a> | 
        <a href="listarPeca.jsp" class="text-white text-decoration-none fw-bold">Estoque de Peças</a> | 
        <a href="listarManutencao.jsp" class="text-white text-decoration-none fw-bold">Nova Ordem de Serviço</a>
    </div>
    <div class="d-flex gap-2 align-items-center">
        <a href="perfil.jsp" class="btn btn-sm btn-outline-light">👤 Meu Perfil</a>
        <button onclick="efetuarLogoff()" class="btn btn-sm btn-danger fw-bold">🚪 Sair (Logoff)</button>
    </div>
</div>

<script>
    function efetuarLogoff() {
        if(confirm("Deseja realmente encerrar sua sessão no SGO?")) {
            localStorage.removeItem("usuarioLogado");
            window.location.href = "login.jsp";
        }
    }
</script>
