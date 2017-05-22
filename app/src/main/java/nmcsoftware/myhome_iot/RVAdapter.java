package nmcsoftware.myhome_iot;

/**
 * Created by nmc on 5/31/16.
 */
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TankViewHolder> {


    public static class TankViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        static TextView TankName;
        static TextView TankPercent;
        static ProgressBar TankBar;

        TankViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            TankName = (TextView)itemView.findViewById(R.id.tankname);
            TankPercent = (TextView)itemView.findViewById(R.id.tankpercent);
            TankBar = (ProgressBar) itemView.findViewById(R.id.p_tank100);
        }
    }

    List<TankCard> TankCards;

    RVAdapter(List<TankCard> TankCards){
        this.TankCards = TankCards;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public TankViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recyclerview, viewGroup, false);
        TankViewHolder tvh = new TankViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TankViewHolder personViewHolder, int i) {
        TankViewHolder.TankName.setText(TankCards.get(i).tankname);
        TankViewHolder.TankPercent.setText(TankCards.get(i).tankpercent);
        TankViewHolder.TankBar.setProgress(TankCards.get(i).tank_value);
    }

    @Override
    public int getItemCount() {
        return TankCards.size();
    }
}