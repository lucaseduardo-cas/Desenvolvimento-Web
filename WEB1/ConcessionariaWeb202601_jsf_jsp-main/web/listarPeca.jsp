<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGO - Estoque de Peças</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4 bg-light">
        <jsp:include page="menu.jsp" />
        
        <div class="row">
            <div class="col-md-4">
                <div class="card shadow mb-4">
                    <div class="card-header bg-success text-white">
                        <h5 class="mb-0" id="form-titulo">Cadastrar Nova Peça</h5>
                    </div>
                    <div class="card-body p-3">
                        <form id="meuFormPeca" onsubmit="return salvarPecaLocal(event)">
                            <input type="hidden" id="p_id">

                            <div class="row g-2 mb-2">
                                <div class="col-md-6">
                                    <label class="form-label fw-bold mb-1">Código:</label>
                                    <input type="text" id="p_codigo" class="form-control form-control-sm" placeholder="Ex: OPH-102" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label fw-bold mb-1">Qtd. Estoque:</label>
                                    <input type="number" id="p_qtd" class="form-control form-control-sm" placeholder="Ex: 5" required>
                                </div>
                            </div>
                            
                            <div class="mb-2">
                                <label class="form-label fw-bold mb-1">Nome da Peça:</label>
                                <input type="text" id="p_nome" class="form-control form-control-sm" placeholder="Ex: Filtro de Óleo" required>
                            </div>
                            <div class="mb-2">
                                <label class="form-label fw-bold mb-1">Modelo Principal:</label>
                                <input type="text" id="p_modelo" class="form-control form-control-sm" placeholder="Ex: Honda Civic EX" required>
                            </div>
                            
                            <div class="row g-2 mb-2">
                                <div class="col-md-6">
                                    <label class="form-label fw-bold mb-1">Anos:</label>
                                    <input type="text" id="p_ano" class="form-control form-control-sm" placeholder="Ex: 2001-2006" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label fw-bold mb-1">Durabilidade:</label>
                                    <input type="text" id="p_durabilidade" class="form-control form-control-sm" placeholder="Ex: 6 meses">
                                </div>
                            </div>

                            <div class="mb-2">
                                <label class="form-label fw-bold mb-1">Compatibilidade Cruzada:</label>
                                <input type="text" id="p_cruzada" class="form-control form-control-sm" placeholder="Ex: Toyota Corolla 1.8">
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-bold mb-1">Observações Técnicas / Uso Severo:</label>
                                <textarea id="p_obs" class="form-control form-control-sm" rows="2" placeholder="Ex: Litragem: 3.5L. Em uso severo, trocar na metade do tempo."></textarea>
                            </div>

                            <button type="submit" id="btn-salvar-peca" class="btn btn-success w-100 shadow-sm fw-bold">Salvar Peça no Estoque</button>
                            <button type="button" id="btn-cancelar-edicao" class="btn btn-outline-secondary w-100 mt-2 btn-sm d-none" onclick="cancelarEdicaoPeca()">Cancelar Edição</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <div class="card shadow p-4">
                    <h4 class="text-secondary mb-4">Estoque Técnico & Compatibilidades</h4>
                    <table class="table table-hover border align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th style="width: 5%"></th>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Aplicação</th>
                                <th>Qtd</th>
                                <th>Durabilidade</th>
                                <th class="text-center" style="width: 25%">Ações</th>
                            </tr>
                        </thead>
                        <tbody id="tabela-pecas-corpo">
                            </tbody>
                    </table>
                    <div id="aviso-vazio" class="text-center text-muted py-3 d-none">
                        Nenhuma peça cadastrada para este usuário ainda.
                    </div>
                </div>
            </div>
        </div>

        <script>
            var usuarioAtivo = localStorage.getItem("usuarioLogado") || "global";
            var chaveBanco = "banco_pecas_" + usuarioAtivo + ".json";

            function obterPecasDoArquivo() {
                var dados = localStorage.getItem(chaveBanco);
                if (!dados) {
                    if (usuarioAtivo === "admin" || usuarioAtivo === "lucas") {
                        var inicial = [
                            { id: 1, codigo: "OPH-102", nome: "Filtro de Óleo Longa Vida", modelo: "Honda Civic EX 1.7", ano: "2001-2005", qtd: "4", durabilidade: "6 meses", cruzada: "Civic LX, Accord 2.0", obs: "Litragem recomendada: 3.5 Litros (óleo 10w30). Em uso severo, realizar troca a cada 3 meses ou 5.000km." },
                            { id: 2, codigo: "BRK-554", nome: "Pastilha de Freio Cerâmica", modelo: "VW Saveiro Trend", ano: "2010-2018", qtd: "2", durabilidade: "12 meses", cruzada: "Gol G5/G6, Voyage", obs: "Uso severo em estradas de terra exige inspeção a cada 5.000km." }
                        ];
                        localStorage.setItem(chaveBanco, JSON.stringify(inicial));
                        return inicial;
                    }
                    return [];
                }
                return JSON.parse(dados);
            }

            function renderizarTabela() {
                var pecas = obterPecasDoArquivo();
                var corpo = document.getElementById("tabela-pecas-corpo");
                var aviso = document.getElementById("aviso-vazio");
                
                corpo.innerHTML = "";
                
                if (pecas.length === 0) {
                    aviso.classList.remove("d-none");
                    return;
                }
                aviso.classList.add("d-none");

                pecas.forEach(function(p, index) {
                    var linha = document.createElement("tr");
                    // CORREÇÃO AQUI: Ajustado o escape do ano para remover caracteres parasitas
                    linha.innerHTML = `
                        <td>
                            <button class="btn btn-sm btn-outline-secondary p-1 py-0" onclick="alternarDetalhe(\${index})">
                                <span id="seta-\${index}">▼</span>
                            </button>
                        </td>
                        <td><span class="badge bg-secondary">\${p.codigo || '---'}</span></td>
                        <td class="fw-bold">\${p.nome || '---'}</td>
                        <td>\${p.modelo || '---'} (\${p.ano || '---'})</td>
                        <td><span class="badge bg-info text-dark fw-bold">\${p.qtd || '0'} un</span></td>
                        <td><span class="text-muted">\${p.durabilidade || 'Indeterminado'}</span></td>
                        <td class="text-center">
                            <button class="btn btn-sm btn-warning py-0 px-2 me-1" onclick="carregarPecaParaEdicao(\${p.id})">Alterar</button>
                            <button class="btn btn-sm btn-danger py-0 px-2" onclick="excluirPecaLocal(\${p.id})">Excluir</button>
                        </td>
                    `;
                    corpo.appendChild(linha);

                    var linhaDetalhe = document.createElement("tr");
                    linhaDetalhe.id = `detalhe-\${index}`;
                    linhaDetalhe.classList.add("d-none", "bg-light");
                    linhaDetalhe.innerHTML = `
                        <td colspan="7" class="p-3 border-top-0">
                            <div class="p-3 bg-white rounded border shadow-sm">
                                <div class="row">
                                    <div class="col-md-6">
                                        <h6><strong>Compatibilidade Cruzada:</strong></h6>
                                        <p class="text-muted mb-0">\${p.cruzada || 'Nenhuma informada'}</p>
                                    </div>
                                    <div class="col-md-6 border-start">
                                        <h6><strong>Ficha de Observações Técnicas & Uso Severo:</strong></h6>
                                        <p class="text-dark bg-warning-subtle p-2 rounded mb-0" style="font-size: 0.9rem; white-space: pre-wrap;">\${p.obs || 'Nenhuma observação técnica cadastrada.'}</p>
                                    </div>
                                </div>
                            </div>
                        </td>
                    `;
                    corpo.appendChild(linhaDetalhe);
                });
            }

            function alternarDetalhe(index) {
                var detalheRow = document.getElementById(`detalhe-\${index}`);
                var seta = document.getElementById(`seta-\${index}`);
                if (detalheRow.classList.contains("d-none")) {
                    detalheRow.classList.remove("d-none");
                    seta.innerText = "▲";
                } else {
                    detalheRow.classList.add("d-none");
                    seta.innerText = "▼";
                }
            }

            function salvarPecaLocal(event) {
                event.preventDefault();
                var pecas = obterPecasDoArquivo();
                var idExistente = document.getElementById("p_id").value;

                if (idExistente) {
                    var id = parseInt(idExistente);
                    var idx = pecas.findIndex(function(p) { return p.id === id; });
                    if (idx !== -1) {
                        pecas[idx].codigo = document.getElementById("p_codigo").value.trim().toUpperCase();
                        pecas[idx].qtd = document.getElementById("p_qtd").value;
                        pecas[idx].nome = document.getElementById("p_nome").value.trim();
                        pecas[idx].modelo = document.getElementById("p_modelo").value.trim();
                        pecas[idx].ano = document.getElementById("p_ano").value.trim();
                        pecas[idx].durabilidade = document.getElementById("p_durabilidade").value.trim();
                        pecas[idx].cruzada = document.getElementById("p_cruzada").value.trim();
                        pecas[idx].obs = document.getElementById("p_obs").value.trim();
                    }
                    cancelarEdicaoPeca();
                } else {
                    var nova = {
                        id: Date.now(),
                        codigo: document.getElementById("p_codigo").value.trim().toUpperCase(),
                        qtd: document.getElementById("p_qtd").value,
                        nome: document.getElementById("p_nome").value.trim(),
                        modelo: document.getElementById("p_modelo").value.trim(),
                        ano: document.getElementById("p_ano").value.trim(),
                        durabilidade: document.getElementById("p_durabilidade").value.trim(),
                        cruzada: document.getElementById("p_cruzada").value.trim(),
                        obs: document.getElementById("p_obs").value.trim()
                    };
                    pecas.push(nova);
                }

                localStorage.setItem(chaveBanco, JSON.stringify(pecas));
                document.getElementById("meuFormPeca").reset();
                renderizarTabela();
                return false;
            }

            function carregarPecaParaEdicao(id) {
                var pecas = obterPecasDoArquivo();
                var p = pecas.find(function(item) { return item.id === id; });
                
                if (p) {
                    document.getElementById("form-titulo").innerText = "Alterar Peça";
                    document.getElementById("btn-salvar-peca").innerText = "Atualizar Peça";
                    document.getElementById("btn-salvar-peca").classList.replace("btn-success", "btn-warning");
                    document.getElementById("btn-cancelar-edicao").classList.remove("d-none");

                    document.getElementById("p_id").value = p.id;
                    document.getElementById("p_codigo").value = p.codigo;
                    document.getElementById("p_qtd").value = p.qtd;
                    document.getElementById("p_nome").value = p.nome;
                    document.getElementById("p_modelo").value = p.modelo;
                    document.getElementById("p_ano").value = p.ano;
                    document.getElementById("p_durabilidade").value = p.durabilidade;
                    document.getElementById("p_cruzada").value = p.cruzada;
                    document.getElementById("p_obs").value = p.obs;
                }
            }

            function cancelarEdicaoPeca() {
                document.getElementById("form-titulo").innerText = "Cadastrar Nova Peça";
                document.getElementById("btn-salvar-peca").innerText = "Salvar Peça no Estoque";
                document.getElementById("btn-salvar-peca").classList.replace("btn-warning", "btn-success");
                document.getElementById("btn-cancelar-edicao").classList.add("d-none");
                document.getElementById("p_id").value = "";
                document.getElementById("meuFormPeca").reset();
            }

            function excluirPecaLocal(id) {
                if (confirm("Remover esta peça do estoque técnico?")) {
                    var pecas = obterPecasDoArquivo();
                    var filtradas = pecas.filter(function(p) { return p.id !== id; });
                    localStorage.setItem(chaveBanco, JSON.stringify(filtradas));
                    renderizarTabela();
                }
            }

            renderizarTabela();
        </script>
    </body>
</html>
