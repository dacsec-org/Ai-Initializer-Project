import { createElement } from 'react';
import { createRoot } from 'react-dom/client';
import { RouterProvider } from 'react-router-dom';
import { router } from 'Frontend/generated/routes.js';
import { AdminService, AdvisersService, AnomaliesService, ChecksumsService, ClassificationsService, ClusteringService, DataBaseService, DirFileService, DownloadersService, EmbeddingService, GenerativeService, HelloWorldService, LoadUnloadService, MessagesService, ModelsService, NLPService, OptimizationsService, PredictiveService, RecognitionsService, RegressionsService, RoboticsService, SecurityService, SequenceService, ServersService, SnapShotsService, SpeechService, TarService } from 'Frontend/generated/endpoints';

function App() {
  return (
    <>
      <RouterProvider router={router} future={{ v7_startTransition: true }} />
      <AdminService />
      <AdvisersService />
      <AnomaliesService />
      <ChecksumsService />
      <ClassificationsService />
      <ClusteringService />
      <DataBaseService />
      <DirFileService />
      <DownloadersService />
      <EmbeddingService />
      <GenerativeService />
      <HelloWorldService />
      <LoadUnloadService />
      <MessagesService />
      <ModelsService />
      <NLPService />
      <OptimizationsService />
      <PredictiveService />
      <RecognitionsService />
      <RegressionsService />
      <RoboticsService />
      <SecurityService />
      <SequenceService />
      <ServersService />
      <SnapShotsService />
      <SpeechService />
      <TarService />
    </>
  );
}

const outlet = document.getElementById('outlet')!;
let root = (outlet as any)._root ?? createRoot(outlet);
(outlet as any)._root = root;
root.render(createElement(App));
