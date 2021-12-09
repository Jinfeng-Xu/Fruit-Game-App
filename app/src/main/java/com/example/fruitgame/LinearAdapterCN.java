package com.example.fruitgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinearAdapterCN extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private OnItemClickListener listener;
    private LinearViewHolder linearViewHolder;
    private String[] fruitName = new String[]{"橙子","苹果","梨","草莓"};


    public LinearAdapterCN(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    /**
     * item显示类型
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        linearViewHolder = new LinearAdapterCN.LinearViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_linear_item, parent,false));
        return linearViewHolder;
    }

    /**
     * 数据的绑定显示
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((LinearViewHolder)holder).textView.setText(fruitName[position]);
        if(position==0) ((LinearViewHolder)holder).imageView.setImageResource(R.drawable.fruit_orange);
        if(position==1) ((LinearViewHolder)holder).imageView.setImageResource(R.drawable.fruit_apple);
        if(position==2) ((LinearViewHolder)holder).imageView.setImageResource(R.drawable.fruit_pear);
        if(position==3) ((LinearViewHolder)holder).imageView.setImageResource(R.drawable.fruit_strawberry);
            //重写点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position);
                }
            });
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return 0;
        else if (position==1)
            return 1;
        else if (position==2)
            return 2;
        else
            return 3;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.iv_image);
        }
    }

    //点击事件接口
    public interface OnItemClickListener{
        void onClick(int pos);
    }
}
