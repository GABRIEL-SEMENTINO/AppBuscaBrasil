package com.example.buscabrasil;

//Classes
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.buscabrasil.task.AsyncCidadeInsert;
import com.example.buscabrasil.task.AsyncCidadeListAll;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity<Cidade> extends AppCompatActivity {

    private ListView cidades;
    private ListView estados;
    private List<Cidade> listaCidades = new ArrayList<Cidade>();
    private TextView id = findViewById(R.id.);
    private String textoPadrao = "Campo obrigatório.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.cidades = findViewById(R.id.ItemList);
        this.estados = findViewById(R.id.ItemList);

        recarregarEstado();
    }

    public void carregarCidade(View view){
        id = findViewById(R.id.BotaoEstado);
        if(id.getText().toString().equals("")) mensagem("Id "+ textoPadrao);
        else {
            listarCidade();
            limparFormulario();
        }
    }

    public void recarregarEstado(View view){
        recarregarEstado();
        limparFormulario();

    }

    public void validaEstado(View view){
        id = findViewById(R.id.BotaoEstado);
        if(id.getText().toString().equals("")) mensagem("Id "+ textoPadrao);
        else {
            salvaCidade(id);
            limparFormulario();
        }
    }

    public void listarCidade(){
        id = findViewById(R.id.BuscaEstado);
        CidadeService cidadeService = RetrofitService.getInstance().create(CidadeService.class);
        final WeakReference<Context> weakReference = new WeakReference(this);
        cidadeService.groupList(Integer.parseInt(id.getText().toString())).enqueue(new Callback<List<Cidade>>() {





            @Override
            public void onResponse(Call<List<CidadeService>> call, Response<List<Cidade>> response) {
                CidadeAdapter cidadeAdapter = new CidadeAdapter(response.body(), weakReference);
                cidades.setAdapter(cidadeAdapter);

            }

            @Override
            public void onFailure(Call<List<Cidade>> call, Throwable t) {
                Toast.makeText(weakReference.get(), "Nenhuma cidade encontrada.", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void salvaCidade(TextView id){
        Cidade cidade = new Cidade();
        CidadeService cidadeService = RetrofitService.getInstance().create(CidadeService.class);
        final WeakReference<Context> weakReference = new WeakReference(this);
        cidadeService.groupList(Integer.parseInt(id.getText().toString())).enqueue(new Callback<List<Cidade>>() {
            @Override
            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                assert response.body() != null;
                listaCidades.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Cidade>> call, Throwable t) {
                Toast.makeText(weakReference.get(), "Não encontrado", Toast.LENGTH_LONG).show();
            }
        });

        new AsyncCidadeInsert(MainActivity.this, cidade).execute();
        mensagem("Cidade Salva");

    }

    public void listarCidade(View view){
        new AsyncCidadeListAll(MainActivity.this).execute();
    }

    public void limparFormulario(){
        id.setText("");
    }

    public void recarregarEstado(){
        EstadoService estadoService  = RetrofitService.getInstance().create(EstadoService.class);
        final WeakReference<Context> weakReference = new WeakReference(this);
        estadoService.findAll().enqueue(new Callback<List<Estado>>() {


            @Override
            public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                EstadoAdapter estadoAdapter = new EstadoAdapter(response.body(), weakReference);
                estados.setAdapter(estadoAdapter);

            }

            @Override
            public void onFailure(Call<List<Estado>> call, Throwable t) {
                Toast.makeText(weakReference.get(), "Não foi possível carregar as cidades.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void mensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }

    public void Salvar(View view) {
    }
}
