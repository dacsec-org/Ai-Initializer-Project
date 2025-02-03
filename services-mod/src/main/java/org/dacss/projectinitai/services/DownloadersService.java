//package org.dacss.projectinitai.services;
///**/
//import org.dacss.projectinitai.downloaders.DownloadAction;
//import org.dacss.projectinitai.downloaders.DownloadersIface;
//import org.dacss.projectinitai.downloaders.LLMDownloader;
//import org.dacss.projectinitai.downloaders.LLMLibraryUtil;
//import org.dacss.projectinitai.security.SecurityApiTokenUtil;
///**/
//import com.vaadin.flow.server.auth.AnonymousAllowed;
//import com.vaadin.hilla.BrowserCallable;
//import com.vaadin.hilla.Endpoint;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Flux;
//
///**
// * <h1>{@link DownloadersService}</h1>
// * Hilla endpoint service class for switching downloading functionality.
// */
//@Service
//@Endpoint
//@BrowserCallable
//@AnonymousAllowed
//public class DownloadersService implements DownloadersIface {
//
//    private static final Logger log = LoggerFactory.getLogger(DownloadersService.class);
//    private static final String RED = "\u001B[31m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String RESET = "\u001B[0m";
//
//    public DownloadersService() {}
//
//    @Override
//    public Flux<Object> download(DownloadAction action) {
//        Flux<Object> flux;
//        try {
//            flux = switch (action) {
//                case API_TOKEN -> SecurityApiTokenUtil.getApiToken();
//                case DOWNLOAD_LLM_JSON -> LLMLibraryUtil.downloadLLMJsonFile();
//                case DOWNLOAD_LLM_MODEL -> LLMDownloader.downloadLLMModel();
//                case CREATE_DIRECTORY, GENERATE_CHECKSUM -> null;
//            };
//        } catch (Exception downloadersServiceExc) {
//            log.error(RED + "Error from DownloadersService performing action: {}" + RESET, action, downloadersServiceExc);
//            return Flux.empty();
//        } finally {
//            log.info(GREEN + "DownloadersService action completed: {}" + RESET, action);
//        }
//        assert flux != null;
//        return flux;
//    }
//}
