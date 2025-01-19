package org.dacss.projectinitai.admin.handlers;
/**/
import org.dacss.projectinitai.advisers.services.AdvisersService;
import org.dacss.projectinitai.checksums.services.ChecksumsService;
import org.dacss.projectinitai.contexts.services.ContextsService;
import org.dacss.projectinitai.directories.services.DirFileService;
import org.dacss.projectinitai.downloaders.services.DownloadersService;
import org.dacss.projectinitai.loaders.services.ModelLoadUnloadService;
import org.dacss.projectinitai.metrics.services.MetricsService;
import org.dacss.projectinitai.models.services.ModelsService;
import org.dacss.projectinitai.processors.services.ProcessorsService;
import org.dacss.projectinitai.rags.services.RAGService;
import org.dacss.projectinitai.security.services.SecurityService;
import org.dacss.projectinitai.servers.ServersService;
import org.dacss.projectinitai.snapshots.SnapShotsService;
import org.dacss.projectinitai.types.services.TypesService;
/**/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminHandler {

    private AdvisersService llmAdviser;
    private ChecksumsService checksumHandler;
    private ContextsService contextManager;
    private DirFileService directoryManager;
    private DownloadersService llmDownloader;
    private ModelLoadUnloadService llmLoader;
    private MetricsService metricsCollector;
    private ModelsService llmCreator;
    private ProcessorsService prePostProcessor;
    private RAGService ragEnhancer;
    private SecurityService securityManager;
    private ServersService backendServerManager;
    private SnapShotsService snapshotManager;
    private TypesService dataTypeHandler;

    @Autowired
    public void setLlmAdviser(AdvisersService llmAdviser) { this.llmAdviser =
            llmAdviser; }

    @Autowired
    public void setChecksumHandler(ChecksumsService checksumHandler) { this.checksumHandler = checksumHandler; }

    @Autowired
    public void setContextManager(ContextsService contextManager) { this.contextManager = contextManager; }

    @Autowired
    public void setDirectoryManager(DirFileService directoryManager) { this.directoryManager = directoryManager; }

    @Autowired
    public void setLlmDownloader(DownloadersService llmDownloader) { this.llmDownloader = llmDownloader; }

    @Autowired
    public void setLlmLoader(ModelLoadUnloadService llmLoader) { this.llmLoader = llmLoader; }

    @Autowired
    public void setMetricsCollector(MetricsService metricsCollector) { this.metricsCollector = metricsCollector; }

    @Autowired
    public void setLlmCreator(ModelsService llmCreator) { this.llmCreator =
            llmCreator; }

    @Autowired
    public void setPrePostProcessor(ProcessorsService prePostProcessor) { this.prePostProcessor = prePostProcessor; }

    @Autowired
    public void setRagEnhancer(RAGService ragEnhancer) { this.ragEnhancer =
            ragEnhancer; }

    @Autowired
    public void setSecurityManager(SecurityService securityManager) { this.securityManager = securityManager; }

    @Autowired
    public void setBackendServerManager(ServersService backendServerManager) { this.backendServerManager = backendServerManager; }

    @Autowired
    public void setSnapshotManager(SnapShotsService snapshotManager) { this.snapshotManager = snapshotManager; }

    @Autowired
    public void setDataTypeHandler(TypesService dataTypeHandler) { this.dataTypeHandler = dataTypeHandler; }

    public void adviseLLM(String llmId) { llmAdviser.advise(llmId); }

    public void handleChecksum(String filePath) { checksumHandler.handle(filePath); }

    public void manageDirectory(String directoryPath) { directoryManager.manage(directoryPath); }

    public void downloadLLM(String modelUrl) { llmDownloader.download(modelUrl); }

    public void manageContext(String contextId) { contextManager.manage(contextId); }

    public void loadLLM(String llmId) { llmLoader.load(llmId); }

    public void collectMetrics() { metricsCollector.collect(); }

    public void createOrMergeLLM(String llmId) { llmCreator.createOrMerge(llmId); }

    public void preProcessData(String data) { prePostProcessor.preProcess(data); }

    public void postProcessData(String data) { prePostProcessor.postProcess(data); }

    public void enhanceWithRAG(String llmId) { ragEnhancer.enhance(llmId); }

    public void enforceSecurity() { securityManager.enforce(); }

    public void manageBackendServer(String serverId) { backendServerManager.manage(serverId); }

    public void createSnapshot(String source, String destination) { snapshotManager.create(source, destination); }

    public void handleDataType(String dataType) { dataTypeHandler.handle(dataType); }
}
