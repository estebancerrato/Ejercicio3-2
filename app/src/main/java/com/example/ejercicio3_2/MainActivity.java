package com.example.ejercicio3_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio3_2.modelo.Empleados;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre, txtapellido, txtedad, txtdireccion, txtpuesto;
    private CollectionReference collectionReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtNombre);
        txtapellido = findViewById(R.id.txtApellidos);
        txtedad = findViewById(R.id.txtEdad);
        txtdireccion = findViewById(R.id.txtDireccion);
        txtpuesto = findViewById(R.id.txtpuesto);

        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnListar = findViewById(R.id.btnListar);

        collectionReference = FirebaseFirestore.getInstance().collection("Empleados");

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ListarEmpleados.class);
                startActivity(intent);
            }
        });

    }

    private void validarCampos(){
        if (txtnombre.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese un nombre"
                    ,Toast.LENGTH_LONG).show();
        }else if (txtapellido.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese un apellido"
                    ,Toast.LENGTH_LONG).show();
        }else if (txtedad.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese su edad"
                    ,Toast.LENGTH_LONG).show();
        }else if (txtdireccion.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese su dirección"
                    ,Toast.LENGTH_LONG).show();
        }else if (txtpuesto.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Campo obligatorio ingrese su puesto de trabajo"
                    ,Toast.LENGTH_LONG).show();
        }else{
            agregarEmpleado();
        }
    }

    private void agregarEmpleado() {

        DocumentReference documentReference = collectionReference.document();
        String id = documentReference.getId();
        Empleados empleados = new Empleados();
        empleados.setId(id);
        empleados.setNombre(txtnombre.getText().toString());
        empleados.setApellidos(txtapellido.getText().toString());
        empleados.setEdad(txtedad.getText().toString());
        empleados.setDireccion(txtdireccion.getText().toString());
        empleados.setPuesto(txtpuesto.getText().toString());

        collectionReference.document(id).set(empleados).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Datos Guardados", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
            }
        });

        //despues de guardar se procede a limpiar las cajas de texto
        limpiarPantalla();

    }
    private void limpiarPantalla(){
        txtnombre.setText("");
        txtapellido.setText("");
        txtedad.setText("");
        txtpuesto.setText("");
        txtdireccion.setText("");
    }
}