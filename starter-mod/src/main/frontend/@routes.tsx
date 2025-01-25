import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
import Flow from 'Frontend/generated/flow/Flow';
import fileRoutes from 'Frontend/generated/file-routes';
import MainLayout from './views/@layout';
import ManageView from './views/servers';
import Snapshots from './views/snapshots';
import SearchModelsView from './views/search-models';
import RagSettingsView from './views/rag';
import MergeModelView from './views/model-merge';
import DeleteModelView from './views/model-destroy';
import ChatClient from './views/chat-client';
import CloneModelView from './views/clone-model';
import DirFileView from './views/directories-files';
import LoadUnloadComponent from './views/load-unload';
import MetricsView from './views/metrics';
import SystemSettingsView from './views/system-settings';
import ModelSettings from './views/model-settings';

export const { router, routes } = new RouterConfigurationBuilder()
  .withFileRoutes(fileRoutes)
  .withReactRoutes([
    {
      element: <MainLayout />,
      children: [
        { path: '/', element: <MainLayout />, handle: { title: 'Main' } },
        { path: '/chat-client', element: <ChatClient />, handle: { title: 'Chat Client' } },
        { path: '/clone-model', element: <CloneModelView />, handle: { title: 'Clone Model' } },
        // { path: '/content-gallery', element: <ContentGallery />, handle: { title: 'Content Gallery' } },
        { path: '/directories-files', element: <DirFileView />, handle: { title: 'Directories and Files' } },
        { path: '/load-unload', element: <LoadUnloadComponent />, handle: { title: 'Load/Unload' } },
        // { path: '/main-message-list', element: <MainMessageList />, handle: { title: 'Message History' } },
        { path: '/metrics', element: <MetricsView />, handle: { title: 'Metrics' } },
        { path: '/model-destroy', element: <DeleteModelView />, handle: { title: 'Delete Model' } },
        { path: '/model-merge', element: <MergeModelView />, handle: { title: 'Merge Models' } },
        { path: '/model-settings', element: <ModelSettings />, handle: { title: 'Model Settings' } },
        { path: '/rag', element: <RagSettingsView />, handle: { title: 'RAG Settings' } },
        { path: '/search-models', element: <SearchModelsView />, handle: { title: 'Search Models' } },
        { path: '/servers', element: <ManageView />, handle: { title: 'Manage' } },
        { path: '/snapshots', element: <Snapshots />, handle: { title: 'Snapshots' } },
        { path: '/system-settings', element: <SystemSettingsView />, handle: { title: 'System Settings' } },
      ],
    },
  ])
  .withFallback(Flow)
  .protect()
  .build();
