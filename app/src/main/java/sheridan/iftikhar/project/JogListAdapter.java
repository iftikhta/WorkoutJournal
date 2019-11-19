package sheridan.iftikhar.project;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JogListAdapter extends RecyclerView.Adapter<JogListAdapter.JogListViewHolder> {

    public class JogListViewHolder extends RecyclerView.ViewHolder {
        public JogListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public JogListAdapter.JogListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull JogListAdapter.JogListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
