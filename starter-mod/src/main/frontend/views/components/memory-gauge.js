import { jsx as _jsx } from "react/jsx-runtime";
import React, { Component } from 'react';
import { RadialGauge } from '@progress/kendo-react-gauges';
import { MetricsService } from 'Frontend/generated/endpoints';

class MemoryGauge extends Component {
  constructor(props) {
    super(props);
    this.state = {
      value: 0,
    };
    this.intervalId = null;
  }

  async fetchData() {
    const data = await MetricsService.measure('memory');
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

    return _jsx(RadialGauge, { ...gaugeOptions });
  }
}

export default MemoryGauge;
