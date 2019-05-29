package helpers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Olivine on 5/10/2017.
 */

public class ViewAssist {
    static  Calendar c = Calendar.getInstance();

    static final int year = c.get(Calendar.YEAR);
    static final int month = c.get(Calendar.MONTH);
    static final int day = c.get(Calendar.DAY_OF_MONTH);
    public static void setDate(Context mContext, final TextView textView, final String title){
        TextView txtView=textView;
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear + 1;
                if(month < 10 && dayOfMonth >= 10){
                    textView.setText(dayOfMonth + "-0" + month + "-" + year);
                }
                if(dayOfMonth < 10 && month >= 10) {
                    textView.setText( "0" +dayOfMonth + "-" + month+ "-" + year);
                }
                if(dayOfMonth < 10 && month < 10) {
                    textView.setText("0" + dayOfMonth + "-0" + month +"-"+ year);
                }
                if(month >= 10 && dayOfMonth >= 10){
                    textView.setText(dayOfMonth + "-" + month + "-" +year );
                }



            }
        }, year, month, day);
        datePickerDialog.setTitle(title);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();

    }
    public static void setDate(Context mContext, final TextView firstTextView, final TextView secondTextView, final int difference, final String title){
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear + 1;
                String chosenDate="";
                if(month < 10 && dayOfMonth >= 10){
                    firstTextView.setText(dayOfMonth + "-0" + month + "-" + year);
                    chosenDate=dayOfMonth + "-0" + month + "-" + year;
                }
                if(dayOfMonth < 10 && month >= 10) {
                    firstTextView.setText( "0" +dayOfMonth + "-" + month+ "-" + year);
                    chosenDate="0" +dayOfMonth + "-" + month+ "-" + year;
                }
                if(dayOfMonth < 10 && month < 10) {
                    firstTextView.setText("0" + dayOfMonth + "-0" + month +"-"+ year);
                    chosenDate="0" +dayOfMonth + "-" + month+ "-" + year;
                }
                if(month >= 10 && dayOfMonth >= 10){
                    firstTextView.setText(dayOfMonth + "-" + month + "-" +year );
                    chosenDate="0" +dayOfMonth + "-" + month+ "-" + year;
                }

                Calendar calenderInstance = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                try {
                    calenderInstance.setTime(simpleDateFormat.parse(chosenDate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calenderInstance.add(Calendar.DATE, difference);
                String newDate=simpleDateFormat.format(calenderInstance.getTime());
                if (!BaseURL.LANGUAGE_ENG)
                {
                    firstTextView.setText(BanglaNumberParser.getBangla(firstTextView.getText().toString()));
                    secondTextView.setText(BanglaNumberParser.getBangla(newDate));
                }
                else secondTextView.setText(newDate);




            }
        }, year, month, day);
        datePickerDialog.setTitle(title);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}
