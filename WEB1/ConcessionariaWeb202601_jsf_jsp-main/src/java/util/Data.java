/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author heube
 */
public class Data {

    //Método para calculo da idade
    public static int calcularIdade(Date dataNascimento) {

        Calendar dataCalendario = new GregorianCalendar();
        dataCalendario.setTime(dataNascimento);

        // Cria um objeto calendar com a data atual
        Calendar hoje = Calendar.getInstance();

        // Obtém a idade baseado no ano
        int idade = hoje.get(Calendar.YEAR) - dataCalendario.get(Calendar.YEAR);

        dataCalendario.add(Calendar.YEAR, idade);

        //se a data de hoje é antes da data de Nascimento, então diminui 1(um)
        if (hoje.before(dataCalendario)) {
            idade--;
        }

        return idade;

    }

    public static String dataAtualFormatada() {
        DateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dataAtual = new java.util.Date();
        return formatar.format(dataAtual);
    }

    public static String formatarData(Date parametro) {
        DateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        if (parametro != null)
            return formatar.format(parametro);
        
        return null;
    }

    public static Date formatarDataSQL(String parametro) throws ParseException {
        DateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        if (!parametro.isEmpty()) {
            return new java.sql.Date(formatar.parse(parametro).getTime());
        }

        return null;
    }

}
