cd "/home/lucas/Documentos/Meu computador/UEG-SI/5-Periodo/programacao-web-I/cod_font_prof_atualizado/ConcessionariaWeb202601_jsf_jsp-main/web/"

cat << 'EOF' > login.jsp
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGO - Autenticação</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="bg-dark d-flex align-items-center" style="height: 100vh;">
        <div class="container">
            <div class="card shadow mx-auto" style="max-width: 420px;">
                
                <!-- Abas para alternar entre Login e Cadastro -->
                <div class="card-header bg-light p-0 border-0">
                    <ul class="nav nav-tabs nav-justified" id="authTabs" role="tablist">
                        <li class="nav-item">
                            <button class="nav-link active py-3 fw-bold border-0 rounded-0" id="login-tab" data-bs-toggle="tab" onclick="mostrarPainel('login')">Acessar</button>
                        </li>
                        <li class="nav-item">
                            <button class="nav-link py-3 fw-bold border-0 rounded-0 text-success" id="cadastro-tab" data-bs-toggle="tab" onclick="mostrarPainel('cadastro')">Criar Conta</button>
                        </li>
                    </ul>
                </div>

                <div class="card-body p-4">
                    <!-- FORMULÁRIO DE LOGIN -->
                    <div id="painel-login">
                        <form action="index.jsp" method="POST" onsubmit="return validarLogin()">
                            <div class="mb-3">
                                <label for="username" class="form-label fw-bold">Usuário / Login:</label>
                                <input type="text" class="form-control" id="username" placeholder="Ex: admin" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label fw-bold">Senha:</label>
                                <input type="password" class="form-control" id="password" placeholder="Digite sua senha" required>
                            </div>
                            <div id="erro-msg" class="alert alert-danger d-none mb-3" role="alert">
                                Usuário ou senha incorretos!
                            </div>
                            <div class="d-grid mt-4">
                                <button type="submit" class="btn btn-primary btn-lg shadow-sm">Entrar no Sistema</button>
                            </div>
                        </form>
                    </div>

                    <!-- FORMULÁRIO DE CADASTRO -->
                    <div id="painel-cadastro" class="d-none">
                        <form onsubmit="return efetuarCadastro(event)">
                            <div class="mb-3">
                                <label for="new_nome" class="form-label fw-bold">Nome Completo:</label>
                                <input type="text" class="form-control" id="new_nome" placeholder="Ex: Lucas Eduardo" required>
                            </div>
                            <div class="mb-3">
                                <label for="new_username" class="form-label fw-bold">Defina seu Usuário:</label>
                                <input type="text" class="form-control" id="new_username" placeholder="Ex: lucas123" required>
                            </div>
                            <div class="mb-3">
                                <label for="new_password" class="form-label fw-bold">Defina sua Senha:</label>
                                <input type="password" class="form-control" id="new_password" placeholder="Mínimo 4 caracteres" required>
                            </div>
                            <div id="sucesso-msg" class="alert alert-success d-none mb-3" role="alert">
                                Conta criada com sucesso! Faça login.
                            </div>
                            <div class="d-grid mt-4">
                                <button type="submit" class="btn btn-success btn-lg shadow-sm">Registrar Oficina</button>
                            </div>
                        </form>
                    </div>

                </div>
                <div class="card-footer text-center py-2 bg-light text-muted">
                    <small>SGO | Padrão de Segurança Acadêmica</small>
                </div>
            </div>
        </div>

        <script>
            // FUNÇÃO QUE FUNCI0NA COMO BANCO EM ARQUIVO LOCAL (BRINCADEIRA COM JSON)
            function obterUsuariosDoArquivo() {
                var arquivo = localStorage.getItem("banco_usuarios.json");
                if (!arquivo) {
                    // Se o arquivo não existir, cria ele com o administrador padrão
                    var padrao = [{ user: "admin", pass: "admin" }];
                    localStorage.setItem("banco_usuarios.json", JSON.stringify(padrao));
                    return padrao;
                }
                return JSON.parse(arquivo);
            }

            function mostrarPainel(tipo) {
                var painelLogin = document.getElementById('painel-login');
                var painelCadastro = document.getElementById('painel-cadastro');
                var tabLogin = document.getElementById('login-tab');
                var tabCadastro = document.getElementById('cadastro-tab');

                if (tipo === 'login') {
                    painelLogin.classList.remove('d-none');
                    painelCadastro.classList.add('d-none');
                    tabLogin.classList.add('active');
                    tabCadastro.classList.remove('active');
                } else {
                    painelLogin.classList.add('d-none');
                    painelCadastro.classList.remove('d-none');
                    tabLogin.classList.remove('active');
                    tabCadastro.classList.add('active');
                }
            }

            function efetuarCadastro(event) {
                event.preventDefault();
                var user = document.getElementById("new_username").value.trim().toLowerCase();
                var pass = document.getElementById("new_password").value;
                var sucessoDiv = document.getElementById("sucesso-msg");

                // Carrega os usuários existentes do arquivo local
                var usuarios = obterUsuariosDoArquivo();

                // Verifica se o usuário já existe no arquivo
                var existe = usuarios.some(function(u) { return u.user === user; });
                if (existe) {
                    alert("Este usuário já está cadastrado no sistema!");
                    return false;
                }

                // Grava o novo registro no JSON do arquivo local
                usuarios.push({ user: user, pass: pass });
                localStorage.setItem("banco_usuarios.json", JSON.stringify(usuarios));
                
                sucessoDiv.classList.remove("d-none");
                
                document.getElementById("new_nome").value = "";
                document.getElementById("new_username").value = "";
                document.getElementById("new_password").value = "";

                setTimeout(function() {
                    sucessoDiv.classList.add("d-none");
                    mostrarPainel('login');
                    document.getElementById("username").value = user;
                }, 1500);

                return false;
            }

            function validarLogin() {
                var user = document.getElementById("username").value.trim().toLowerCase();
                var pass = document.getElementById("password").value;
                var erroDiv = document.getElementById("erro-msg");

                // Carrega e valida direto do arquivo JSON guardado no navegador
                var usuarios = obterUsuariosDoArquivo();
                var autenticado = usuarios.some(function(u) {
                    return u.user === user && u.pass === pass;
                });

                if (autenticado) {
                    localStorage.setItem("usuarioLogado", user);
                    return true;
                } else {
                    erroDiv.classList.remove("d-none");
                    return false;
                }
            }
        </script>
    </body>
</html>
EOF