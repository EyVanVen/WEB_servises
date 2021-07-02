package homework2;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();

        Reader fileReader = new FileReader("C:\\Users\\BVA\\IdeaProjects\\WEB_services\\src\\main\\java\\homework2\\candies.xml");

        System.out.println(new FileReader("C:\\Users\\BVA\\IdeaProjects\\WEB_services\\src\\main\\java\\homework2\\candies.xml").getEncoding());

        XMLStreamReader reader = factory.createXMLStreamReader(fileReader);

        ArrayList <Candy> list = new ArrayList<>();

        while (reader.hasNext()) {

            switch (reader.next()) {
                case XMLStreamReader.START_ELEMENT :

                    String elem = reader.getName().toString();

                    switch (elem) {
                        case "candy" :
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                               list.add(new Candy(reader.getAttributeValue(1), reader.getAttributeValue(2), reader.getAttributeValue(3), reader.getAttributeValue(4)));
                            }

                            break;

                        case "factory" :
                            reader.next();
                            break;
                    }

                    System.out.println("Start element: " + elem);
                    break;

                case XMLStreamReader.END_ELEMENT :
                    System.out.println("End element: " + reader.getName());
                    break;

            }

        }
        System.out.println(list);
    }

}