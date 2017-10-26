package dataprev.renegociabrasil;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataprev.renegociabrasil.database.DataAdapter;
import dataprev.renegociabrasil.database.DataBaseHelper;
import dataprev.renegociabrasil.provider.CnpjProvider;

public class ContribuintesActivity extends AppCompatActivity  {

    private static final String TAG = "ContribuintesActivity";
    ListView simpleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribuintes);
        String passedArg = getIntent().getExtras().getString("user");
        simpleListView=(ListView)findViewById(R.id.listView);


        final Contribuinte[] values = new Contribuinte[] { new Contribuinte("013.568.224-01", "Vinicius de Araujo Porto", true),
                                 new Contribuinte("01.067.479/0001-46", "Empresa Nápoles", true),
                                 new Contribuinte("01.065.846/0001-72", "Empresa Goianesia", false),};

        // create the grid item mapping
        String[] from = new String[] {"icon", "numeroinscricao","nome"};
        int[] to = new int[] { R.id.icon, R.id.numeroinscricao, R.id.nome };

        // prepare the list of all records
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < 3; i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("icon", values[i].isPossuidebitos() ? String.valueOf(R.drawable.go) : String.valueOf(R.drawable.stop));
            map.put("numeroinscricao", values[i].getNumeroinscricao() );
            map.put("nome", values[i].getNome());



            fillMaps.add(map);
        }

        // fill in the grid_item layout
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.list_item, from, to);
        simpleListView.setAdapter(adapter);

//perform listView item click event
        simpleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();.makeText(getApplicationContext(),fruitsNames[i],Toast.LENGTH_LONG).show();//show the selected image in toast according to position
                Intent intent = new Intent(getApplicationContext(), LeisActivity.class);
                intent.putExtra("nome", values[i].getNome());
                intent.putExtra("numeroInscricao", values[i].getNumeroinscricao());
                startActivity(intent);
            }
        });
    }


}
