import React, { Component } from 'react';
import { RadialGauge } from '@progress/kendo-react-gauges';
import { MetricsService } from 'Frontend/generated/endpoints';

interface CpuGaugeProps {
  value?: number;
}

interface CpuGaugeState {
  value: number;
}

interface MetricData {
  value: number;
}

/**
 * <h1>{@link CpuGauge}</h1>
 */
class CpuGauge extends Component<CpuGaugeProps, CpuGaugeState> {
  private intervalId: NodeJS.Timeout | null = null;

  constructor(props: CpuGaugeProps) {
    super(props);
    this.state = {
      value: 0,
    };
  }

  /**
   * <h3>{@link fetchData}</h3>
   * Fetches the CPU data from the reactive backend.
   */
  async fetchData() {
    const data = await MetricsService.measure('cpu') as unknown as MetricData;
    this.setState({ value: data.value });
  }

  async componentDidMount() {
    await this.fetchData();
    this.intervalId = setInterval(() => this.fetchData(), 1000);
  }

  componentWillUnmount() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  render() {
    const { value } = this.state;
    const { value: propValue } = this.props;

    const gaugeOptions = {
      value: propValue ?? value,
      scale: {
        max: 100,
        majorUnit: 20,
        minorUnit: 2,
        ranges: [
          { from: 0, to: 40, color: '#28a745' },
          { from: 40, to: 70, color: '#ffc107' },
          { from: 70, to: 100, color: '#dc3545' }
        ]
      },
      pointer: {
        value: propValue ?? value
      }
    };

    return <RadialGauge {...gaugeOptions} />;
  }
}

export default CpuGauge;
