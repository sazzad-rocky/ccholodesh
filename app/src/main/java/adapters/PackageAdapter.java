/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.olivine.parjatanbichitra.cholodesh.PackageDetailsActivity;
import com.olivine.parjatanbichitra.cholodesh.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import constants.Travel;
import helpers.BanglaNumberParser;
import helpers.BaseURL;
import helpers.PDFTools;
import model.Package;

/**
 * Created by Olivine on 6/12/2017.
 */

public class PackageAdapter extends RecyclerView.Adapter<PackageViewHolder> {
    private Context mContext;
    private Package [] packages;
    String pdfdownload;


    public PackageAdapter(Context mContext, Package[] packages) {
        this.mContext = mContext;
        this.packages = packages;
    }


    @Override
    public PackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_packagelist_new,parent,false);
        PackageViewHolder viewholder=new PackageViewHolder(view);
        //Toast.makeText(mContext,"this",Toast.LENGTH_LONG).show();
        return viewholder;
    }

    @Override
    public void onBindViewHolder(PackageViewHolder holder, int position) {

        final Package tourPackage=packages[position];

        String packageName = tourPackage.getPackageName();
        pdfdownload = tourPackage.getpackage_file();



        String locFrom = tourPackage.getPackageLocationFrom();
        String to = " To ";
        String locTo = tourPackage.getPackageLocationTo();
        String destName = tourPackage.getDestinationName();
        String packageDay = tourPackage.getPackageDay();
        String day = " Days ";
        String packageNight = tourPackage.getPackageNight();
        String night = " Night";
        String price = tourPackage.getPackagePrice();
        String by = "By ";
        String providerName = tourPackage.getProviderName();
        String discount = tourPackage.getPackageDiscount();
        String discountPrice = tourPackage.packageDiscountPrice;
        String disCountDflt = "0";

        if (!BaseURL.LANGUAGE_ENG){

            packageName = tourPackage.packageNameBn;
            locFrom = tourPackage.locationFromBn;
            to  = " থেকে ";
            locTo = tourPackage.locationToBn;
            destName = tourPackage.destinationNameBn;
            packageDay = BanglaNumberParser.getBangla(packageDay);
            day = " দিন ";
            packageNight = BanglaNumberParser.getBangla(packageNight);
            night = " রাত";
            price = BanglaNumberParser.getBangla(price);
            providerName = tourPackage.providerNameBn;
            discount = BanglaNumberParser.getBangla(discount);
            discountPrice = BanglaNumberParser.getBangla(discountPrice);
            by = "";
            disCountDflt = BanglaNumberParser.getBangla(disCountDflt);

            holder.pckgCodForLang.setText("প্যাকেজ কোড  :");
            holder.pckgNamForLang.setText("প্যাকেজের নাম :");
            holder.placeeForLang.setText("জায়গা  :");
            holder.priceForLang.setText("মূল্য    :");
            holder.discountForLang.setText("ডিসকাউন্ট    :");
            holder.discountPrcForLang.setText("ডিসকাউন্ট মূল্য  :");
            holder.btnDetails.setText("  সফর পরিকল্পনা  ");


        }


        holder.packageName.setText(packageName);


        holder.tourPlace.setText(locFrom+to+locTo);
        holder.destination.setText(""+destName);
        holder.dayNight.setText(packageDay+day+packageNight+night);
        holder.packageCost.setText(price+"৳");
        holder.operatorName.setText(by+providerName);
        holder.code.setText(tourPackage.packageCode);
        if (tourPackage.getPackageDiscount().length() > 0)
            holder.discount.setText( discount+ "%");
        else
            holder.discount.setText(disCountDflt + "%");
        holder.discountPrice.setText(discountPrice + "৳");
        String url= BaseURL.PACKAGE_IMAGE_BASE_URL+tourPackage.getPackageGalleryImage();


        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .fit()
                .into(holder.test);



        holder.packageParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent packageDetailsIntent=new Intent(mContext,PackageDetailsActivity.class);
                packageDetailsIntent.putExtra(Travel.PACKAGE_KEY,tourPackage.getPackageId());
                packageDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                packageDetailsIntent.putExtra("IMAGE_URL",tourPackage.getPackageGalleryImage());
                packageDetailsIntent.putExtra("PDF_URL",pdfdownload);
                mContext.startActivity(packageDetailsIntent);
            }
        });

        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent packageDetailsIntent=new Intent(mContext,PackageDetailsActivity.class);
                packageDetailsIntent.putExtra(Travel.PACKAGE_KEY,tourPackage.getPackageId());
                packageDetailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                packageDetailsIntent.putExtra("IMAGE_URL",tourPackage.getPackageGalleryImage());
                mContext.startActivity(packageDetailsIntent);

            }
        });


    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return packages.length;
    }

}

class PackageViewHolder extends RecyclerView.ViewHolder{
    TextView packageName;
    TextView tourPlace;
    TextView operatorName;
    TextView destination;
    TextView dayNight;
    TextView packageCost;
    ImageView test;
    LinearLayout packageParent;
    Button btnDetails;
    TextView code;
    TextView discount;
    TextView discountPrice;

    TextView pckgCodForLang;
    TextView pckgNamForLang;
    TextView placeeForLang;
    TextView priceForLang;
    TextView discountForLang;
    TextView discountPrcForLang;

    public PackageViewHolder(View itemView)
    {
        super(itemView);

        packageName = (TextView) itemView.findViewById(R.id.packageName);
        tourPlace= (TextView) itemView.findViewById(R.id.tourPlace);
        operatorName= (TextView) itemView.findViewById(R.id.operatorName);
        destination= (TextView) itemView.findViewById(R.id.destination);
        dayNight= (TextView) itemView.findViewById(R.id.dayNight);
        packageCost= (TextView) itemView.findViewById(R.id.packageCost);
        packageParent= (LinearLayout) itemView.findViewById(R.id.packageParent);
        test= (ImageView) itemView.findViewById(R.id.imageViewLoad);
        btnDetails = (Button) itemView.findViewById(R.id.btnDetails);
        code = (TextView) itemView.findViewById(R.id.packageCode);
        discount = (TextView) itemView.findViewById(R.id.discount);
        discountPrice = (TextView) itemView.findViewById(R.id.discountPrice);
        pckgCodForLang = (TextView) itemView.findViewById(R.id.pckgCodForLang);
        pckgNamForLang = (TextView) itemView.findViewById(R.id.pckgNamForLang);
        placeeForLang = (TextView) itemView.findViewById(R.id.placeeForLang);
        priceForLang = (TextView) itemView.findViewById(R.id.priceForLang);
        discountForLang = (TextView) itemView.findViewById(R.id.discountForLang);
        discountPrcForLang = (TextView) itemView.findViewById(R.id.discountPrcForLang);
    }


}
