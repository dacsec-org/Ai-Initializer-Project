import React, { useEffect, useState } from 'react';
import { RadialGauge } from '@progress/kendo-react-gauges';
import { Metrics } from '../../bridges/metrics-bridge';
import { MetricsTypes } from '../../enums/MetricsTypes';

interface DiskGaugeProps {
  value?: number;
}

interface MetricData {
  value: number;
}

const DiskGauge: React.FC<DiskGaugeProps> = ({ value: propValue }) => {
  const [value, setValue] = useState(0);

  const fetchData = async () => {
    const data = await Metrics.getMetrics(MetricsTypes.DISK) as unknown as MetricData;
    setValue(data.value);
  };

  useEffect(() => {
    fetchData().then(r => console.log(r));
    const intervalId = setInterval(() => fetchData(), 1000);
    return () => clearInterval(intervalId);
  }, []);

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
};

export default DiskGauge;
