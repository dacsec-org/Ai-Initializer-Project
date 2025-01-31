package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import com.vaadin.hilla.Endpoint;
import org.dacss.projectinitai.classifications.ClassificationsIface;
import org.dacss.projectinitai.classifications.ClassificationsTypes;
import org.dacss.projectinitai.classifications.utillities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * <h1>{@link ClassificationsService}</h1>
 * Backend hilla endpoint service for classifying data.
 */
@Service
@Endpoint
@BrowserCallable
@AnonymousAllowed
public class ClassificationsService implements ClassificationsIface {

    private static final Logger log = LoggerFactory.getLogger(ClassificationsService.class);
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public ClassificationsService() {
    }

    @Override
    public Flux<Object> classify(ClassificationsTypes type) {
        Flux<Object> flux;
        try {
            flux = switch (type) {
                case DECISION_TREE -> DecisionTreeUtil.classify();
                case LOGISTIC_REGRESSION -> LogisticRegressionUtil.classify();
                case NAIVE_BAYES -> NaiveBayesUtil.classify();
                case NEURAL_NETWORK -> NeuralNetworkUtil.classify();
                case RANDOM_FOREST -> RandomForestUtil.classify();
                case SVM -> SVMUtil.classify();
            };
        } catch (Exception classificationsServiceExc) {
            log.error(RED + "Error from ClassificationsService performing classification: {}" + RESET, type, classificationsServiceExc);
            return Flux.empty();
        } finally {
            log.info(GREEN + "ClassificationsService classification completed: {}" + RESET, type);
        }
        assert flux != null;
        return flux;
    }
}
