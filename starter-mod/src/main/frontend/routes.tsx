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
