package com.example.creacion_modelos;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.creacion_modelos.models.Material;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MaterialRecyclingAdapter extends RecyclerView.Adapter<MaterialRecyclingAdapter.MyViewHolder> {

    public MainActivity mainActivity;
    public ArrayList<Material> materials;

    public MaterialRecyclingAdapter(MainActivity mainActivity, ArrayList<Material> materials){
        this.mainActivity = mainActivity;
        this.materials = materials;
    }

    @NonNull
    @Override
    public MaterialRecyclingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view   = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.registrar_material_reciclaje, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialRecyclingAdapter.MyViewHolder holder, int position) {

        Material material = materials.get(position);
        holder.nombreMaterial.setText(material.name);
        holder.price.setText(material.price + "");
        holder.weight.setText(material.weight + "");

        material.calculateGain(material.price);
        holder.gain.setText(material.gain + "");


        holder.price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String value = holder.price.getText().toString();

                if(!value.isEmpty()) {

                    double newPrice = Double.parseDouble(value);
                    material.calculateGain(newPrice);
                    holder.gain.setText(material.gain + "");

                    mainActivity.recycling.calculateToalGain();
                    mainActivity.totalGains.setText("Total ganancia: $ " + mainActivity.recycling.gains + " COP");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}

        });

        holder.weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String value = holder.weight.getText().toString();

                if(!value.isEmpty()) {

                    double newWeight = Double.parseDouble(value);

                    material.weight = newWeight;
                    material.calculateGain();
                    holder.gain.setText(material.gain + "");

                    mainActivity.recycling.calculateToalGain();
                    mainActivity.totalGains.setText("Total ganancia: $ " + mainActivity.recycling.gains + " COP");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}

        });

    }

    @Override
    public int getItemCount() {
        return this.materials.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombreMaterial;
        TextView price;
        TextView weight;
        TextView gain;

        public MyViewHolder(@NonNull View item) {
            super(item);

            nombreMaterial  = item.findViewById(R.id.nombreMaterial);
            price           = item.findViewById(R.id.price);
            weight          = item.findViewById(R.id.weight);
            gain            = item.findViewById(R.id.gain);
        }
    }
}
