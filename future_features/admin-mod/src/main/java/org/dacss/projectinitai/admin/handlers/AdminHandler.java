package org.dacss.projectinitai.admin.handlers;
/**/
import org.dacss.projectinitai.advisers.AdviseAction;
import org.dacss.projectinitai.advisers.AdvisersIface;
import org.dacss.projectinitai.anomalies.AnomaliesIface;
import org.dacss.projectinitai.anomalies.AnomalyTypes;
import org.dacss.projectinitai.checksums.ChecksumActions;
import org.dacss.projectinitai.checksums.ChecksumsIface;
import org.dacss.projectinitai.classifications.ClassificationsIface;
import org.dacss.projectinitai.classifications.ClassificationsTypes;
import org.dacss.projectinitai.clustering.ClusteringIface;
import org.dacss.projectinitai.databases.DataBaseIface;
import org.dacss.projectinitai.databases.DBTypes;
import org.dacss.projectinitai.databases.DBActions;
import org.dacss.projectinitai.directories.DirectoriesIface;
import org.dacss.projectinitai.directories.DirectoryActions;
import org.dacss.projectinitai.downloaders.DownloadAction;
import org.dacss.projectinitai.downloaders.DownloadersIface;
import org.dacss.projectinitai.embedding.EmbeddingIface;
import org.dacss.projectinitai.embedding.EmbeddingTypes;
import org.dacss.projectinitai.generative.GenerativeIface;
import org.dacss.projectinitai.krr.KRRIface;
import org.dacss.projectinitai.loaders.LoadUnLoadActions;
import org.dacss.projectinitai.loaders.LoadersIface;
import org.dacss.projectinitai.messages.MessageAction;
import org.dacss.projectinitai.messages.MessagesIface;
import org.dacss.projectinitai.metrics.MetricsIface;
import org.dacss.projectinitai.metrics.MetricsTypes;
import org.dacss.projectinitai.models.ModelActions;
import org.dacss.projectinitai.models.ModelIface;
import org.dacss.projectinitai.nlp.NLPIface;
import org.dacss.projectinitai.optimizations.OptimizationsIface;
import org.dacss.projectinitai.reductions.ReductionsIface;
import org.dacss.projectinitai.security.SecurityActions;
import org.dacss.projectinitai.security.SecurityIface;
import org.dacss.projectinitai.sequence.SequenceIface;
import org.dacss.projectinitai.servers.ServerActions;
import org.dacss.projectinitai.servers.ServerTypes;
import org.dacss.projectinitai.servers.ServersIface;
import org.dacss.projectinitai.snapshots.SnapShotsActions;
import org.dacss.projectinitai.snapshots.SnapShotsIface;
//import org.dacss.projectinitai.tar.TarActions;
//import org.dacss.projectinitai.tar.TarIface;
import org.dacss.projectinitai.vision.VisionIface;
import org.dacss.projectinitai.speech.SpeechIface;
import org.dacss.projectinitai.predictive.PredictiveIface;
import org.dacss.projectinitai.recommendations.RecommendationsIface;
import org.dacss.projectinitai.recognitions.RecognitionsIface;
import org.dacss.projectinitai.regressions.RegressionsIface;
import org.dacss.projectinitai.reinforcement.ReinforcementIface;
import org.dacss.projectinitai.robotics.RoboticsIface;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * <h1>{@link AdminHandler}</h1>
 * Handler class for the admin LLM operations.
 */
public class AdminHandler implements AdvisersIface, AnomaliesIface, ChecksumsIface, ClassificationsIface, ClusteringIface, DataBaseIface, DirectoriesIface,
        DownloadersIface, EmbeddingIface, GenerativeIface, KRRIface, LoadersIface, MessagesIface, MetricsIface, ModelIface, NLPIface,
        OptimizationsIface, PredictiveIface, RecognitionsIface,
        RecommendationsIface, ReductionsIface, RegressionsIface,
        ReinforcementIface, RoboticsIface, SecurityIface, SequenceIface,
        ServersIface, SnapShotsIface, SpeechIface, /*TarIface,*/ VisionIface {


    @Override
    public Flux<Object> advise(AdviseAction action) { return null; }
    @Override
    public Flux<Object> detectAnomaly(AnomalyTypes type) { return null; }
    @Override
    public Flux<Object>calculateChecksum(ChecksumActions action) { return null; }
    @Override
    public Flux<Object> classify(ClassificationsTypes type) { return null; }
    @Override
    public void performClustering() {}
    @Override
    public Flux<Object> performDatabaseAction(DBTypes type, DBActions action, Object data) { return null; }
    @Override
    public Flux<Object> processDirFile(DirectoryActions action, String path, String fileName) { return null; }
    @Override
    public Flux<Object> download(DownloadAction action, String llmName) { return null; }
    @Override
    public Flux<Object> processEmbedding(EmbeddingTypes type) { return null; }
    @Override
    public void processKRR() { }
    @Override
    public Flux<Object> processModel(ModelActions actions, String modelPath1, String modelPath2) { return null; }
    @Override
    public Flux<Object> processMessages(MessageAction action) { return null; }
    @Override
    public Flux<Object> measure(MetricsTypes type) { return null; }
    @Override
    public void processText(String action, String data) {}
    @Override
    public void optimize(String action, String data) {}
    @Override
    public Flux<Object> secure(SecurityActions action) { return null; }
    @Override
    public Flux<Object> manageServer(ServerActions action, ServerTypes type) { return null; }
    @Override
    public Flux<Object> manageSnapshots(SnapShotsActions action) { return null; }
    @Override
    public void processInput() {}
//    @Override
//    public Flux<Object> processTar(TarActions action) { return null; }
    @Override
    public void reduceDimensions() {}
    @Override
    public void predict() {}
    @Override
    public Flux<Object> loadUnloadLLM(LoadUnLoadActions action) { return null; }
    @Override
    public void recognizeSpeech() {}
    @Override
    public void regress()  {}
    @Override
    public void learn() {}
    @Override
    public void recommend() {}
    @Override
    public void execute() {}
    @Override
    public void processRecognitions() {}
    @Override
    public void processGenerative() {}
    @Override
    public void modelSequence() {}
}

