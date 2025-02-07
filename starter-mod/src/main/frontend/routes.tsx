import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
// @ts-ignore
import Flow from 'Frontend/generated/flow/Flow';
import MainLayout, { config as mainLayoutConfig } from './views/@layout';
import ChatClientView from './views/chat/chat-client';
import CloneModelView from './views/models/clone-model';
import ContentGalleryView from './views/gallery/content-gallery';
import DirFileView from './views/dir/directories-files';
import EmbeddingSettingsView from './views/embedding-settings';
import LoadUnloadView from './views/load/load-unload';
import DestroyModelView from './views/models/model-destroy';
import MetricsView from './views/metric/metrics';
import MergeModelView from './views/models/model-merge';
import ModelSettingsView from './views/models/model-settings';
import SearchModelsView from './views/search/search-models';
import ManageServersView from './views/servers';
import SnapshotsView from './views/snaps/snapshots';
import SystemSettingsView from './views/settings/system-settings';
import HelloWorldView from './views/hello/hello-world';

export const { router, routes } = new RouterConfigurationBuilder()
  .withReactRoutes([
    {
      path: '',
      element: <MainLayout />,
      handle: { title: mainLayoutConfig.title },
    },
    { path: '/chat-client', element: <ChatClientView />, handle: { title: 'Chat' } },
    { path: '/clone-model', element: <CloneModelView />, handle: { title: 'Clone' } },
    { path: '/content-gallery', element: <ContentGalleryView items={[]} />, handle: { title: 'Content Gallery' } },
    { path: '/directories-files', element: <DirFileView />, handle: { title: 'Directories ~ Files' } },
    { path: '/embedding-settings', element: <EmbeddingSettingsView />, handle: { title: 'Embedding' } },
    { path: '/load-unload', element: <LoadUnloadView />, handle: { title: 'Load ~ Unload' } },
    { path: '/hello-world', element: <HelloWorldView />, handle: { title: 'Hello World' } },
    { path: '/metrics', element: <MetricsView />, handle: { title: 'Metrics' } },
    { path: '/model-destroy', element: <DestroyModelView />, handle: { title: 'Delete ~ Model' } },
    { path: '/model-merge', element: <MergeModelView />, handle: { title: 'Merge Model' } },
    { path: '/model-settings', element: <ModelSettingsView />, handle: { title: 'Model Settings' } },
    { path: '/search-models', element: <SearchModelsView searchQuery={''} onModelsFetched={function(models: any[]): void {
        throw new Error('Function not implemented.');
    } } onLoading={function(loading: boolean): void {
        throw new Error('Function not implemented.');
    } } />, handle: { title: 'Search Models' } },
    { path: '/servers', element: <ManageServersView />, handle: { title: 'Servers' } },
    { path: '/snapshots', element: <SnapshotsView />, handle: { title: 'Snapshots' } },
    { path: '/system-settings', element: <SystemSettingsView />, handle: { title: 'System Settings' } },
  ])
  .withFallback(Flow)
  .build();
