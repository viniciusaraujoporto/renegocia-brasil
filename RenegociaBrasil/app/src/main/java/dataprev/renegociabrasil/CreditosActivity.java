package dataprev.renegociabrasil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreditosActivity extends AppCompatActivity {

    ListView simpleListView;
    String[] ListViewItems = new String[] {
            "Numero do Crédito: 601200022 \nValor Principal: 339.519,00\n Multa: 8%\nJuros: 67.903,00\n",
            "Numero do Crédito: 601200023 \nValor Principal: 339.519,00\n Multa: 8%\nJuros: 67.903,00\n",
            "Numero do Crédito: 601200024 \nValor Principal: 339.519,00\n Multa: 8%\nJuros: 67.903,00\n",
            "Numero do Crédito: 601200025 \nValor Principal: 339.519,00\n Multa: 8%\nJuros: 67.903,00\n",
            "Numero do Crédito: 601200026 \nValor Principal: 339.519,00\n Multa: 8%\nJuros: 67.903,00\n",
            "Numero do Crédito: 601200027 \nValor Principal: 339.519,00\n Multa: 8%\nJuros: 67.903,00\n"
    };

    SparseBooleanArray sparseBooleanArray ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditos);

        simpleListView=(ListView)findViewById(R.id.listView);


        final Credito[] values = new Credito[] {
                new Credito("601200022", "R$ 3.000,00", "R$ 300,00","R$ 150,00","R$ 3.450,00"),
                new Credito("601200023", "R$ 5.000,00", "R$ 500,00","R$ 250,00","R$ 5.750,00"),
                new Credito("601200024", "R$ 7.000,00", "R$ 700,00","R$ 350,00","R$ 8.050,00"),
                new Credito("601200022", "R$ 1.000,00", "R$ 100,00","R$ 50,00","R$ 1.150,00"),
                new Credito("601200023", "R$ 9.000,00", "R$ 900,00","R$ 450,00","R$ 10.350,00"),
                new Credito("601200024", "R$ 4.000,00", "R$ 400,00","R$ 200,00","R$ 4.600,00")

        };

        // create the grid item mapping
        String[] from = new String[] {"numerocredito", "valorprincipal","valorjuros","valormulta","valortotal"};
        int[] to = new int[] { R.id.numerocredito, R.id.valorprincipal, R.id.valorjuros, R.id.valormulta, R.id.valortotal };

        // prepare the list of all records
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < 6; i++){
            HashMap<String, String> map = new HashMap<String, String>();

            map.put("numerocredito", values[i].getNumerocredito());
            map.put("valorprincipal", "Principal: " + values[i].getValorprincipal() );
            map.put("valorjuros", "Multa: " + values[i].getValormulta());
            map.put("valormulta", "Juros: " + values[i].getValorjuros());
            map.put("valortotal", "Total: " + values[i].getValortotal());

            fillMaps.add(map);
        }

        // fill in the grid_item layout
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.list_credito_item, from, to);

        simpleListView.setAdapter(adapter);

//perform listView item click event
        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                sparseBooleanArray = simpleListView.getCheckedItemPositions();

                String ValueHolder = "" ;

                int i = 0 ;

                while (i < sparseBooleanArray.size()) {

                    if (sparseBooleanArray.valueAt(i)) {

                        ValueHolder += ListViewItems [ sparseBooleanArray.keyAt(i) ] + ",";
                    }

                    i++ ;
                }
            }
        });

        Button verCreditosLeiButton = (Button) findViewById(R.id.lei_button);
        verCreditosLeiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aderirParcelamento();
            }
        });




//        simpleListView = (ListView)findViewById(R.id.listView);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (CreditosActivity.this,
//                        android.R.layout.simple_list_item_multiple_choice,
//                        android.R.id.text1, ListViewItems );
//
//        simpleListView.setAdapter(adapter);
//
//        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // TODO Auto-generated method stub
//
//                sparseBooleanArray = simpleListView.getCheckedItemPositions();
//
//                String ValueHolder = "" ;
//
//                int i = 0 ;
//
//                while (i < sparseBooleanArray.size()) {
//
//                    if (sparseBooleanArray.valueAt(i)) {
//
//                        ValueHolder += ListViewItems [ sparseBooleanArray.keyAt(i) ] + ",";
//                    }
//
//                    i++ ;
//                }
//
//                ValueHolder = ValueHolder.replaceAll("(,)*$", "");
//
//                String valorTotal = "\n\nValor total: "+"1.500,00";
//                String valorDesc = "\n\nValor com desconto: "+"1.000,00";
//
//
//                Toast.makeText(CreditosActivity.this, ValueHolder+valorTotal+valorDesc, Toast.LENGTH_LONG).show();
//
//            }
//        });



    }

    public void aderirParcelamento(){
        Toast.makeText(CreditosActivity.this, "Parcelamento realizado com sucesso. Valor da Guia de GPS gerada: R$ 33.350,00", Toast.LENGTH_LONG).show();
    }


}
