<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGO - Meu Perfil</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4 bg-light">
        <jsp:include page="menu.jsp" />
        
        <div class="card shadow mx-auto" style="max-width: 500px;">
            <div class="card-header bg-dark text-white text-center py-3">
                <h5 class="mb-0">Informações do Operador</h5>
            </div>
            <div class="card-body p-4 text-center">
                <div class="mb-3">
                    <img src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png" alt="Avatar" class="rounded-circle" style="width: 100px; height: 100px;">
                </div>
                <h4 class="text-primary mb-1" id="perfil-nome">Administrador Padrão</h4>
                <p class="text-muted mb-4">Nível de Acesso: Gestor de Oficina</p>
                
                <hr>
                
                <div class="row text-start mt-3">
                    <div class="col-6 mb-2">
                        <span class="fw-bold text-secondary d-block">Login de Acesso:</span>
                        <span id="perfil-login" class="badge bg-secondary fs-6">admin</span>
                    </div>
                    <div class="col-6 mb-2">
                        <span class="fw-bold text-secondary d-block">Status do Sistema:</span>
                        <span class="badge bg-success fs-6">Autenticado</span>
                    </div>
                </div>
            </div>
            <div class="card-footer bg-light text-center py-3">
                <a href="index.jsp" class="btn btn-outline-secondary btn-sm">Voltar ao Painel</a>
            </div>
        </div>

        <script>
            // Recupera o nome do usuário cadastrado na sessão se existir
            var usuarioAtivo = localStorage.getItem("usuarioLogado");
            if (usuarioAtivo && usuarioAtivo !== "admin") {
                document.getElementById("perfil-nome").innerText = usuarioAtivo.toUpperCase();
                document.getElementById("perfil-login").innerText = usuarioAtivo;
            }
        </script>
    </body>
</html>
