import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
import Flow from 'Frontend/generated/flow/Flow';
import MainLayout from './views/@layout';
import ChatClientView from './views/chat-client';
import CloneModelView from './views/clone-model';
import ContentGalleryView from './views/content-gallery';
import DirFileView from './views/directories-files';
import EmbeddingSettingsView from './views/embedding-settings';
import LoadUnloadView from './views/load-unload';
import DestroyModelView from './views/model-destroy';
import MetricsView from './views/metrics';
import MergeModelView from './views/model-merge';
import ModelSettingsView from './views/model-settings';
import SearchModelsView from './views/search-models';
import ManageServersView from './views/servers';
import SnapshotsView from './views/snapshots';
import SystemSettingsView from './views/system-settings';
import HelloWorldView from './views/hello-world';

export const { router, routes } = new RouterConfigurationBuilder()
  .withReactRoutes([
    {
      path: '',
      element: <MainLayout />,
      handle: { title: 'Home' },
      children: [
        { index: true, element: <ChatClientView />, handle: { title: 'Chat' } }, // Default route
        { path: 'chat-client', element: <ChatClientView />, handle: { title: 'Chat' } },
        { path: 'clone-model', element: <CloneModelView />, handle: { title: 'Clone' } },
        { path: 'content-gallery', element: <ContentGalleryView items={[]} />, handle: { title: 'Content Gallery' } },
        { path: 'directories-files', element: <DirFileView />, handle: { title: 'Directories ~ Files' } },
        { path: 'embedding-settings', element: <EmbeddingSettingsView />, handle: { title: 'Embedding' } },
        { path: 'load-unload', element: <LoadUnloadView />, handle: { title: 'Load ~ Unload' } },
        { path: 'hello-world', element: <HelloWorldView />, handle: { title: 'Hello World' } },
        { path: 'metrics', element: <MetricsView />, handle: { title: 'Metrics' } },
        { path: 'model-destroy', element: <DestroyModelView />, handle: { title: 'Delete ~ Model' } },
        { path: 'model-merge', element: <MergeModelView />, handle: { title: 'Merge Model' } },
        { path: 'model-settings', element: <ModelSettingsView />, handle: { title: 'Model Settings' } },
        { path: 'search-models', element: <SearchModelsView />, handle: { title: 'Search Models' } },
        { path: 'servers', element: <ManageServersView />, handle: { title: 'Servers' } },
        { path: 'snapshots', element: <SnapshotsView />, handle: { title: 'Snapshots' } },
        { path: 'system-settings', element: <SystemSettingsView />, handle: { title: 'System Settings' } },
      ],
    },
  ])
  .withFallback(Flow)
  .build();
