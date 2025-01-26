// @ts-ignore
import { Route } from '@vaadin/router';
// import { ViewConfig } from '@vaadin/hilla-file-router/types.js';

import './views/@layout';
import './views/chat-client';
import './views/clone-model';
import './views/content-gallery';
import './views/directories-files';
import './views/load-unload';
import './views/main-message-list';
import './views/metrics';
import './views/model-destroy';
import './views/model-merge';
import './views/model-settings';
import './views/embedding-settings';
import './views/search-models';
import './views/servers';
import './views/snapshots';
import './views/system-settings';

export type ViewRoute = Route & {
  title?: string;
  icon?: string;
  children?: ViewRoute[];
};

export const views: ViewRoute[] = [
  // place routes below (more info https://hilla.dev/docs/routing)
  {
    path: '',
    component: '',
    menu: { order: 0 },
    icon: '',
    title: ''
  },
  {
    path: 'chat-client',
    component: 'chat-client',
    menu: { order: 1 },
    icon: 'line-awesome/svg/rocket-chat.svg',
    title: 'Chat'
  },
  {
    path: 'clone-model',
    component: 'clone-model',
    menu: { order: 2 },
    icon: 'line-awesome/svg/clone-solid.svg',
    title: 'Clone Model'
  },
  {
    path: 'content-gallery',
    component: 'content-gallery',
    menu: { order: 3 },
    icon: 'line-awesome/svg/gallery.svg',
    title: 'Gallery'
  },
  {
    path: 'directories-files',
    component: 'directories-files',
    menu: { order: 4 },
    icon: 'line-awesome/svg/folder-open-solid.svg',
    title: 'Directories ~ Files'
  },
  {
    path: 'load-unload',
    component: 'load-unload',
    menu: { order: 5 },
    icon: 'line-awesome/svg/upload-solid.svg',
    title: 'Load ~ Unload'
  },
  {
    path: 'main-message-list',
    component: 'main-message-list',
    menu: { order: 6 },
    icon: 'line-awesome/svg/comment-alt.svg',
    title: 'Message History'
  },
  {
    path: 'metrics',
    component: 'metrics',
    menu: { order: 7 },
    icon: 'line-awesome/svg/chart-line-solid.svg',
    title: 'Metrics'
  },
  {
    path: 'model-destroy',
    component: 'model-destroy',
    menu: { order: 8 },
    icon: 'line-awesome/svg/trash-alt-solid.svg',
    title: 'Delete Model'
  },
  {
    path: 'model-merge',
    component: 'model-merge',
    menu: { order: 9 },
    icon: 'line-awesome/svg/merge-solid.svg',
    title: 'Merge Models'
  },
  {
    path: 'model-settings',
    component: 'model-settings',
    menu: { order: 10 },
    icon: 'line-awesome/svg/robot-solid.svg',
    title: 'Model Settings'
  },
  {
    path: 'embedding-settings',
    component: 'embedding-settings',
    menu: { order: 11 },
    icon: 'line-awesome/svg/cogs.svg',
    title: 'Embedding'

  },
  {
    path: 'search-models',
    component: 'search-models',
    menu: { order: 12 },
    icon: 'line-awesome/svg/search-solid.svg',
    title: 'Search Models'
  },
  {
    path: 'servers',
    component: 'servers',
    menu: { order: 13 },
    icon: 'line-awesome/svg/server.svg',
    title: 'Servers'
  },
  {
    path: 'snapshots',
    component: 'snapshots',
    menu: { order: 14 },
    icon: 'line-awesome/svg/camera.svg',
    title: 'Snapshots'
  },
  {
    path: 'system-settings',
    component: 'system-settings',
    menu: { order: 15 },
    icon: 'line-awesome/svg/cog-solid.svg',
    title: 'System Settings'
  }
];

export const routes: ViewRoute[] = [
  {
    path: '',
    component: 'main-layout',
    children: [...views]
  }
];


// import { RouterConfigurationBuilder } from '@vaadin/hilla-file-router/runtime.js';
// import Flow from 'Frontend/generated/flow/Flow';
// import fileRoutes from 'Frontend/generated/file-routes';

// import { AdminService, AdvisersService, AnomaliesService, ChecksumsService, ClassificationsService, ClusteringService, DataBaseService
//   , DirFileService, DownloadersService, EmbeddingService, GenerativeService, HelloWorldService, LoadUnloadService, MessagesService
//   , ModelsService, NLPService, OptimizationsService, PredictiveService, RecognitionsService, RegressionsService, RoboticsService
//   , SecurityService, SequenceService, ServersService, SnapShotsService, SpeechService, TarService } from 'Frontend/generated/endpoints';
//
// export const { router, routes } = new RouterConfigurationBuilder()
//   .withFileRoutes(fileRoutes)
//   .withReactRoutes([
//     {
//       element: <MainLayout />,
//       children: [
//         { path: '/', element: <MainLayout />, handle: { title: 'Main' } },
//         { path: '/chat-client', element: <ChatClient />, handle: { title: 'Chat Client' } },
//         { path: '/clone-model', element: <CloneModelView />, handle: { title: 'Clone Model' } },
//         // { path: '/content-gallery', element: <ContentGallery />, handle: { title: 'Content Gallery' } },
//         { path: '/directories-files', element: <DirFileView />, handle: { title: 'Directories and Files' } },
//         { path: '/load-unload', element: <LoadUnloadComponent />, handle: { title: 'Load/Unload' } },
//         // { path: '/main-message-list', element: <MainMessageList />, handle: { title: 'Message History' } },
//         { path: '/metrics', element: <MetricsView />, handle: { title: 'Metrics' } },
//         { path: '/model-destroy', element: <DeleteModelView />, handle: { title: 'Delete Model' } },
//         { path: '/model-merge', element: <MergeModelView />, handle: { title: 'Merge Models' } },
//         { path: '/model-settings', element: <ModelSettings />, handle: { title: 'Model Settings' } },
//         { path: '/rag', element: <RagSettingsView />, handle: { title: 'RAG Settings' } },
//         { path: '/search-models', element: <SearchModelsView />, handle: { title: 'Search Models' } },
//         { path: '/servers', element: <ManageView />, handle: { title: 'Manage' } },
//         { path: '/snapshots', element: <Snapshots />, handle: { title: 'Snapshots' } },
//         { path: '/system-settings', element: <SystemSettingsView />, handle: { title: 'System Settings' } },
//       ],
//     },
//   ])
//   .withFallback(Flow)
//   .protect()
//   .build();

