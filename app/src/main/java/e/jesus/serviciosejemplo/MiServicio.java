package e.jesus.serviciosejemplo;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jesus on 27/12/2017.
 */

public class MiServicio extends Service {

    MiTarea miTarea;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Servicio Creado", Toast.LENGTH_SHORT).show();
        miTarea = new MiTarea();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        miTarea.execute();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Servicio Destruido", Toast.LENGTH_SHORT).show();
        miTarea.cancel(true);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MiTarea extends AsyncTask<Nullable, String, String>{


        private DateFormat dateFormat;
        private String date;
        private boolean cent;

        @Override
        protected String doInBackground(Nullable... nullables) {

            while (cent){

                date = dateFormat.format(new Date());

                try {
                    publishProgress(date);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dateFormat = new SimpleDateFormat("HH:mm:ss");
            cent = true;

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            Toast.makeText(MiServicio.this, "Hora Actual es: " + values[0], Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            cent = false;
        }
    }
}
