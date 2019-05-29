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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;
import com.olivine.parjatanbichitra.cholodesh.ShowPopUp;
import com.squareup.picasso.Picasso;

import helpers.BaseURL;
import model.DestinationAttraction;

/**
 * Created by rhythmshahriar on 7/19/17.
 */

public class AttractionAdapter extends RecyclerView.Adapter<ViewHolderAttraction> {

    private Context mContext;
    private DestinationAttraction[] destinationAttractions;


    public AttractionAdapter(Context mContext, DestinationAttraction[] destinationAttractions) {
        this.mContext = mContext;
        this.destinationAttractions = destinationAttractions;

    }

//

    @Override
    public ViewHolderAttraction onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_attraction,parent,false);
        ViewHolderAttraction viewHolder=new ViewHolderAttraction(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolderAttraction holder, int position) {

        Object object=destinationAttractions[position];
        if(object instanceof DestinationAttraction){
            final DestinationAttraction destinationAttraction= (DestinationAttraction) object;

            if (BaseURL.LANGUAGE_ENG)
            {
                holder.attractionName.setText(destinationAttraction.getDestinationAttractionTitle());
               // details= destinationAttraction.getDestinationAttractionDetails();
            }
            else if (!BaseURL.LANGUAGE_ENG && destinationAttraction.getDestinationAttractionTitleBn() != null)
            {
                holder.attractionName.setText(destinationAttraction.getDestinationAttractionTitleBn());
               // details= destinationAttraction.getDestinationAttractionDetailsBn();
            }
            else
            {
                holder.attractionName.setText(destinationAttraction.getDestinationAttractionTitle());
                //details= destinationAttraction.getDestinationAttractionDetailsBn();
            }

            String url= BaseURL.DESTINATION_ATTRACTION_IMAGE_BASE_URL+destinationAttraction.getDestinationAttractionImage();

            Picasso.with(mContext)
                    .load(url)
                    .placeholder(R.drawable.image_placeholder)
                    .fit()
                    .into(holder.attractionImage);




            final String details = destinationAttraction.getDestinationAttractionDetails();
            final String detailsBn = destinationAttraction.getDestinationAttractionDetailsBn();
            holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popup=new Intent(mContext,ShowPopUp.class);
                if (BaseURL.LANGUAGE_ENG)popup.putExtra("DETAILS_ATTRACTION",details);
                else if (detailsBn != null && !BaseURL.LANGUAGE_ENG) popup.putExtra("DETAILS_ATTRACTION",detailsBn);
                else
                {
                    popup.putExtra("DETAILS_ATTRACTION",details);
                }

                //popup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(popup);
            }
        });

        }

    }

    @Override
    public int getItemCount() {
        return destinationAttractions.length ;
    }


}

class ViewHolderAttraction extends RecyclerView.ViewHolder
{
    TextView attractionName;
    ImageView attractionImage;
    LinearLayout parent;

    public ViewHolderAttraction (View itemView)
    {
        super(itemView);
        attractionName= (TextView) itemView.findViewById(R.id.attractionName);
        attractionImage= (ImageView) itemView.findViewById(R.id.attractionImage);
        parent = (LinearLayout) itemView.findViewById(R.id.parent);
    }



}
