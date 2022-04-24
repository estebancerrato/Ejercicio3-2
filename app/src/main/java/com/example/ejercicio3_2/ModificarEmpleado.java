package com.example.ejercicio3_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ModificarEmpleado extends AppCompatActivity {

    EditText nombres, apellidos, edad, puesto, direccion, codigo;
    Button botonActualizar, botonEliminar;
    Context context = this;


    private CollectionReference collectionReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_empleado);

        codigo = (EditText) findViewById(R.id.mtxtid);
        nombres = (EditText) findViewById(R.id.mtxtNombre);
        apellidos = (EditText) findViewById(R.id.mtxtApellidos);
        edad = (EditText) findViewById(R.id.mtxtEdad);
        puesto = (EditText) findViewById(R.id.mtxtpuesto);
        direccion = (EditText) findViewById(R.id.mtxtDireccion);

        botonActualizar = (Button) findViewById(R.id.mbtnActualizar);
        botonEliminar = (Button) findViewById(R.id.mbtnEliminar);

        codigo.setText(getIntent().getStringExtra("codigo"));
        nombres.setText(getIntent().getStringExtra("nombre"));
        apellidos.setText(getIntent().getStringExtra("apellidos"));
        edad.setText(getIntent().getStringExtra("edad"));
        puesto.setText(getIntent().getStringExtra("puesto"));
        direccion.setText(getIntent().getStringExtra("direccion"));

        collectionReference = FirebaseFirestore.getInstance().collection("Empleados");


        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarEmpleado();
            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Eliminar");
                alertDialogBuilder
                        .setMessage("¿Desea eliminar a "+nombres.getText()+" "+apellidos.getText()+" ?")
                        .setCancelable(false)
                        .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                eliminarEmpleado();
                                startActivity(new Intent(getApplicationContext(), ListarEmpleados.class));
                                finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    private void actualizarEmpleado() {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombres.getText().toString());
        map.put("apellidos", apellidos.getText().toString());
        map.put("edad", edad.getText().toString());
        map.put("direccion", direccion.getText().toString());
        map.put("puesto", puesto.getText().toString());
        collectionReference.document(codigo.getText().toString()).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Registro actualizado con exito"
                        ,Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al actualizar al empleado"
                        ,Toast.LENGTH_LONG).show();
            }
        });

        startActivity(new Intent(getApplicationContext(), ListarEmpleados.class));
        finish();


    }

    private void eliminarEmpleado() {
        String id = codigo.getText().toString();
        collectionReference.document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Registro eliminado con exito "
                        ,Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al eliminar empleado"
                        ,Toast.LENGTH_LONG).show();
            }
        });


    }

}