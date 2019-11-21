package sheridan.iftikhar.project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sheridan.iftikhar.project.Room.FreeWeight;
import sheridan.iftikhar.project.Room.Jog;

public class FreeWeightListAdapter extends RecyclerView.Adapter<FreeWeightListAdapter.FreeWeightListViewHolder> {

    public class FreeWeightListViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDate, tvRepetions,tvPounds;

        //revisit this if i run into bugs
        public FreeWeightListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvRepetions = itemView.findViewById(R.id.tvRepetions);
            tvPounds= itemView.findViewById(R.id.tvPounds);
        }
    }

    private final LayoutInflater mInflater;
    private List<FreeWeight> mFreeWeights;

    //why isnt this being used wth?
    FreeWeightListAdapter(Context context){ mInflater = LayoutInflater.from(context); }

    //duno what this is for?
    void setFreeWeights(List<FreeWeight> freeWeights){
        mFreeWeights = freeWeights;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FreeWeightListAdapter.FreeWeightListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.free_weight_item, parent, false);
        //itemView.setOnClickListener(V->GoEdit());
        return new FreeWeightListAdapter.FreeWeightListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FreeWeightListAdapter.FreeWeightListViewHolder holder, int position) {
        if(mFreeWeights != null){
            FreeWeight current = mFreeWeights.get(position);
            holder.tvPounds.setText(Integer.toString(current.getFreeWeightPounds())); //convert to string if error?
            holder.tvDate.setText(current.getFreeWeightDate()); //convert to string if error?
            holder.tvRepetions.setText(Integer.toString(current.getFreeWeightRepitions())); //convert to string if error?
        }
        else{
            holder.tvRepetions.setText("No repetitions set"); //convert to string if error?
            holder.tvDate.setText("No date set"); //convert to string if error?
            holder.tvPounds.setText("No pounds set"); //convert to string if error?
        }
        holder.itemView.setOnClickListener(v->GoEdit(holder,position));
    }

     void GoEdit(FreeWeightListAdapter.FreeWeightListViewHolder holder, int position){
        Bundle mBundle = new Bundle();
        mBundle.putInt("p", position);

        //reminder to update this navigation
        Navigation.findNavController(holder.itemView).navigate(R.id.action_showJogFragment_to_editJogFragment, mBundle);

         //mNavController.navigate(R.id.action_showJogFragment_to_editJogFragment, mBundle);

    }

    @Override
    public int getItemCount() {
        if(mFreeWeights != null){
            return mFreeWeights.size();
        }
        return 0;
    }


}
