package robertosantin.nice.guarita.ip.nice_guarita_ip_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTaskParams taskParams = new NetworkTaskParams(2, 0, 1, 1);

                new NetworkTask() {
                    @Override
                    protected void onPostExecute(Boolean sucesso) {
                        if (sucesso) {
                            Toast.makeText(getApplicationContext(), button1.getText() + " acionado com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Falha ao acionar " + button1.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(taskParams);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTaskParams taskParams = new NetworkTaskParams(2, 1, 2, 1);

                new NetworkTask() {
                    @Override
                    protected void onPostExecute(Boolean sucesso) {
                        if (sucesso) {
                            Toast.makeText(getApplicationContext(), button2.getText() + " acionado com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Falha ao acionar " + button2.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(taskParams);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTaskParams taskParams = new NetworkTaskParams(3, 0, 1, 1);

                new NetworkTask() {
                    @Override
                    protected void onPostExecute(Boolean sucesso) {
                        if (sucesso) {
                            Toast.makeText(getApplicationContext(), button3.getText() + " acionado com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Falha ao acionar " + button3.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(taskParams);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkTaskParams taskParams = new NetworkTaskParams(3, 0, 2, 1);

                new NetworkTask() {
                    @Override
                    protected void onPostExecute(Boolean sucesso) {
                        if (sucesso) {
                            Toast.makeText(getApplicationContext(), button4.getText() + " acionado com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Falha ao acionar " + button4.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(taskParams);
            }
        });

    }
}