package dataprev.renegociabrasil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class CreditosActivity extends AppCompatActivity {

    ListView listview ;
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

        listview = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (CreditosActivity.this,
                        android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, ListViewItems );

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                sparseBooleanArray = listview.getCheckedItemPositions();

                String ValueHolder = "" ;

                int i = 0 ;

                while (i < sparseBooleanArray.size()) {

                    if (sparseBooleanArray.valueAt(i)) {

                        ValueHolder += ListViewItems [ sparseBooleanArray.keyAt(i) ] + ",";
                    }

                    i++ ;
                }

                ValueHolder = ValueHolder.replaceAll("(,)*$", "");

                String valorTotal = "\n\nValor total: "+"1.500,00";
                String valorDesc = "\n\nValor com desconto: "+"1.000,00";


                Toast.makeText(CreditosActivity.this, ValueHolder+valorTotal+valorDesc, Toast.LENGTH_LONG).show();

            }
        });

    }

}
