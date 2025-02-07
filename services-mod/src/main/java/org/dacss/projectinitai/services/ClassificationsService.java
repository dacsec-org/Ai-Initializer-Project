package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
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
@BrowserCallable
@AnonymousAllowed
public class ClassificationsService implements ClassificationsIface {

    private static final Logger log = LoggerFactory.getLogger(ClassificationsService.class);

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
            log.error("{}: Error handling classification: {}", classificationsServiceExc, type);
            return Flux.empty();
        } finally {
            log.info("{}: Classification operation completed:", type);
        }
        return flux;
    }
}
