/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Marca;
import negocio.NMarca;

/**
 *
 * @author heube
 */
@WebServlet(name = "CMarca", urlPatterns = {"/CMarca"})
public class CMarca extends HttpServlet {

    private static final String MANTER = "manterMarca.jsp";
    private static final String LISTAR = "listarMarca.jsp";

    private NMarca nMarca;

    public CMarca() {
        super();
        nMarca = new NMarca();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //http://localhost:8080/ConcessionariaWeb_202601/Cmarca?acao=listar
        String acao = request.getParameter("acao");
        String avancar = "";

        if (acao.equalsIgnoreCase("listar")) {

            //Tratativas de chamar a tela de listagem
            List<Marca> lista = nMarca.listar();
            request.setAttribute("listamarcas", lista);
            avancar = LISTAR;

        } else if (acao.equalsIgnoreCase("excluir")) {

            //Tratativas da exclusão de marcas
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            Marca marca = new Marca();
            marca.setCodigo(codigo);

            try {
                nMarca.excluir(marca);
            } catch (SQLException ex) {
                Logger.getLogger(CMarca.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Voltar para a listagem, atualizando a lista
            List<Marca> lista = nMarca.listar();
            request.setAttribute("listamarcas", lista);
            avancar = LISTAR;

        } else if (acao.equalsIgnoreCase("alterar")) {

            int codigo = Integer.parseInt(request.getParameter("codigo"));
            Marca marca = nMarca.consultar(codigo);
            request.setAttribute("marca", marca);
            avancar = MANTER;

        } else {
            //Opção padrao - caso o usuário não informe nenhuma ação na URL
            avancar = MANTER;
        }

        //Navegar para a tela que foi setada na variável avancar
        RequestDispatcher tela = request.getRequestDispatcher(avancar);
        tela.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Marca marca = new Marca();

        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");

        if (codigo != null && !codigo.isEmpty()) {
            marca.setCodigo(Integer.parseInt(codigo));
        }
        marca.setNome(nome);

        String avancar = "";

        try {

            nMarca.salvar(marca);

            List<Marca> lista = nMarca.listar();
            request.setAttribute("listamarcas", lista);
            avancar = LISTAR;

        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            avancar = MANTER;
        }

        //Navegar para a tela que foi setada na variável avancar
        RequestDispatcher tela = request.getRequestDispatcher(avancar);
        tela.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
