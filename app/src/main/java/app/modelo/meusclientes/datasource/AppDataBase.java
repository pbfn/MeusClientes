package app.modelo.meusclientes.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

import app.modelo.meusclientes.api.AppUtil;
import app.modelo.meusclientes.datamodel.ClienteDataModel;
import app.modelo.meusclientes.model.Cliente;

public class AppDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "MeusClientes.sqlite";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;

    public AppDataBase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(AppUtil.TAG,"AppDataBase: Criando Banco de Dados");
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClienteDataModel.criarTabela());
        Log.d(AppUtil.TAG,"onCreate: Tabela cliente criada...: "+ClienteDataModel.criarTabela());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /**
     *Método para incluir dados no banco de dados
     * @return
     */
    public boolean insert(String tabela, ContentValues dados){
        db = getWritableDatabase();
        boolean retorno = false;
        //regra de negócio
        try{
            retorno =  db.insert(tabela,null,dados) > 0;
            return retorno;
        }catch (Exception e){
            Log.d(AppUtil.TAG,"insert: " + e.getMessage());
        }

        return  retorno;
    }

    /**
     *Método para deletar dados no banco de dados
     * @return
     */
    public boolean deleteById(String tabela, int id){

        db = getWritableDatabase();
        boolean retorno = false;
        //regra de negócio
        try{
            retorno =  db.delete(tabela,
                    "id=?",
                    new String[] {String.valueOf(id)})>0;
            return retorno;
        }catch (Exception e){
            Log.d(AppUtil.TAG,"insert: " + e.getMessage());
        }

        return  retorno;
    }


    /**
     *Método para alterar dados no banco de dados
     * @return
     */
    public boolean updateById(String tabela, ContentValues dados){
        db = getWritableDatabase();
        boolean retorno = false;
        //regra de negócio

        try{

            retorno =  db.update(tabela,
                    dados,
                    "id=?",
                    new String[] {String.valueOf(dados.get("id"))})>0;

            return retorno;
        }catch (Exception e){
            Log.d(AppUtil.TAG,"insert: " + e.getMessage());
        }

        return  retorno;
    }

    /**
     *Método para alterar dados no banco de dados
     * @return
     */
    public List<Cliente> getAllClientes(String tabela){
        db = getWritableDatabase();
        Cliente cliente;

        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM "+tabela;
        Cursor cursor;

        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do {
                cliente = new Cliente();
                cliente.setId(cursor.getInt(cursor.getColumnIndex(ClienteDataModel.ID)));
                cliente.setNome(cursor.getString(cursor.getColumnIndex(ClienteDataModel.NOME)));
                cliente.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDataModel.EMAIL)));
                cliente.setTelefone(cursor.getString(cursor.getColumnIndex(ClienteDataModel.TELEFONE)));
                clientes.add(cliente);
            }while (cursor.moveToNext());
    
        }

        return clientes;
    }
}
