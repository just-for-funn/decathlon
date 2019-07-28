package com.dwt.training.exports.imp;

import com.dwt.training.exports.FileExporter;
import com.dwt.training.exports.UserPosition;
import lombok.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;

public class XmlExporter implements FileExporter {

    BiConsumer<String,String> fileWriter = (file,content)-> {
        try {
            Files.write(Paths.get(file) ,content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    @Override
    public void export(List<UserPosition> users, String fileName) {
        fileWriter.accept(fileName , createXml(users));
    }

    @SneakyThrows
    private String createXml(List<UserPosition> users) {

        Wrapper root = Wrapper
                        .builder()
                        .users(users)
                        .build();

        JAXBContext jaxbContext = JAXBContext.newInstance(Wrapper.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(root, sw);
        return sw.toString();
    }


    @Data
    @Builder
    @XmlRootElement(name = "decathlon-list")
    @NoArgsConstructor
    @AllArgsConstructor
    static class Wrapper{
        List<UserPosition> users;
    }

}
