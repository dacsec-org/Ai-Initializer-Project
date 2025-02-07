import React, { useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import GpuGauge from './gpu-gauge';
import CpuGauge from './cpu-gauge';
import MemoryGauge from './memory-gauge';
import DiskGauge from './disk-gauge';
import NetworkGauge from './network-gauge';

export const config: ViewConfig = {
  menu: { order: 7, icon: 'line-awesome/svg/chart-line-solid.svg', title: 'Metrics' },
};

const MetricsView: React.FC = () => {
  const [showGpu, setShowGpu] = useState(true);
  const [showCpu, setShowCpu] = useState(true);
  const [showMemory, setShowMemory] = useState(true);
  const [showDisk, setShowDisk] = useState(true);
  const [showNetwork, setShowNetwork] = useState(true);

  return (
    <div className="flex flex-col h-full items-center justify-center p-l text-center box-border">
      <h1>Server Metrics</h1>
      <div className="grid grid-cols-2 gap-4">
        {showGpu && (
          <div>
            <button onClick={() => setShowGpu(false)}>x</button>
            <GpuGauge />
          </div>
        )}
        {showCpu && (
          <div>
            <button onClick={() => setShowCpu(false)}>x</button>
            <CpuGauge />
          </div>
        )}
        {showMemory && (
          <div>
            <button onClick={() => setShowMemory(false)}>x</button>
            <MemoryGauge />
          </div>
        )}
        {showDisk && (
          <div>
            <button onClick={() => setShowDisk(false)}>x</button>
            <DiskGauge />
          </div>
        )}
        {showNetwork && (
          <div>
            <button onClick={() => setShowNetwork(false)}>x</button>
            <NetworkGauge />
          </div>
        )}
      </div>
    </div>
  );
};

export default MetricsView;
