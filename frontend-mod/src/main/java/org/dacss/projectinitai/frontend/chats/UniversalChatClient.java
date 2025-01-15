//package org.dacss.projectinitai.chats;
//
//import org.dacss.projectinitai.processors.ProcessingAdviserIface;
//import org.dacss.projectinitai.components.ContextualAdviserComp;
//import org.dacss.projectinitai.processors.components.ProcessorFactoryComp;
//import org.dacss.projectinitai.processors.enums.MessageType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UniversalChatClient {
//
//    private final ProcessorFactoryComp processorFactory;
//    private final ContextualAdviserComp<String> contextualAdviser;
//
//    @Autowired
//    public UniversalChatClient(
//            ProcessorFactoryComp processorFactory,
//            ContextualAdviserComp<String> contextualAdviser) {
//        this.processorFactory = processorFactory;
//        this.contextualAdviser = contextualAdviser;
//    }
//
//    public Object sendMessage(MessageType messageType, Object message) {
//        ProcessingAdviserIface<?> preProcessingAdviser = processorFactory.getProcessor(messageType);
//        Object preProcessedMessage = preProcessingAdviser.process(message);
//        Object postProcessedResponse = preProcessingAdviser.process(preProcessedMessage);
//        contextualAdviser.updateContext(preProcessedMessage.toString(), postProcessedResponse.toString());
//        return postProcessedResponse;
//    }
//
//    public String getContext() {
//        return contextualAdviser.getContext();
//    }
//}
