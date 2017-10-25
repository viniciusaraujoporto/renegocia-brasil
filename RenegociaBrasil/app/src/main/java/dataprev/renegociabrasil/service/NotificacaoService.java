package dataprev.renegociabrasil.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dataprev.renegociabrasil.R;
import dataprev.renegociabrasil.TweetActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.support.v7.app.NotificationCompat.*;
import static dataprev.renegociabrasil.R.drawable.renegociabrasil1;

public class NotificacaoService extends Service {

    public NotificacaoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    private boolean estaConectado() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info.isConnected();
    }

    private void criarNotificacao(String usuario, String texto, int id) {
        int icone = R.drawable.ic_launcher;
        String aviso = getString(R.string.aviso);
        long data = System.currentTimeMillis();
        String titulo = usuario + " " + getString(R.string.titulo);
        /*Context context = getApplicationContext();
        Intent intent = new Intent(context, TweetActivity.class);
        intent.putExtra(TweetActivity.USUARIO, usuario.toString());
        intent.putExtra(TweetActivity.TEXTO, texto.toString());*/
        /*PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent, Intent.FILL_IN_ACTION);

        Notification notification = new Notification(icone, aviso, data);
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notification.defaults |= Notification.DEFAULT_SOUND;

        notification.setLatestEventInfo(context, titulo, texto, pendingIntent);

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(ns);
        notificationManager.notify(id, notification);*/

        String msg = "O Governo lança nova Lei de renegociação da dívida previdenciária, informamos " +
                "aos credores a oportunidade de renegociação da dívida.";

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(renegociabrasil1)
                        .setContentTitle("Renegocia Brasil")
                        .setContentText(msg);
// Creates an explicit intent for an Activity in your app
        Intent intent = new Intent(this, TweetActivity.class);
        intent.putExtra(TweetActivity.USUARIO, usuario.toString());
        intent.putExtra(TweetActivity.TEXTO, texto.toString());

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        //stackBuilder.addParentStack(ResultActivity.class);
        stackBuilder.addParentStack(TweetActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(id, mBuilder.build());

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //nossa implementação será feita aqui

        /*ScheduledThreadPoolExecutor pool =
                new ScheduledThreadPoolExecutor(1);
        long delayInicial = 0;
        long periodo = 10;
        TimeUnit unit = TimeUnit.MINUTES;
        pool.scheduleAtFixedRate(new NotificacaoTask(),
                delayInicial, periodo,unit);*/

        String texto = "O Governo lança nova Lei de renegociação da dívida previdenciária, informamos " +
                "aos credores a oportunidade de renegociação da dívida.";
        final Random numRandomico = new Random();
        criarNotificacao("Renegocia Brasil" , texto, numRandomico.nextInt(10));

        /*NotificacaoTask rc = new NotificacaoTask();
        Thread t1 = new Thread(rc);
        t1.start();
*/
        return START_STICKY;
    }

    private class NotificacaoTask implements Runnable {
        @Override
        public void run() {

            final Random numRandomico = new Random();

            criarNotificacao("teste" , "teste", numRandomico.nextInt(10));
        }
    }
}
