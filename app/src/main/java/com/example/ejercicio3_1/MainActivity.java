package com.example.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ejercicio3_1.modelo.SQLiteConexion;
import com.example.ejercicio3_1.modelo.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre, txtapellido, txtedad, txtdireccion, txtpuesto;

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

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        SQLiteDatabase db = conexion.getWritableDatabase();


        ContentValues valores = new ContentValues();

        valores.put(Transacciones.nombres, txtnombre.getText().toString());
        valores.put(Transacciones.apellidos, txtapellido.getText().toString());
        valores.put(Transacciones.edad, txtedad.getText().toString());
        valores.put(Transacciones.puesto, txtpuesto.getText().toString());
        valores.put(Transacciones.direccion, txtdireccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablaEmpleado, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro ingreso con exito, Codigo " + resultado.toString()
                ,Toast.LENGTH_LONG).show();

        db.close();

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