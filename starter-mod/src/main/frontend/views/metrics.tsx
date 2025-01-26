import React, { Component } from 'react';
import { Subscription } from 'rxjs';
import { MetricsService } from 'Frontend/generated/endpoints';
import GpuGauge from './components/gpu-gauge';
import CpuGauge from './components/cpu-gauge';
import MemoryGauge from './components/memory-gauge';
import DiskGauge from './components/disk-gauge';
import NetworkGauge from './components/network-gauge';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';

export const config: ViewConfig = {
  menu: { order: 7, icon: 'line-awesome/svg/chart-line-solid.svg', title: 'Metrics' },
}

interface MetricsViewState {
  gpu: number;
  cpu: number;
  memory: number;
  disk: number;
  network: number;
  showGpu: boolean;
  showCpu: boolean;
  showMemory: boolean;
  showDisk: boolean;
  showNetwork: boolean;
}

class MetricsView extends Component<{}, MetricsViewState> {
  private subscriptions: Subscription[] = [];

  constructor(props: {}) {
    super(props);
    this.state = {
      gpu: 0,
      cpu: 0,
      memory: 0,
      disk: 0,
      network: 0,
      showGpu: true,
      showCpu: true,
      showMemory: true,
      showDisk: true,
      showNetwork: true,
    };
  }

  componentDidMount() {
    this.subscribeToMetric('gpu', (value) => this.setState({ gpu: value }));
    this.subscribeToMetric('cpu', (value) => this.setState({ cpu: value }));
    this.subscribeToMetric('memory', (value) => this.setState({ memory: value }));
    this.subscribeToMetric('disk', (value) => this.setState({ disk: value }));
    this.subscribeToMetric('network', (value) => this.setState({ network: value }));
  }

  componentWillUnmount() {
    this.subscriptions.forEach((sub) => sub.unsubscribe());
  }

  subscribeToMetric(action: string, setter: (value: number) => void) {
    MetricsService.measure(action);
    const intervalId = setInterval(() => {
      const simulatedValue = Math.random() * 100; // Simulate a value
      setter(simulatedValue);
    }, 1000);
    const subscription = new Subscription(() => clearInterval(intervalId));
    this.subscriptions.push(subscription);
  }

  render() {
    const { gpu, cpu, memory, disk, network, showGpu, showCpu, showMemory, showDisk, showNetwork } = this.state;

    return (
      <div className="flex flex-col h-full items-center justify-center p-l text-center box-border">
        <h1>Server Metrics</h1>
        {showGpu && (
          <div>
            <button onClick={() => this.setState({ showGpu: false })}>x</button>
            <GpuGauge value={gpu} />
          </div>
        )}
        {showCpu && (
          <div>
            <button onClick={() => this.setState({ showCpu: false })}>x</button>
            <CpuGauge value={cpu} />
          </div>
        )}
        {showMemory && (
          <div>
            <button onClick={() => this.setState({ showMemory: false })}>x</button>
            <MemoryGauge value={memory} />
          </div>
        )}
        {showDisk && (
          <div>
            <button onClick={() => this.setState({ showDisk: false })}>x</button>
            <DiskGauge value={disk} />
          </div>
        )}
        {showNetwork && (
          <div>
            <button onClick={() => this.setState({ showNetwork: false })}>x</button>
            <NetworkGauge value={network} />
          </div>
        )}
      </div>
    );
  }
}

export default MetricsView;
