package com.ericmas001.duproprio.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ContactWebservice extends AsyncTask<String, Void, String>
{
    @SuppressWarnings("rawtypes")
    public static final Class[] PARAMETER_TYPES = new Class[] { String.class, Exception.class };

    private Object m_Obj;
    private Method m_Method;
    private Exception m_Exception;

    public static <T extends Context> void CallWS(T obj, String method, String url)
    {
        CallWS(obj, method, url, obj);
    }

    public static <T extends Context> void CallWS(T obj, String method, String url, Object caller)
    {
        try
        {
            new ContactWebservice(caller, caller.getClass().getMethod(method, ContactWebservice.PARAMETER_TYPES)).execute(url);
        }
        catch (NoSuchMethodException e)
        {
            Toast.makeText(obj, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public ContactWebservice(Object obj, Method method)
    {
        m_Obj = obj;
        m_Method = method;
    }

    @Override
    protected String doInBackground(String... urls)
    {
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

        HttpGet request = new HttpGet(urls[0]);

        try
        {
            HttpResponse response = client.execute(request);
            InputStream stream = response.getEntity().getContent();
            if (stream != null)
                return convertStreamToString(stream);
            return null;
        }
        catch (Exception e)
        {
            m_Exception = e;
            return null;
        }
    }

    protected void onPostExecute(String result)
    {
        Object[] parameters = new Object[2];
        parameters[0] = result;
        parameters[1] = m_Exception;
        try
        {
            m_Method.invoke(m_Obj, parameters);
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    private String convertStreamToString(InputStream is)
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return android.text.Html.fromHtml(sb.toString()).toString();
    }
}
