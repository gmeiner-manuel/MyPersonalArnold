package at.htl.personalarnold.model;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.example.personalarnold.R;

import at.htl.personalarnold.controller.DashboardScreen;

public class NotificationDisplayService extends Service {
    final int NOTIFICATION_ID = 10;

    public NotificationDisplayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        displayNotification("Test-Notification", "Dies ist eine Test-Notification mit einem sher langem Text den man ausklappen muss");
        return super.onStartCommand(intent, flags, startId);
    }

    public void displayNotification(String title, String text) {

        Intent notification = new Intent(this, DashboardScreen.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, 0, notification, 0);

        NotificationCompat.Builder notifications = new NotificationCompat.Builder(this).setContentTitle(title)
                .setContentText(text)
                //Bild bei Notification
                .setSmallIcon(R.drawable.personalarnoldlogotransperent)
                //Vibration
                .setVibrate(new long[]{0, 300, 300, 300})
                //Um darauf hinzuweisen was es ausführen soll bei einem Klick
                .setContentIntent(notificationPendingIntent)
                // Damit sich Notification schließt nach Öffnung
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                //Damit man die ganze Notification lesen kann und sie nicht abgekürzt wird falls sie zu lang ist
                .setStyle(new NotificationCompat.BigTextStyle().bigText(text));

        NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationmanager.notify(NOTIFICATION_ID, notifications.build());
    }
}