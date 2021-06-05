package app.modelo.meusclientes.view;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.modelo.meusclientes.R;
import app.modelo.meusclientes.controller.ClienteController;
import app.modelo.meusclientes.model.Cliente;


public class ListarClientesCardFragment extends Fragment {

    View view;
    EditText editPesquisarNome;
    ListView listView;
    List<Cliente> clienteList;
    List<String> clientes;

    ClienteController clienteController;

    ArrayAdapter<String> clienteAdapter;
    ArrayList<HashMap<String,String>> filtroClientes;

    Cliente objCliente;

    public ListarClientesCardFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_listar_clientes_card, container, false);

        TextView txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.fragmento_listar_clientes_cards);

        txtTitulo.setTextColor(ColorStateList.valueOf(Color.BLACK));

        clienteController=new ClienteController(getContext());

        listView = (ListView) view.findViewById(R.id.listView);
        editPesquisarNome = view.findViewById(R.id.editPesquisarNome);

        clienteList = clienteController.listar();
        clientes = clienteController.gerarListaDeClientesListView();


        clienteAdapter = new ArrayAdapter<>(
                getContext(),
                R.layout.fragment_listar_cliente_item_card,
                R.id.txtItemLista,
                clientes);

        listView.setAdapter(clienteAdapter);


        editPesquisarNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence filtro, int start, int count, int after) {
                ListarClientesCardFragment.this.clienteAdapter.getFilter().filter(filtro);
            }

            @Override
            public void onTextChanged(CharSequence filtro, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }


}
