import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import React, { Component } from 'react';
import GpuGauge from './components/gpu-gauge';
import CpuGauge from './components/cpu-gauge';
import MemoryGauge from './components/memory-gauge';
import DiskGauge from './components/disk-gauge';
import NetworkGauge from './components/network-gauge';
import { ViewConfig } from '@vaadin/hilla-file-router';

export const config = {
  menu: { order: 7, icon: 'line-awesome/svg/chart-line-solid.svg', title: 'Metrics' },
};

class MetricsView extends Component {
  constructor(props) {
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
      _jsxs("div", {
        className: "flex flex-col h-full items-center justify-center p-l text-center box-border",
        children: [
          _jsx("h1", { children: "Server Metrics" }),
          showGpu && _jsxs("div", {
            children: [
              _jsx("button", { onClick: () => this.setState({ showGpu: false }), children: "x" }),
              _jsx(GpuGauge, {})
            ]
          }),
          showCpu && _jsxs("div", {
            children: [
              _jsx("button", { onClick: () => this.setState({ showCpu: false }), children: "x" }),
              _jsx(CpuGauge, {})
            ]
          }),
          showMemory && _jsxs("div", {
            children: [
              _jsx("button", { onClick: () => this.setState({ showMemory: false }), children: "x" }),
              _jsx(MemoryGauge, {})
            ]
          }),
          showDisk && _jsxs("div", {
            children: [
              _jsx("button", { onClick: () => this.setState({ showDisk: false }), children: "x" }),
              _jsx(DiskGauge, {})
            ]
          }),
          showNetwork && _jsxs("div", {
            children: [
              _jsx("button", { onClick: () => this.setState({ showNetwork: false }), children: "x" }),
              _jsx(NetworkGauge, {})
            ]
          })
        ]
      })
    );
  }
}

export default MetricsView;
