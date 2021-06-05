package app.modelo.meusclientes.datamodel;

public class ClienteDataModel {

    //NOME DA  TABELA
    public static final String TABELA = "cliente";

    //ATRIBUTOS DA TABELA
    public static final String ID="id"; //integer
    public static final String NOME="nome"; //text
    public static final String TELEFONE="telefone"; //text
    public static final String EMAIL="email"; //text
    public static final String CEP="cep"; //text
    public static final String LOGRADOURO="logradouro"; //text
    public static final String NUMERO="numero"; //text
    public static final String BAIRRO="bairro"; //text
    public static final String CIDADE="cidade"; //text
    public static final String ESTADO="estado"; //text
    public static final String TERMOS_DE_USO="termosdeuso"; //integer

    //QUERY PAR CRIAR A TABELA
    public static  String queryCriarTabela ="";

    //MÉTODO PARA GERAR O SCRIPT DE CRIAÇÃO DA TABELA
    public static String criarTabela(){

        queryCriarTabela +="CREATE TABLE "+TABELA+" (";
        queryCriarTabela +=ID+" integer primary key autoincrement, ";
        queryCriarTabela +=NOME+" text, ";
        queryCriarTabela +=TELEFONE+" text, ";
        queryCriarTabela +=EMAIL+" text, ";
        queryCriarTabela +=CEP+" integer, ";
        queryCriarTabela +=LOGRADOURO+" text, ";
        queryCriarTabela +=NUMERO+" text, ";
        queryCriarTabela +=BAIRRO+" text, ";
        queryCriarTabela +=CIDADE+" text, ";
        queryCriarTabela +=ESTADO+" text, ";
        queryCriarTabela +=TERMOS_DE_USO+" integer";
        queryCriarTabela +=")";

        return queryCriarTabela;
    }

}
