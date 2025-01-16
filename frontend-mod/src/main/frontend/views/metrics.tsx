import React, { Component } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { ServerHealthService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = { menu: { order: 6, icon: 'line-awesome/svg/file.svg' }, title: 'Metrics' };

interface MetricsViewState {
  metrics: any;
}

class MetricsView extends Component<{}, MetricsViewState> {
  constructor(props: {}) {
    super(props);
    this.state = {
      metrics: null
    };
  }

  async componentDidMount() {
    const data = await ServerHealthService.getMetrics();
    this.setState({ metrics: data });
  }

  render() {
    const { metrics } = this.state;

    return (
      <div className="flex flex-col h-full items-center justify-center p-l text-center box-border">
        <h1>Server Metrics</h1>
        {metrics ? (
          <pre>{JSON.stringify(metrics, null, 2)}</pre>
        ) : (
          <p>Loading...</p>
        )}
      </div>
    );
  }
}

export default MetricsView;
