package com.example.ejercicio3_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ejercicio3_2.modelo.Empleados;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListarEmpleados extends AppCompatActivity {


    ListView lista;
    ArrayList<Empleados> listaEmpleado;
    ArrayList <String> ArregloEmpleado;
    Context context = this;

    private CollectionReference collectionReference;

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empleados);

        lista = (ListView) findViewById(R.id.listaEmpleados);

        texto = findViewById(R.id.textView7);
        collectionReference = FirebaseFirestore.getInstance().collection("Empleados");
        listaEmpleado = new ArrayList<>();
        obtenerlistaEmpleado();


       lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               ObtenerEmpleado(i);

           }
       });

    }

    private void obtenerlistaEmpleado(){
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    for (QueryDocumentSnapshot item : queryDocumentSnapshots) {
                        Empleados employee = item.toObject(Empleados.class);
                        listaEmpleado.add(employee);
                    }

                    llenarlista();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

    }

    private void ObtenerEmpleado(int id) {
        Empleados empleado = listaEmpleado.get(id);

        Intent intent = new Intent(getApplicationContext(),ModificarEmpleado.class);

        intent.putExtra("codigo", empleado.getId()+"");
        intent.putExtra("nombre", empleado.getNombre());
        intent.putExtra("apellidos", empleado.getApellidos());
        intent.putExtra("edad", empleado.getEdad());
        intent.putExtra("puesto", empleado.getPuesto());
        intent.putExtra("direccion", empleado.getDireccion());

        startActivity(intent);
        finish();
    }



    private void llenarlista()
    {
        ArregloEmpleado = new ArrayList<String>();

        for (int i = 0; i< listaEmpleado.size(); i++)
        {
            ArregloEmpleado.add(
                    listaEmpleado.get(i).getNombre()+" "+
                    listaEmpleado.get(i).getApellidos()+" | "+
                    listaEmpleado.get(i).getEdad()+" | "+
                    listaEmpleado.get(i).getDireccion()+" | "+
                    listaEmpleado.get(i).getPuesto());

        }

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, ArregloEmpleado);
        lista.setAdapter(adp);
    }
}