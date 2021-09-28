package edu.bsu.main;

import java.io.IOException;
import java.io.InputStream;

import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

public class WikiConnecter
{

    public InputStream wikiConnect (String Url) throws IOException {
        try{
            URL url = new URL(Url);
            URLConnection connect = url.openConnection();
            InputStream inputStream = connect.getInputStream();
            connect.setRequestProperty("User-Agent", "CS222FirstProject/0.1 (dlbarton@bsu.edu)");
            connect.connect();
            return inputStream;
        }
        catch(ConnectException connectException)
        {
            System.out.println("Connection error");
            return null;
        }
    }
}

