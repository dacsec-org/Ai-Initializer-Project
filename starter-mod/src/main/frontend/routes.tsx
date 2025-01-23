import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
import Flow from 'Frontend/generated/flow/Flow';
// import fileRoutes from 'Frontend/generated/file-routes';
import MainLayout from 'Frontend/views/@layout';
import ChatClient from 'Frontend/views/chat-client';
import CloneModelView from 'Frontend/views/clone-model';
// import ContentGallery from 'Frontend/views/content-gallery';
import DirFileView from 'Frontend/views/directories-files';
import LoadUnloadComponent from 'Frontend/views/load-unload';
// import MainMessageList from 'Frontend/views/main-message-list';
import MetricsView from 'Frontend/views/metrics';
import DeleteModelView from 'Frontend/views/model-destroy';
import MergeModelView from 'Frontend/views/model-merge';
import RagSettingsView from 'Frontend/views/rag';
import SearchModelsView from 'Frontend/views/search-models';
import ManageView from 'Frontend/views/servers';
import Snapshots from 'Frontend/views/snapshots';
// import SystemSettingsView from 'Frontend/views/system-settings';

import { AdminService } from 'Frontend/generated/org/dacss/projectinitai/admin/services/AdminService';
import { AdvisersService } from 'Frontend/generated/org/dacss/projectinitai/advisers/AdvisersService';
import { AnomaliesService } from 'Frontend/generated/org/dacss/projectinitai/anomalies/AnomaliesService';
import { ChecksumsService } from 'Frontend/generated/org/dacss/projectinitai/checksums/ChecksumsService';
import { ClassificationsService } from 'Frontend/generated/org/dacss/projectinitai/classifications/ClassificationsService';
import { ClusteringService } from 'Frontend/generated/org/dacss/projectinitai/clustering/ClusteringService';
import { DataBaseService } from 'Frontend/generated/org/dacss/projectinitai/databases/DataBaseService';
import { DirFileService } from 'Frontend/generated/org/dacss/projectinitai/directories/DirFileService';
import { DownloadersService } from 'Frontend/generated/org/dacss/projectinitai/downloaders/DownloadersService';
import { EmbeddingService } from 'Frontend/generated/org/dacss/projectinitai/embedding/EmbeddingService';
import { GenerativeService } from 'Frontend/generated/org/dacss/projectinitai/generative/GenerativeService';
import { KRRService } from 'Frontend/generated/org/dacss/projectinitai/krr/KRRService';
import { LoadUnloadService } from 'Frontend/generated/org/dacss/projectinitai/loaders/LoadUnloadService';
import { MessagesService } from 'Frontend/generated/org/dacss/projectinitai/messages/MessagesService';
import { MetricsService } from 'Frontend/generated/org/dacss/projectinitai/metrics/services/MetricsService';
import { ModelsService } from 'Frontend/generated/org/dacss/projectinitai/models/ModelsService';
import { NLPService } from 'Frontend/generated/org/dacss/projectinitai/nlp/NLPService';
import { OptimizationsService } from 'Frontend/generated/org/dacss/projectinitai/optimizations/OptimizationsService';
import { PredictiveService } from 'Frontend/generated/org/dacss/projectinitai/predictive/PredictiveService';
import { RecognitionsService } from 'Frontend/generated/org/dacss/projectinitai/recognitions/RecognitionsService';
import { RecommendationsService } from 'Frontend/generated/org/dacss/projectinitai/recommendations/RecommendationsService';
import { ReductionsService } from 'Frontend/generated/org/dacss/projectinitai/reductions/ReductionsService';
import { RegressionsService } from 'Frontend/generated/org/dacss/projectinitai/regressions/RegressionsService';
import { ReinforcementService } from 'Frontend/generated/org/dacss/projectinitai/reinforcement/ReinforcementService';
import { RoboticsService } from 'Frontend/generated/org/dacss/projectinitai/robotics/RoboticsService';
import { SecurityService } from 'Frontend/generated/org/dacss/projectinitai/security/SecurityService';
import { SequenceService } from 'Frontend/generated/org/dacss/projectinitai/sequence/SequenceService';
import { ServersService } from 'Frontend/generated/org/dacss/projectinitai/servers/ServersService';
import { SnapShotsService } from 'Frontend/generated/org/dacss/projectinitai/snapshots/SnapShotsService';
import { SpeechService } from 'Frontend/generated/org/dacss/projectinitai/speech/SpeechService';
import { TarService } from 'Frontend/generated/org/dacss/projectinitai/tar/TarService';
import { VisionService } from 'Frontend/generated/org/dacss/projectinitai/vision/VisionService';

export const { router, routes } = new RouterConfigurationBuilder()
  // .withFileRoutes(fileRoutes)
  .withReactRoutes(
    [
      {
        element: <MainLayout />,
        handle: { title: 'Main' },
        children: [
          { path: '/chat-client', element: <ChatClient />, handle: { title: 'Chat Client' } },
          { path: '/clone-model', element: <CloneModelView />, handle: { title: 'Clone Model' } },
          // { path: '/content-gallery', element: <ContentGallery />, handle: { title: 'Content Gallery' } },
          { path: '/directories-files', element: <DirFileView />, handle: { title: 'Directories and Files' } },
          { path: '/load-unload', element: <LoadUnloadComponent />, handle: { title: 'Load/Unload Model' } },
          // { path: '/main-message-list', element: <MainMessageList />, handle: { title: 'Message History' } },
          { path: '/metrics', element: <MetricsView />, handle: { title: 'Metrics' } },
          { path: '/model-destroy', element: <DeleteModelView />, handle: { title: 'Delete Model' } },
          { path: '/model-merge', element: <MergeModelView />, handle: { title: 'Merge Models' } },
          { path: '/rag', element: <RagSettingsView />, handle: { title: 'RAG Settings' } },
          { path: '/search-models', element: <SearchModelsView />, handle: { title: 'Search Models' } },
          { path: '/manage', element: <ManageView />, handle: { title: 'Manage' } },
          { path: '/snapshots', element: <Snapshots />, handle: { title: 'Snapshots' } },
          // { path: '/system-settings', element: <SystemSettingsView />, handle: { title: 'System Settings' } }
        ],
      }
    ]
  )
  .withFallback(Flow)
  .protect()
  .build();

export class HuggingFaceService {
}

export class SnapShotService {
}
