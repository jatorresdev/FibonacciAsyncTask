package co.angeltorres.fibonacciasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.texto);
        button = (Button) findViewById(R.id.boton);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        progressBar.setMax(100);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Sucesión de Fibonacci \n");
                new MyAsyncTask().execute(19);
            }
        });
    }

    public void fibonacci(int numero) {
        int fibo1 = 0;
        int fibo2 = 1;
        int copia = 0;

        textView.setText(textView.getText() + "0,");
        for (int i = 1; i <= numero; i++) {
            textView.setText(textView.getText() + String.valueOf(fibo2) + ", ");

            copia = fibo2;
            fibo2 = fibo1 + fibo2;
            fibo1 = copia;
        }

        textView.setText(textView.getText() + "\n");
    }

    public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... params) {
            int max = params[0];
            for (int i = 1; i <= max; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return "Fin";

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int contador = values[0];
            progressBar.setProgress(values[0]);

            fibonacci(contador);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
