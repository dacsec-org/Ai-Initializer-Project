package org.dacss.projectinitai;

import org.dacss.projectinitai.generative.Generative;
import org.dacss.projectinitai.krr.KnowledgeRepresentationReasoning;
import org.dacss.projectinitai.nlp.NaturalLanguageProcessing;
import org.dacss.projectinitai.optimization.Optimization;
import org.dacss.projectinitai.predictive.PredictiveAnalytics;
import org.dacss.projectinitai.recognition.Recognition;
import org.dacss.projectinitai.recomondation.RecommendationSystems;
import org.dacss.projectinitai.reinforcement.ReinforcementLearning;
import org.dacss.projectinitai.robotics.Robotics;
import org.dacss.projectinitai.speech.SpeechRecognition;
import org.dacss.projectinitai.vision.ComputerVision;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * <h1>{@link ContextsTest}</h1>
 * Test suite for the context enums in the contexts-mod module.
 * Enums under test:
 * <ul>
 *     <li>{@link NaturalLanguageProcessing}</li>
 *     <li>{@link Generative}</li>
 *     <li>{@link KnowledgeRepresentationReasoning}</li>
 *     <li>{@link Optimization}</li>
 *     <li>{@link PredictiveAnalytics}</li>
 *     <li>{@link Recognition}</li>
 *     <li>{@link RecommendationSystems}</li>
 *     <li>{@link ReinforcementLearning}</li>
 *     <li>{@link Robotics}</li>
 *     <li>{@link SpeechRecognition}</li>
 *     <li>{@link ComputerVision}</li>
 * </ul>
 */
public class ContextsTest {

    @DataProvider(name = "NaturalLanguageProcessingData")
    public Object[][] createNaturalLanguageProcessingData() {
        return new Object[][] {
            {NaturalLanguageProcessing.TEXT_GENERATION, "TEXT_GENERATION", 0},
            {NaturalLanguageProcessing.SENTIMENT_ANALYSIS, "SENTIMENT_ANALYSIS", 1},
            {NaturalLanguageProcessing.NAMED_ENTITY_RECOGNITION, "NAMED_ENTITY_RECOGNITION", 2},
            {NaturalLanguageProcessing.MACHINE_TRANSLATION, "MACHINE_TRANSLATION", 3},
            {NaturalLanguageProcessing.TEXT_SUMMARIZATION, "TEXT_SUMMARIZATION", 4}
        };
    }

    @Test(dataProvider = "NaturalLanguageProcessingData")
    public void testNaturalLanguageProcessingEnum(NaturalLanguageProcessing nlp, String expectedName, int expectedOrdinal) {
        assertNotNull(nlp, "Enum value should not be null");
        assertEquals(nlp.name(), expectedName, "Enum name should match");
        assertEquals(nlp.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "GenerativeData")
    public Object[][] createGenerativeData() {
        return new Object[][] {
            {Generative.DEEPFAKES, "DEEPFAKES", 0},
            {Generative.GENERATIVE_ADVERSARIAL_NETWORKS, "GENERATIVE_ADVERSARIAL_NETWORKS", 1},
            {Generative.TEXT_TO_IMAGE, "TEXT_TO_IMAGE", 2},
            {Generative.VARIATIONAL_AUTOENCODERS, "VARIATIONAL_AUTOENCODERS", 3},
            {Generative.MUSIC_GENERATION, "MUSIC_GENERATION", 4},
            {Generative.TEXT_GENERATION, "TEXT_GENERATION", 5}
        };
    }

    @Test(dataProvider = "GenerativeData")
    public void testGenerativeEnum(Generative generative, String expectedName, int expectedOrdinal) {
        assertNotNull(generative, "Enum value should not be null");
        assertEquals(generative.name(), expectedName, "Enum name should match");
        assertEquals(generative.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "KnowledgeRepresentationReasoningData")
    public Object[][] createKnowledgeRepresentationReasoningData() {
        return new Object[][] {
            {KnowledgeRepresentationReasoning.KNOWLEDGE_GRAPHS, "KNOWLEDGE_GRAPHS", 0},
            {KnowledgeRepresentationReasoning.ONTOLOGIES, "ONTOLOGIES", 1},
            {KnowledgeRepresentationReasoning.RULE_BASED_SYSTEMS, "RULE_BASED_SYSTEMS", 2}
        };
    }

    @Test(dataProvider = "KnowledgeRepresentationReasoningData")
    public void testKnowledgeRepresentationReasoningEnum(KnowledgeRepresentationReasoning krr, String expectedName, int expectedOrdinal) {
        assertNotNull(krr, "Enum value should not be null");
        assertEquals(krr.name(), expectedName, "Enum name should match");
        assertEquals(krr.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "OptimizationData")
    public Object[][] createOptimizationData() {
        return new Object[][] {
            {Optimization.LINEAR_PROGRAMMING, "LINEAR_PROGRAMMING", 0},
            {Optimization.INTEGER_PROGRAMMING, "INTEGER_PROGRAMMING", 1},
            {Optimization.GENETIC_ALGORITHMS, "GENETIC_ALGORITHMS", 2}
        };
    }

    @Test(dataProvider = "OptimizationData")
    public void testOptimizationEnum(Optimization optimization, String expectedName, int expectedOrdinal) {
        assertNotNull(optimization, "Enum value should not be null");
        assertEquals(optimization.name(), expectedName, "Enum name should match");
        assertEquals(optimization.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "PredictiveAnalyticsData")
    public Object[][] createPredictiveAnalyticsData() {
        return new Object[][] {
            {PredictiveAnalytics.TIME_SERIES_FORECASTING, "TIME_SERIES_FORECASTING", 0},
            {PredictiveAnalytics.ANOMALY_DETECTION, "ANOMALY_DETECTION", 1},
            {PredictiveAnalytics.PREDICTIVE_MAINTENANCE, "PREDICTIVE_MAINTENANCE", 2}
        };
    }

    @Test(dataProvider = "PredictiveAnalyticsData")
    public void testPredictiveAnalyticsEnum(PredictiveAnalytics predictiveAnalytics, String expectedName, int expectedOrdinal) {
        assertNotNull(predictiveAnalytics, "Enum value should not be null");
        assertEquals(predictiveAnalytics.name(), expectedName, "Enum name should match");
        assertEquals(predictiveAnalytics.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "RecognitionData")
    public Object[][] createRecognitionData() {
        return new Object[][] {
            {Recognition.FACIAL_RECOGNITION, "FACIAL_RECOGNITION", 0},
            {Recognition.OBJECT_RECOGNITION, "OBJECT_RECOGNITION", 1},
            {Recognition.TEXT_RECOGNITION, "TEXT_RECOGNITION", 2},
            {Recognition.VOICE_RECOGNITION, "VOICE_RECOGNITION", 3},
            {Recognition.GESTURE_RECOGNITION, "GESTURE_RECOGNITION", 4},
            {Recognition.SCENE_RECOGNITION, "SCENE_RECOGNITION", 5}
        };
    }

    @Test(dataProvider = "RecognitionData")
    public void testRecognitionEnum(Recognition recognition, String expectedName, int expectedOrdinal) {
        assertNotNull(recognition, "Enum value should not be null");
        assertEquals(recognition.name(), expectedName, "Enum name should match");
        assertEquals(recognition.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "RecommendationSystemsData")
    public Object[][] createRecommendationSystemsData() {
        return new Object[][] {
            {RecommendationSystems.COLLABORATIVE_FILTERING, "COLLABORATIVE_FILTERING", 0},
            {RecommendationSystems.CONTENT_BASED_FILTERING, "CONTENT_BASED_FILTERING", 1},
            {RecommendationSystems.HYBRID_RECOMMENDATION_SYSTEMS, "HYBRID_RECOMMENDATION_SYSTEMS", 2},
            {RecommendationSystems.KNOWLEDGE_BASED_RECOMMENDATION_SYSTEMS, "KNOWLEDGE_BASED_RECOMMENDATION_SYSTEMS", 3},
            {RecommendationSystems.DEMOGRAPHIC_BASED_RECOMMENDATION_SYSTEMS, "DEMOGRAPHIC_BASED_RECOMMENDATION_SYSTEMS", 4}
        };
    }

    @Test(dataProvider = "RecommendationSystemsData")
    public void testRecommendationSystemsEnum(RecommendationSystems recommendationSystems, String expectedName, int expectedOrdinal) {
        assertNotNull(recommendationSystems, "Enum value should not be null");
        assertEquals(recommendationSystems.name(), expectedName, "Enum name should match");
        assertEquals(recommendationSystems.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "ReinforcementLearningData")
    public Object[][] createReinforcementLearningData() {
        return new Object[][] {
            {ReinforcementLearning.AUTONOMOUS_DRIVING, "AUTONOMOUS_DRIVING", 0},
            {ReinforcementLearning.GAME_PLAYING, "GAME_PLAYING", 1},
            {ReinforcementLearning.ROBOTICS_CONTROL, "ROBOTICS_CONTROL", 2},
            {ReinforcementLearning.SIMULATION_ENVIRONMENTS, "SIMULATION_ENVIRONMENTS", 3},
            {ReinforcementLearning.STRATEGY_OPTIMIZATION, "STRATEGY_OPTIMIZATION", 4},
            {ReinforcementLearning.RESOURCE_MANAGEMENT, "RESOURCE_MANAGEMENT", 5}
        };
    }

    @Test(dataProvider = "ReinforcementLearningData")
    public void testReinforcementLearningEnum(ReinforcementLearning reinforcementLearning, String expectedName, int expectedOrdinal) {
        assertNotNull(reinforcementLearning, "Enum value should not be null");
        assertEquals(reinforcementLearning.name(), expectedName, "Enum name should match");
        assertEquals(reinforcementLearning.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "RoboticsData")
    public Object[][] createRoboticsData() {
        return new Object[][] {
            {Robotics.MOTION_CONTROL, "MOTION_CONTROL", 0},
            {Robotics.OBJECT_MANIPULATION, "OBJECT_MANIPULATION", 1},
            {Robotics.PATH_PLANNING, "PATH_PLANNING", 2},
            {Robotics.SENSOR_INTEGRATION, "SENSOR_INTEGRATION", 3},
            {Robotics.AUTONOMOUS_NAVIGATION, "AUTONOMOUS_NAVIGATION", 4},
            {Robotics.HUMAN_ROBOT_INTERACTION, "HUMAN_ROBOT_INTERACTION", 5}
        };
    }

    @Test(dataProvider = "RoboticsData")
    public void testRoboticsEnum(Robotics robotics, String expectedName, int expectedOrdinal) {
        assertNotNull(robotics, "Enum value should not be null");
        assertEquals(robotics.name(), expectedName, "Enum name should match");
        assertEquals(robotics.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "SpeechRecognitionData")
    public Object[][] createSpeechRecognitionData() {
        return new Object[][] {
            {SpeechRecognition.SPEECH_TO_TEXT, "SPEECH_TO_TEXT", 0},
            {SpeechRecognition.TEXT_TO_SPEECH, "TEXT_TO_SPEECH", 1},
            {SpeechRecognition.VOICE_RECOGNITION, "VOICE_RECOGNITION", 2}
        };
    }

    @Test(dataProvider = "SpeechRecognitionData")
    public void testSpeechRecognitionEnum(SpeechRecognition speechRecognition, String expectedName, int expectedOrdinal) {
        assertNotNull(speechRecognition, "Enum value should not be null");
        assertEquals(speechRecognition.name(), expectedName, "Enum name should match");
        assertEquals(speechRecognition.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }

    @DataProvider(name = "ComputerVisionData")
    public Object[][] createComputerVisionData() {
        return new Object[][] {
            {ComputerVision.IMAGE_CLASSIFICATION, "IMAGE_CLASSIFICATION", 0},
            {ComputerVision.OBJECT_DETECTION, "OBJECT_DETECTION", 1},
            {ComputerVision.IMAGE_SEGMENTATION, "IMAGE_SEGMENTATION", 2},
            {ComputerVision.IMAGE_GENERATION, "IMAGE_GENERATION", 3},
            {ComputerVision.IMAGE_SUPER_RESOLUTION, "IMAGE_SUPER_RESOLUTION", 4},
            {ComputerVision.IMAGE_RECOGNITION, "IMAGE_RECOGNITION", 5}
        };
    }

    @Test(dataProvider = "ComputerVisionData")
    public void testComputerVisionEnum(ComputerVision computerVision, String expectedName, int expectedOrdinal) {
        assertNotNull(computerVision, "Enum value should not be null");
        assertEquals(computerVision.name(), expectedName, "Enum name should match");
        assertEquals(computerVision.ordinal(), expectedOrdinal, "Enum ordinal should match");
    }
}
