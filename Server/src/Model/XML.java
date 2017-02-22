package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *сериализация и десериализация
 * @author Daniil
 */
public class XML {

    public static <T extends XMLSerializable> void serialize(T obj, OutputStream stream) throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(obj, stream);
        Writer w = new OutputStreamWriter(stream);
        w.write("\n<end/>\n");
        w.flush();
    }

    public static Object deserialize(InputStream stream, Class<? extends XMLSerializable> cls) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        BufferedReader isr = new BufferedReader(new InputStreamReader(stream));
        String line = isr.readLine();
        while(!"<end/>".equals(line)){
            sb.append(line);
            line = isr.readLine();
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(sb.toString().getBytes());
        
        JAXBContext context = JAXBContext.newInstance(cls);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return unmarshaller.unmarshal(bais);
    }
}
