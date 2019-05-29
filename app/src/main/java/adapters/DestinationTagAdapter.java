/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olivine.parjatanbichitra.cholodesh.R;

/**
 * Created by rhythmshahriar on 7/19/17.
 */

public class DestinationTagAdapter extends RecyclerView.Adapter<DestinationViewHolder> {

    private Context mContext;
    private Object[] tags;

    public DestinationTagAdapter(Context mContext, Object[] tags) {
        this.mContext = mContext;
        this.tags = tags;
    }
    @Override
    public DestinationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.layout_tag_list,parent,false);
        DestinationViewHolder inclusionViewHolder=new DestinationViewHolder(view);
        return inclusionViewHolder;

    }

    @Override
    public void onBindViewHolder(DestinationViewHolder holder, int position) {

        Object object=tags[position];
        if(object instanceof String){
            String tag= (String) object;
            holder.tagName.setText(tag);

        }

    }

    @Override
    public int getItemCount() {
        return tags.length ;
    }
}


class DestinationViewHolder extends RecyclerView.ViewHolder{
    TextView tagName;

    public DestinationViewHolder(View itemView) {
        super(itemView);
        tagName= (TextView) itemView.findViewById(R.id.tag);

    }
}