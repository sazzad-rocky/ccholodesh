package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import model.Youtube;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder>  {
    private Context context;
    private List<Youtube> youtubes;

    public YoutubeAdapter(Context context, List<Youtube> youtubes) {
        this.context = context;
        this.youtubes = youtubes;
    }

    @Override
    public YoutubeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(YoutubeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return youtubes.size();
    }

    public class YoutubeViewHolder extends ViewHolder {


        View convertview;
        public YoutubeViewHolder(View itemView) {
            super(itemView);
            this.convertview = itemView;
        }
    }

}
