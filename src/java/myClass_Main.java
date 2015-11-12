/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.json.*;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author FGALVIS
 */
@WebServlet("/myClass_Main")
public class myClass_Main extends HttpServlet {
    private Object pair;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int option = Integer.parseInt(request.getParameter("optionValue"));    
        String url;
        // do some processing here...
        // get response writer
        switch (option) {
            case 1:{
                int userValue = Integer.parseInt(request.getParameter("userValue"));
                String htmlres="";
                // build HTML code
                for(int i=0;i<userValue;i++){
                    htmlres = htmlres+"<input type=\"text\" id=\"inputValue\" name=\"inputValue\" class=\"form-group form-control\" placeholder=\"Nombre de Pais "+(i+1)+"\" required autofocus>";    
                }
                request.setAttribute("htmlres", htmlres );
                url = "/myPage_core.jsp";
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            break;
            case 2:{
                String [] input  = request.getParameterValues("inputValue");
                String sourceURL = "http://api.myjson.com/bins/vcv1"; //just a string
                // Connect to the URL using java's native library
                URL urlobject = new URL(sourceURL);
                HttpURLConnection conection = (HttpURLConnection) urlobject.openConnection();
                conection.connect();
                // Convert to a JSON object to print data
                String [][] contry_values = new String[10][input.length];
                String zipcode = null;
                JSONParser jp = new JSONParser(); //from gson
                JSONObject root = null; 
                JSONArray root_array = null;
                try {
                    root = (JSONObject) jp.parse(new InputStreamReader(conection.getInputStream())); //Convert the input stream to a json element
                } catch (ParseException ex) {
                    Logger.getLogger(myClass_Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                boolean firstName = (boolean) root.containsKey("countries");
                root = (JSONObject) root.get("countries");
                root_array = (JSONArray) root.get("country");
                //Iterator j = root_array.iterator();
                for(int i=0;i<input.length;i++){
                    Iterator j = root_array.iterator();
                    while (j.hasNext()){
                        JSONObject inner = (JSONObject) j.next();
                        if(inner.containsValue(input[i])){
                            contry_values[0][i] = (String) inner.get("isoNumeric");
                            contry_values[1][i] = (String) inner.get("countryName");
                            contry_values[2][i] = (String) inner.get("continentName");
                            contry_values[3][i] = (String) inner.get("capital");
                            contry_values[4][i] = (String) inner.get("languages");
                            if (contry_values[4][i].length()>11){
                                String strOut = contry_values[4][i];
                                String trimedstring = strOut.substring(0, 10) + "...";// count start in 0 and 8 is excluded
                                contry_values[4][i] = trimedstring;
                            }
                            contry_values[5][i] = (String) inner.get("population");
                            contry_values[6][i] = (String) inner.get("areaInSqKm");
                            contry_values[7][i] = (String) inner.get("countryCode");
                            contry_values[8][i] = "https://es.wikipedia.org/wiki/"+((String) inner.get("countryName"));
                        }
                    }                    
                }
                System.out.println("The first name is: " + zipcode);
                request.setAttribute("firstname", zipcode);
                String htmlres="<table class=\"table\">\n"+"<caption>Datos de Paises</caption>\n"+"<thead>\n"+
                        "<tr>\n"+"<th>COD INT.</th>\n"+"<th>AVB.</th>\n"+"<th>Nombre</th>\n"+"<th>Continente</th>\n"+"<th>Capital</th>\n"+"<th>Idioma</th>\n"+"<th>Poblacion</th>\n"+"<th>Area km2</th>\n"+"<th>Wiki URL</th>\n"+
                        "</tr>\n"+"</thead>\n"+"<tbody>";
                // build HTML code
                for(int h=0;h<input.length;h++){
                    if ((contry_values[0][h]) != null){
                        htmlres = htmlres+"<tr><th scope=\"row\">"+contry_values[0][h]+"</th>";
                    for(int g=0;g<9;g++){
                        if(g!=8){
                            htmlres = htmlres+"<td>"+contry_values[g][h]+"</td>";
                        }else{
                            htmlres = htmlres+"<td>"+"<a href=\""+contry_values[g][h]+"\" class=\"btn btn-info\" role=\"button\">"+contry_values[7][h]+"</a>"+"</td>";
                        }
                    }
                    htmlres = htmlres+"</tr>";
                    } else {
                        htmlres = htmlres+"<tr><th scope=\"row\">"+"#Er."+h+"</th>";
                        htmlres = htmlres+"<td colspan=\"9\">"+"El pais \""+input[h]+"\" no esta en la BD, verifique que el nombre esta en inglés, e intente de nuevo"+"</td>";
                    }
                }
                htmlres = htmlres+"<tr>";
                htmlres = htmlres+"</tbody>\n"+"</table>";
                request.setAttribute("htmlres", htmlres );
                url = "/myPage_res.jsp";
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            break;
            case 3:{
                url = "/index.jsp";
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher(url);
                rd.forward(request, response);
            } 
            break;
            default:{
                url = "/index.jsp";
                ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            break;
        }
    }
}

