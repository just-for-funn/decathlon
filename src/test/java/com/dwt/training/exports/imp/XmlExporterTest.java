package com.dwt.training.exports.imp;

import com.dwt.training.event.EventType;
import com.dwt.training.exports.UserPosition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.BiConsumer;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class XmlExporterTest {

    @Mock
    BiConsumer<String , String> fileWriter;

    @InjectMocks
    XmlExporter exporter;


    @Test
    void shouldConvertToXml() throws IOException, URISyntaxException {
        UserPosition data =  UserPosition.builder()
                .user("test")
                .position(Arrays.asList(1,2,3))
                .scores(Arrays.asList(of(EventType.DISCUS_THROW , 100.0) , of(EventType.HIGH_JUMP , 20.0)))
                .build();

        exporter.export(Arrays.asList(data,data,data) , "test");

        verify(fileWriter).accept("test" , readResource("decathlon.xml" ));
    }

    private String readResource(String resource) throws URISyntaxException, IOException {
        return new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource(resource).toURI())));
    }

    private UserPosition.EventAndScore of(EventType event, double score) {
        return UserPosition.EventAndScore.builder()
                .event(event)
                .score(score)
                .build();
    }

}