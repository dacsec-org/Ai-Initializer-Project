package org.dacss.projectinitai.services;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;
import org.dacss.projectinitai.classifications.ClassificationsIface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <h1>{@link ClassificationsService}</h1>
 * Backend hilla endpoint service for classifying data.
 */
@Service
@BrowserCallable
@AnonymousAllowed
public class ClassificationsService implements ClassificationsIface {


    private static final Logger log = LoggerFactory.getLogger(ClassificationsService.class);

    /**
     * <h2>{@link #ClassificationsService()}</h2>
     */
    public ClassificationsService() {
    }


    /**
     * <h2>{@link ClassificationsIface#classify()}</h2>
     * classify data into predefined classes.
     */
    @Override
    public void classify() {

    }
}

//    /**
//     * <h2>{@link #handleClassificationAction(String, String)}</h2>
//     * @param action The action to be performed.
//     * @param data The data to be classified.
//     * @return The result of the action.
//     */
//    public Object handleClassificationAction(String action, String data) {
//        return switch (ClassificationsContexts.valueOf(action.toUpperCase())) {
//            case LOGISTIC_REGRESSION -> handler.classifyWithLogisticRegression(data);
//            case DECISION_TREE -> handler.classifyWithDecisionTree(data);
//            case RANDOM_FOREST -> handler.classifyWithRandomForest(data);
//            case SVM -> handler.classifyWithSVM(data);
//            case NAIVE_BAYES -> handler.classifyWithNaiveBayes(data);
//            case NEURAL_NETWORK -> handler.classifyWithNeuralNetwork(data);
//        };
//    }
