//package org.dacss.projectinitai.tar;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import reactor.core.publisher.Flux;
//
///**
// * <h1>TarIface</h1>
// * Functional interface for handling tar operations.
// * This interface provides methods to process tar actions such as compress, extract, and destroy.
// */
//@FunctionalInterface
//public interface TarIface {
//
//    Logger log = LoggerFactory.getLogger(TarIface.class);
//    String RED = "\u001B[31m";
//    String GREEN = "\u001B[32m";
//    String RESET = "\u001B[0m";
//
//    /**
//     * <h3>{@link TarActions}</h3>
//     * Processes a tar action.
//     * This method handles the specified tar action and logs the completion or error of the operation.
//     *
//     * @param action the tar action to perform (compress, extract, or destroy)
//     * @return a {@link Flux} that emits the result of the tar action
//     */
//    static Flux<Object> processTarAction(TarActions action) {
//        try {
//            Flux<Object> result = switch (action) {
//                case COMPRESS -> TarCompressorUtil.createTarFile();
//                case EXTRACT -> TarExtractor.extractTarFile();
//                case DESTROY -> TarDestroy.destroyTarFile();
//            };
//            log.info(GREEN + "TarIface action completed: {}" + RESET, action);
//            return result;
//        } catch (Exception tarExc) {
//            log.error(RED + "Error handling tar operation: {}" + RESET, action, tarExc);
//            return Flux.error(new RuntimeException("Error handling tar operation: " + action, tarExc));
//        }
//    }
//
//    /**
//     * <h3>{@link #processTar(TarActions)}</h3>
//     * Processes a tar action.
//     * This method is intended to be implemented by classes that use this interface.
//     *
//     * @param action the tar action to perform (compress, extract, or destroy)
//     * @return a {@link Flux} that emits the result of the tar action
//     */
//    Flux<Object> processTar(TarActions action);
//}
