package org.dacss.projectinitai.admin;
/**/
import org.dacss.projectinitai.advisers.AdviserHandler;
import org.dacss.projectinitai.checksums.ChecksumHandler;
import org.dacss.projectinitai.contexts.ContextsHandler;
import org.dacss.projectinitai.directories.DirFileHandler;
import org.dacss.projectinitai.downloaders.DownloadersIface;
import org.dacss.projectinitai.loaders.LoadUnloadHandler;
import org.dacss.projectinitai.metrics.MetricsHandler;
import org.dacss.projectinitai.models.ModelHandler;
import org.dacss.projectinitai.processors.handlers.ProcessorsHandler;
import org.dacss.projectinitai.security.SecurityHandler;
import org.dacss.projectinitai.servers.ServersHandler;
import org.dacss.projectinitai.snapshots.SnapShotsHandler;
import org.dacss.projectinitai.types.TypesHandler;
/**/
import org.dacss.projectinitai.vision.VisionIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminHandler {
    //todo: simplify this class. inject chained::functional::interfaces instead of handlers
    // for the admin to use as tools.

    private DownloadersIface llmDownloader;
    private VisionIface computerVisionHandler;
    private AdviserHandler adviserHandler;
    private ChecksumHandler checksumHandler;
    private ContextsHandler contextHandler;
    private DirFileHandler directoryManager;
    private LoadUnloadHandler llmLoader;
    private MetricsHandler metricsCollector;
    private ModelHandler llmCreator;
    private ProcessorsHandler prePostProcessor;
    private SecurityHandler securityManager;
    private ServersHandler backendServerManager;
    private SnapShotsHandler snapshotHandler;
    private TypesHandler dataTypeHandler;

    @Autowired
    public void setLlmAdviser(AdviserHandler adviserHandler) { this.adviserHandler = adviserHandler; }

    @Autowired
    public void setChecksumHandler(ChecksumHandler checksumHandler) { this.checksumHandler = checksumHandler; }

    @Autowired
    public void setContextManager(ContextsHandler contextHandler) { this.contextHandler = contextHandler; }

    @Autowired
    public void setDirectoryManager(DirFileHandler directoryManager) { this.directoryManager = directoryManager; }

    /**
     * <h2>{@link #setLlmDownloader(DownloadersIface)}</h2>
     * @param llmDownloader
     */
    @Autowired
    public void setLlmDownloader(DownloadersIface llmDownloader) { this.llmDownloader = llmDownloader; }

    /**
     * <h2>{@link #setComputerVisionHandler(VisionIface)}</h2>
     * @param computerVisionHandler
     */
    @Autowired
    public void setComputerVisionHandler(VisionIface computerVisionHandler) { this.computerVisionHandler = computerVisionHandler; }

    @Autowired
    public void setLlmLoader(LoadUnloadHandler llmLoader) { this.llmLoader = llmLoader; }

    @Autowired
    public void setMetricsCollector(MetricsHandler metricsCollector) { this.metricsCollector = metricsCollector; }

    @Autowired
    public void setLlmCreator(ModelHandler llmCreator) { this.llmCreator = llmCreator; }

    @Autowired
    public void setPrePostProcessor(ProcessorsHandler prePostProcessor) { this.prePostProcessor = prePostProcessor; }

    @Autowired
    public void setSecurityManager(SecurityHandler securityManager) { this.securityManager = securityManager; }

    @Autowired
    public void setBackendServerManager(ServersHandler backendServerManager) { this.backendServerManager = backendServerManager; }

    @Autowired
    public void setSnapshotManager(SnapShotsHandler snapshotHandler) { this.snapshotHandler = snapshotHandler; }

    @Autowired
    public void setDataTypeHandler(TypesHandler dataTypeHandler) { this.dataTypeHandler = dataTypeHandler; }

    public void adviseLLM(String llmId) { adviserHandler.advise(); }

    public void handleChecksum(String filePath) { checksumHandler.calculateChecksum(); }

    public void manageDirectory(String directoryPath) { directoryManager.manage(directoryPath); }

    /**
     * <h2>{@link #downloadLLM(String)}</h2>
     * @param modelUrl
     */
    public void downloadLLM(String modelUrl) { String path = llmDownloader.download(modelUrl); }

    /**
     * <h2>{@link #processImage()}</h2>
     */
    public void processImage() { computerVisionHandler.processInput(); }

    public void manageContext(String contextId) { contextHandler.getContext(); }

    public void loadLLM(String path) { llmLoader.loadModel(path); }

    public void collectMetrics() { metricsCollector.measure(); }

    public void createOrMergeLLM(String llmId) { llmCreator.processModel(); }

    public void preProcessData(String data) { prePostProcessor.preProcess(data); }

    public void postProcessData(String data) { prePostProcessor.postProcess(data); }

    public void enforceSecurity() { securityManager.secure(); }

    public void manageBackendServer(String serverId) { backendServerManager.manage(); }

    public void createSnapshot(String source, String destination) { snapshotHandler.createSnapshot(source, destination); }

    public void handleDataType(String dataType) { dataTypeHandler.handle(dataType); }
}
