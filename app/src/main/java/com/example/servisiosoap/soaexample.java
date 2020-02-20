package com.example.servisiosoap;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class soaexample extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public soaexample(String name) {
        super(name);
    }
    public Boolean nuevasoa(){
        Log.i("RecyclerViewPosition", "Inicia la clase" );
        boolean resul = true;


        final String NAMESPACE ="http://pruebaws.mycompany.com/";
        final String URL="http://192.168.1.87:22646/pruebaWS/usuarios?wsdl";
        final String METHOD_NAME ="consultar";
        final String SOAP_ACTION ="http://pruebaws.mycompany.com/soapConsultar";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("nombre", "pasto");
        request.addProperty("password", "Secure99");
        request.addProperty("imei", "1234567");
        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(URL);

        try
        {
            Log.i("RecyclerViewPosition", "ENTRA AL TRY" );
            transporte.call(SOAP_ACTION, envelope);

// Get the SoapResult from the envelope body.
            SoapObject resSoap =(SoapObject)envelope.getResponse();


            Log.e("result data", "CHINGONNNNNNNNNNN" + resSoap);



            SoapObject ic = (SoapObject)resSoap.getProperty(0);
            String result = ic.getProperty(0).toString();
            // 获取返回的数据


            Log.i("RecyclerViewPosition", "RESULTADO"+ic+result );



        }
        catch (Exception e)

        {
            Log.i("RecyclerViewPosition", "eNTRA AL CACHE------>"+e);
            resul = false;
        }
        Log.i("RecyclerViewPosition", "resultado"+resul );
        return resul;
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
