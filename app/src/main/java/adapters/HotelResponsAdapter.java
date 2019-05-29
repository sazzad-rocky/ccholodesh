package adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.R;

import java.util.ArrayList;
import java.util.List;

import helpers.BaseURL;
import model.ConfirmOperator;
import model.HotelRespons;
import model.OperatorConfirmCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelResponsAdapter extends RecyclerView.Adapter<HotelResponsViewholder> {
    private Context mContext;
    private List<HotelRespons> hotelRespons;
    ProgressDialog progressDialog;
private ArrayList <HotelRespons> hotelResponstwo;
private int count=0;
    private boolean token = false;
    int i = 0;
    int check=0;
    OperatorConfirmCallback callback;
    public HotelResponsAdapter(Context mContext, List<HotelRespons> hotelRespons) {
        this.mContext = mContext;
        this.hotelRespons = hotelRespons;
    }


    @Override
    public HotelResponsViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_row_hotel_respons, parent, false);
        HotelResponsViewholder hotelResponsViewholder = new HotelResponsViewholder(view);
        callback = new OperatorConfirmCallback((Activity) mContext);
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCanceledOnTouchOutside(false);

        return hotelResponsViewholder;
    }


    @Override
    public void onBindViewHolder(final HotelResponsViewholder holder, int position) {
        final HotelRespons respons = hotelRespons.get(position);
        int idd=Integer.parseInt(respons.gettailormade_confirm_status());
        if (idd==1){
            check=1;
        }
        // final HotelRespons accommodationProvider=hotelRespons[position];
        //   String providerName = accommodationProvider.gettailormade_operator_name();
        final ConfirmOperator confirmOperator = new ConfirmOperator();
        confirmOperator.settailormade_customer_id(respons.gettailormade_customer_id());
        confirmOperator.settailormade_id(respons.gettailormade_id());
        confirmOperator.settailormade_operator_id(respons.gettailormade_operator_id());
        confirmOperator.settailormade_reply_id(respons.gettailormade_reply_id());



       // Toast.makeText(mContext, "status"+idd, Toast.LENGTH_SHORT).show();
        if (!BaseURL.LANGUAGE_ENG) {
            //  cost = BanglaNumberParser.getBangla(cost);
        }
        //     holder.cost.setText(cost+ " ৳");
        //Toast.makeText(mContext,respons.getTailormadeDays()+" days Trip from "+ respons.getTailormadeDepartDistrictName()+ " to "+respons.getTailormadeDestinationDistrictName(),Toast.LENGTH_SHORT).show();
//        if (idd==1){
           holder.tailormade_operator_name.setText(respons.gettailormade_operator_name());
//          //  hotelResponstwo.add(respons);
//            count=count+1;
//        }
        if (idd==1 && check==1){
            holder.submit.setText("Confirmed");
            holder.submit.setEnabled(false);
        }
        if (check==1){
            holder.submit.setEnabled(false);
        }else {
            holder.submit.setEnabled(true);
        }
        final String url = respons.getreply_file();
        if (url.length()<5){
            holder.attachment.setVisibility(View.GONE);
        }
        holder.attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url.length()>5) {
//                    mContext.startActivity(new Intent(mContext, PdfActivity.class)
//                    .putExtra("pdfurl","https://www.adobe.com/content/dam/acom/en/devnet/acrobat/pdfs/pdf_open_parameters.pdf"));
////                //  PDFTools.showPDFUrl(mContext, "http://cholodeshbd.twistermedia.com/" + url);

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://cholodesh.com/" + url));
                    try {
                       mContext.startActivity(Intent.createChooser(intent, "Choose Your Browser..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(mContext, "No Browser Clients Installed.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(mContext, "No attachment found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.tailormade_reply_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callReady(respons.gettailormade_reply_message(), respons.gettailormade_operator_name());

            }
        });
        if (holder.submit.getText() != "Confirmed") {
            holder.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Confirm operator")
                            .setMessage("Do you want to confirm this operator ?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    HotelRespons hotelRespons = new HotelRespons();
                                    hotelRespons.setConfarmation_massage(respons.gettailormade_operator_id());
                                    holder.submit.setText("Confirmed");
                                    holder.submit.setEnabled(false);
                                    token = true;
                                    //start
                                    progressDialog.setMessage("Loading.....");
                                    progressDialog.show();
                                    callback.confirmOperator(confirmOperator).enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            if (response.body() != null) {
                                                String meesage = "Your Oparator selection succesful.";
                                                if (!BaseURL.LANGUAGE_ENG) {
                                                    meesage = " আপনার অপারেটর নির্বাচন সফলভাবে হয়েছে  ";
                                                }
                                                progressDialog.cancel();
                                                Toast.makeText(mContext, meesage, Toast.LENGTH_LONG).show();
                                                ((Activity) mContext).finish();

                                                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                                builder.setMessage(meesage);
                                                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        builder.setCancelable(true);
                                                    }
                                                });
                                                builder.show();


                                            } else {
                                                String meesage = "Something Went Wrong";
                                                if (!BaseURL.LANGUAGE_ENG) {
                                                    meesage = " কিছু ভুল হয়েছে";
                                                }
                                                Toast.makeText(mContext, meesage, Toast.LENGTH_LONG).show();
                                                //Toast.makeText(HotelRoomListActivity.this,"Null",Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });


                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //do nothing
                                }
                            })
                            .show();

                }
            });
        }
//        holder.travellers.setText(respons.getTailormadePerson()+ "Traveler(s)");
//        holder.active.setText(respons.gettailormade_confirm_status());
//
//        holder.dates.setText("Start Date: "+respons.getTailormadeFromDate() + "\n" + "Return Date: "+respons.getTailormadeToDate());

//        holder.parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                TailorMadeDataHolder.clearAll();
////                TailorMadeDataHolder.tailorMadeId = respons.getTailormadeId();
//////                Toast.makeText(mContext, ""+respons.gettailormade_confirm_status(), Toast.LENGTH_SHORT).show();
//////                Toast.makeText(mContext, ""+respons.gettailormade_confirm_message(), Toast.LENGTH_LONG).show();
////
////                TailorMadeDataHolder.return_status=respons.gettailormade_confirm_status();
////                TailorMadeDataHolder.return_massage= respons.gettailormade_confirm_message();
////
////
////                TailorMadeDataHolder.Status = respons.status;
////                TailorMadeDataHolder.noOfDays = respons.getTailormadeDays();
////                TailorMadeDataHolder.noOfTourists = respons.getTailormadePerson();
////                TailorMadeDataHolder.departDate = respons.getTailormadeFromDate();
////                TailorMadeDataHolder.returnDate = respons.getTailormadeToDate();
////                TailorMadeDataHolder.providerName = respons.providerName;
////                TailorMadeDataHolder.providerImage = respons.providerImage;
////                TailorMadeDataHolder.confDate = respons.confirmationDate;
////                TailorMadeDataHolder.biddingAmount = respons.biddingAmount;
////                TailorMadeDataHolder.noOfChildren = respons.noOfChildren;
////                TailorMadeDataHolder.childrenDesc = respons.childrenDesc;
////                TailorMadeDataHolder.makeRoutes(respons.getTailormadeId(),(Activity) mContext);
////                Intent intent = new Intent(mContext,TailorMadeActivity.class);
////                intent.putExtra("TAILOR_MADE_ID",respons.getTailormadeId()+"");
////                intent.putExtra("DESTINATION_IMAGE_PATH",url);
////                String cost = respons.getTailormadeTotalCost();
//
////                if (!BaseURL.LANGUAGE_ENG){
////
////                    cost = BanglaNumberParser.getBangla(cost);
////                }
////                intent.putExtra("COST_TOTAL",cost);
////                mContext.startActivity(intent);
//
//
//            }
//        });

    }

    public void callReady(String str, String hotel_name) {
        ArrayList<HotelRespons> rooms = new ArrayList<>();
        final Dialog dialog = new Dialog(mContext);
        //, android.R.style.Theme_Black_NoTitleBar_Fullscreen
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.hotel_call_back);
        TextView msg = (TextView) dialog.findViewById(R.id.msg);
        TextView hotel_namee = (TextView) dialog.findViewById(R.id.hotelNamer);
        hotel_namee.setText(hotel_name);
        String massage = Html.fromHtml(str).toString();
        msg.setText(massage);


        final Button confarm, cancel;
        confarm = (Button) dialog.findViewById(R.id.ok);
        confarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        dialog.show();

    }

    @Override
    public int getItemCount() {

        if (count!=0){
            return count;
        }
        else {
            return hotelRespons.size();
        }

    }


}

class HotelResponsViewholder extends RecyclerView.ViewHolder {
    // RelativeLayout parent;
    TextView tailormade_operator_name;
    Button tailormade_reply_message;
    Button attachment;
    Button submit;

//    TextView dayNight;
//    TextView travellers;
//    ImageView imageViewLoad;
//    TextView dates;
//    TextView active;

    public HotelResponsViewholder(View itemView) {
        super(itemView);
        //  parent= (RelativeLayout) itemView.findViewById(R.id.parent);
        tailormade_operator_name = (TextView) itemView.findViewById(R.id.tailormade_operator_name);
        tailormade_reply_message = (Button) itemView.findViewById(R.id.tailormade_reply_message);
        submit = (Button) itemView.findViewById(R.id.submitt);
        attachment = (Button) itemView.findViewById(R.id.attachment);
        tailormade_reply_message.setText("Detail's");

//
//        dayNight = (TextView) itemView.findViewById(R.id.dayNight);
//        travellers= (TextView) itemView.findViewById(R.id.travellers);
//        imageViewLoad = (ImageView) itemView.findViewById(R.id.imageViewLoad);
//        dates = (TextView) itemView.findViewById(R.id.dates);
//
//        active = (TextView) itemView.findViewById(R.id.active);

    }
}
