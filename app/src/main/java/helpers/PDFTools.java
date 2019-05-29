package helpers;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
public class PDFTools {
    private static final String TAG = "PDFTools";
    private static final String GOOGLE_DRIVE_PDF_READER_PREFIX = "http://drive.google.com/viewer?url=";
    private static final String PDF_MIME_TYPE = "application/pdf";
    private static final String HTML_MIME_TYPE = "text/html";
    public static void showPDFUrl(final Context context, final String pdfUrl) {
        if (isPDFSupported(context)) {
            downloadAndOpenPDF(context, pdfUrl);
        } else {
            askToOpenPDFThroughGoogleDrive(context, pdfUrl);
        }
    }
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void downloadAndOpenPDF(final Context context, final String pdfUrl) {
        String filename = "";
        try {
            filename = new GetFileInfo().execute(pdfUrl).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // The place where the downloaded PDF file will be put
        final File tempFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), filename);
        Log.e(TAG, "File Path:" + tempFile);
        if (tempFile.exists()) {
            // If we have downloaded the file before, just go ahead and show it.
            openPDF(context, Uri.fromFile(tempFile));
            //
            Uri contentUri = FileProvider.getUriForFile(context, "com.olivine.parjatanbichitra.cholodesh", tempFile);


            openPDF(context, FileProvider.getUriForFile(context, "com.olivine.parjatanbichitra.cholodesh.fileprovider",tempFile));
            //
            return;
        }

        // Show progress dialog while downloading
        final ProgressDialog progress = ProgressDialog.show(context, "hellow", "how", true);

        // Create the download request
        DownloadManager.Request r = new DownloadManager.Request(Uri.parse(pdfUrl));
        r.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, filename);
        final DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        BroadcastReceiver onComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (!progress.isShowing()) {
                    return;
                }
                context.unregisterReceiver(this);

                progress.dismiss();
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                Cursor c = dm.query(new DownloadManager.Query().setFilterById(downloadId));

                if (c.moveToFirst()) {
                    int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                       // openPDF(context, Uri.fromFile(tempFile));

                        Uri contentUri = FileProvider.getUriForFile(context, "com.olivine.parjatanbichitra.cholodesh", tempFile);
                        contentUri.fromFile(tempFile);
                    }
                }
                c.close();
            }
        };
        context.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        // Enqueue the request
        dm.enqueue(r);
    }


    public static void askToOpenPDFThroughGoogleDrive(final Context context, final String pdfUrl) {
        new AlertDialog.Builder(context)
                .setTitle("Download Document")
                .setMessage("Do you want to download document?")
                .setNegativeButton("no", null)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openPDFThroughGoogleDrive(context, pdfUrl);
                    }
                })
                .show();
    }

    public static void openPDFThroughGoogleDrive(final Context context, final String pdfUrl) {
        Intent i = new Intent(Intent.ACTION_VIEW);
      //  i.setDataAndType(Uri.parse(GOOGLE_DRIVE_PDF_READER_PREFIX + pdfUrl), HTML_MIME_TYPE);
        //start
        final File tempFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "test.pdf");

        Uri apkURI = FileProvider.getUriForFile(
                context,
                context.getApplicationContext()
                        .getPackageName() + ".provider",   tempFile);
        i.setDataAndType(apkURI, PDF_MIME_TYPE);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //end

        context.startActivity(i);
    }

    public static final void openPDF(Context context, Uri localUri) {
        final File tempFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "test.pdf");

        Intent i = new Intent(Intent.ACTION_VIEW);
      //  i.setDataAndType(localUri, PDF_MIME_TYPE);
        //
        Uri apkURI = FileProvider.getUriForFile(
                context,
                context.getApplicationContext()
                        .getPackageName() + ".provider", tempFile);
        i.setDataAndType(apkURI, PDF_MIME_TYPE);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //
        context.startActivity(i);
    }

    public static boolean isPDFSupported(Context context) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        final File tempFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "test.pdf");
       // i.setDataAndType(Uri.fromFile(tempFile), PDF_MIME_TYPE);
//
        Uri apkURI = FileProvider.getUriForFile(
                context,
                context.getApplicationContext()
                        .getPackageName() + ".provider", tempFile);
        i.setDataAndType(apkURI, PDF_MIME_TYPE);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        //

        return context.getPackageManager().queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    // get File name from url
    static class GetFileInfo extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            URL url;
            String filename = null;
            try {
                url = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                conn.setInstanceFollowRedirects(false);
                if (conn.getHeaderField("Content-Disposition") != null) {
                    String depo = conn.getHeaderField("Content-Disposition");

                    String depoSplit[] = depo.split("filename=");
                    filename = depoSplit[1].replace("filename=", "").replace("\"", "").trim();
                } else {
                    filename = "download.pdf";
                }
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
            }
            return filename;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // use result as file name
        }
    }
}
