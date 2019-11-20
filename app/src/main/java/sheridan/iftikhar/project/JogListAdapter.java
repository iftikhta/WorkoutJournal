package sheridan.iftikhar.project;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sheridan.iftikhar.project.Room.Jog;

public class JogListAdapter extends RecyclerView.Adapter<JogListAdapter.JogListViewHolder> {

    public class JogListViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvDate, tvDuration,tvIntensity;

        //revisit this if i run into bugs
        public JogListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvIntensity= itemView.findViewById(R.id.tvIntensity);

        }
    }

    private final LayoutInflater mInflater;
    private List<Jog> mJogs;

    //why isnt this being used wth?
    JogListAdapter(Context context){ mInflater = LayoutInflater.from(context); }

    //duno what this is for?
    void setJogs(List<Jog> jogs){
        mJogs = jogs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JogListAdapter.JogListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.jog_item, parent, false);
        //itemView.setOnClickListener(V->GoEdit());
        return new JogListAdapter.JogListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JogListAdapter.JogListViewHolder holder, int position) {
        if(mJogs != null){
            Jog current = mJogs.get(position);
            holder.tvIntensity.setText(Integer.toString(current.getJogIntensity())); //convert to string if error?
            holder.tvDate.setText(current.getJogDate()); //convert to string if error?
            holder.tvDuration.setText(Integer.toString(current.getJogDuration())); //convert to string if error?
        }
        else{
            holder.tvIntensity.setText("No intensity set"); //convert to string if error?
            holder.tvDate.setText("No date set"); //convert to string if error?
            holder.tvDuration.setText("No Duration set"); //convert to string if error?
        }
        holder.itemView.setOnClickListener(v->GoEdit(holder,position));
    }

     void GoEdit(JogListAdapter.JogListViewHolder holder, int position){
        Bundle mBundle = new Bundle();
        mBundle.putInt("p", position);
        Navigation.findNavController(holder.itemView).navigate(R.id.action_showJogFragment_to_editJogFragment, mBundle);

         //mNavController.navigate(R.id.action_showJogFragment_to_editJogFragment, mBundle);

    }

    @Override
    public int getItemCount() {
        if(mJogs != null){
            return mJogs.size();
        }
        return 0;
    }


}
