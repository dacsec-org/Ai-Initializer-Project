import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import GpuGauge from './components/gpu-gauge';
import CpuGauge from './components/cpu-gauge';
import MemoryGauge from './components/memory-gauge';
import DiskGauge from './components/disk-gauge';
import NetworkGauge from './components/network-gauge';

export const config: ViewConfig = {
  menu: { order: 7, icon: 'line-awesome/svg/chart-line-solid.svg', title: 'Metrics' },
};

interface MetricsViewState {
  showGpu: boolean;
  showCpu: boolean;
  showMemory: boolean;
  showDisk: boolean;
  showNetwork: boolean;
}

/**
 * <h1>{@link MetricsView}</h1>
 * A view that displays the metrics gauges.
 * todo: make this window draggable so it can run in the background
 */
class MetricsView extends Component<{}, MetricsViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      showGpu: true,
      showCpu: true,
      showMemory: true,
      showDisk: true,
      showNetwork: true,
    };
  }

  render() {
    const { showGpu, showCpu, showMemory, showDisk, showNetwork } = this.state;

    return (
      <div className="flex flex-col h-full items-center justify-center p-l text-center box-border">
        <h1>Server Metrics</h1>
        {showGpu && (
          <div>
            <button onClick={() => this.setState({ showGpu: false })}>x</button>
            <GpuGauge />
          </div>
        )}
        {showCpu && (
          <div>
            <button onClick={() => this.setState({ showCpu: false })}>x</button>
            <CpuGauge />
          </div>
        )}
        {showMemory && (
          <div>
            <button onClick={() => this.setState({ showMemory: false })}>x</button>
            <MemoryGauge />
          </div>
        )}
        {showDisk && (
          <div>
            <button onClick={() => this.setState({ showDisk: false })}>x</button>
            <DiskGauge />
          </div>
        )}
        {showNetwork && (
          <div>
            <button onClick={() => this.setState({ showNetwork: false })}>x</button>
            <NetworkGauge />
          </div>
        )}
      </div>
    );
  }
}

export default MetricsView;
