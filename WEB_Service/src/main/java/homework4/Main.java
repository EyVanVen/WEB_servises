package homework4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


@WebServlet(name = "currency", value = "/currency")
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest req, HttpServletResponse resp)  throws IOException {

        //Please, sorry for symbols, if's just lazy encoding or I'm stupid ¯\_(ツ)_/¯

        resp.setContentType("html/text;charset=Cp1252");

        URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String result = null;

        try {
            BufferedReader bufferedR = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            char[] buf = new char[100000000];
            StringBuilder sb = new StringBuilder();

            for (int i = bufferedR.read(buf); i > 0; i = bufferedR.read(buf)) {
                sb.append(new String(buf, 0, i));
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            connection.disconnect();
        }


        Gson parser = new GsonBuilder().create();

        Currency[] currencies = parser.fromJson(result, Currency[].class);

        PrintWriter writer = resp.getWriter();

        writer.println("Nbu request");


        for (Currency currency : currencies) {
            if (currency.getCc().equalsIgnoreCase("UAN") |
                    currency.getCc().equalsIgnoreCase("RUB") |
                    currency.getCc().equalsIgnoreCase("USD") |
                    currency.getCc().equalsIgnoreCase("EUR")) {
                writer.println("Name:" + currency.getTxt());
                writer.println("Shortname:" + currency.getCc());
                writer.println("Rate:" + currency.getRate());
                writer.println("Exchange date:" + currency.getExchangedate());
                writer.println("r030:" + currency.getR030());
            }
        }

        writer.flush();
        writer.close();


    }
}
