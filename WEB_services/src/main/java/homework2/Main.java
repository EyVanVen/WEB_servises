package homework2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();

        Reader reader = new FileReader("C:\\Users\\BVA\\IdeaProjects\\WEB_services\\src\\main\\java\\homework2\\candies.xml");

        System.out.println(new FileReader("C:\\Users\\BVA\\IdeaProjects\\WEB_services\\src\\main\\java\\homework2\\candies.xml").getEncoding());

        XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(reader);

        ArrayList <Candy> list = new ArrayList<>();

        while (xmlStreamReader.hasNext()) {

            switch (xmlStreamReader.next()) {
                case XMLStreamReader.START_ELEMENT :

                    String elem = xmlStreamReader.getName().toString();

                    switch (elem) {
                        case "candy" :
                            for (int i = 0; i < xmlStreamReader.getAttributeCount(); i++) {
                               list.add(new Candy(xmlStreamReader.getAttributeValue(1), xmlStreamReader.getAttributeValue(2), xmlStreamReader.getAttributeValue(3), xmlStreamReader.getAttributeValue(4)));
                            }

                            break;

                        case "factory" :
                            xmlStreamReader.next();
                            break;
                    }

                    System.out.println("Start element: " + elem);
                    break;

                case XMLStreamReader.END_ELEMENT :
                    System.out.println("End element: " + xmlStreamReader.getName());
                    break;

            }

        }
        System.out.println(list);
    }

}