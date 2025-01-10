package org.dacss.projectinitai.configs;

import org.dacss.projectinitai.advisers.processors.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorConf {

    @Bean
    public AudioProcessor audioProcessor() { return new AudioProcessor(); }

    @Bean
    public CsvProcessor csvProcessor() { return new CsvProcessor(); }

    @Bean
    public DocumentProcessor documentProcessor() { return new DocumentProcessor(); }

    @Bean
    public EncodingProcessor encodingProcessor() { return new EncodingProcessor(); }

    @Bean
    public HtmlProcessor htmlProcessor() { return new HtmlProcessor(); }

    @Bean
    public ImageProcessor imageProcessor() { return new ImageProcessor(); }

    @Bean
    public JsonProcessor jsonProcessor() { return new JsonProcessor(); }

    @Bean
    public MissingValuesProcessor missingValuesProcessor() { return new MissingValuesProcessor(); }

    @Bean
    public PdfProcessor pdfProcessor() { return new PdfProcessor(); }

    @Bean
    public TextProcessor textProcessor() { return new TextProcessor(); }

    @Bean
    public TokenizationProcessor tokenizationProcessor() { return new TokenizationProcessor(); }

    @Bean
    public VectorizationProcessor vectorizationProcessor() { return new VectorizationProcessor(); }

    @Bean
    public VideoProcessor videoProcessor() { return new VideoProcessor(); }

    @Bean
    public XmlProcessor xmlProcessor() { return new XmlProcessor(); }
}
