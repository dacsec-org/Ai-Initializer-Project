package org.dacss.projectinitai.frontend.configs;

import org.dacss.projectinitai.processors.components.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConf {

    @Bean
    public AudioProcessorComp audioProcessor() { return new AudioProcessorComp(); }

    @Bean
    public CsvProcessorComp csvProcessor() { return new CsvProcessorComp(); }

    @Bean
    public DocumentProcessorComp documentProcessor() { return new DocumentProcessorComp(); }

    @Bean
    public EncodingProcessorComp encodingProcessor() { return new EncodingProcessorComp(); }

    @Bean
    public HtmlProcessorComp htmlProcessor() { return new HtmlProcessorComp(); }

    @Bean
    public ImageProcessorComp imageProcessor() { return new ImageProcessorComp(); }

    @Bean
    public JsonProcessorComp jsonProcessor() { return new JsonProcessorComp(); }

    @Bean
    public MissingValuesProcessorComp missingValuesProcessor() { return new MissingValuesProcessorComp(); }

    @Bean
    public PdfProcessorComp pdfProcessor() { return new PdfProcessorComp(); }

    @Bean
    public TextProcessorComp textProcessor() { return new TextProcessorComp(); }

    @Bean
    public TokenizationProcessorComp tokenizationProcessor() { return new TokenizationProcessorComp(); }

    @Bean
    public VectorizationProcessorComp vectorizationProcessor() { return new VectorizationProcessorComp(); }

    @Bean
    public VideoProcessorComp videoProcessor() { return new VideoProcessorComp(); }

    @Bean
    public XmlProcessorComp xmlProcessor() { return new XmlProcessorComp(); }
}
