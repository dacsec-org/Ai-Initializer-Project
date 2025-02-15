//package org.dacss.projectinitai.services;
//
//import org.dacss.projectinitai.tar.TarActions;
//import org.dacss.projectinitai.tar.TarIface;
//import org.dacss.projectinitai.tar.TarCompressorUtil;
//import org.dacss.projectinitai.tar.TarDestroy;
//import org.dacss.projectinitai.tar.TarExtractor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
///**
// * <h1>{@link TarService}</h1>
// * Service class for tar operations.
// */
//@Service
//
//import org.dacss.projectinitai.annotations.Bridge;
//
//@Bridge(service = "tar-service")
//public class TarService implements TarIface {
//
//    private static final Logger log = LoggerFactory.getLogger(TarService.class);
//    private static final String RED = "\u001B[31m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String RESET = "\u001B[0m";
//
//    public TarService() {}
//
//    @Override
//    public Object processTar(TarActions action) {
//        Object result;
//        try {
//            result = switch (action) {
//                case COMPRESS -> TarCompressorUtil.createTarFile();
//                case EXTRACT -> TarExtractor.extractTarFile();
//                case DESTROY -> TarDestroy.destroyTarFile();
//            };
//        } catch (Exception tarExc) {
//            log.error(RED + "Error handling tar operation: {}" + RESET, action, tarExc);
//            return "Error handling tar operation: " + action;
//        } finally {
//            log.info(GREEN + "TarService action completed: {}" + RESET, action);
//        }
//        return result;
//    }
//}
