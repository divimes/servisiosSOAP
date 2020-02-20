package com.example.servisiosoap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

SoapPrimitive resultString;
String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("RecyclerViewPosition", "Entra al main" );
        SegundoPlano tarea=new SegundoPlano();
        tarea.execute();

    }

    private class SegundoPlano extends AsyncTask<Void,Void,Void>{
        @Override
        protected  void onPreExecute(){

        }
        @Override
        protected Void doInBackground(Void... params){
            convertir();
            return null;
        }
        @Override
        protected  void onPostExecute(Void result){

            Log.i("RecyclerViewPosition", "RESULTADO DEL SEGUNDO PLANO"+ resultString.toString()+" mas mensaje"+mensaje );
        }

    }

    private void convertir(){
        String SOAP_ACTION ="http://pruebaws.mycompany.com/consultar";
        String METHOD_NAME ="consultar";
        String NAMESPACE ="http://pruebaws.mycompany.com/";
        String URL="http://192.168.1.87:22646/pruebaWS/usuarios?WSDL";
        Log.i("RecyclerViewPosition", "Entra a CONVERTIR" );

        try {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("nombre", "pasto");
            Request.addProperty("password", "Secure99");
            Request.addProperty("imei", "1234567");
            Log.i("RecyclerViewPosition", "Entra al TRY" );
            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //envelope.dotNet = true;
            envelope.setOutputSoapObject(Request);
            HttpTransportSE androidHTTP = new HttpTransportSE(URL);
            androidHTTP.call(SOAP_ACTION, envelope);
            Log.i("RecyclerViewPosition", "DESPUES DEL CALL" );
            resultString=(SoapPrimitive) envelope.getResponse();
            mensaje="OK";
        }catch (Exception e){
            mensaje="ERRORRRRRRR"+e.getMessage();
            Log.i("RecyclerViewPosition", "ENTRA AL CACAAHHHHHHHH" );
        }
    }



}
