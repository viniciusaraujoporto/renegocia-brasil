package dataprev.renegociabrasil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dataprev.renegociabrasil.service.DetalheActivity;

public class TweetActivity extends AppCompatActivity {

    public static final String TEXTO = "texto";
    public static final String USUARIO = "usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);
        TextView usuarioTextView =
                (TextView) findViewById(R.id.usuario);
        TextView textoTextView = (TextView) findViewById(R.id.texto);
        String usuario = getIntent().getStringExtra(USUARIO);
        String texto = getIntent().getStringExtra(TEXTO);
        usuarioTextView.setText(usuario);
        textoTextView.setText(texto);
    }

    public void sendNotification(View view) {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}