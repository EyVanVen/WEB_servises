package homework3.task2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "calculator", value = "/calculate")
public class CalcOperations extends HttpServlet {

    private List<String> operations = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        try {
            String operation = req.getParameter("operation");
            System.out.println(operation);
            double num1 = Double.valueOf(req.getParameter("num1"));
            System.out.println(num1);
            double num2 = Double.valueOf(req.getParameter("num2"));
            System.out.println(num2);

            switch (operation) {
                case "add": operations.add(num1 + " + " + num2 + " = " + (num1+num2));
                    break;
                case "sub": operations.add(num1 + " - " + num2 + " = " + (num1-num2));
                    break;
                case "mult": operations.add(num1 + " * " + num2 + " = " + (num1*num2));
                    break;
                case "div": operations.add(num1 + " / " + num2 + " = " + (num1/num2));
                    break;
                default: throw new Exception();
            }


            writer.println("<html>");
            writer.println("<body>");

            for (String result : operations) {
                writer.print("<p>" + result + "</p>");
            }

        }  catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println(e);
        } finally {
            writer.println("</body>");
            writer.println("</html>");
            writer.close();
        }

    }

}
